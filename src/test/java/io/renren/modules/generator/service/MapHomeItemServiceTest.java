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
import io.renren.modules.generator.entity.MapHomeItemEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapHomeItemServiceTest {
    @Autowired
    private MapHomeItemService mapHomeItemService;

    @Test
    public void test(){
        try {
            ClassPathResource classPathResource = new ClassPathResource("/static/data/map_home_item.json");
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
			
			List<MapHomeItemEntity> list = new ArrayList<MapHomeItemEntity>();
			
			for(int i = 0; i < jsonArray2.size(); i++) {
				cn.hutool.json.JSONObject jsonObject = jsonArray2.getJSONObject(i);
				
				MapHomeItemEntity mapHomeItemEntity = new MapHomeItemEntity();
				mapHomeItemEntity.setMapHomeItemId(new Long(jsonObject.getInt("id")));
				mapHomeItemEntity.setX(new Long(jsonObject.getInt("x")));
				mapHomeItemEntity.setY(new Long(jsonObject.getInt("y")));
				mapHomeItemEntity.setName(jsonObject.getStr("name"));
				mapHomeItemEntity.setLevelV(new Long(jsonObject.getInt("level")));
				mapHomeItemEntity.setBoolValue(jsonObject.getBool("boolValue") ? 1L : 0L);
				mapHomeItemEntity.setBoolValue2(jsonObject.getBool("boolValue2") ? 1L : 0L);
				mapHomeItemEntity.setIntValue(new Long(jsonObject.getInt("intValue")));
				mapHomeItemEntity.setIntValue2(new Long(jsonObject.getInt("intValue_2")));
				mapHomeItemEntity.setIntValue3(new Long(jsonObject.getInt("intValue_3")));
				mapHomeItemEntity.setNumberCd(new Long(jsonObject.getInt("numberCD")));
				mapHomeItemEntity.setNumberCd2(new Long(jsonObject.getInt("numberCD_2")));
				mapHomeItemEntity.setNumberCd3(new Long(jsonObject.getInt("numberCD_3")));
				mapHomeItemEntity.setIsUseMaterial1(jsonObject.getBool("isUseMaterial_1") ? 1L : 0L);
				mapHomeItemEntity.setIsUseMaterial2(jsonObject.getBool("isUseMaterial_2") ? 1L : 0L);
				mapHomeItemEntity.setIsUseMaterial3(jsonObject.getBool("isUseMaterial_3") ? 1L : 0L);
				mapHomeItemEntity.setCardType1(new Long(jsonObject.getInt("cardType_1")));
				mapHomeItemEntity.setCardName1(jsonObject.getStr("cardName_1"));
				mapHomeItemEntity.setCardType2(new Long(jsonObject.getInt("cardType_2")));
				mapHomeItemEntity.setCardName2(jsonObject.getStr("cardName_3"));
				mapHomeItemEntity.setCardType3(new Long(jsonObject.getInt("cardType_3")));
				mapHomeItemEntity.setCardName3(jsonObject.getStr("cardName_3"));
				mapHomeItemEntity.setStringValue(jsonObject.getStr("stringValue"));
				
				
				list.add(mapHomeItemEntity);
				System.out.println("---------------");
			}

			mapHomeItemService.saveBatch(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
