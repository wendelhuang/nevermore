package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.MapHomeCliffDao;
import io.renren.modules.generator.entity.MapHomeCliffEntity;
import io.renren.modules.generator.service.MapHomeCliffService;


@Service("mapHomeCliffService")
public class MapHomeCliffServiceImpl extends ServiceImpl<MapHomeCliffDao, MapHomeCliffEntity> implements MapHomeCliffService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MapHomeCliffEntity> page = this.page(
                new Query<MapHomeCliffEntity>().getPage(params),
                new QueryWrapper<MapHomeCliffEntity>()
        );

        return new PageUtils(page);
    }

}