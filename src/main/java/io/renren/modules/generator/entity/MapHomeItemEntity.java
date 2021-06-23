package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @date 2021-06-23 23:46:24
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
	@TableField("bool_value_2")
	private Long boolValue2;
	/**
	 * 
	 */
	private Long intValue;
	/**
	 * 
	 */
	@TableField("int_value_2")
	private Long intValue2;
	/**
	 * 
	 */
	@TableField("int_value_3")
	private Long intValue3;
	/**
	 * 
	 */
	private Long numberCd;
	/**
	 * 
	 */
	@TableField("number_cd_2")
	private Long numberCd2;
	/**
	 * 
	 */
	@TableField("number_cd_3")
	private Long numberCd3;
	/**
	 * 
	 */
	@TableField("is_use_material_1")
	private Long isUseMaterial1;
	/**
	 * 
	 */
	@TableField("is_use_material_2")
	private Long isUseMaterial2;
	/**
	 * 
	 */
	@TableField("is_use_material_3")
	private Long isUseMaterial3;
	/**
	 * 
	 */
	@TableField("card_type_1")
	private Long cardType1;
	/**
	 * 
	 */
	@TableField("card_name_1")
	private String cardName1;
	/**
	 * 
	 */
	@TableField("card_type_2")
	private Long cardType2;
	/**
	 * 
	 */
	@TableField("card_name_2")
	private String cardName2;
	/**
	 * 
	 */
	@TableField("card_type_3")
	private Long cardType3;
	/**
	 * 
	 */
	@TableField("card_name_3")
	private String cardName3;
	/**
	 * 
	 */
	private String stringValue;

}
