package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.MapHomeSizeDao;
import io.renren.modules.generator.entity.MapHomeSizeEntity;
import io.renren.modules.generator.service.MapHomeSizeService;


@Service("mapHomeSizeService")
public class MapHomeSizeServiceImpl extends ServiceImpl<MapHomeSizeDao, MapHomeSizeEntity> implements MapHomeSizeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MapHomeSizeEntity> page = this.page(
                new Query<MapHomeSizeEntity>().getPage(params),
                new QueryWrapper<MapHomeSizeEntity>()
        );

        return new PageUtils(page);
    }

}