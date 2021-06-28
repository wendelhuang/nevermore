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
import io.renren.modules.generator.entity.MapHomeDecoEntity;
import io.renren.modules.generator.service.MapHomeDecoService;
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
@RequestMapping("generator/maphomedeco")
public class MapHomeDecoController {
    @Autowired
    private MapHomeDecoService mapHomeDecoService;
    
    @RequestMapping("/listAll")
    public R listAll(@RequestParam Map<String, Object> params){
        Map<String, Object> data = new HashMap<String, Object>();
        QueryWrapper<MapHomeDecoEntity> mapHomeDecoQueryWrapper = new QueryWrapper<>();
        mapHomeDecoQueryWrapper.orderByAsc("k");
        List<MapHomeDecoEntity> list = mapHomeDecoService.list(mapHomeDecoQueryWrapper);
        
        Map<String, List<String>> mapHomeDecoList = new HashMap<>();
        for(int i = 0; i < list.size(); i++) {
        	List<String> m = mapHomeDecoList.getOrDefault(list.get(i).getK(), new ArrayList<>());
        	m.add(list.get(i).getV());
        	mapHomeDecoList.put(list.get(i).getK(), m);
        }
        data.put("data", data);
        return R.ok(data);
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:maphomedeco:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = mapHomeDecoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:maphomedeco:info")
    public R info(@PathVariable("id") Long id){
		MapHomeDecoEntity mapHomeDeco = mapHomeDecoService.getById(id);

        return R.ok().put("mapHomeDeco", mapHomeDeco);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:maphomedeco:save")
    public R save(@RequestBody MapHomeDecoEntity mapHomeDeco){
		mapHomeDecoService.save(mapHomeDeco);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:maphomedeco:update")
    public R update(@RequestBody MapHomeDecoEntity mapHomeDeco){
		mapHomeDecoService.updateById(mapHomeDeco);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:maphomedeco:delete")
    public R delete(@RequestBody Long[] ids){
		mapHomeDecoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
