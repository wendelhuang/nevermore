package io.renren.modules.generator.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;

import io.renren.modules.app.annotation.Login;
import io.renren.modules.generator.entity.MapHomeEntity;
import io.renren.modules.generator.service.MapHomeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-06-22 21:03:50
 */
@RestController
@RequestMapping("generator/maphome")
public class MapHomeController {
    @Autowired
    private MapHomeService mapHomeService;
    

    @RequestMapping("/listOrigin")
    public R listOrigin() {
    	Map<String, Object> data = Maps.newHashMap();
        QueryWrapper<MapHomeEntity> mapHomeQueryWrapper = new QueryWrapper<>();
        mapHomeQueryWrapper.eq("user_id", 0L);
        List<MapHomeEntity> list = mapHomeService.list(mapHomeQueryWrapper);
        data.put("mapHome", list.stream().filter(Objects::nonNull).map(m -> this.transfer(m)).collect(Collectors.toList()));
        return R.ok().put("data", data);
    }
    
    @RequestMapping("/updateByUser")
    public R updateByUser() {
    	Map<String, Object> data = Maps.newHashMap();
        QueryWrapper<MapHomeEntity> mapHomeQueryWrapper = new QueryWrapper<>();
        mapHomeQueryWrapper.eq("user_id", 0L);
        List<MapHomeEntity> list = mapHomeService.list(mapHomeQueryWrapper);
        data.put("mapHome", list.stream().filter(Objects::nonNull).map(m -> this.transfer(m)).collect(Collectors.toList()));
        return R.ok().put("data", data);
    }
    
    private Map<String, Object> transfer(MapHomeEntity mapHomeEntity) {
    	if (Objects.isNull(mapHomeEntity)) {
    		return null;
    	}
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("x", mapHomeEntity.getX());
    	result.put("y", mapHomeEntity.getY());
    	result.put("isSea", new Long(1L).equals(mapHomeEntity.getIssea()));
    	result.put("isUse", new Long(1L).equals(mapHomeEntity.getIssea()));
    	result.put("isVisit", new Long(1L).equals(mapHomeEntity.getIssea()));
    	return result;
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
