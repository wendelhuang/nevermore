package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.SysOauthRoleUserEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-08-03 07:56:58
 */
public interface SysOauthRoleUserService extends IService<SysOauthRoleUserEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

