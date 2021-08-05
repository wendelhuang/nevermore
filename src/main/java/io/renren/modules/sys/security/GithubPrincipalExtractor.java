package io.renren.modules.sys.security;

import org.springframework.stereotype.Component;

import io.renren.modules.generator.entity.SysOauthRoleEntity;
import io.renren.modules.sys.entity.SysUserEntity;

@Component
public class GithubPrincipalExtractor extends AbstractPrincipalExtractor {
    @Override
    public SysUserEntity getUserByOpenId(String id) {
        return super.sysUserService.getUserByGithubId(id);
    }

    @Override
    public SysOauthRoleEntity getUserRoleByOauth2ClientName() {
    	SysOauthRoleEntity role = new SysOauthRoleEntity();
        role.setName("GITHUB");
        return role;
    }

	@Override
	public SysUserEntity saveUserOpenId(SysUserEntity sysUser, String id) {
		if (sysUser == null) {
			sysUser = new SysUserEntity();
		}
		sysUser.setGithubid(id);
		return sysUser;
	}
}
