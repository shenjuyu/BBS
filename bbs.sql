/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 120.77.87.218:3306
 Source Schema         : bbs

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 16/08/2019 10:03:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bbs_admin
-- ----------------------------
DROP TABLE IF EXISTS `bbs_admin`;
CREATE TABLE `bbs_admin`  (
  `a_id` int(11) NOT NULL AUTO_INCREMENT,
  `a_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `a_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `a_tel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `a_power` int(11) NULL DEFAULT NULL,
  `a_state` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`a_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bbs_admin
-- ----------------------------
INSERT INTO `bbs_admin` VALUES (2, '张三', '123456', '17568034608', 1, 1);
INSERT INTO `bbs_admin` VALUES (3, '李四', '123456', '19012122323', 2, 1);
INSERT INTO `bbs_admin` VALUES (4, '王五', '123456', '17568056948', 2, 1);

-- ----------------------------
-- Table structure for bbs_board
-- ----------------------------
DROP TABLE IF EXISTS `bbs_board`;
CREATE TABLE `bbs_board`  (
  `b_id` int(11) NOT NULL AUTO_INCREMENT,
  `b_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `b_parentid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`b_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bbs_board
-- ----------------------------
INSERT INTO `bbs_board` VALUES (0, '根板块', -1);
INSERT INTO `bbs_board` VALUES (1, 'NET技术', 0);
INSERT INTO `bbs_board` VALUES (2, 'Java技术', 0);
INSERT INTO `bbs_board` VALUES (3, '数据库技术', 0);
INSERT INTO `bbs_board` VALUES (4, '娱乐', 0);
INSERT INTO `bbs_board` VALUES (5, '校园专区', 0);
INSERT INTO `bbs_board` VALUES (6, 'C#语言', 1);
INSERT INTO `bbs_board` VALUES (7, 'WinForms', 1);
INSERT INTO `bbs_board` VALUES (8, 'ADO.NET', 1);
INSERT INTO `bbs_board` VALUES (9, 'ASP.NET', 1);
INSERT INTO `bbs_board` VALUES (10, 'Java基础', 2);
INSERT INTO `bbs_board` VALUES (11, 'JSP技术', 2);
INSERT INTO `bbs_board` VALUES (12, 'Servlet技术', 2);
INSERT INTO `bbs_board` VALUES (13, 'Eclipse应用', 2);
INSERT INTO `bbs_board` VALUES (14, 'SQL Server基础', 3);
INSERT INTO `bbs_board` VALUES (15, 'SQL Server高级', 3);
INSERT INTO `bbs_board` VALUES (16, '灌水乐园', 4);
INSERT INTO `bbs_board` VALUES (17, '新闻播报', 4);
INSERT INTO `bbs_board` VALUES (18, '失物招领', 5);
INSERT INTO `bbs_board` VALUES (19, '表白墙', 5);
INSERT INTO `bbs_board` VALUES (21, '后端开发', 2);
INSERT INTO `bbs_board` VALUES (23, '嘿嘿', 5);
INSERT INTO `bbs_board` VALUES (24, '广告', 5);
INSERT INTO `bbs_board` VALUES (25, '呕吼', 4);

-- ----------------------------
-- Table structure for bbs_impeachtopic
-- ----------------------------
DROP TABLE IF EXISTS `bbs_impeachtopic`;
CREATE TABLE `bbs_impeachtopic`  (
  `ti_id` int(11) NOT NULL AUTO_INCREMENT,
  `ti_uid` int(11) NULL DEFAULT NULL,
  `ti_uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ti_tid` int(11) NULL DEFAULT NULL,
  `ti_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ti_reason` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `ti_state` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ti_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bbs_impeachtopic
-- ----------------------------
INSERT INTO `bbs_impeachtopic` VALUES (1, 1, 'yc', 1, '灌水', '无路赛', 1);
INSERT INTO `bbs_impeachtopic` VALUES (4, 1, 'yc', 2, '嘿嘿嘿', '无路赛', 2);
INSERT INTO `bbs_impeachtopic` VALUES (5, 2, 'sjy', 3, '这段代码什么意思', 'asdf', 1);
INSERT INTO `bbs_impeachtopic` VALUES (6, 1, 'yc', 21, 'ssdaf', 'sdaf', 2);
INSERT INTO `bbs_impeachtopic` VALUES (7, 2, 'sjy', 24, '这个问题应该怎么办呀', '这个人发布了不良信息', 2);
INSERT INTO `bbs_impeachtopic` VALUES (8, 7, '活动社', 24, '这个问题应该怎么办呀', '这个人发帖违规了', 3);

-- ----------------------------
-- Table structure for bbs_impeachuser
-- ----------------------------
DROP TABLE IF EXISTS `bbs_impeachuser`;
CREATE TABLE `bbs_impeachuser`  (
  `ui_id` int(11) NOT NULL AUTO_INCREMENT,
  `ui_uid` int(11) NULL DEFAULT NULL,
  `ui_uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ui_rid` int(11) NULL DEFAULT NULL,
  `ui_rname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ui_reason` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `ui_state` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`ui_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bbs_impeachuser
-- ----------------------------
INSERT INTO `bbs_impeachuser` VALUES (1, 1, 'yc', 1, '张三', '无路赛', 3);
INSERT INTO `bbs_impeachuser` VALUES (2, 5, '沈俊羽', 2, 'sjy', '这个人好烦呀', 2);

-- ----------------------------
-- Table structure for bbs_reply
-- ----------------------------
DROP TABLE IF EXISTS `bbs_reply`;
CREATE TABLE `bbs_reply`  (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `r_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `r_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `r_publishtime` datetime(0) NULL DEFAULT NULL,
  `r_modifytime` datetime(0) NULL DEFAULT NULL,
  `u_id` int(11) NULL DEFAULT NULL,
  `t_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`r_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bbs_reply
-- ----------------------------
INSERT INTO `bbs_reply` VALUES (12, '1', '<p>不学java&nbsp; C#天下第一</p>', '2019-08-13 19:49:15', '2019-08-13 19:49:15', 4, 15);
INSERT INTO `bbs_reply` VALUES (13, '1', '<p>Java 赛高</p>', '2019-08-13 19:51:01', '2019-08-13 19:51:01', 1, 15);
INSERT INTO `bbs_reply` VALUES (16, '1', '<p>顶贴</p>', '2019-08-16 09:34:45', '2019-08-16 09:34:45', 6, 24);
INSERT INTO `bbs_reply` VALUES (17, '1', '<p>我也不晓得</p>', '2019-08-16 09:35:14', '2019-08-16 09:35:14', 2, 24);

-- ----------------------------
-- Table structure for bbs_topic
-- ----------------------------
DROP TABLE IF EXISTS `bbs_topic`;
CREATE TABLE `bbs_topic`  (
  `t_id` int(11) NOT NULL AUTO_INCREMENT,
  `t_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `t_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `t_publishtime` datetime(0) NULL DEFAULT NULL,
  `t_modifytime` datetime(0) NULL DEFAULT NULL,
  `u_id` int(11) NULL DEFAULT NULL,
  `b_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bbs_topic
-- ----------------------------
INSERT INTO `bbs_topic` VALUES (2, '谁帮我看看程序', '士大夫', '2019-08-06 15:47:34', '2019-08-06 15:47:40', 1, 6);
INSERT INTO `bbs_topic` VALUES (3, '这段代码什么意思', 'adfa', '2019-08-06 16:31:17', '2019-08-06 16:31:19', 1, 7);
INSERT INTO `bbs_topic` VALUES (4, '这里有个错误', '反对者', '2019-08-06 16:48:31', '2019-08-06 16:48:33', 1, 7);
INSERT INTO `bbs_topic` VALUES (6, '43岁的老程序员，你们要问的快问，我准备退休了............', '你们要问的快问，我准备退休了', '2019-08-08 10:19:22', '2019-08-08 10:19:26', 1, 8);
INSERT INTO `bbs_topic` VALUES (8, '\r\n我是如何从程序员做到技术总监的，欢迎灌水', '我这条路，经过了5年多时间，从程序员，小组长，系统分析员，项目经理，产品经', '2019-08-22 10:20:35', '2019-08-23 10:20:39', 1, 10);
INSERT INTO `bbs_topic` VALUES (9, '大神们，请问图中代码是什么意思！', '聊天的时候，群里一位大佬突然插了一张有代码的图。这会是个什么梗呢', '2019-08-17 10:21:09', '2019-08-18 10:21:15', 1, 11);
INSERT INTO `bbs_topic` VALUES (10, '图片真滴不重要，评论区才重要', '现在学习java、python、大数据、人工智能是王道吧。我现在自学的', '2019-08-20 10:21:57', '2019-09-05 10:22:01', 1, 12);
INSERT INTO `bbs_topic` VALUES (11, '有没有做软件的大神或者懂软件的大神，帮', '帮我个忙我给你1000，看到的', '2019-08-24 10:22:41', '2019-08-26 10:22:45', 1, 13);
INSERT INTO `bbs_topic` VALUES (12, '小白入门编程，是不是从Python语言开始呢？说这个', '学完之后，在学习C语言，或者其他高级语言是吗？', '2019-08-29 10:23:14', '2019-08-31 10:23:19', 1, 14);
INSERT INTO `bbs_topic` VALUES (13, '大佬们，这是哪错了', '大佬们，这是哪错了', '2019-09-08 10:23:58', '2019-09-08 10:24:03', 1, 15);
INSERT INTO `bbs_topic` VALUES (14, '\r\n来个大神 SIM卡 复制 有没有可能?', '\r\n来个大神 SIM卡 复制 有没有可能?', '2019-09-01 10:24:47', '2019-09-03 10:24:53', 1, 16);
INSERT INTO `bbs_topic` VALUES (15, '\r\n在校的学生，如何利用闲暇时间，学好Java编', '今天楼主给大家讲一下在校学生都关心的问题', '2019-08-01 10:25:18', '2019-08-02 10:25:23', 1, 17);
INSERT INTO `bbs_topic` VALUES (16, '\r\n有什么外部设备可以识别手机上的文字并且做出反应。或者说有没有', '或者说有没有教程', '2019-08-12 10:25:50', '2019-08-14 10:25:55', 1, 18);
INSERT INTO `bbs_topic` VALUES (17, '\r\n感谢各位前辈', '请问各位前辈如果一个萌新小白想学习编程该从哪个语言学起？求', '2019-08-03 10:26:34', '2019-08-08 10:26:39', 1, 19);
INSERT INTO `bbs_topic` VALUES (24, '这个问题应该怎么办呀', '<p>大神们帮帮忙呀<img src=\"http://img.baidu.com/hi/jx2/j_0013.gif\"/><img src=\"../bbsimages/jsp/upload/image/20190816/1565919264380016999.jpg\" title=\"1565919264380016999.jpg\" alt=\"{$REKVQ[T0Z0%%~R1ZK~WNM.jpg\"/></p>', '2019-08-16 09:34:26', '2019-08-16 09:34:26', 6, 6);

-- ----------------------------
-- Table structure for bbs_user
-- ----------------------------
DROP TABLE IF EXISTS `bbs_user`;
CREATE TABLE `bbs_user`  (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_head` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_regtime` datetime(0) NULL DEFAULT NULL,
  `u_sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `u_state` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`u_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of bbs_user
-- ----------------------------
INSERT INTO `bbs_user` VALUES (1, 'yc', 'a', '1.gif', '2019-08-06 09:13:52', '男', 1);
INSERT INTO `bbs_user` VALUES (2, 'sjy', 'a', '2.gif', '2019-08-10 21:10:39', '男', 1);
INSERT INTO `bbs_user` VALUES (3, '李泽霖', '123456', '14.gif', '2019-08-11 16:23:05', '男', 1);
INSERT INTO `bbs_user` VALUES (6, '王五', '123456', '4.gif', '2019-08-16 09:33:40', '男', 1);
INSERT INTO `bbs_user` VALUES (7, '活动社', '123456', '10.gif', '2019-08-16 09:45:51', '男', 1);

-- ----------------------------
-- View structure for bbs_boardview
-- ----------------------------
DROP VIEW IF EXISTS `bbs_boardview`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `bbs_boardview` AS select `b`.`b_id` AS `b_id`,`b`.`b_name` AS `b_name`,`b`.`b_parentid` AS `b_parentid`,`t`.`t_content` AS `t_content`,`t`.`t_id` AS `t_id`,`t`.`t_title` AS `t_title`,`t`.`t_modifytime` AS `t_modifytime`,`t`.`t_publishtime` AS `t_publishtime`,`u`.`u_id` AS `u_id`,`u`.`u_sex` AS `u_sex`,`u`.`u_head` AS `u_head`,`u`.`u_name` AS `u_name`,`u`.`u_regtime` AS `u_regtime`,`u`.`u_state` AS `u_state` from ((`bbs_board` `b` join `bbs_user` `u`) join `bbs_topic` `t`) where ((`b`.`b_id` = `t`.`b_id`) and (`t`.`u_id` = `u`.`u_id`));

SET FOREIGN_KEY_CHECKS = 1;
