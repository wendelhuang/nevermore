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
@TableName("tb_map_home")
public class MapHomeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long id;
	/**
	 * 
	 */
	private Long mapHomeId;
	/**
	 * 
	 */
	private Long userId;
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
	private Long issea;
	/**
	 * 
	 */
	private Long isuse;
	/**
	 * 
	 */
	private Long isvisit;

}
