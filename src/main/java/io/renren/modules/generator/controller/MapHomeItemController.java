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
import io.renren.modules.generator.entity.MapHomeItemEntity;
import io.renren.modules.generator.service.MapHomeItemService;
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
@RequestMapping("generator/maphomeitem")
public class MapHomeItemController {
    @Autowired
    private MapHomeItemService mapHomeItemService;
    

    @RequestMapping("/listOrigin")
    public R listOrigin() {
    	Map<String, Object> data = Maps.newHashMap();
        QueryWrapper<MapHomeItemEntity> mapHomeItemQueryWrapper = new QueryWrapper<>();
        mapHomeItemQueryWrapper.eq("user_id", 0L);
        List<MapHomeItemEntity> list = mapHomeItemService.list(mapHomeItemQueryWrapper);
        data.put("mapHomeItem", list.stream().filter(Objects::nonNull).map(m -> this.transfer(m)).collect(Collectors.toList()));
        return R.ok().put("data", data);
    }
    
    private Map<String, Object> transfer(MapHomeItemEntity mapHomeItemEntity) {
    	if (Objects.isNull(mapHomeItemEntity)) {
    		return null;
    	}
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("id", mapHomeItemEntity.getMapHomeItemId());
    	result.put("x", mapHomeItemEntity.getX());
    	result.put("y", mapHomeItemEntity.getY());
    	result.put("name", mapHomeItemEntity.getName());
    	result.put("level", mapHomeItemEntity.getLevelV());
    	result.put("boolValue", new Long(1L).equals(mapHomeItemEntity.getBoolValue()));
    	result.put("boolValue2", new Long(1L).equals(mapHomeItemEntity.getBoolValue2()));
    	result.put("intValue", mapHomeItemEntity.getIntValue());
    	result.put("intValue_2", mapHomeItemEntity.getIntValue2());
    	result.put("intValue_3", mapHomeItemEntity.getIntValue3());
    	result.put("numberCD", mapHomeItemEntity.getNumberCd());
    	result.put("numberCD_2", mapHomeItemEntity.getNumberCd2());
    	result.put("numberCD_3", mapHomeItemEntity.getNumberCd3());
    	result.put("isUseMaterial_1", new Long(1L).equals(mapHomeItemEntity.getIsUseMaterial1()));
    	result.put("isUseMaterial_2", new Long(1L).equals(mapHomeItemEntity.getIsUseMaterial2()));
    	result.put("isUseMaterial_3", new Long(1L).equals(mapHomeItemEntity.getIsUseMaterial3()));
    	result.put("cardType_1", mapHomeItemEntity.getCardType1());
    	result.put("cardName_1", mapHomeItemEntity.getCardName1());
    	result.put("cardType_2", mapHomeItemEntity.getCardType2());
    	result.put("cardName_2", mapHomeItemEntity.getCardName2());
    	result.put("cardType_3", mapHomeItemEntity.getCardType3());
    	result.put("cardName_3", mapHomeItemEntity.getCardName3());
    	result.put("stringValue", mapHomeItemEntity.getStringValue());
    	return result;
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
