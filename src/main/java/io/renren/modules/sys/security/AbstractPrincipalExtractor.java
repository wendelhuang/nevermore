package io.renren.modules.sys.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import io.renren.modules.generator.entity.SysOauthRoleEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserService;

public abstract class AbstractPrincipalExtractor implements PrincipalExtractor {

	@Autowired
	SysUserService sysUserService;

	// 用户openid
	public abstract SysUserEntity getUserByOpenId(String id);

	// 用户角色，用“FACEBOOK"代表facebook用户，”GITHUB"代表"github用户
	public abstract SysOauthRoleEntity getUserRoleByOauth2ClientName();

	public abstract SysUserEntity saveUserOpenId(SysUserEntity sysUser, String id);

	@Override
	public Object extractPrincipal(Map<String, Object> map) {
		System.out.println("-----------------------------------------");
		for (String k : map.keySet()) {
			System.out.println(String.format("%s-----------------%s", k, map.get(k).toString()));
		}
		System.out.println("-----------------------------------------");
		// 得到对于的社交平台的openid
		String id = map.get("id").toString();
		// Check if we've already registered this uer
		System.out.println("id: " + id);
		SysUserEntity user = getUserByOpenId(id);
		if (user == null) {
			System.out.println("--------------cannot find user---------------");
			// If we haven't registered this user yet, create a new one
//      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//      // This Details object exposes a token that allows us to interact with Facebook on this user's behalf
//      String token = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
			user = new SysUserEntity();
			user.setUsername(map.get("id").toString());
			saveUserOpenId(user, id);
			// Set the default Roles for users registered via Facebook
			List<SysOauthRoleEntity> authorities = new ArrayList<>();
			SysOauthRoleEntity role = getUserRoleByOauth2ClientName();
			authorities.add(role);
			// Oauth2Client客戶端特有角色
			authorities.add(getUserRoleByOauth2ClientName());
			sysUserService.saveUser(user, role);
		}
		System.out.println("--------------find user---------------");
		return user.getUsername();
	}
}
