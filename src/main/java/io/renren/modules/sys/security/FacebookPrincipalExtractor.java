package io.renren.modules.sys.security;

import org.springframework.stereotype.Component;

import io.renren.modules.generator.entity.SysOauthRoleEntity;
import io.renren.modules.sys.entity.SysUserEntity;

@Component
public class FacebookPrincipalExtractor extends AbstractPrincipalExtractor {

    @Override
    public SysUserEntity getUserByOpenId(String id) {
        return super.sysUserService.getUserByFacebookId(id);
    }

    @Override
    public SysOauthRoleEntity getUserRoleByOauth2ClientName() {
    	SysOauthRoleEntity role = new SysOauthRoleEntity();
        role.setName("FACEBOOK");
        return role;
    }

	@Override
	public SysUserEntity saveUserOpenId(SysUserEntity sysUser, String id) {
		if (sysUser == null) {
			sysUser = new SysUserEntity();
		}
		sysUser.setFacebookid(id);
		return sysUser;
	}
}
