package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.SysOauthRoleUserDao;
import io.renren.modules.generator.entity.SysOauthRoleUserEntity;
import io.renren.modules.generator.service.SysOauthRoleUserService;


@Service("sysOauthRoleUserService")
public class SysOauthRoleUserServiceImpl extends ServiceImpl<SysOauthRoleUserDao, SysOauthRoleUserEntity> implements SysOauthRoleUserService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SysOauthRoleUserEntity> page = this.page(
                new Query<SysOauthRoleUserEntity>().getPage(params),
                new QueryWrapper<SysOauthRoleUserEntity>()
        );

        return new PageUtils(page);
    }

}