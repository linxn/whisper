/*
 Navicat Premium Data Transfer

 Source Server         : 本地Mysql
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : localhost:3306
 Source Schema         : chat

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 05/06/2018 11:02:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for desire
-- ----------------------------
DROP TABLE IF EXISTS `desire`;
CREATE TABLE `desire`  (
  `d_id` int(11) NOT NULL AUTO_INCREMENT,
  `d_publisher_id` int(11) NULL DEFAULT NULL,
  `d_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `d_likes_count` int(11) NULL DEFAULT 0,
  `d_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `d_realize` tinyint(4) NULL DEFAULT 0,
  PRIMARY KEY (`d_id`) USING BTREE,
  INDEX `d_fk_publisher_id`(`d_publisher_id`) USING BTREE,
  CONSTRAINT `d_fk_publisher_id` FOREIGN KEY (`d_publisher_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of desire
-- ----------------------------
INSERT INTO `desire` VALUES (1, 7, '我的愿望是快高长大~', 12, '2018-05-18 23:16:06', 0);
INSERT INTO `desire` VALUES (2, 7, '希望以后能实现自己的理想', 77, '2018-05-18 23:16:18', 0);
INSERT INTO `desire` VALUES (3, 7, '爸妈永远不老', 211, '2018-05-18 23:16:29', 0);
INSERT INTO `desire` VALUES (4, 7, '我的愿望是世界和平', 234, '2018-05-18 23:16:44', 1);
INSERT INTO `desire` VALUES (5, 7, '可不可以没有蛀牙= =', 74, '2018-05-18 23:17:08', 0);
INSERT INTO `desire` VALUES (6, 7, '我的愿望是bala', 13, '2018-05-18 23:17:39', 0);
INSERT INTO `desire` VALUES (7, 7, '我的愿望是balabla', 2, '2018-05-18 23:17:48', 0);
INSERT INTO `desire` VALUES (8, 7, '我的愿望是balabalabala', 8, '2018-05-18 23:18:08', 0);
INSERT INTO `desire` VALUES (9, 7, '我的愿望是有好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多愿望', 1, '2018-05-18 23:18:33', 0);

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend`  (
  `f_id` int(11) NOT NULL AUTO_INCREMENT,
  `f_type` int(11) NULL DEFAULT NULL,
  `f_from_id` int(11) NULL DEFAULT NULL,
  `f_to_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`f_id`) USING BTREE,
  INDEX `f_fk_from_id`(`f_from_id`) USING BTREE,
  INDEX `f_fk_to_id`(`f_to_id`) USING BTREE,
  CONSTRAINT `f_fk_from_id` FOREIGN KEY (`f_from_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `f_fk_to_id` FOREIGN KEY (`f_to_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES (1, 1, 1, 7);
INSERT INTO `friend` VALUES (2, 1, 2, 7);
INSERT INTO `friend` VALUES (3, 1, 3, 7);
INSERT INTO `friend` VALUES (4, 1, 7, 8);
INSERT INTO `friend` VALUES (6, 1, 3, 8);
INSERT INTO `friend` VALUES (8, 2, 7, 9);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `m_id` int(11) NOT NULL AUTO_INCREMENT,
  `m_type` int(11) NULL DEFAULT NULL,
  `m_from_id` int(11) NULL DEFAULT NULL,
  `m_to_id` int(11) NULL DEFAULT NULL,
  `m_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `m_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `m_if_read` tinyint(4) NULL DEFAULT 1,
  PRIMARY KEY (`m_id`) USING BTREE,
  INDEX `m_fk_from_id`(`m_from_id`) USING BTREE,
  INDEX `m_fk_to_id`(`m_to_id`) USING BTREE,
  CONSTRAINT `m_fk_from_id` FOREIGN KEY (`m_from_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `m_fk_to_id` FOREIGN KEY (`m_to_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, 20, 7, 8, '#qq_1#', '2018-05-20 02:41:50', 1);
INSERT INTO `message` VALUES (2, 20, 7, 8, '#qq_4#', '2018-05-20 02:42:08', 1);
INSERT INTO `message` VALUES (3, 20, 8, 7, 'zhege#qq_1#asfsadf', '2018-05-20 02:43:37', 1);
INSERT INTO `message` VALUES (4, 20, 7, 8, '#qq_1#嘿嘿', '2018-05-20 03:28:24', 0);
INSERT INTO `message` VALUES (5, 20, 7, 9, '#qq_2#你好呀', '2018-05-20 03:28:48', 0);
INSERT INTO `message` VALUES (6, 20, 7, 9, '#qq_46#啧啧', '2018-05-20 03:29:04', 0);
INSERT INTO `message` VALUES (7, 20, 7, 8, '#qq_1#', '2018-05-20 05:01:08', 0);
INSERT INTO `message` VALUES (8, 20, 7, 8, '#qq_13#', '2018-05-20 05:01:18', 0);
INSERT INTO `message` VALUES (9, 20, 7, 8, '你好呀', '2018-05-20 11:03:46', 0);
INSERT INTO `message` VALUES (10, 20, 7, 8, '#qq_2#', '2018-05-20 11:04:01', 0);
INSERT INTO `message` VALUES (11, 20, 7, 8, '#qq_1#', '2018-05-20 11:04:05', 0);
INSERT INTO `message` VALUES (12, 20, 7, 8, '#qq_14#', '2018-05-20 11:04:10', 0);
INSERT INTO `message` VALUES (13, 20, 7, 8, '#qq_13#', '2018-05-20 11:04:16', 0);
INSERT INTO `message` VALUES (14, 20, 7, 8, '#qq_16#', '2018-05-20 11:04:22', 0);
INSERT INTO `message` VALUES (15, 20, 7, 8, '#qq_1#', '2018-05-20 11:04:38', 0);

-- ----------------------------
-- Table structure for systemmessagelog
-- ----------------------------
DROP TABLE IF EXISTS `systemmessagelog`;
CREATE TABLE `systemmessagelog`  (
  `sm_id` int(11) NOT NULL AUTO_INCREMENT,
  `sm_type` int(11) NULL DEFAULT NULL,
  `sm_from_id` int(11) NULL DEFAULT NULL,
  `sm_to_id` int(11) NULL DEFAULT NULL,
  `sm_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sm_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sm_id`) USING BTREE,
  INDEX `sm_fk_from_id`(`sm_from_id`) USING BTREE,
  INDEX `sm_fk_to_id`(`sm_to_id`) USING BTREE,
  CONSTRAINT `sm_fk_from_id` FOREIGN KEY (`sm_from_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sm_fk_to_id` FOREIGN KEY (`sm_to_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 145 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of systemmessagelog
-- ----------------------------
INSERT INTO `systemmessagelog` VALUES (1, 26, NULL, NULL, NULL, '2018-05-19 23:07:16');
INSERT INTO `systemmessagelog` VALUES (2, 26, NULL, NULL, NULL, '2018-05-19 23:07:22');
INSERT INTO `systemmessagelog` VALUES (3, 23, NULL, NULL, NULL, '2018-05-19 23:07:29');
INSERT INTO `systemmessagelog` VALUES (4, 19, NULL, NULL, NULL, '2018-05-19 23:07:45');
INSERT INTO `systemmessagelog` VALUES (5, 34, NULL, NULL, NULL, '2018-05-19 23:07:45');
INSERT INTO `systemmessagelog` VALUES (6, 6, NULL, NULL, NULL, '2018-05-19 23:07:50');
INSERT INTO `systemmessagelog` VALUES (7, 19, NULL, NULL, NULL, '2018-05-19 23:07:51');
INSERT INTO `systemmessagelog` VALUES (8, 34, NULL, NULL, NULL, '2018-05-19 23:07:51');
INSERT INTO `systemmessagelog` VALUES (9, 6, NULL, NULL, NULL, '2018-05-19 23:08:07');
INSERT INTO `systemmessagelog` VALUES (10, 3, NULL, NULL, '{uUsername : \"test1\", uUserpwd : \"123\"}', '2018-05-20 02:12:04');
INSERT INTO `systemmessagelog` VALUES (11, 3, NULL, NULL, '{uUsername : \"test1\", uUserpwd : \"123\"}', '2018-05-20 02:14:42');
INSERT INTO `systemmessagelog` VALUES (12, 3, NULL, NULL, '{uUsername : \"test1\", uUserpwd : \"123\"}', '2018-05-20 02:20:22');
INSERT INTO `systemmessagelog` VALUES (13, 3, NULL, NULL, '{uUsername : \"test1\", uUserpwd : \"123\"}', '2018-05-20 02:26:31');
INSERT INTO `systemmessagelog` VALUES (14, 3, NULL, NULL, '{uUsername : \"test1\", uUserpwd : \"123\"}', '2018-05-20 02:27:09');
INSERT INTO `systemmessagelog` VALUES (15, 3, NULL, NULL, '{uUsername : \"test1\", uUserpwd : \"123\"}', '2018-05-20 02:28:37');
INSERT INTO `systemmessagelog` VALUES (16, 19, 7, NULL, NULL, '2018-05-20 02:28:40');
INSERT INTO `systemmessagelog` VALUES (17, 34, 7, NULL, NULL, '2018-05-20 02:28:40');
INSERT INTO `systemmessagelog` VALUES (18, 5, 7, NULL, '{uId : \"7\", uOnline : \"1\"}', '2018-05-20 02:28:40');
INSERT INTO `systemmessagelog` VALUES (19, 3, NULL, NULL, '{uUsername : \"test1\", uUserpwd : \"123\"}', '2018-05-20 02:30:26');
INSERT INTO `systemmessagelog` VALUES (20, 34, 7, NULL, NULL, '2018-05-20 02:30:28');
INSERT INTO `systemmessagelog` VALUES (21, 19, 7, NULL, NULL, '2018-05-20 02:30:28');
INSERT INTO `systemmessagelog` VALUES (22, 5, 7, NULL, '{uId : \"7\", uOnline : \"1\"}', '2018-05-20 02:30:29');
INSERT INTO `systemmessagelog` VALUES (23, 3, NULL, NULL, '{uUsername : \"test1\", uUserpwd : \"123\"}', '2018-05-20 02:36:34');
INSERT INTO `systemmessagelog` VALUES (24, 34, 7, NULL, NULL, '2018-05-20 02:36:40');
INSERT INTO `systemmessagelog` VALUES (25, 19, 7, NULL, NULL, '2018-05-20 02:36:40');
INSERT INTO `systemmessagelog` VALUES (26, 5, 7, NULL, '{uId : \"7\", uOnline : \"1\"}', '2018-05-20 02:36:40');
INSERT INTO `systemmessagelog` VALUES (27, 34, 7, NULL, NULL, '2018-05-20 02:37:58');
INSERT INTO `systemmessagelog` VALUES (28, 5, 7, NULL, '{uId : \"7\", uOnline : \"1\"}', '2018-05-20 02:37:58');
INSERT INTO `systemmessagelog` VALUES (29, 19, 7, NULL, NULL, '2018-05-20 02:37:58');
INSERT INTO `systemmessagelog` VALUES (30, 5, 7, NULL, '{uId : \"7\", uOnline : \"1\"}', '2018-05-20 02:39:47');
INSERT INTO `systemmessagelog` VALUES (31, 34, 7, NULL, NULL, '2018-05-20 02:39:47');
INSERT INTO `systemmessagelog` VALUES (32, 19, 7, NULL, NULL, '2018-05-20 02:39:47');
INSERT INTO `systemmessagelog` VALUES (33, 19, 7, NULL, NULL, '2018-05-20 02:41:02');
INSERT INTO `systemmessagelog` VALUES (34, 34, 7, NULL, NULL, '2018-05-20 02:41:02');
INSERT INTO `systemmessagelog` VALUES (35, 5, 7, NULL, '{uId : \"7\", uOnline : \"1\"}', '2018-05-20 02:41:02');
INSERT INTO `systemmessagelog` VALUES (36, 19, 7, NULL, NULL, '2018-05-20 02:41:43');
INSERT INTO `systemmessagelog` VALUES (37, 34, 7, NULL, NULL, '2018-05-20 02:41:43');
INSERT INTO `systemmessagelog` VALUES (38, 5, 7, NULL, '{uId : \"7\", uOnline : \"1\"}', '2018-05-20 02:41:43');
INSERT INTO `systemmessagelog` VALUES (39, 3, NULL, NULL, '{uUsername : \"test2\", uUserpwd : \"123\"}', '2018-05-20 02:43:06');
INSERT INTO `systemmessagelog` VALUES (40, 34, 8, NULL, NULL, '2018-05-20 02:43:08');
INSERT INTO `systemmessagelog` VALUES (41, 19, 8, NULL, NULL, '2018-05-20 02:43:08');
INSERT INTO `systemmessagelog` VALUES (42, 5, 8, NULL, '{uId : \"8\", uOnline : \"1\"}', '2018-05-20 02:43:08');
INSERT INTO `systemmessagelog` VALUES (43, 3, NULL, NULL, '{uUsername : \"test2\", uUserpwd : \"123\"}', '2018-05-20 03:00:06');
INSERT INTO `systemmessagelog` VALUES (44, 34, 8, NULL, NULL, '2018-05-20 03:00:09');
INSERT INTO `systemmessagelog` VALUES (45, 19, 8, NULL, NULL, '2018-05-20 03:00:09');
INSERT INTO `systemmessagelog` VALUES (46, 5, 8, NULL, '{uId : \"8\", uOnline : \"1\"}', '2018-05-20 03:00:09');
INSERT INTO `systemmessagelog` VALUES (47, 19, 8, NULL, NULL, '2018-05-20 03:05:02');
INSERT INTO `systemmessagelog` VALUES (48, 34, 8, NULL, NULL, '2018-05-20 03:05:02');
INSERT INTO `systemmessagelog` VALUES (49, 5, 8, NULL, '{uId : \"8\", uOnline : \"1\"}', '2018-05-20 03:05:02');
INSERT INTO `systemmessagelog` VALUES (50, 19, 8, NULL, NULL, '2018-05-20 03:06:08');
INSERT INTO `systemmessagelog` VALUES (51, 34, 8, NULL, NULL, '2018-05-20 03:06:08');
INSERT INTO `systemmessagelog` VALUES (52, 5, 8, NULL, '{uId : \"8\", uOnline : \"1\"}', '2018-05-20 03:06:08');
INSERT INTO `systemmessagelog` VALUES (53, 19, 8, NULL, NULL, '2018-05-20 03:07:59');
INSERT INTO `systemmessagelog` VALUES (54, 5, 8, NULL, '{uId : \"8\", uOnline : \"1\"}', '2018-05-20 03:07:59');
INSERT INTO `systemmessagelog` VALUES (55, 34, 8, NULL, NULL, '2018-05-20 03:07:59');
INSERT INTO `systemmessagelog` VALUES (56, 3, NULL, NULL, '{uUsername : \"test2\", uUserpwd : \"123\"}', '2018-05-20 03:13:12');
INSERT INTO `systemmessagelog` VALUES (57, 34, 8, NULL, NULL, '2018-05-20 03:13:15');
INSERT INTO `systemmessagelog` VALUES (58, 19, 8, NULL, NULL, '2018-05-20 03:13:15');
INSERT INTO `systemmessagelog` VALUES (59, 5, 8, NULL, '{uId : \"8\", uOnline : \"1\"}', '2018-05-20 03:13:15');
INSERT INTO `systemmessagelog` VALUES (60, 34, 8, NULL, NULL, '2018-05-20 03:18:04');
INSERT INTO `systemmessagelog` VALUES (61, 19, 8, NULL, NULL, '2018-05-20 03:18:04');
INSERT INTO `systemmessagelog` VALUES (62, 5, 8, NULL, '{uId : \"8\", uOnline : \"1\"}', '2018-05-20 03:18:04');
INSERT INTO `systemmessagelog` VALUES (63, 34, NULL, NULL, NULL, '2018-05-20 03:18:21');
INSERT INTO `systemmessagelog` VALUES (64, 19, NULL, NULL, NULL, '2018-05-20 03:18:21');
INSERT INTO `systemmessagelog` VALUES (65, 19, NULL, NULL, NULL, '2018-05-20 03:18:28');
INSERT INTO `systemmessagelog` VALUES (66, 34, NULL, NULL, NULL, '2018-05-20 03:18:28');
INSERT INTO `systemmessagelog` VALUES (67, 34, NULL, NULL, NULL, '2018-05-20 03:18:43');
INSERT INTO `systemmessagelog` VALUES (68, 19, NULL, NULL, NULL, '2018-05-20 03:18:43');
INSERT INTO `systemmessagelog` VALUES (69, 34, NULL, NULL, NULL, '2018-05-20 03:20:48');
INSERT INTO `systemmessagelog` VALUES (70, 19, NULL, NULL, NULL, '2018-05-20 03:20:48');
INSERT INTO `systemmessagelog` VALUES (71, 34, NULL, NULL, NULL, '2018-05-20 03:22:14');
INSERT INTO `systemmessagelog` VALUES (72, 19, NULL, NULL, NULL, '2018-05-20 03:22:14');
INSERT INTO `systemmessagelog` VALUES (73, 19, NULL, NULL, NULL, '2018-05-20 03:23:36');
INSERT INTO `systemmessagelog` VALUES (74, 34, NULL, NULL, NULL, '2018-05-20 03:23:36');
INSERT INTO `systemmessagelog` VALUES (75, 19, NULL, NULL, NULL, '2018-05-20 03:25:56');
INSERT INTO `systemmessagelog` VALUES (76, 34, NULL, NULL, NULL, '2018-05-20 03:25:56');
INSERT INTO `systemmessagelog` VALUES (77, 34, NULL, NULL, NULL, '2018-05-20 03:26:55');
INSERT INTO `systemmessagelog` VALUES (78, 19, NULL, NULL, NULL, '2018-05-20 03:26:55');
INSERT INTO `systemmessagelog` VALUES (79, 34, NULL, NULL, NULL, '2018-05-20 03:27:22');
INSERT INTO `systemmessagelog` VALUES (80, 19, NULL, NULL, NULL, '2018-05-20 03:27:22');
INSERT INTO `systemmessagelog` VALUES (81, 19, NULL, NULL, NULL, '2018-05-20 03:27:23');
INSERT INTO `systemmessagelog` VALUES (82, 34, NULL, NULL, NULL, '2018-05-20 03:27:23');
INSERT INTO `systemmessagelog` VALUES (83, 19, NULL, NULL, NULL, '2018-05-20 03:27:44');
INSERT INTO `systemmessagelog` VALUES (84, 34, NULL, NULL, NULL, '2018-05-20 03:27:44');
INSERT INTO `systemmessagelog` VALUES (85, 3, NULL, NULL, '{uUsername : \"test1\", uUserpwd : \"123\"}', '2018-05-20 03:28:12');
INSERT INTO `systemmessagelog` VALUES (86, 19, 7, NULL, NULL, '2018-05-20 03:28:14');
INSERT INTO `systemmessagelog` VALUES (87, 34, 7, NULL, NULL, '2018-05-20 03:28:14');
INSERT INTO `systemmessagelog` VALUES (88, 5, 7, NULL, '{uId : \"7\", uOnline : \"1\"}', '2018-05-20 03:28:14');
INSERT INTO `systemmessagelog` VALUES (89, 3, NULL, NULL, '{uUsername : \"test1\", uUserpwd : \"123\"}', '2018-05-20 04:45:11');
INSERT INTO `systemmessagelog` VALUES (90, 19, 7, NULL, NULL, '2018-05-20 04:45:16');
INSERT INTO `systemmessagelog` VALUES (91, 34, 7, NULL, NULL, '2018-05-20 04:45:16');
INSERT INTO `systemmessagelog` VALUES (92, 5, 7, NULL, '{uId : \"7\", uOnline : \"1\"}', '2018-05-20 04:45:16');
INSERT INTO `systemmessagelog` VALUES (93, 3, NULL, NULL, '{uUsername : \"test1\", uUserpwd : \"123\"}', '2018-05-20 05:00:59');
INSERT INTO `systemmessagelog` VALUES (94, 34, 7, NULL, NULL, '2018-05-20 05:01:02');
INSERT INTO `systemmessagelog` VALUES (95, 19, 7, NULL, NULL, '2018-05-20 05:01:02');
INSERT INTO `systemmessagelog` VALUES (96, 5, 7, NULL, '{uId : \"7\", uOnline : \"1\"}', '2018-05-20 05:01:02');
INSERT INTO `systemmessagelog` VALUES (97, 3, NULL, NULL, '{uUsername : \"test1\", uUserpwd : \"123\"}', '2018-05-20 11:03:28');
INSERT INTO `systemmessagelog` VALUES (98, 34, 7, NULL, NULL, '2018-05-20 11:03:32');
INSERT INTO `systemmessagelog` VALUES (99, 19, 7, NULL, NULL, '2018-05-20 11:03:32');
INSERT INTO `systemmessagelog` VALUES (100, 5, 7, NULL, '{uId : \"7\", uOnline : \"1\"}', '2018-05-20 11:03:32');
INSERT INTO `systemmessagelog` VALUES (101, 34, 7, NULL, NULL, '2018-05-20 11:04:50');
INSERT INTO `systemmessagelog` VALUES (102, 19, 7, NULL, NULL, '2018-05-20 11:04:50');
INSERT INTO `systemmessagelog` VALUES (103, 5, 7, NULL, '{uId : \"7\", uOnline : \"1\"}', '2018-05-20 11:04:50');
INSERT INTO `systemmessagelog` VALUES (104, 23, 7, NULL, NULL, '2018-05-20 11:04:51');
INSERT INTO `systemmessagelog` VALUES (105, 26, 7, NULL, NULL, '2018-05-20 11:05:05');
INSERT INTO `systemmessagelog` VALUES (106, 26, 7, NULL, NULL, '2018-05-20 11:05:07');
INSERT INTO `systemmessagelog` VALUES (107, 26, 7, NULL, NULL, '2018-05-20 11:05:08');
INSERT INTO `systemmessagelog` VALUES (108, 26, 7, NULL, NULL, '2018-05-20 11:05:08');
INSERT INTO `systemmessagelog` VALUES (109, 26, 7, NULL, NULL, '2018-05-20 11:05:08');
INSERT INTO `systemmessagelog` VALUES (110, 26, 7, NULL, NULL, '2018-05-20 11:05:09');
INSERT INTO `systemmessagelog` VALUES (111, 26, 7, NULL, NULL, '2018-05-20 11:05:09');
INSERT INTO `systemmessagelog` VALUES (112, 26, 7, NULL, NULL, '2018-05-20 11:05:09');
INSERT INTO `systemmessagelog` VALUES (113, 26, 7, NULL, NULL, '2018-05-20 11:05:10');
INSERT INTO `systemmessagelog` VALUES (114, 26, 7, NULL, NULL, '2018-05-20 11:05:10');
INSERT INTO `systemmessagelog` VALUES (115, 26, 7, NULL, NULL, '2018-05-20 11:05:10');
INSERT INTO `systemmessagelog` VALUES (116, 26, 7, NULL, NULL, '2018-05-20 11:05:10');
INSERT INTO `systemmessagelog` VALUES (117, 26, 7, NULL, NULL, '2018-05-20 11:05:10');
INSERT INTO `systemmessagelog` VALUES (118, 26, 7, NULL, NULL, '2018-05-20 11:05:10');
INSERT INTO `systemmessagelog` VALUES (119, 26, 7, NULL, NULL, '2018-05-20 11:05:11');
INSERT INTO `systemmessagelog` VALUES (120, 26, 7, NULL, NULL, '2018-05-20 11:05:11');
INSERT INTO `systemmessagelog` VALUES (121, 26, 7, NULL, NULL, '2018-05-20 11:05:11');
INSERT INTO `systemmessagelog` VALUES (122, 26, 7, NULL, NULL, '2018-05-20 11:05:11');
INSERT INTO `systemmessagelog` VALUES (123, 26, 7, NULL, NULL, '2018-05-20 11:05:11');
INSERT INTO `systemmessagelog` VALUES (124, 26, 7, NULL, NULL, '2018-05-20 11:05:12');
INSERT INTO `systemmessagelog` VALUES (125, 26, 7, NULL, NULL, '2018-05-20 11:05:12');
INSERT INTO `systemmessagelog` VALUES (126, 26, 7, NULL, NULL, '2018-05-20 11:05:12');
INSERT INTO `systemmessagelog` VALUES (127, 26, 7, NULL, NULL, '2018-05-20 11:05:12');
INSERT INTO `systemmessagelog` VALUES (128, 26, 7, NULL, NULL, '2018-05-20 11:05:12');
INSERT INTO `systemmessagelog` VALUES (129, 26, 7, NULL, NULL, '2018-05-20 11:05:13');
INSERT INTO `systemmessagelog` VALUES (130, 26, 7, NULL, NULL, '2018-05-20 11:05:13');
INSERT INTO `systemmessagelog` VALUES (131, 26, 7, NULL, NULL, '2018-05-20 11:05:13');
INSERT INTO `systemmessagelog` VALUES (132, 26, 7, NULL, NULL, '2018-05-20 11:05:13');
INSERT INTO `systemmessagelog` VALUES (133, 26, 7, NULL, NULL, '2018-05-20 11:05:13');
INSERT INTO `systemmessagelog` VALUES (134, 26, 7, NULL, NULL, '2018-05-20 11:05:14');
INSERT INTO `systemmessagelog` VALUES (135, 26, 7, NULL, NULL, '2018-05-20 11:05:14');
INSERT INTO `systemmessagelog` VALUES (136, 26, 7, NULL, NULL, '2018-05-20 11:05:14');
INSERT INTO `systemmessagelog` VALUES (137, 26, 7, NULL, NULL, '2018-05-20 11:05:14');
INSERT INTO `systemmessagelog` VALUES (138, 26, 7, NULL, NULL, '2018-05-20 11:05:14');
INSERT INTO `systemmessagelog` VALUES (139, 26, 7, NULL, NULL, '2018-05-20 11:05:15');
INSERT INTO `systemmessagelog` VALUES (140, 26, 7, NULL, NULL, '2018-05-20 11:05:15');
INSERT INTO `systemmessagelog` VALUES (141, 26, 7, NULL, NULL, '2018-05-20 11:05:15');
INSERT INTO `systemmessagelog` VALUES (142, 26, 7, NULL, NULL, '2018-05-20 11:05:15');
INSERT INTO `systemmessagelog` VALUES (143, 26, 7, NULL, NULL, '2018-05-20 11:05:16');
INSERT INTO `systemmessagelog` VALUES (144, 26, 7, NULL, NULL, '2018-05-20 11:05:16');
INSERT INTO `systemmessagelog` VALUES (145, 26, 7, NULL, NULL, '2018-05-20 11:05:16');

-- ----------------------------
-- Table structure for treehole
-- ----------------------------
DROP TABLE IF EXISTS `treehole`;
CREATE TABLE `treehole`  (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_publisher_id` int(11) NULL DEFAULT NULL,
  `t_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `t_likes_count` int(11) NULL DEFAULT 0,
  `t_publish_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`t_id`) USING BTREE,
  INDEX `t_fk_publisher_id`(`t_publisher_id`) USING BTREE,
  CONSTRAINT `t_fk_publisher_id` FOREIGN KEY (`t_publisher_id`) REFERENCES `user` (`u_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of treehole
-- ----------------------------
INSERT INTO `treehole` VALUES (1, 7, '快点做完毕设= = 做太久辣', 66, '2018-05-18 23:10:25');
INSERT INTO `treehole` VALUES (2, 7, '树洞是个好东西', 99, '2018-05-18 23:10:41');
INSERT INTO `treehole` VALUES (3, 7, '请问女朋友是学校发的还是自己领的？', 233, '2018-05-18 23:11:03');
INSERT INTO `treehole` VALUES (4, 7, '我有悄悄话但是不想告诉你', 8, '2018-05-18 23:11:18');
INSERT INTO `treehole` VALUES (5, 7, '只是看一眼树洞 别多想', 6, '2018-05-18 23:12:33');
INSERT INTO `treehole` VALUES (6, 7, '怎么觉得有点像华工小灯神？', 23, '2018-05-18 23:12:48');
INSERT INTO `treehole` VALUES (7, 7, '我的悄悄话是bala', 0, '2018-05-18 23:13:16');
INSERT INTO `treehole` VALUES (8, 7, '我的悄悄话是balabala', 0, '2018-05-18 23:13:46');
INSERT INTO `treehole` VALUES (9, 7, '我的悄悄话是balabalabala', 0, '2018-05-18 23:14:13');
INSERT INTO `treehole` VALUES (10, 7, '我有好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多好多悄悄话', 0, '2018-05-18 23:14:40');
INSERT INTO `treehole` VALUES (16, 9, '我的悄悄话是……我选你', 1, '2018-05-19 22:37:12');
INSERT INTO `treehole` VALUES (17, NULL, '请问', 0, '2018-05-19 22:54:29');
INSERT INTO `treehole` VALUES (18, NULL, '驱蚊器翁', 0, '2018-05-19 22:54:44');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `u_userpwd` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `u_re_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `u_online` tinyint(4) NULL DEFAULT 0,
  `u_head_photo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`u_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', '2018-05-18 22:48:44', 1, '1.jpg');
INSERT INTO `user` VALUES (2, 'linxn', 'e10adc3949ba59abbe56e057f20f883e', '2018-05-18 22:48:57', 1, '2.jpg');
INSERT INTO `user` VALUES (3, 'John', 'e10adc3949ba59abbe56e057f20f883e', '2018-05-18 22:49:21', 1, '3.jpg');
INSERT INTO `user` VALUES (4, 'Lisa', 'e10adc3949ba59abbe56e057f20f883e', '2018-05-18 22:49:28', 1, '4.jpg');
INSERT INTO `user` VALUES (5, 'Sean', 'e10adc3949ba59abbe56e057f20f883e', '2018-05-18 22:49:36', 1, '5.jpg');
INSERT INTO `user` VALUES (6, 'Coco', 'e10adc3949ba59abbe56e057f20f883e', '2018-05-18 22:49:46', 1, '6.jpg');
INSERT INTO `user` VALUES (7, 'test1', '202cb962ac59075b964b07152d234b70', '2018-05-18 22:50:11', 1, '1.jpg');
INSERT INTO `user` VALUES (8, 'test2', '202cb962ac59075b964b07152d234b70', '2018-05-18 22:50:16', 1, '2.jpg');
INSERT INTO `user` VALUES (9, 'test3', '202cb962ac59075b964b07152d234b70', '2018-05-18 22:50:23', 1, '3.jpg');

SET FOREIGN_KEY_CHECKS = 1;
