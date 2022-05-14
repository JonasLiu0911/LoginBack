
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
                                  `longitude` decimal(14,10) NOT NULL COMMENT '目的位置经度',
                                  `latitude` decimal(14,10) NOT NULL COMMENT '目的位置纬度',
                                  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户id',
                                  `schedule_title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL  DEFAULT '' COMMENT '事项标题',
                                  `schedule_info` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '事项具体信息',
                                  `schedule_start_time` bigint(20) NOT NULL COMMENT '事项起始时间',
                                  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- 用户日程安排 记录
-- ----------------------------
INSERT INTO `user_schedule` VALUES (1, 112.861774, 27.883111, 2, '中期检查', '要按时完成哦', '1649730000', '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `user_schedule` VALUES (2, 113.861531, 27.883136, 5, '测试数据', '这是一条测试数据', '1649557200', '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `user_schedule` VALUES (3, 111.836151, 27.813683, 5, '测试数据2', '这是另一条测试数据', '1649812800', '2022-04-02 10:04:05', '2022-04-02 10:04:05');
INSERT INTO `user_schedule` VALUES (4, 112.861774, 27.683813, 5, '标题', '哈哈哈哈哈哈', '1650677400', '2022-04-02 10:05:05', '2022-04-02 10:05:05');
INSERT INTO `user_schedule` VALUES (5, 112.861774, 27.883111, 5, '新增数据3', '这是一条新增数据', '1647999000', '2022-04-02 10:08:05', '2022-04-02 10:08:05');
INSERT INTO `user_schedule` VALUES (6, 113.861531, 27.883136, 5, '测试长度', '这是一条测试数据哦原来他放假吗范德萨可根据是的发达国家我的发表的发表反对是对方说的话', '1649395800', '2022-04-01 10:04:05', '2022-04-01 10:04:05');

-- ----------------------------
-- 创建 user_head 表结构
-- ----------------------------
DROP TABLE IF EXISTS `user_avatar`;
CREATE TABLE `user_avatar`  (
                                  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                                  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户id',
                                  `head_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL  DEFAULT '' COMMENT '头像路径',
                                  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- 创建 location_tags 表结构 // 自定义位置点的标签
-- ----------------------------
DROP TABLE IF EXISTS `location_tags`;
CREATE TABLE `location_tags`  (
                                  `loc_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                                  `longitude` decimal(14,10) NOT NULL COMMENT 'POI位置经度',
                                  `latitude` decimal(14,10) NOT NULL COMMENT 'POI位置纬度',
                                  `location_desc` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL  DEFAULT '' COMMENT '位置描述',
                                  `location_tag` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '位置标签',
                                  `location_radius` int NOT NULL COMMENT '范围',
                                  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                                  PRIMARY KEY (`loc_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

INSERT INTO `location_tags` VALUES (1, 112.8690514251, 27.8890796904, '图书馆', '学习', 60, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (2, 112.8676154954, 27.8898287998, '第二田径场', '运动', 200, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (3, 112.8721136535, 27.8885931581, '南苑食堂', '进餐/休闲', 60, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (4, 112.8713376701, 27.8893084375, '雅园食堂', '进餐/休闲', 30, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (5, 112.8706160783, 27.8908694749, '服务大楼', '业务办理', 30, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (6, 112.8696932387, 27.8917941651, '俱乐部超市', '购物', 30, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (7, 112.8767459791, 27.8855885815, '体育馆', '运动', 50, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (8, 112.8751359951, 27.8853630332, '第四田径场', '运动', 200, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (9, 112.8733918471, 27.8849602626, '琴湖金翰林公寓区', '休息/娱乐', 300, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (10, 112.8716585767, 27.8837487360, '金翰林商业街', '购物/娱乐', 200, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (11, 112.8706432725, 27.8850150332, '校医院', '疗养', 50, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (12, 112.8702842899, 27.8860525478, '一田文化广场', '休闲/娱乐', 100, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (13, 112.8732576780, 27.8871609305, '老琴湖公寓', '休息/娱乐', 200, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (14, 112.8747915116, 27.8864907394, '琴湖食堂', '进餐/休闲', 50, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (15, 112.8700558425, 27.8880308716, '第一教学楼', '学习', 50, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (16, 112.8708789645, 27.8900832788, '校园卡管理中心', '业务办理', 10, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (17, 112.8695562103, 27.8845817756, '南门铜像广场', '休闲/娱乐', 150, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (18, 112.8680767666, 27.8841113413, '兴湘学院', '学习/学业相关', 50, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (19, 112.8678374437, 27.8836666817, '兴湘教学楼区', '学习', 100, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (20, 112.8667061047, 27.8846913203, '联建商业街', '休闲/娱乐', 130, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (21, 112.8675691125, 27.8868307868, '土木楼-计算中心', '学习/学业相关', 60, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (22, 112.8664740329, 27.8876169618, '工科楼', '学习/学业相关', 60, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (23, 112.8670614582, 27.8882098128, '网络与信息中心', '业务办理', 30, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (24, 112.8659301188, 27.8882871402, '信息楼', '学习/学业相关', 50, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (25, 112.8660824135, 27.8893181772, '第二教学楼', '学习', 60, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (26, 112.8652846736, 27.8892730680, '高分子楼', '学习/学业相关', 50, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (27, 112.8650598554, 27.8902718754, '化学-化工学院', '学习/学业相关', 50, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (28, 112.8689107582, 27.8881517998, '数学院-南山阶梯教室', '学习/学业相关', 50, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (29, 112.8691283228, 27.8872882916, '碧泉书院-马克思主义学院', '学习/学业相关', 50, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (30, 112.8690920612, 27.8865987685, '体训馆', '运动', 50, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (31, 112.8722685116, 27.8878553566, '学生活动中心', '休闲/娱乐/学习', 50, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (32, 112.8711081624, 27.8887059627, '南苑宿舍', '休息', 100, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (33, 112.8725078307, 27.8907100263, '东坡村', '休闲/休息', 200, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (34, 112.8715287865, 27.8916186058, '湘大子校', '业务办理', 100, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (35, 112.8676706294, 27.8925078452, '逸夫楼', '学习', 50, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (36, 112.8701218580, 27.8956007876, '北苑学生公寓', '休息', 100, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (37, 112.8697447451, 27.8947308965, '北苑食堂', '进餐/休闲', 30, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (38, 112.8720726918, 27.8949435345, '北斗村', '休息', 200, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (39, 112.8756625156, 27.8915218929, '教师公寓南', '休息/业务办理', 300, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (40, 112.8744151420, 27.8947437545, '教师公寓北', '休息/业务办理', 200, '2022-04-01 10:04:05', '2022-04-01 10:04:05');
INSERT INTO `location_tags` VALUES (41, 112.8682391806, 27.8934502306, '第三教学楼', '学习', 60, '2022-04-01 10:04:05', '2022-04-01 10:04:05');

-- ----------------------------
-- 创建 temp 表结构   中转表
-- ----------------------------
DROP TABLE IF EXISTS `temp`;
CREATE TABLE `temp`  (
                                  `temp_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                                  `user_tel` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户手机号',
                                  `cur_time` bigint(20) NOT NULL COMMENT '位置时间点',
                                  `loc_describe` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL  DEFAULT '' COMMENT '位置描述',
                                  `tag` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '位置标签',
                                  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                                  PRIMARY KEY (`temp_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- 创建 analyze_result 表结构   行程分析结果表
-- ----------------------------
DROP TABLE IF EXISTS `analyze_result`;
CREATE TABLE `analyze_result`  (
                         `result_id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
                         `user_tel` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户手机号',
                         `begin_time` bigint(20) NOT NULL COMMENT '开始时间点',
                         `finish_time` bigint(20) NOT NULL COMMENT '结束时间点',
                         `result_desc` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL  DEFAULT '' COMMENT '行为位置描述',
                         `result_tag` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '行为标签',
                         `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
                         PRIMARY KEY (`result_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;