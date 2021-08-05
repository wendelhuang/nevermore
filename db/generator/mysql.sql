 
    CREATE TABLE `tb_map_home_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `k` varchar(1000) DEFAULT NULL,
  `v` varchar(1000) DEFAULT NULL,
  `o` bigint(20) DEFAULT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

   CREATE TABLE `tb_map_home_deco` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `k` varchar(1000) DEFAULT NULL,
  `v` varchar(1000) DEFAULT NULL,
  `o` bigint(20) DEFAULT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;


   CREATE TABLE `tb_map_home` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `k` varchar(1000) DEFAULT NULL,
  `v` varchar(1000) DEFAULT NULL,
  `o` bigint(20) DEFAULT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;


   CREATE TABLE `tb_map_home_cliff` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `k` varchar(1000) DEFAULT NULL,
  `v` varchar(1000) DEFAULT NULL,
  `o` bigint(20) DEFAULT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;


   CREATE TABLE `tb_map_home_size` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `k` varchar(1000) DEFAULT NULL,
  `v` varchar(1000) DEFAULT NULL,
  `o` bigint(20) DEFAULT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;
