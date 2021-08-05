/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.exception.RRException;
import io.renren.common.utils.Constant;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;
import io.renren.modules.generator.entity.SysOauthRoleEntity;
import io.renren.modules.generator.entity.SysOauthRoleUserEntity;
import io.renren.modules.generator.service.SysOauthRoleService;
import io.renren.modules.generator.service.SysOauthRoleUserService;
import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.service.SysUserService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysOauthRoleService sysOauthRoleService;
	@Autowired
	private SysOauthRoleUserService sysOauthRoleUserService;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String username = (String)params.get("username");
		Long createUserId = (Long)params.get("createUserId");

		IPage<SysUserEntity> page = this.page(
			new Query<SysUserEntity>().getPage(params),
			new QueryWrapper<SysUserEntity>()
				.like(StringUtils.isNotBlank(username),"username", username)
				.eq(createUserId != null,"create_user_id", createUserId)
		);

		return new PageUtils(page);
	}

	@Override
	public List<String> queryAllPerms(Long userId) {
		return baseMapper.queryAllPerms(userId);
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return baseMapper.queryAllMenuId(userId);
	}

	@Override
	public SysUserEntity queryByUserName(String username) {
		return baseMapper.queryByUserName(username);
	}

	@Override
	@Transactional
	public void saveUser(SysUserEntity user) {
		user.setCreateTime(new Date());
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
		user.setSalt(salt);
		this.save(user);
		
		//检查角色是否越权
		checkRole(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional
	public void update(SysUserEntity user) {
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
		}
		this.updateById(user);
		
		//检查角色是否越权
		checkRole(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	public void deleteBatch(Long[] userId) {
		this.removeByIds(Arrays.asList(userId));
	}

	@Override
	public boolean updatePassword(Long userId, String password, String newPassword) {
		SysUserEntity userEntity = new SysUserEntity();
		userEntity.setPassword(newPassword);
		return this.update(userEntity,
				new QueryWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password));
	}
	
	/**
	 * 检查角色是否越权
	 */
	private void checkRole(SysUserEntity user){
		if(user.getRoleIdList() == null || user.getRoleIdList().size() == 0){
			return;
		}
		//如果不是超级管理员，则需要判断用户的角色是否自己创建
		if(user.getCreateUserId() == Constant.SUPER_ADMIN){
			return ;
		}
		
		//查询用户创建的角色列表
		List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

		//判断是否越权
		if(!roleIdList.containsAll(user.getRoleIdList())){
			throw new RRException("新增用户所选角色，不是本人创建");
		}
	}

	@Override
	public SysUserEntity getUserByFacebookId(String id) {
		QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("facebookid", id);
		List<SysUserEntity> list = this.baseMapper.selectList(queryWrapper);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public SysUserEntity getUserByTwitterId(String id) {
		QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("twitterid", id);
		List<SysUserEntity> list = this.baseMapper.selectList(queryWrapper);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public SysUserEntity getUserByGithubId(String id) {
		QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("githubid", id);
		List<SysUserEntity> list = this.baseMapper.selectList(queryWrapper);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public void saveUser(SysUserEntity user, SysOauthRoleEntity role) {
		if (!Optional.ofNullable(role).map(SysOauthRoleEntity::getName).isPresent()) {
			saveUser(user);
			return;
		}
		QueryWrapper<SysOauthRoleEntity> sysOauthRoleQueryWrapper = new QueryWrapper<>();
		sysOauthRoleQueryWrapper.eq("name", role.getName());
		SysOauthRoleEntity sysOauthRoleEntity = sysOauthRoleService.getOne(sysOauthRoleQueryWrapper);
		if (Objects.isNull(sysOauthRoleEntity)) {
			sysOauthRoleService.save(role);
			role.setSysOauthRoleId(role.getId());
			sysOauthRoleService.updateById(role);
		}
		
		// save user firstly
		this.save(user);
		
		// save user oauth mapping
		SysOauthRoleUserEntity sysOauthRoleUserEntity = new SysOauthRoleUserEntity();
		sysOauthRoleUserEntity.setSysUserId(user.getUserId());
		sysOauthRoleUserEntity.setSysOauthRoleId(role.getSysOauthRoleId());
		sysOauthRoleUserService.save(sysOauthRoleUserEntity);
		sysOauthRoleUserEntity.setSysOauthRoleUserId(sysOauthRoleUserEntity.getId());
		sysOauthRoleUserService.updateById(sysOauthRoleUserEntity);
		
	}
}