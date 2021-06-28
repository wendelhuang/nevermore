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

import io.renren.modules.generator.entity.MapHomeDecoEntity;
import io.renren.modules.generator.entity.MapHomeItemEntity;
import io.renren.modules.generator.service.MapHomeItemService;
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
@RequestMapping("generator/maphomeitem")
public class MapHomeItemController {
    @Autowired
    private MapHomeItemService mapHomeItemService;
    

    
    @RequestMapping("/listAll")
    public R listAll(@RequestParam Map<String, Object> params){
        Map<String, Object> data = new HashMap<String, Object>();
        QueryWrapper<MapHomeItemEntity> mapHomeItemQueryWrapper = new QueryWrapper<>();
        mapHomeItemQueryWrapper.orderByAsc("k");
        List<MapHomeItemEntity> list = mapHomeItemService.list(mapHomeItemQueryWrapper);
        
        Map<String, List<String>> mapHomeItemList = new HashMap<>();
        for(int i = 0; i < list.size(); i++) {
        	List<String> m = mapHomeItemList.getOrDefault(list.get(i).getK(), new ArrayList<>());
        	m.add(list.get(i).getV());
        	mapHomeItemList.put(list.get(i).getK(), m);
        }
        data.put("data", data);
        return R.ok(data);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:maphomeitem:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = mapHomeItemService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:maphomeitem:info")
    public R info(@PathVariable("id") Long id){
		MapHomeItemEntity mapHomeItem = mapHomeItemService.getById(id);

        return R.ok().put("mapHomeItem", mapHomeItem);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:maphomeitem:save")
    public R save(@RequestBody MapHomeItemEntity mapHomeItem){
		mapHomeItemService.save(mapHomeItem);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:maphomeitem:update")
    public R update(@RequestBody MapHomeItemEntity mapHomeItem){
		mapHomeItemService.updateById(mapHomeItem);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:maphomeitem:delete")
    public R delete(@RequestBody Long[] ids){
		mapHomeItemService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
