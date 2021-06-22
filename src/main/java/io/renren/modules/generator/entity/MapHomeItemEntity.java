package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-06-22 21:18:39
 */
@Data
@TableName("tb_map_home_item")
public class MapHomeItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long userId;
	/**
	 * 
	 */
	private Long mapHomeItemId;
	/**
	 * 
	 */
	private Long x;
	/**
	 * 
	 */
	private Long y;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private Long levelV;
	/**
	 * 
	 */
	private Long boolValue;
	/**
	 * 
	 */
	private Long boolValue2;
	/**
	 * 
	 */
	private Long intValue;
	/**
	 * 
	 */
	private Long intValue2;
	/**
	 * 
	 */
	private Long intValue3;
	/**
	 * 
	 */
	private Long numberCd;
	/**
	 * 
	 */
	private Long numberCd2;
	/**
	 * 
	 */
	private Long numberCd3;
	/**
	 * 
	 */
	private Long isUseMaterial1;
	/**
	 * 
	 */
	private Long isUseMaterial2;
	/**
	 * 
	 */
	private Long isUseMaterial3;
	/**
	 * 
	 */
	private String cardType1;
	/**
	 * 
	 */
	private String cardName1;
	/**
	 * 
	 */
	private String cardType2;
	/**
	 * 
	 */
	private String cardName2;
	/**
	 * 
	 */
	private String cardType3;
	/**
	 * 
	 */
	private String cardName3;
	/**
	 * 
	 */
	private String stringValue;

}
