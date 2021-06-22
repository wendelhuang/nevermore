package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.MapHomeItemEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-06-22 21:03:50
 */
public interface MapHomeItemService extends IService<MapHomeItemEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

