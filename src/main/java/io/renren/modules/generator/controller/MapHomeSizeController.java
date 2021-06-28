package io.renren.modules.generator.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import io.renren.modules.generator.entity.MapHomeEntity;
import io.renren.modules.generator.entity.MapHomeSizeEntity;
import io.renren.modules.generator.service.MapHomeSizeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-06-28 21:17:26
 */
@RestController
@RequestMapping("generator/maphomesize")
public class MapHomeSizeController {
    @Autowired
    private MapHomeSizeService mapHomeSizeService;
    

    @RequestMapping("/listAll")
    public R listAll(@RequestParam Map<String, Object> params){
        Map<String, Object> data = new HashMap<String, Object>();
        
        data.put("transverse", 146);
        data.put("transverse_half", 66);
        data.put("vertical", 107);
        data.put("vertical_half", 70);
        
        return R.ok(data);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:maphomesize:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = mapHomeSizeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:maphomesize:info")
    public R info(@PathVariable("id") Long id){
		MapHomeSizeEntity mapHomeSize = mapHomeSizeService.getById(id);

        return R.ok().put("mapHomeSize", mapHomeSize);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:maphomesize:save")
    public R save(@RequestBody MapHomeSizeEntity mapHomeSize){
		mapHomeSizeService.save(mapHomeSize);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:maphomesize:update")
    public R update(@RequestBody MapHomeSizeEntity mapHomeSize){
		mapHomeSizeService.updateById(mapHomeSize);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:maphomesize:delete")
    public R delete(@RequestBody Long[] ids){
		mapHomeSizeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
