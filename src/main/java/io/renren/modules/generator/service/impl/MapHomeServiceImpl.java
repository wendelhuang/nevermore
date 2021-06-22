package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.MapHomeDao;
import io.renren.modules.generator.entity.MapHomeEntity;
import io.renren.modules.generator.service.MapHomeService;


@Service("mapHomeService")
public class MapHomeServiceImpl extends ServiceImpl<MapHomeDao, MapHomeEntity> implements MapHomeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MapHomeEntity> page = this.page(
                new Query<MapHomeEntity>().getPage(params),
                new QueryWrapper<MapHomeEntity>()
        );

        return new PageUtils(page);
    }

}