/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.generator.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import io.renren.modules.generator.entity.MapHomeDecoEntity;
import io.renren.modules.generator.entity.MapHomeEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapHomeDecoServiceTest {
    @Autowired
    private MapHomeDecoService mapHomeDecoService;

    @Test
    public void test(){
        try {
            ClassPathResource classPathResource = new ClassPathResource("/static/data/map_home_deco.json");
            File file = classPathResource.getFile();
			FileInputStream fileInputStream = new FileInputStream(file);
			
			StringBuffer stringBuffer = new StringBuffer();
			int n = 0;
			while(n != -1) {
				n = fileInputStream.read();
				char c = (char) n;
				stringBuffer.append(c);
			}
			System.out.println(stringBuffer.toString());
			
			cn.hutool.json.JSONArray jsonArray2 = new cn.hutool.json.JSONArray(stringBuffer.toString());
			
			List<MapHomeDecoEntity> list = new ArrayList<MapHomeDecoEntity>();
			
			for(int i = 0; i < jsonArray2.size(); i++) {
				cn.hutool.json.JSONObject jsonObject = jsonArray2.getJSONObject(i);
				
				MapHomeDecoEntity mapHomeDecoEntity = new MapHomeDecoEntity();
				mapHomeDecoEntity.setName(jsonObject.getStr("name"));
				mapHomeDecoEntity.setDeviationX(jsonObject.getBigDecimal("deviation_x"));
				mapHomeDecoEntity.setDeviationY(jsonObject.getBigDecimal("deviation_y"));
				mapHomeDecoEntity.setScaleX(new Long(jsonObject.getInt("scale_x")));
				mapHomeDecoEntity.setScaleY(new Long(jsonObject.getInt("scale_y")));
				mapHomeDecoEntity.setScaleZ(new Long(jsonObject.getInt("scale_z")));
				
				list.add(mapHomeDecoEntity);
				System.out.println("---------------");
			}
			
			System.out.println(list.get(0).getName());

			mapHomeDecoService.saveBatch(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
