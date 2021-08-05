package io.renren.modules.sys.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import io.renren.modules.generator.entity.SysOauthRoleEntity;
import io.renren.modules.generator.service.SysOauthRoleService;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.service.SysUserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService { //自定义UserDetailsService 接口

    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysOauthRoleService sysOauthRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) { //重写loadUserByUsername 方法获得 userdetails 类型用户
        System.out.println("loadUserByUserName: " + username);
        SysUserEntity user = sysUserService.queryByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        
        List<SysOauthRoleEntity> oauthRoleEntities = sysOauthRoleService.querySysOauthRoleByUserId(user.getUserId());
        //用于添加用户的权限。只要把用户权限添加到authorities。
        for(SysOauthRoleEntity role:oauthRoleEntities)
        {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            System.out.println(role.getName());
        }
        UserDetails userDetails = new User(user.getUsername(), user.getPassword(), authorities);
        return userDetails;
    }
}