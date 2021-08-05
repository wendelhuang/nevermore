package io.renren.modules.sys.security.service;

import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;

import io.renren.modules.sys.security.AbstractPrincipalExtractor;
import io.renren.modules.sys.service.SysUserService;

public class MyUserInfoTokenServices extends UserInfoTokenServices {

    private SysUserService sysUserService;

    private String oauth2Type;
    public MyUserInfoTokenServices(String userInfoEndPointUrl, String clienId, AbstractPrincipalExtractor principalExtractor) {
        super(userInfoEndPointUrl, clienId);
        super.setPrincipalExtractor(principalExtractor);
    }
}
