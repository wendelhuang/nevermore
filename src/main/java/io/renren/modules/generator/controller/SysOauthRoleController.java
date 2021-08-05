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

import io.renren.modules.generator.entity.SysOauthRoleEntity;
import io.renren.modules.generator.service.SysOauthRoleService;
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
@RequestMapping("generator/sysoauthrole")
public class SysOauthRoleController {
    @Autowired
    private SysOauthRoleService sysOauthRoleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:sysoauthrole:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sysOauthRoleService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:sysoauthrole:info")
    public R info(@PathVariable("id") Integer id){
		SysOauthRoleEntity sysOauthRole = sysOauthRoleService.getById(id);

        return R.ok().put("sysOauthRole", sysOauthRole);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:sysoauthrole:save")
    public R save(@RequestBody SysOauthRoleEntity sysOauthRole){
		sysOauthRoleService.save(sysOauthRole);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:sysoauthrole:update")
    public R update(@RequestBody SysOauthRoleEntity sysOauthRole){
		sysOauthRoleService.updateById(sysOauthRole);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:sysoauthrole:delete")
    public R delete(@RequestBody Integer[] ids){
		sysOauthRoleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
