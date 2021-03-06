package io.renren.modules.generator.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.MapHomeDecoDao;
import io.renren.modules.generator.entity.MapHomeDecoEntity;
import io.renren.modules.generator.service.MapHomeDecoService;


@Service("mapHomeDecoService")
public class MapHomeDecoServiceImpl extends ServiceImpl<MapHomeDecoDao, MapHomeDecoEntity> implements MapHomeDecoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MapHomeDecoEntity> page = this.page(
                new Query<MapHomeDecoEntity>().getPage(params),
                new QueryWrapper<MapHomeDecoEntity>()
        );

        return new PageUtils(page);
    }

}