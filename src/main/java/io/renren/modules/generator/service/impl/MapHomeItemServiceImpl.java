package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.MapHomeItemDao;
import io.renren.modules.generator.entity.MapHomeItemEntity;
import io.renren.modules.generator.service.MapHomeItemService;


@Service("mapHomeItemService")
public class MapHomeItemServiceImpl extends ServiceImpl<MapHomeItemDao, MapHomeItemEntity> implements MapHomeItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MapHomeItemEntity> page = this.page(
                new Query<MapHomeItemEntity>().getPage(params),
                new QueryWrapper<MapHomeItemEntity>()
        );

        return new PageUtils(page);
    }

}