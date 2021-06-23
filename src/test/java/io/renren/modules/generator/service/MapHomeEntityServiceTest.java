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

import io.renren.modules.generator.entity.MapHomeEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapHomeEntityServiceTest {
    @Autowired
    private MapHomeService mapHomeService;

    @Test
    public void test(){
        try {
            ClassPathResource classPathResource = new ClassPathResource("/static/data/map_home.json");
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
			
			List<MapHomeEntity> list = new ArrayList<MapHomeEntity>();
			
			for(int i = 0; i < jsonArray2.size(); i++) {
				cn.hutool.json.JSONObject jsonObject = jsonArray2.getJSONObject(i);
				
				MapHomeEntity mapHomeEntity = new MapHomeEntity();
				mapHomeEntity.setMapHomeId(new Long(jsonObject.getInt("id")));
				mapHomeEntity.setX(new Long(jsonObject.getInt("x")));
				mapHomeEntity.setY(new Long(jsonObject.getInt("y")));
				mapHomeEntity.setIssea(jsonObject.getBool("isSea") ? 1L : 0L);
				mapHomeEntity.setIsuse(jsonObject.getBool("isUse") ? 1L : 0L);
				mapHomeEntity.setIsvisit(jsonObject.getBool("isVisit") ? 1L : 0L);
				
				list.add(mapHomeEntity);
				System.out.println("---------------");
			}
			

			mapHomeService.saveBatch(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
