package io.renren.modules.generator.service.impl;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.SysOauthRoleDao;
import io.renren.modules.generator.entity.SysOauthRoleEntity;
import io.renren.modules.generator.entity.SysOauthRoleUserEntity;
import io.renren.modules.generator.service.SysOauthRoleService;
import io.renren.modules.generator.service.SysOauthRoleUserService;


@Service("sysOauthRoleService")
public class SysOauthRoleServiceImpl extends ServiceImpl<SysOauthRoleDao, SysOauthRoleEntity> implements SysOauthRoleService {
	@Autowired
	SysOauthRoleUserService sysOauthRoleUserService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysOauthRoleEntity> page = this.page(
                new Query<SysOauthRoleEntity>().getPage(params),
                new QueryWrapper<SysOauthRoleEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public List<SysOauthRoleEntity> querySysOauthRoleByUserId(Long id) {
		QueryWrapper<SysOauthRoleUserEntity> sysOauthRoleUserQueryWrapper = new QueryWrapper<>();
		sysOauthRoleUserQueryWrapper.eq("sys_user_id", id);
		List<SysOauthRoleUserEntity> list = sysOauthRoleUserService.list(sysOauthRoleUserQueryWrapper);
		if (CollectionUtils.isEmpty(list)) {
			return Lists.newArrayList();
		}
		List<Long> sysOauthRoleId = list.stream().map(SysOauthRoleUserEntity::getSysOauthRoleId).collect(Collectors.toList());
		return this.baseMapper.selectList(new QueryWrapper<SysOauthRoleEntity>().in("id", sysOauthRoleId));
	}

}