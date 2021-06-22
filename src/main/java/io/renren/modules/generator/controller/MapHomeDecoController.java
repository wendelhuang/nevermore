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

import io.renren.modules.generator.entity.MapHomeDecoEntity;
import io.renren.modules.generator.entity.MapHomeEntity;
import io.renren.modules.generator.service.MapHomeDecoService;
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
@RequestMapping("generator/maphomedeco")
public class MapHomeDecoController {
    @Autowired
    private MapHomeDecoService mapHomeDecoService;
    
    @RequestMapping("/listOrigin")
    public R listOrigin() {
    	Map<String, Object> data = Maps.newHashMap();
        QueryWrapper<MapHomeDecoEntity> mapHomeDecoQueryWrapper = new QueryWrapper<>();
        mapHomeDecoQueryWrapper.eq("user_id", 0L);
        List<MapHomeDecoEntity> list = mapHomeDecoService.list(mapHomeDecoQueryWrapper);
        data.put("mapHomeDeco", list.stream().filter(Objects::nonNull).map(m -> this.transfer(m)).collect(Collectors.toList()));
        return R.ok().put("data", data);
    }
    
    private Map<String, Object> transfer(MapHomeDecoEntity mapHomeDecoEntity) {
    	if (Objects.isNull(mapHomeDecoEntity)) {
    		return null;
    	}
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("name", mapHomeDecoEntity.getName());
    	result.put("deviation_x", mapHomeDecoEntity.getDeviationX());
    	result.put("deviation_y", mapHomeDecoEntity.getDeviationY());
    	result.put("scale_x", mapHomeDecoEntity.getScaleX());
    	result.put("scale_y", mapHomeDecoEntity.getScaleY());
    	result.put("scale_z", mapHomeDecoEntity.getScaleZ());
    	return result;
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
