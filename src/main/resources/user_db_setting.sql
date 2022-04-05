
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
INSERT INTO `user_info` VALUES (1, 'Jonas', 1, 22, '18374815703', 'byphone', '', '2022-02-09 23:04:05', '2022-02-09 23:04:05');
INSERT INTO `user_info` VALUES (2, 'Rainy', 2, 21, '15273147786', 'byphone', '', '2022-02-11 16:06:57', '2022-02-11 16:06:57');

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
INSERT INTO `user_password` VALUES (1, '4QrcOUm6Wau+VuBX8g+IPg==', 1, '2022-02-09 23:04:05', '2022-02-09 23:04:05');
INSERT INTO `user_password` VALUES (2, '4QrcOUm6Wau+VuBX8g+IPg==', 2, '2022-02-11 16:06:57', '2022-02-11 16:06:57');

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- 创建 user_schedule 表结构
-- ----------------------------
DROP TABLE IF EXISTS `user_schedule`;
CREATE TABLE `user_schedule`  (
                                  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                                  `longitude` decimal(12,7) NOT NULL COMMENT '目的位置经度',
                                  `latitude` decimal(12,7) NOT NULL COMMENT '目的位置纬度',
                                  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户id',
                                  `schedule_title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL  DEFAULT '' COMMENT '事项标题',
                                  `schedule_info` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '事项具体信息',
                                  `schedule_start_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '事项起始时间',
                                  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- 用户日程安排 记录
-- ----------------------------
INSERT INTO `user_schedule` VALUES (1, 112.861774, 27.883111, 2, '中期检查', '要按时完成哦', '2022-04-10 12:00:00', '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `user_schedule` VALUES (2, 113.861531, 27.883136, 5, '测试数据', '这是一条测试数据', '2022-04-12 14:30:00', '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `user_schedule` VALUES (3, 111.836151, 27.813683, 5, '测试数据2', '这是另一条测试数据', '2022-04-15 14:30:00', '2022-04-02 10:04:05', '2022-04-02 10:04:05');
INSERT INTO `user_schedule` VALUES (4, 112.861774, 27.683813, 5, '标题', '哈哈哈哈哈哈', '2022-04-10 14:30:00', '2022-04-02 10:05:05', '2022-04-02 10:05:05');