 CREATE TABLE `tb_map_home_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `map_home_item_id` bigint(20) DEFAULT NULL,
  `x` bigint(20) DEFAULT NULL,
  `y` bigint(20) DEFAULT NULL,
  `name` varchar(1000) DEFAULT NULL,
  `level_v` bigint(20) DEFAULT NULL,
  `bool_value` bigint(20) DEFAULT NULL,
  `bool_value_2` bigint(20) DEFAULT NULL,
  `int_value` bigint(20) DEFAULT NULL,
  `int_value_2` bigint(20) DEFAULT NULL,
  `int_value_3` bigint(20) DEFAULT NULL,
  `number_cd` bigint(20) DEFAULT NULL,
  `number_cd_2` bigint(20) DEFAULT NULL,
  `number_cd_3` bigint(20) DEFAULT NULL,
  `is_use_material_1` bigint(20) DEFAULT NULL,
  `is_use_material_2` bigint(20) DEFAULT NULL,
  `is_use_material_3` bigint(20) DEFAULT NULL,
  `card_type_1` varchar(1000) DEFAULT NULL,
  `card_name_1` varchar(1000) DEFAULT NULL,
  `card_type_2` varchar(1000) DEFAULT NULL,
  `card_name_2` varchar(1000) DEFAULT NULL,
  `card_type_3` varchar(1000) DEFAULT NULL,
  `card_name_3` varchar(1000) DEFAULT NULL,
  `string_value` varchar(1000) DEFAULT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;



    CREATE TABLE `tb_map_home_deco` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `map_home_deco_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `name` varchar(1000) DEFAULT NULL,
  `deviation_x` decimal(19,6) DEFAULT NULL,
  `deviation_y` decimal(19,6) DEFAULT NULL,
  `scale_x` bigint(20) DEFAULT NULL,
  `scale_y` bigint(20) DEFAULT NULL,
  `scale_z` bigint(20) DEFAULT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;
    
    
    CREATE TABLE `tb_map_home` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `map_home_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `x` bigint(20) DEFAULT NULL,
  `y` bigint(20) DEFAULT NULL,
  `isSea` bigint(20) DEFAULT NULL,
  `isUse` bigint(20) DEFAULT NULL,
  `isVisit` bigint(20) DEFAULT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;