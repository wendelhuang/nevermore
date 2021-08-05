package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.generator.entity.SysOauthRoleUserEntity;
import io.renren.modules.generator.service.SysOauthRoleUserService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-08-03 07:56:58
 */
@RestController
@RequestMapping("generator/sysoauthroleuser")
public class SysOauthRoleUserController {
    @Autowired
    private SysOauthRoleUserService sysOauthRoleUserService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:sysoauthroleuser:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysOauthRoleUserService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:sysoauthroleuser:info")
    public R info(@PathVariable("id") Integer id){
		SysOauthRoleUserEntity sysOauthRoleUser = sysOauthRoleUserService.getById(id);

        return R.ok().put("sysOauthRoleUser", sysOauthRoleUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:sysoauthroleuser:save")
    public R save(@RequestBody SysOauthRoleUserEntity sysOauthRoleUser){
		sysOauthRoleUserService.save(sysOauthRoleUser);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:sysoauthroleuser:update")
    public R update(@RequestBody SysOauthRoleUserEntity sysOauthRoleUser){
		sysOauthRoleUserService.updateById(sysOauthRoleUser);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:sysoauthroleuser:delete")
    public R delete(@RequestBody Integer[] ids){
		sysOauthRoleUserService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
