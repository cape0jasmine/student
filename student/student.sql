/*
Navicat MySQL Data Transfer

Source Server         : mysql57
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : student

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-02-23 11:33:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `college`
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college` (
  `id` int(2) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES ('2', '电子信息学院');
INSERT INTO `college` VALUES ('3', '工商管理学院');
INSERT INTO `college` VALUES ('4', '财经管理学院');
INSERT INTO `college` VALUES ('5', '汽车工程学院');

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cid` int(255) NOT NULL AUTO_INCREMENT,
  `cname` varchar(255) DEFAULT NULL,
  `cgrade` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '高等数学', '4');

-- ----------------------------
-- Table structure for `major`
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `id` int(2) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `academy_id` int(2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `major_college` (`academy_id`),
  CONSTRAINT `major_ibfk_1` FOREIGN KEY (`academy_id`) REFERENCES `college` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('8', '工业设计', '2');
INSERT INTO `major` VALUES ('9', '艺术', '3');
INSERT INTO `major` VALUES ('10', '光伏工程技术', '2');
INSERT INTO `major` VALUES ('11', '光伏发电技术与应用', '2');
INSERT INTO `major` VALUES ('12', '物联网应用技术', '2');
INSERT INTO `major` VALUES ('13', '计算机应用技术', '2');
INSERT INTO `major` VALUES ('14', '通讯技术', '2');
INSERT INTO `major` VALUES ('15', '电子信息工程技术', '2');
INSERT INTO `major` VALUES ('16', '物流管理', '3');
INSERT INTO `major` VALUES ('17', '电子商务', '3');
INSERT INTO `major` VALUES ('18', '市场营销', '3');
INSERT INTO `major` VALUES ('19', '国际贸易实务', '3');
INSERT INTO `major` VALUES ('20', '工商企业管理', '3');
INSERT INTO `major` VALUES ('21', '商务管理', '3');
INSERT INTO `major` VALUES ('22', '会计', '4');
INSERT INTO `major` VALUES ('23', '金融管理与实务', '4');
INSERT INTO `major` VALUES ('24', '财务管理', '4');
INSERT INTO `major` VALUES ('25', '酒店管理', '4');
INSERT INTO `major` VALUES ('26', '旅游管理', '4');
INSERT INTO `major` VALUES ('27', '食品营养与检测', '5');
INSERT INTO `major` VALUES ('28', '汽车技术服务与营销', '5');
INSERT INTO `major` VALUES ('29', '汽车检测与维修技术', '5');
INSERT INTO `major` VALUES ('30', '旅游管理', '3');
INSERT INTO `major` VALUES ('32', 'dd', '2');
INSERT INTO `major` VALUES ('33', '艺术', '3');
INSERT INTO `major` VALUES ('36', '市场营销', '3');

-- ----------------------------
-- Table structure for `score`
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` varchar(50) NOT NULL,
  `student_id` varchar(50) DEFAULT '' COMMENT '学生id',
  `subject` varchar(50) DEFAULT '' COMMENT '科目',
  `score` varchar(50) DEFAULT '' COMMENT '分数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('2', '1111', '高等数学', '100');
INSERT INTO `score` VALUES ('21171443824', '001', '高等数学', '40');
INSERT INTO `score` VALUES ('3', '2016', '高等数学', '90');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `academy` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT '123456',
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('电子信息学院', '电子信息工程技术', '001', '王五', '男', '123456', '11@null', null);
INSERT INTO `student` VALUES ('工商管理学院', '商务管理', '1111', '王五', '女', '123456', null, null);
INSERT INTO `student` VALUES ('计算机科学与技术', '计算机科学与技术', '2016', 'shen', '女', '123456', '2584903069@qq.com', null);

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `name` varchar(50) DEFAULT '',
  `sex` varchar(50) DEFAULT '',
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('admin', 'admin', '', '', '740873589@qq.com');
