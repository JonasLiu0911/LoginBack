
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- 创建 user_info 表结构
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
                              `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                              `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '姓名',
                              `gender` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '性别 1-男，2-女',
                              `age` int(3) UNSIGNED NOT NULL DEFAULT 0 COMMENT '年龄',
                              `telephone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
                              `register_mode` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '注册方式',
                              `third_part_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '第三方id',
                              `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                              PRIMARY KEY (`id`) USING BTREE,
                              UNIQUE INDEX `KEY_UNIQUE_PHONE`(`telephone`) USING BTREE COMMENT '手机号唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- 用户信息 记录
-- ----------------------------
INSERT INTO `user_info` VALUES (2, 'Jonas', 1, 22, '18374815703', 'byphone', '', '2022-02-09 23:04:05', '2022-02-09 23:04:05');
INSERT INTO `user_info` VALUES (5, 'Rainy', 2, 21, '15273147786', 'byphone', '', '2022-02-11 16:06:57', '2022-02-11 16:06:57');

-- ----------------------------
-- 创建 user_password 表结构
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password`  (
                                  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                                  `encrypt_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '加密后的密码',
                                  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户id',
                                  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- 用户密码 记录
-- ----------------------------
INSERT INTO `user_password` VALUES (27, '4QrcOUm6Wau+VuBX8g+IPg==', 2, '2022-02-09 23:04:05', '2022-02-09 23:04:05');
INSERT INTO `user_password` VALUES (28, '4QrcOUm6Wau+VuBX8g+IPg==', 5, '2022-02-11 16:06:57', '2022-02-11 16:06:57');

SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS `user_schedule`;
CREATE TABLE `user_location`  (
                                  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                                  `longitude` decimal(10,7) NOT NULL COMMENT '经度',
                                  `latitude` decimal(10,7) NOT NULL COMMENT '纬度',
                                  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户id',
                                  `poi_info` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '位置信息',
                                  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;