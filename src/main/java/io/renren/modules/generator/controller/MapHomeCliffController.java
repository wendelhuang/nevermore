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

import io.renren.modules.generator.entity.MapHomeCliffEntity;
import io.renren.modules.generator.entity.MapHomeEntity;
import io.renren.modules.generator.service.MapHomeCliffService;
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
@RequestMapping("generator/maphomecliff")
public class MapHomeCliffController {
    @Autowired
    private MapHomeCliffService mapHomeCliffService;
    

    @RequestMapping("/listAll")
    public R listAll(@RequestParam Map<String, Object> params){
        Map<String, Object> data = new HashMap<String, Object>();
        QueryWrapper<MapHomeCliffEntity> mapHomeCliffQueryWrapper = new QueryWrapper<>();
        mapHomeCliffQueryWrapper.orderByAsc("k");
        List<MapHomeCliffEntity> list = mapHomeCliffService.list(mapHomeCliffQueryWrapper);
        
        Map<String, List<String>> mapHomeCliffList = new HashMap<>();
        for(int i = 0; i < list.size(); i++) {
        	List<String> m = mapHomeCliffList.getOrDefault(list.get(i).getK(), new ArrayList<>());
        	m.add(list.get(i).getV());
        	mapHomeCliffList.put(list.get(i).getK(), m);
        }
        data.put("data", data);
        return R.ok(data);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:maphomecliff:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = mapHomeCliffService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:maphomecliff:info")
    public R info(@PathVariable("id") Long id){
		MapHomeCliffEntity mapHomeCliff = mapHomeCliffService.getById(id);

        return R.ok().put("mapHomeCliff", mapHomeCliff);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:maphomecliff:save")
    public R save(@RequestBody MapHomeCliffEntity mapHomeCliff){
		mapHomeCliffService.save(mapHomeCliff);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:maphomecliff:update")
    public R update(@RequestBody MapHomeCliffEntity mapHomeCliff){
		mapHomeCliffService.updateById(mapHomeCliff);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:maphomecliff:delete")
    public R delete(@RequestBody Long[] ids){
		mapHomeCliffService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
