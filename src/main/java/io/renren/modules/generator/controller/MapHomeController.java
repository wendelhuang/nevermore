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
import io.renren.modules.generator.service.MapHomeService;
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
@RequestMapping("generator/maphome")
public class MapHomeController {
    @Autowired
    private MapHomeService mapHomeService;
    

    @RequestMapping("/listAll")
    public R listAll(@RequestParam Map<String, Object> params){
        Map<String, Object> data = new HashMap<String, Object>();
        QueryWrapper<MapHomeEntity> mapHomeQueryWrapper = new QueryWrapper<>();
        mapHomeQueryWrapper.orderByAsc("k");
        List<MapHomeEntity> list = mapHomeService.list(mapHomeQueryWrapper);
        
        Map<String, List<String>> mapHomeList = new HashMap<>();
        for(int i = 0; i < list.size(); i++) {
        	List<String> m = mapHomeList.getOrDefault(list.get(i).getK(), new ArrayList<>());
        	m.add(list.get(i).getV());
        	mapHomeList.put(list.get(i).getK(), m);
        }
        data.put("data", data);
        return R.ok(data);
    }
    

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:maphome:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = mapHomeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:maphome:info")
    public R info(@PathVariable("id") Long id){
		MapHomeEntity mapHome = mapHomeService.getById(id);

        return R.ok().put("mapHome", mapHome);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:maphome:save")
    public R save(@RequestBody MapHomeEntity mapHome){
		mapHomeService.save(mapHome);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:maphome:update")
    public R update(@RequestBody MapHomeEntity mapHome){
		mapHomeService.updateById(mapHome);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:maphome:delete")
    public R delete(@RequestBody Long[] ids){
		mapHomeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
