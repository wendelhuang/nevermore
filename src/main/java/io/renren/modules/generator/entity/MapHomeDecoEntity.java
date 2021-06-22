package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
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
@TableName("tb_map_home_deco")
public class MapHomeDecoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long mapHomeDecoId;
	/**
	 * 
	 */
	private Long userId;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private BigDecimal deviationX;
	/**
	 * 
	 */
	private BigDecimal deviationY;
	/**
	 * 
	 */
	private Long scaleX;
	/**
	 * 
	 */
	private Long scaleY;
	/**
	 * 
	 */
	private Long scaleZ;

}
