/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50714
Source Host           : localhost:3306
Source Database       : graduation_project_management

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2017-03-23 13:40:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for answer_group
-- ----------------------------
DROP TABLE IF EXISTS `answer_group`;
CREATE TABLE `answer_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `leader_id` int(11) NOT NULL,
  `year` varchar(5) NOT NULL,
  `student_ids` varchar(300) DEFAULT NULL,
  `teacher_ids` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answer_group
-- ----------------------------
INSERT INTO `answer_group` VALUES ('1', '2', '2013', '5,7,', '3,4,');

-- ----------------------------
-- Table structure for answer_group_member
-- ----------------------------
DROP TABLE IF EXISTS `answer_group_member`;
CREATE TABLE `answer_group_member` (
  `group_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  `year` varchar(5) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answer_group_member
-- ----------------------------

-- ----------------------------
-- Table structure for answer_group_student
-- ----------------------------
DROP TABLE IF EXISTS `answer_group_student`;
CREATE TABLE `answer_group_student` (
  `group_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `year` varchar(5) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of answer_group_student
-- ----------------------------

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `class_id` varchar(200) NOT NULL,
  `major_id` int(11) NOT NULL,
  `department_no` int(11) NOT NULL,
  `count` int(11) DEFAULT '0',
  PRIMARY KEY (`id`,`class_id`),
  UNIQUE KEY `classID` (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES ('1', '1406102', '1', '6', '4');
INSERT INTO `classes` VALUES ('3', '1406101', '1', '6', '2');
INSERT INTO `classes` VALUES ('4', '1406401', '2', '6', '0');
INSERT INTO `classes` VALUES ('5', '1406402', '2', '6', '0');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `d_no` int(5) NOT NULL,
  `name` varchar(100) NOT NULL,
  `count` int(11) NOT NULL DEFAULT '0',
  `sort` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`id`,`d_no`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '1', '建筑与城市规划学院', '1', '1');
INSERT INTO `department` VALUES ('2', '2', '市政与测绘工程学院', '0', '2');
INSERT INTO `department` VALUES ('3', '3', '土木工程学院', '0', '3');
INSERT INTO `department` VALUES ('4', '4', '城市管理学院', '0', '4');
INSERT INTO `department` VALUES ('5', '5', '外国语学院', '0', '5');
INSERT INTO `department` VALUES ('6', '6', '信息与电子工程学院', '5', '6');
INSERT INTO `department` VALUES ('7', '7', '美术与艺术设计学院', '0', '7');
INSERT INTO `department` VALUES ('8', '8', '化学与环境工程学院', '0', '8');
INSERT INTO `department` VALUES ('9', '9', '数学与计算机科学学院', '0', '9');
INSERT INTO `department` VALUES ('10', '10', '体育学院', '0', '10');
INSERT INTO `department` VALUES ('11', '11', '通信与电子工程学院', '0', '11');
INSERT INTO `department` VALUES ('12', '12', '音乐学院', '0', '12');
INSERT INTO `department` VALUES ('13', '13', '文学院', '0', '13');
INSERT INTO `department` VALUES ('14', '14', '商学院', '0', '14');
INSERT INTO `department` VALUES ('15', '15', '机电工程学院', '0', '15');
INSERT INTO `department` VALUES ('16', '16', '马克思学院', '0', null);

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` decimal(10,0) NOT NULL COMMENT '排序（升序）',
  `parent_id` varchar(64) DEFAULT '0' COMMENT '父级编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dict
-- ----------------------------
INSERT INTO `dict` VALUES ('1', '0', '正常', 'del_flag', '删除标记', '10', '0');
INSERT INTO `dict` VALUES ('2', '1', '删除', 'del_flag', '删除标记', '20', '0');
INSERT INTO `dict` VALUES ('3', '1', '显示', 'show_hide', '显示/隐藏', '10', '0');
INSERT INTO `dict` VALUES ('4', '0', '隐藏', 'show_hide', '显示/隐藏', '20', '0');
INSERT INTO `dict` VALUES ('5', '0', '未上传', 'upload', '未上传/已上传', '10', '0');
INSERT INTO `dict` VALUES ('6', '1', '已上传', 'upload', '未上传/已上传', '20', '0');
INSERT INTO `dict` VALUES ('7', '0', '男', 'sex', '性别', '10', '0');
INSERT INTO `dict` VALUES ('8', '1', '女', 'sex', '性别', '20', '0');
INSERT INTO `dict` VALUES ('9', '1', '教授', 'professional', '职称', '10', '0');
INSERT INTO `dict` VALUES ('10', '2', '副教授', 'professional', '职称', '20', '0');
INSERT INTO `dict` VALUES ('11', '1', '简单', 'level', '难度', '10', '0');
INSERT INTO `dict` VALUES ('12', '2', '中等', 'level', '难度', '20', '0');
INSERT INTO `dict` VALUES ('13', '3', '困难', 'level', '难度', '30', '0');
INSERT INTO `dict` VALUES ('14', '1', 'javaweb开发', 'kind', '类别', '10', '0');
INSERT INTO `dict` VALUES ('15', '2', '安卓开发', 'kind', '类别', '20', '0');
INSERT INTO `dict` VALUES ('16', '0', '未选择', 'state', '状态', '10', '0');
INSERT INTO `dict` VALUES ('17', '1', '已选择', 'state', '状态', '20', '0');
INSERT INTO `dict` VALUES ('18', '0', '未上传', 'schedule', '进度计划表状态', '10', '0');
INSERT INTO `dict` VALUES ('19', '1', '正在审查', 'schedule', '进度计划表状态', '20', '0');
INSERT INTO `dict` VALUES ('20', '2', '未通过', 'schedule', '进度计划表状态', '30', '0');
INSERT INTO `dict` VALUES ('21', '3', '通过', 'schedule', '进度计划表状态', '40', '0');
INSERT INTO `dict` VALUES ('22', '0', '未审核', 'understanding', '审题报告状态', '10', '0');
INSERT INTO `dict` VALUES ('23', '1', '未通过', 'understanding', '审题报告状态', '20', '0');
INSERT INTO `dict` VALUES ('24', '2', '通过', 'understanding', '审题报告状态', '30', '0');
INSERT INTO `dict` VALUES ('25', '0', '未批阅', 'marking', '批阅状态', '10', '0');
INSERT INTO `dict` VALUES ('26', '1', '已批阅', 'marking', '批阅状态', '20', '0');
INSERT INTO `dict` VALUES ('27', '0', '未评阅', 'review', '评阅状态', '10', '0');
INSERT INTO `dict` VALUES ('28', '1', '未通过', 'review', '评阅状态', '20', '0');
INSERT INTO `dict` VALUES ('29', '2', '通过', 'review', '评阅状态', '30', '0');
INSERT INTO `dict` VALUES ('30', '0', '未通过', 'report', '成绩状态', '10', '0');
INSERT INTO `dict` VALUES ('31', '1', '通过', 'report', '成绩状态', '20', '0');
INSERT INTO `dict` VALUES ('32', '0', '未开始', 'step', '步骤状态', '10', '0');
INSERT INTO `dict` VALUES ('33', '1', '正在进行', 'step', '步骤状态', '20', '0');
INSERT INTO `dict` VALUES ('34', '2', '已完成', 'step', '步骤状态', '30', '0');

-- ----------------------------
-- Table structure for download
-- ----------------------------
DROP TABLE IF EXISTS `download`;
CREATE TABLE `download` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `issuer` int(11) NOT NULL,
  `issueDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `description` varchar(200) DEFAULT NULL,
  `receive` varchar(200) NOT NULL COMMENT '1系统管理员2教务秘书3教研室主任4教师5学生',
  `uploadFile` varchar(200) NOT NULL,
  `uploadPath` varchar(500) NOT NULL,
  `uploadFileOldName` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of download
-- ----------------------------
INSERT INTO `download` VALUES ('1', 'test', '1', '2017-01-19 15:36:13', '测试', '5', '1484782609575QQ截图20161206013930.png', 'E://gpmsUpload/admin/1484782609575QQ截图20161206013930.png', 'QQ截图20161206013930.png');
INSERT INTO `download` VALUES ('2', 'test', '1', '2017-01-19 15:37:37', '测试', '2', '1484782654010QQ截图20170105012756.png', 'E://gpmsUpload/admin/1484782654010QQ截图20170105012756.png', 'QQ截图20170105012756.png');
INSERT INTO `download` VALUES ('3', 'test', '1', '2017-01-19 15:37:44', '测试', '2', '1484783949988QQ截图20161222110826.png', 'E://gpmsUpload/admin/1484783949988QQ截图20161222110826.png', 'QQ截图20161222110826.png');
INSERT INTO `download` VALUES ('4', 'test', '1', '2017-01-19 15:37:48', '测试', '4', '1484784740655QQ截图20170105012353.png', 'E://gpmsUpload/admin/1484784740655QQ截图20170105012353.png', 'QQ截图20170105012353.png');
INSERT INTO `download` VALUES ('5', 'test', '1', '2017-01-19 15:37:55', '测试', '4', '1484784954294QQ截图20170105012353.png', 'E://gpmsUpload/admin/1484784954294QQ截图20170105012353.png', 'QQ截图20170105012353.png');
INSERT INTO `download` VALUES ('6', 'test', '1', '2017-01-19 15:38:01', '测试', '4', '1484785269488QQ截图20170105012353.png', 'E://gpmsUpload/admin/1484785269488QQ截图20170105012353.png', 'QQ截图20170105012353.png');
INSERT INTO `download` VALUES ('9', '图片', '1', '2017-01-19 16:20:31', '测试', '2', '1484814031208QQ截图20170105012353.png', 'E://gpmsUpload/admin/1484814031208QQ截图20170105012353.png', 'QQ截图20170105012353.png');
INSERT INTO `download` VALUES ('10', '图片', '1', '2017-01-19 16:22:23', '测试', '4', '1484814143830QQ截图20170105012353.png', 'E://gpmsUpload/admin/1484814143830QQ截图20170105012353.png', 'QQ截图20170105012353.png');
INSERT INTO `download` VALUES ('11', '图片', '1', '2017-01-19 16:35:31', '测试', '4', '1484814931987QQ截图20170105012353.png', 'E://gpmsUpload/admin/1484814931987QQ截图20170105012353.png', 'QQ截图20170105012353.png');
INSERT INTO `download` VALUES ('12', '图片', '1', '2017-01-19 16:37:54', '未上传/已上传', '3', '1484815074857QQ截图20170105012353.png', 'E://gpmsUpload/admin/1484815074857QQ截图20170105012353.png', 'QQ截图20170105012353.png');
INSERT INTO `download` VALUES ('13', '图片', '1', '2017-01-19 16:38:50', '测试', '3', '1484815130467QQ截图20170105012353.png', 'E://gpmsUpload/admin/1484815130467QQ截图20170105012353.png', 'QQ截图20170105012353.png');
INSERT INTO `download` VALUES ('15', '图片', '1', '2017-01-20 07:25:08', '测试', '4,5', '1484868294696QQ截图20170105012353.png', 'E://gpmsUpload/admin/1484868294696QQ截图20170105012353.png', 'QQ截图20170105012353.png');
INSERT INTO `download` VALUES ('16', '图片', '1', '2017-01-20 08:23:00', '测试', '4,5', '1484871779847QQ截图20161222110826.png', 'E://gpmsUpload/admin/1484871779847QQ截图20161222110826.png', 'QQ截图20161222110826.png');
INSERT INTO `download` VALUES ('17', '图片', '1', '2017-01-20 08:26:32', '测试', '3,4,5', '14848719926812016年项目清单.xlsx', 'E://gpmsUpload/admin/14848719926812016年项目清单.xlsx', '2016年项目清单.xlsx');
INSERT INTO `download` VALUES ('18', '教务秘书资料上传测试', '2', '2017-03-22 21:03:25', '教务秘书资料上传测试', '5', '1490187805370icheck.zip', 'E://gpmsUpload/0001/1490187805370icheck.zip', 'icheck.zip');

-- ----------------------------
-- Table structure for expand_student
-- ----------------------------
DROP TABLE IF EXISTS `expand_student`;
CREATE TABLE `expand_student` (
  `s_id` int(11) NOT NULL,
  `node` varchar(15) NOT NULL,
  `department_no` int(11) NOT NULL,
  `major_id` int(11) NOT NULL,
  `classes` int(11) NOT NULL,
  `title_id` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `review_tId` int(11) DEFAULT NULL COMMENT '评阅教师id',
  `year` varchar(5) NOT NULL,
  `grade` varchar(5) DEFAULT NULL,
  `step_1` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否完成（0未开始1审查2通过）',
  `step_2` tinyint(1) NOT NULL DEFAULT '0',
  `step_3` tinyint(1) NOT NULL DEFAULT '0',
  `step_4` tinyint(1) NOT NULL DEFAULT '0',
  `step_5` tinyint(1) NOT NULL DEFAULT '0',
  `step_6` tinyint(1) NOT NULL DEFAULT '0',
  `step_7` tinyint(1) NOT NULL DEFAULT '0',
  `step_8` tinyint(1) NOT NULL DEFAULT '0',
  `step_9` tinyint(1) NOT NULL DEFAULT '0',
  `step_10` tinyint(1) NOT NULL DEFAULT '0',
  `step_now` tinyint(1) NOT NULL DEFAULT '1' COMMENT '当前步骤',
  `schedule_num` tinyint(1) DEFAULT NULL COMMENT '设计进度计划数量',
  `answer_id` int(11) DEFAULT NULL COMMENT '答辩组id',
  `answer_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '答辩分组（0未分组1已分组）',
  `expand_flag` tinyint(1) NOT NULL DEFAULT '1' COMMENT '学生拓展标识',
  `school_report_id` tinyint(1) DEFAULT NULL COMMENT '成绩单id',
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of expand_student
-- ----------------------------
INSERT INTO `expand_student` VALUES ('5', '2014051656', '6', '1', '1406102', '1', '2', '2', '2013', null, '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '10', '3', '1', '0', '1', '1');
INSERT INTO `expand_student` VALUES ('7', '2014051654', '6', '1', '1406102', '2', '3', '3', '2013', null, '2', '2', '2', '2', '2', '2', '2', '2', '0', '0', '9', '3', '1', '0', '1', null);
INSERT INTO `expand_student` VALUES ('8', '2014051655', '6', '1', '1406102', '3', '2', '2', '2013', null, '2', '2', '2', '2', '2', '2', '0', '0', '0', '0', '7', null, null, '0', '1', null);
INSERT INTO `expand_student` VALUES ('11', '2014051629', '6', '1', '1406101', '7', '2', '2', '2013', null, '2', '2', '2', '2', '2', '2', '2', '2', '0', '0', '9', '3', null, '0', '1', null);

-- ----------------------------
-- Table structure for expand_teacher
-- ----------------------------
DROP TABLE IF EXISTS `expand_teacher`;
CREATE TABLE `expand_teacher` (
  `t_id` int(11) NOT NULL,
  `node` varchar(15) NOT NULL,
  `department_no` int(11) NOT NULL,
  `research_direction` varchar(30) NOT NULL,
  `professional_title` tinyint(1) NOT NULL,
  `expand_flag` tinyint(1) NOT NULL DEFAULT '2' COMMENT '教师拓展标识',
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of expand_teacher
-- ----------------------------
INSERT INTO `expand_teacher` VALUES ('2', '0001', '6', 'WEB开发', '1', '2');
INSERT INTO `expand_teacher` VALUES ('3', '0002', '6', '安卓开发', '1', '2');
INSERT INTO `expand_teacher` VALUES ('4', '0003', '6', '软件开发', '1', '2');
INSERT INTO `expand_teacher` VALUES ('10', '0004', '6', '嵌入式', '1', '2');

-- ----------------------------
-- Table structure for graduation_schedule
-- ----------------------------
DROP TABLE IF EXISTS `graduation_schedule`;
CREATE TABLE `graduation_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL COMMENT '学生ID',
  `sort` varchar(3) NOT NULL COMMENT '排序',
  `content` varchar(50) NOT NULL COMMENT '内容',
  `start_date` datetime NOT NULL COMMENT '开始时间',
  `end_date` datetime NOT NULL COMMENT '截止时间',
  `report_id` int(11) NOT NULL COMMENT '报告id',
  `report_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否报告通过（0未上传1审查2未通过3通过）',
  `uploadFile` varchar(200) DEFAULT NULL,
  `uploadPath` varchar(500) DEFAULT NULL,
  `uploadFileOldName` varchar(200) DEFAULT NULL,
  `teacher_id` int(11) NOT NULL,
  `teacher_advise` text COMMENT '教师意见',
  `year` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of graduation_schedule
-- ----------------------------
INSERT INTO `graduation_schedule` VALUES ('1', '5', '1', '需求分析', '2017-02-26 11:08:06', '2017-02-23 11:08:09', '1', '3', '1488198440515需求分析报告 （改）.doc', 'E://gpmsUpload/2014051656/1488198440515需求分析报告 （改）.doc', '需求分析报告 （改）.doc', '2', null, '2013');
INSERT INTO `graduation_schedule` VALUES ('2', '5', '2', '系统设计', '2017-03-01 11:11:15', '2017-03-03 11:11:26', '1', '3', '1488198488727系统设计报告.doc', 'E://gpmsUpload/2014051656/1488198488727系统设计报告.doc', '系统设计报告.doc', '2', null, '2013');
INSERT INTO `graduation_schedule` VALUES ('3', '5', '3', '系统测试', '2017-03-04 11:13:12', '2017-03-06 11:13:21', '1', '3', '1488198660804系统测试报告.doc', 'E://gpmsUpload/2014051656/1488198660804系统测试报告.doc', '系统测试报告.doc', '2', null, '2013');
INSERT INTO `graduation_schedule` VALUES ('4', '7', '1', '1', '2017-01-01 00:00:00', '2017-02-02 00:00:00', '6', '3', '1488269236660需求分析报告.doc', 'E://gpmsUpload/2014051654/1488269236660需求分析报告.doc', '需求分析报告.doc', '3', null, '2013');
INSERT INTO `graduation_schedule` VALUES ('5', '7', '2', '2', '2017-03-01 00:00:00', '2017-04-01 00:00:00', '6', '3', '1488304655460系统设计报告.doc', 'E://gpmsUpload/2014051654/1488304655460系统设计报告.doc', '系统设计报告.doc', '3', null, '2013');
INSERT INTO `graduation_schedule` VALUES ('6', '7', '3', '3', '2017-05-01 00:00:00', '2017-06-01 00:00:00', '6', '3', '1488304674047系统测试报告.doc', 'E://gpmsUpload/2014051654/1488304674047系统测试报告.doc', '系统测试报告.doc', '3', null, '2013');
INSERT INTO `graduation_schedule` VALUES ('7', '8', '1', '需求分析', '2017-01-01 00:00:00', '2017-02-01 00:00:00', '7', '3', '1488465551819需求分析报告.doc', 'E://gpmsUpload/2014051655/1488465551819需求分析报告.doc', '需求分析报告.doc', '2', '<font size=\"5\">测试啊啊啊啊啊啊啊啊啊啊啊</font>', '2013');
INSERT INTO `graduation_schedule` VALUES ('8', '8', '2', '系统设计', '2017-03-01 00:00:00', '2017-04-01 00:00:00', '7', '3', '1488465551819需求分析报告.doc', 'E://gpmsUpload/2014051655/1488465551819需求分析报告.doc', '需求分析报告.doc', '2', '测试', '2013');
INSERT INTO `graduation_schedule` VALUES ('9', '11', '1', '需求分析', '2017-03-15 00:00:00', '2017-04-15 00:00:00', '8', '3', '1489577059523需求分析报告.doc', 'E://gpmsUpload/2014051629/1489577059523需求分析报告.doc', '需求分析报告.doc', '2', '<br>', '2013');
INSERT INTO `graduation_schedule` VALUES ('10', '11', '2', '系统设计', '2017-04-16 00:00:00', '2017-05-16 00:00:00', '8', '3', '1489577152894系统设计报告.doc', 'E://gpmsUpload/2014051629/1489577152894系统设计报告.doc', '系统设计报告.doc', '2', '通过', '2013');
INSERT INTO `graduation_schedule` VALUES ('11', '11', '3', '系统测试', '2017-05-17 00:00:00', '2017-06-17 00:00:00', '8', '3', '1489577190744系统测试报告.doc', 'E://gpmsUpload/2014051629/1489577190744系统测试报告.doc', '系统测试报告.doc', '2', '测试', '2013');

-- ----------------------------
-- Table structure for graduation_title
-- ----------------------------
DROP TABLE IF EXISTS `graduation_title`;
CREATE TABLE `graduation_title` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL COMMENT '课题名称',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `level` tinyint(1) NOT NULL COMMENT '难度',
  `kind` tinyint(1) NOT NULL COMMENT '种类',
  `teacher_id` int(11) NOT NULL COMMENT '教师id',
  `year` varchar(5) NOT NULL COMMENT '级数',
  `select_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否选择（0未选1已选）',
  `student_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of graduation_title
-- ----------------------------
INSERT INTO `graduation_title` VALUES ('1', '基于java的毕业设计管理系统', '采用spring等主流框架', '3', '2', '2', '2013', '1', '5');
INSERT INTO `graduation_title` VALUES ('2', '基于java的XXXX系统开发', '测试', '1', '1', '3', '2013', '1', '7');
INSERT INTO `graduation_title` VALUES ('3', '基于XXXXX', '测试', '1', '2', '2', '2013', '1', '8');
INSERT INTO `graduation_title` VALUES ('7', '基于spring的毕业设计管理系统', 'javase', '3', '1', '2', '2013', '1', '11');

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `sort` decimal(10,0) DEFAULT NULL,
  `department_no` int(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('1', '网络工程', '1', '6');
INSERT INTO `major` VALUES ('2', '软件工程', '2', '6');
INSERT INTO `major` VALUES ('3', '信息管理与信息系统', '3', '6');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `parent_id` int(32) NOT NULL,
  `parent_ids` varchar(200) DEFAULT NULL,
  `name` varchar(30) NOT NULL,
  `sort` decimal(10,0) DEFAULT NULL,
  `href` varchar(100) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `is_show` char(1) NOT NULL DEFAULT '0' COMMENT '是否显示（0显示1不显示）',
  `permission` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------

-- ----------------------------
-- Table structure for middle_check
-- ----------------------------
DROP TABLE IF EXISTS `middle_check`;
CREATE TABLE `middle_check` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `upload_date` datetime NOT NULL,
  `year` varchar(5) NOT NULL,
  `title_id` int(11) NOT NULL,
  `state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '中期检查状态（0未上传1正在审查2未通过3通过）',
  `uploadFile` varchar(200) NOT NULL,
  `uploadPath` varchar(500) NOT NULL,
  `uploadFileOldName` varchar(200) NOT NULL,
  `param_id` int(11) DEFAULT NULL,
  `teacher_id` int(11) NOT NULL,
  `teacher_advise` text COMMENT '教师意见',
  `check_id` int(11) DEFAULT NULL COMMENT '审核教师id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of middle_check
-- ----------------------------
INSERT INTO `middle_check` VALUES ('1', '5', '2017-03-01 02:08:56', '2013', '1', '3', '1488305336525论文一稿.doc', 'E://gpmsUpload/2014051656/1488305336525论文一稿.doc', '论文一稿.doc', '1', '2', null, null);
INSERT INTO `middle_check` VALUES ('2', '5', '2017-03-01 00:01:56', '2013', '1', '1', '1488297716256中期检查.doc', 'E://gpmsUpload/2014051656/1488297716256中期检查.doc', '中期检查.doc', '2', '2', null, null);
INSERT INTO `middle_check` VALUES ('3', '5', '2017-03-01 00:20:03', '2013', '1', '1', '1488298803650学生工作处.zip', 'E://gpmsUpload/2014051656/1488298803650学生工作处.zip', '学生工作处.zip', '3', '2', null, null);
INSERT INTO `middle_check` VALUES ('4', '7', '2017-03-01 01:59:35', '2013', '2', '1', '1488304775609论文一稿.doc', 'E://gpmsUpload/2014051654/1488304775609论文一稿.doc', '论文一稿.doc', '1', '3', null, null);
INSERT INTO `middle_check` VALUES ('5', '7', '2017-03-01 11:59:08', '2013', '2', '1', '1488340748200学生工作处.zip', 'E://gpmsUpload/2014051654/1488340748200学生工作处.zip', '学生工作处.zip', '2', '3', null, null);
INSERT INTO `middle_check` VALUES ('6', '7', '2017-03-01 11:57:49', '2013', '2', '3', '1488340669941xsgz.zip', 'E://gpmsUpload/2014051654/1488340669941xsgz.zip', 'xsgz.zip', '3', '3', null, null);
INSERT INTO `middle_check` VALUES ('7', '11', '2017-03-15 20:44:04', '2013', '7', '3', '1489581844069论文一稿.doc', 'E://gpmsUpload/2014051629/1489581844069论文一稿.doc', '论文一稿.doc', '1', '2', '中期检查测试1', '2');
INSERT INTO `middle_check` VALUES ('8', '11', '2017-03-22 15:39:45', '2013', '7', '3', '1490168385325xsgz.zip', 'E://gpmsUpload/2014051629/1490168385325xsgz.zip', 'xsgz.zip', '2', '2', '通过', '2');
INSERT INTO `middle_check` VALUES ('9', '11', '2017-03-15 20:44:19', '2013', '7', '3', '1489581859581icheck.zip', 'E://gpmsUpload/2014051629/1489581859581icheck.zip', 'icheck.zip', '3', '2', '通过', '2');

-- ----------------------------
-- Table structure for middle_check_parameter
-- ----------------------------
DROP TABLE IF EXISTS `middle_check_parameter`;
CREATE TABLE `middle_check_parameter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(200) NOT NULL,
  `suffix` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of middle_check_parameter
-- ----------------------------
INSERT INTO `middle_check_parameter` VALUES ('1', '论文', '论文报告', 'Doc');
INSERT INTO `middle_check_parameter` VALUES ('2', '源程序', '系统源程序，打包ZIP格式', 'Zip');
INSERT INTO `middle_check_parameter` VALUES ('3', '相关文件', '毕设相关文件，打包ZIP格式', 'Zip');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL,
  `title` varchar(100) NOT NULL,
  `content` varchar(200) NOT NULL,
  `issue_id` int(11) NOT NULL,
  `receive _ids` varchar(2000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------

-- ----------------------------
-- Table structure for opening_report
-- ----------------------------
DROP TABLE IF EXISTS `opening_report`;
CREATE TABLE `opening_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `upload_date` datetime NOT NULL,
  `year` varchar(5) NOT NULL,
  `title_id` int(11) NOT NULL,
  `report_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否报告通过（0审查1未通过2通过）',
  `uploadFile` varchar(200) NOT NULL,
  `uploadPath` varchar(500) NOT NULL,
  `uploadFileOldName` varchar(200) NOT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `teacher_advise` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of opening_report
-- ----------------------------
INSERT INTO `opening_report` VALUES ('1', '5', '2017-02-26 11:06:33', '2013', '1', '2', '1487927826423基于SpringMVC的汽车销售管理系统的设计与实现毕业论文.doc', 'E://gpmsUpload/2014051656/1487927826423基于SpringMVC的汽车销售管理系统的设计与实现毕业论文.doc', '基于SpringMVC的汽车销售管理系统的设计与实现毕业论文.doc', '2', null);
INSERT INTO `opening_report` VALUES ('6', '7', '2017-02-28 16:05:45', '2013', '2', '1', '1488269145404开题报告.doc', 'E://gpmsUpload/2014051654/1488269145404开题报告.doc', '开题报告.doc', '3', null);
INSERT INTO `opening_report` VALUES ('7', '8', '2017-03-02 22:36:04', '2013', '3', '2', '1488465364222需求分析报告.doc', 'E://gpmsUpload/2014051655/1488465364222需求分析报告.doc', '需求分析报告.doc', '2', '<div style=\"text-align: center;\"><span style=\"letter-spacing: 0px;\"><font size=\"5\">1、测试一</font></span></div><div style=\"text-align: left;\"><span style=\"letter-spacing: 0px;\"><font size=\"5\">2、测试二</font></span></div>');
INSERT INTO `opening_report` VALUES ('8', '11', '2017-03-15 19:23:17', '2013', '7', '2', '1489576997735开题报告.doc', 'E://gpmsUpload/2014051629/1489576997735开题报告.doc', '开题报告.doc', '2', '恭喜，开题报告通过');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `permission` varchar(255) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'admin', '1');
INSERT INTO `permission` VALUES ('2', 'secretary', '2');
INSERT INTO `permission` VALUES ('3', 'director', '3');
INSERT INTO `permission` VALUES ('4', 'teacher', '4');
INSERT INTO `permission` VALUES ('5', 'student', '5');
INSERT INTO `permission` VALUES ('6', 'teacher', '2');
INSERT INTO `permission` VALUES ('7', 'teacher', '3');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `count` int(11) NOT NULL DEFAULT '0',
  `role_name` varchar(30) NOT NULL,
  `data_scope` varchar(200) DEFAULT NULL COMMENT '数据范围',
  PRIMARY KEY (`id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '系统管理员', '拥有至高无上的权利,操作系统的所有权限', '1', 'admin', '');
INSERT INTO `role` VALUES ('2', '教务秘书', '教务秘书', '0', 'secretary', '');
INSERT INTO `role` VALUES ('3', '教研室主任', '教研室主任', '0', 'director', '');
INSERT INTO `role` VALUES ('4', '教师', '教师', '0', 'teacher', null);
INSERT INTO `role` VALUES ('5', '学生', '学生', '0', 'student', null);
INSERT INTO `role` VALUES ('6', '评阅教师', '评阅教师', '0', 'review', null);

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `role_id` int(32) NOT NULL,
  `menu_id` int(32) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1');
INSERT INTO `role_menu` VALUES ('1', '2');
INSERT INTO `role_menu` VALUES ('1', '3');
INSERT INTO `role_menu` VALUES ('1', '4');
INSERT INTO `role_menu` VALUES ('1', '5');
INSERT INTO `role_menu` VALUES ('1', '7');
INSERT INTO `role_menu` VALUES ('1', '8');
INSERT INTO `role_menu` VALUES ('1', '9');
INSERT INTO `role_menu` VALUES ('1', '10');
INSERT INTO `role_menu` VALUES ('1', '11');
INSERT INTO `role_menu` VALUES ('1', '12');
INSERT INTO `role_menu` VALUES ('1', '13');
INSERT INTO `role_menu` VALUES ('1', '14');
INSERT INTO `role_menu` VALUES ('1', '15');
INSERT INTO `role_menu` VALUES ('1', '16');
INSERT INTO `role_menu` VALUES ('1', '17');
INSERT INTO `role_menu` VALUES ('1', '18');
INSERT INTO `role_menu` VALUES ('1', '19');
INSERT INTO `role_menu` VALUES ('1', '20');
INSERT INTO `role_menu` VALUES ('1', '21');
INSERT INTO `role_menu` VALUES ('1', '22');
INSERT INTO `role_menu` VALUES ('1', '23');
INSERT INTO `role_menu` VALUES ('1', '24');
INSERT INTO `role_menu` VALUES ('1', '25');
INSERT INTO `role_menu` VALUES ('1', '26');
INSERT INTO `role_menu` VALUES ('1', '27');
INSERT INTO `role_menu` VALUES ('1', '28');
INSERT INTO `role_menu` VALUES ('1', '29');
INSERT INTO `role_menu` VALUES ('1', '30');
INSERT INTO `role_menu` VALUES ('1', '31');
INSERT INTO `role_menu` VALUES ('1', '32');
INSERT INTO `role_menu` VALUES ('1', '33');
INSERT INTO `role_menu` VALUES ('1', '34');
INSERT INTO `role_menu` VALUES ('1', '35');
INSERT INTO `role_menu` VALUES ('1', '36');
INSERT INTO `role_menu` VALUES ('1', '37');
INSERT INTO `role_menu` VALUES ('1', '38');
INSERT INTO `role_menu` VALUES ('1', '39');
INSERT INTO `role_menu` VALUES ('1', '40');
INSERT INTO `role_menu` VALUES ('1', '41');
INSERT INTO `role_menu` VALUES ('1', '42');
INSERT INTO `role_menu` VALUES ('1', '43');
INSERT INTO `role_menu` VALUES ('1', '44');
INSERT INTO `role_menu` VALUES ('1', '45');
INSERT INTO `role_menu` VALUES ('1', '46');
INSERT INTO `role_menu` VALUES ('1', '47');
INSERT INTO `role_menu` VALUES ('1', '48');
INSERT INTO `role_menu` VALUES ('1', '49');
INSERT INTO `role_menu` VALUES ('1', '50');
INSERT INTO `role_menu` VALUES ('1', '51');
INSERT INTO `role_menu` VALUES ('1', '52');
INSERT INTO `role_menu` VALUES ('2', '1');
INSERT INTO `role_menu` VALUES ('2', '2');
INSERT INTO `role_menu` VALUES ('2', '3');
INSERT INTO `role_menu` VALUES ('2', '4');
INSERT INTO `role_menu` VALUES ('2', '5');
INSERT INTO `role_menu` VALUES ('2', '6');
INSERT INTO `role_menu` VALUES ('2', '8');
INSERT INTO `role_menu` VALUES ('2', '9');
INSERT INTO `role_menu` VALUES ('2', '11');
INSERT INTO `role_menu` VALUES ('2', '12');
INSERT INTO `role_menu` VALUES ('2', '13');
INSERT INTO `role_menu` VALUES ('2', '15');
INSERT INTO `role_menu` VALUES ('2', '16');
INSERT INTO `role_menu` VALUES ('2', '18');
INSERT INTO `role_menu` VALUES ('2', '21');
INSERT INTO `role_menu` VALUES ('2', '22');
INSERT INTO `role_menu` VALUES ('2', '23');
INSERT INTO `role_menu` VALUES ('2', '24');
INSERT INTO `role_menu` VALUES ('2', '27');
INSERT INTO `role_menu` VALUES ('2', '28');
INSERT INTO `role_menu` VALUES ('2', '29');
INSERT INTO `role_menu` VALUES ('2', '31');
INSERT INTO `role_menu` VALUES ('2', '32');
INSERT INTO `role_menu` VALUES ('2', '34');
INSERT INTO `role_menu` VALUES ('2', '35');
INSERT INTO `role_menu` VALUES ('2', '37');
INSERT INTO `role_menu` VALUES ('2', '38');
INSERT INTO `role_menu` VALUES ('2', '40');
INSERT INTO `role_menu` VALUES ('2', '41');
INSERT INTO `role_menu` VALUES ('2', '43');

-- ----------------------------
-- Table structure for school_report
-- ----------------------------
DROP TABLE IF EXISTS `school_report`;
CREATE TABLE `school_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `report_id` varchar(20) NOT NULL,
  `student_id` int(11) NOT NULL,
  `score` varchar(3) NOT NULL,
  `pass_flag` tinyint(1) NOT NULL COMMENT '0未通过1通过',
  `date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of school_report
-- ----------------------------
INSERT INTO `school_report` VALUES ('1', '201405165620170302', '5', '95', '0', '2017-03-02 12:14:22');

-- ----------------------------
-- Table structure for system_parameter
-- ----------------------------
DROP TABLE IF EXISTS `system_parameter`;
CREATE TABLE `system_parameter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `param_key` varchar(30) NOT NULL,
  `label` varchar(30) NOT NULL,
  `param_value` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_parameter
-- ----------------------------
INSERT INTO `system_parameter` VALUES ('1', 'year', '当前年级', '2013');
INSERT INTO `system_parameter` VALUES ('2', 'department', '启用学院', '信息与电子工程学院');
INSERT INTO `system_parameter` VALUES ('3', 'version', '系统版本', 'v1.1');
INSERT INTO `system_parameter` VALUES ('4', 'maxTeacherNum', '学生选择老师最大数量', '8');
INSERT INTO `system_parameter` VALUES ('5', 'reviewDate', '评阅时间', '2017-03-01');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL COMMENT '姓名',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `idcard` varchar(20) NOT NULL COMMENT '身份证',
  `mobile` varchar(13) DEFAULT NULL COMMENT '手机',
  `address` varchar(50) NOT NULL COMMENT '地址',
  `sex` tinyint(1) NOT NULL COMMENT '性别（0男1女）',
  `age` tinyint(3) NOT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `is_validate` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否验证邮箱（0否1是）',
  `qq` varchar(15) DEFAULT NULL COMMENT 'qq',
  `expand_flag` tinyint(1) NOT NULL COMMENT '拓展标识0管理员1学生2教师',
  `header_pic` varchar(30) DEFAULT NULL,
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` int(11) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0不删除1删除）',
  `sysYear_paramId` tinyint(1) NOT NULL DEFAULT '1' COMMENT '系统年级参数ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '系统管理员', 'admin', '3c9e87237581d165b5f139bf901bd778296f8becb8268e363fe2dbbe', '0', '0', '湖南益阳', '0', '0', '0', '0', '0', '0', 'img/man.jpg', '1', '2016-12-23 16:39:19', '1', '2016-12-23 16:39:15', '', '0', '1');
INSERT INTO `sys_user` VALUES ('2', '周建存', '0001', '3c9e87237581d165b5f139bf901bd778296f8becb8268e363fe2dbbe', '0', '18670050440', '湖南益阳', '0', '40', '123456@qq.com', '0', '123456', '2', 'img/man.jpg', null, '2016-12-23 16:41:26', '1', '2017-02-22 11:41:55', null, '0', '1');
INSERT INTO `sys_user` VALUES ('3', '小明', '0002', '3c9e87237581d165b5f139bf901bd778296f8becb8268e363fe2dbbe', '0', '0', '湖南益阳', '0', '30', '222222@qq.com', '0', '222222', '2', 'img/man.jpg', null, '2016-12-23 16:42:24', null, null, null, '0', '1');
INSERT INTO `sys_user` VALUES ('4', '小红', '0003', '3c9e87237581d165b5f139bf901bd778296f8becb8268e363fe2dbbe', '0', '0', '湖南益阳', '1', '30', '333333@qq.com', '0', '333333', '2', 'img/woman.jpg', null, '2016-12-23 16:43:14', null, null, null, '0', '1');
INSERT INTO `sys_user` VALUES ('5', '刘佳乐', '2014051656', '3c9e87237581d165b5f139bf901bd778296f8becb8268e363fe2dbbe', '43012419403116559', '18670050440', '湖南长沙', '0', '22', '394702110@qq.com', '0', '394702110', '1', 'img/man.jpg', null, '2016-12-23 16:44:12', null, null, null, '0', '1');
INSERT INTO `sys_user` VALUES ('7', '肖帆', '2014051654', '3c9e87237581d165b5f139bf901bd778296f8becb8268e363fe2dbbe', '430124199506111234', '18670050440', '湖南长沙', '0', '20', '394702110@qq.com', '0', '394702110', '1', 'img/man.jpg', null, '2017-01-30 13:36:37', '1', '2017-02-21 11:28:42', '1', '0', '1');
INSERT INTO `sys_user` VALUES ('8', '张烁', '2014051655', '3c9e87237581d165b5f139bf901bd778296f8becb8268e363fe2dbbe', '430124199506111234', '18670050440', '湖南长沙', '0', '22', '394702110@qq.com', '0', '394702110', '1', 'img/man.jpg', '1', '2017-02-22 10:39:12', '1', '2017-02-22 10:39:12', '无', '0', '1');
INSERT INTO `sys_user` VALUES ('10', '李华', '0004', '3c9e87237581d165b5f139bf901bd778296f8becb8268e363fe2dbbe', '0', '18670050440', '湖南长沙', '0', '20', '394702110@qq.com', '0', '394702110', '2', 'img/man.jpg', '1', '2017-02-22 11:51:47', '1', '2017-02-22 11:51:47', null, '0', '1');
INSERT INTO `sys_user` VALUES ('11', '陈立帆', '2014051629', '3c9e87237581d165b5f139bf901bd778296f8becb8268e363fe2dbbe', '432503199509140313', '18975681311', '湖南娄底', '0', '22', '394702110@qq.com', '0', '0', '1', 'img/man.jpg', '1', '2017-03-15 17:53:42', '1', '2017-03-15 17:53:42', null, '0', '1');

-- ----------------------------
-- Table structure for teacher_marking
-- ----------------------------
DROP TABLE IF EXISTS `teacher_marking`;
CREATE TABLE `teacher_marking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `supload_date` datetime NOT NULL,
  `student_question` text,
  `teacher_id` int(11) NOT NULL,
  `teacher_advise` text,
  `tupload_date` datetime DEFAULT NULL,
  `sort` varchar(2) NOT NULL,
  `year` varchar(5) NOT NULL,
  `title_id` int(11) NOT NULL,
  `state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0未批阅1已批阅）',
  `suploadFile` varchar(200) NOT NULL,
  `suploadPath` varchar(500) NOT NULL,
  `suploadFileOldName` varchar(200) NOT NULL,
  `tuploadFile` varchar(200) DEFAULT NULL,
  `tuploadPath` varchar(500) DEFAULT NULL,
  `tuploadFileOldName` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_marking
-- ----------------------------
INSERT INTO `teacher_marking` VALUES ('1', '5', '2017-02-28 19:48:55', '<p>1、不会<p><p>2、不会啊<p><p>3、还是不会<p>', '2', '<p>1、加油<p><p>2、加油<p><p>3、再加油<p>', '2017-02-28 20:55:36', '1', '2013', '1', '1', '1488282535844论文一稿.doc', 'E://gpmsUpload/2014051656/1488282535844论文一稿.doc', '论文一稿.doc', '教师建议.doc', 'E://gpmsUpload/2014051656/教师建议.doc', '教师建议.doc');
INSERT INTO `teacher_marking` VALUES ('2', '5', '2017-02-28 19:51:26', '<p>1、不会<p><p>2、不会啊<p><p>3、还是不会<p>', '2', '<font size=\"5\">测试教师批阅（教师意见）</font>', null, '2', '2013', '1', '1', '1488282686566论文一稿.doc', 'E://gpmsUpload/2014051656/1488282686566论文一稿.doc', '论文一稿.doc', '1489407240211教师建议.doc', 'E://gpmsUpload/0001/1489407240211教师建议.doc', '教师建议.doc');
INSERT INTO `teacher_marking` VALUES ('3', '7', '2017-03-01 01:58:25', null, '3', null, null, '1', '2013', '2', '1', '1488304705277论文一稿.doc', 'E://gpmsUpload/2014051654/1488304705277论文一稿.doc', '论文一稿.doc', null, null, null);
INSERT INTO `teacher_marking` VALUES ('4', '5', '2017-03-01 02:50:58', null, '2', '<i><b><font size=\"5\">测试教师批阅二（教师建议）</font></b></i>', null, '3', '2013', '1', '1', '1488307858940论文评阅.doc', 'E://gpmsUpload/2014051656/1488307858940论文评阅.doc', '论文评阅.doc', '1489407478232教师建议.doc', 'E://gpmsUpload/0001/1489407478232教师建议.doc', '教师建议.doc');
INSERT INTO `teacher_marking` VALUES ('5', '7', '2017-03-01 12:07:22', null, '3', null, null, '2', '2013', '2', '1', '1488341242221论文评阅.doc', 'E://gpmsUpload/2014051654/1488341242221论文评阅.doc', '论文评阅.doc', null, null, null);
INSERT INTO `teacher_marking` VALUES ('6', '8', '2017-03-13 21:05:22', null, '2', '<font size=\"5\">测试测试测试</font>', null, '1', '2013', '3', '1', '1489410322473论文评阅.doc', 'E://gpmsUpload/2014051655/1489410322473论文评阅.doc', '论文评阅.doc', null, null, null);
INSERT INTO `teacher_marking` VALUES ('7', '11', '2017-03-15 20:32:33', null, '2', '测试通过', null, '1', '2013', '7', '1', '1489581153524论文一稿.doc', 'E://gpmsUpload/2014051629/1489581153524论文一稿.doc', '论文一稿.doc', '1489581445337教师建议.doc', 'E://gpmsUpload/0001/1489581445337教师建议.doc', '教师建议.doc');

-- ----------------------------
-- Table structure for teacher_review
-- ----------------------------
DROP TABLE IF EXISTS `teacher_review`;
CREATE TABLE `teacher_review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sort` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `teacher_advise` varchar(200) DEFAULT NULL,
  `tupload_date` datetime DEFAULT NULL,
  `year` varchar(5) NOT NULL,
  `title_id` int(11) NOT NULL,
  `state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0未查阅1未通过2通过）',
  `suploadFile` varchar(200) NOT NULL,
  `suploadPath` varchar(500) NOT NULL,
  `suploadFileOldName` varchar(200) NOT NULL,
  `tuploadFile` varchar(200) DEFAULT NULL,
  `tuploadPath` varchar(500) DEFAULT NULL,
  `tuploadFileOldName` varchar(200) DEFAULT NULL,
  `supload_date` datetime NOT NULL,
  `review_tId` int(11) DEFAULT NULL COMMENT '评阅教师id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_review
-- ----------------------------
INSERT INTO `teacher_review` VALUES ('1', '1', '5', '2', '<p>1、加油<p><p>2、加油<p><p>3、再加油<p>', '2017-03-01 03:03:08', '2013', '1', '2', '1488308532529论文评阅.doc', 'E://gpmsUpload/2014051656/1488308532529论文评阅.doc', '论文评阅.doc', '教师建议.doc', 'E://gpmsUpload/2014051656/教师建议.doc', '教师建议.doc', '2017-03-01 03:02:12', '2');
INSERT INTO `teacher_review` VALUES ('2', '2', '5', '2', null, null, '2013', '1', '2', '1488308790081论文评阅2.doc', 'E://gpmsUpload/2014051656/1488308790081论文评阅2.doc', '论文评阅2.doc', null, null, null, '2017-03-01 03:06:30', '2');
INSERT INTO `teacher_review` VALUES ('5', '1', '7', '3', null, null, '2013', '2', '1', '1488342156802论文评阅.doc', 'E://gpmsUpload/2014051654/1488342156802论文评阅.doc', '论文评阅.doc', null, null, null, '2017-03-01 12:22:36', '3');
INSERT INTO `teacher_review` VALUES ('7', '2', '7', '3', null, null, '2013', '2', '2', '1488343152809论文评阅2.doc', 'E://gpmsUpload/2014051654/1488343152809论文评阅2.doc', '论文评阅2.doc', null, null, null, '2017-03-01 12:39:12', '3');
INSERT INTO `teacher_review` VALUES ('8', '1', '11', '2', '测试评阅工作', null, '2013', '7', '1', '1490107393410论文评阅.doc', 'E://gpmsUpload/2014051629/1490107393410论文评阅.doc', '论文评阅.doc', '1490156276987教师建议.doc', 'E://gpmsUpload/0001/1490156276987教师建议.doc', '教师建议.doc', '2017-03-21 22:43:13', '2');
INSERT INTO `teacher_review` VALUES ('9', '2', '11', '2', '评阅工作通过测试', null, '2013', '7', '2', '1490156469669论文评阅2.doc', 'E://gpmsUpload/2014051629/1490156469669论文评阅2.doc', '论文评阅2.doc', null, null, null, '2017-03-22 12:21:09', '2');

-- ----------------------------
-- Table structure for teacher_year_answer
-- ----------------------------
DROP TABLE IF EXISTS `teacher_year_answer`;
CREATE TABLE `teacher_year_answer` (
  `teacher_id` int(11) NOT NULL,
  `year` varchar(5) NOT NULL,
  `answer_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '答辩分组（0未分组1已分组）',
  `answer_id` int(11) DEFAULT NULL COMMENT '答辩分组id',
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_year_answer
-- ----------------------------
INSERT INTO `teacher_year_answer` VALUES ('2', '2013', '1', '1');
INSERT INTO `teacher_year_answer` VALUES ('3', '2013', '1', '1');
INSERT INTO `teacher_year_answer` VALUES ('4', '2013', '1', '1');
INSERT INTO `teacher_year_answer` VALUES ('10', '2013', '0', null);

-- ----------------------------
-- Table structure for teacher_year_review
-- ----------------------------
DROP TABLE IF EXISTS `teacher_year_review`;
CREATE TABLE `teacher_year_review` (
  `teacher_id` int(11) NOT NULL,
  `year` varchar(5) NOT NULL,
  `review_flag` tinyint(1) DEFAULT '0' COMMENT '评阅分组状态（0未分组1已分组）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_year_review
-- ----------------------------
INSERT INTO `teacher_year_review` VALUES ('2', '2013', '1');
INSERT INTO `teacher_year_review` VALUES ('3', '2013', '1');
INSERT INTO `teacher_year_review` VALUES ('4', '2013', '0');
INSERT INTO `teacher_year_review` VALUES ('10', '2013', '0');

-- ----------------------------
-- Table structure for teacher_year_student
-- ----------------------------
DROP TABLE IF EXISTS `teacher_year_student`;
CREATE TABLE `teacher_year_student` (
  `teacher_id` int(11) NOT NULL,
  `year` varchar(5) NOT NULL,
  `student_sum` int(2) NOT NULL DEFAULT '0',
  `student_ids` varchar(200) DEFAULT NULL,
  `review_tId` int(11) DEFAULT NULL COMMENT '评阅教师id',
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher_year_student
-- ----------------------------
INSERT INTO `teacher_year_student` VALUES ('2', '2013', '3', '5,8,11,', null);
INSERT INTO `teacher_year_student` VALUES ('3', '2013', '1', '7,', null);

-- ----------------------------
-- Table structure for understanding_report
-- ----------------------------
DROP TABLE IF EXISTS `understanding_report`;
CREATE TABLE `understanding_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `upload_date` datetime NOT NULL,
  `year` varchar(5) NOT NULL,
  `title_id` int(11) NOT NULL,
  `pass_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否通过（0未查阅1未通过2通过）',
  `uploadFile` varchar(200) NOT NULL,
  `uploadPath` varchar(500) NOT NULL,
  `uploadFileOldName` varchar(200) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  `teacher_advise` text COMMENT '教师意见',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of understanding_report
-- ----------------------------
INSERT INTO `understanding_report` VALUES ('5', '5', '2017-02-24 17:17:06', '2013', '1', '2', '1487927826423基于SpringMVC的汽车销售管理系统的设计与实现毕业论文.doc', 'E://gpmsUpload/2014051656/1487927826423基于SpringMVC的汽车销售管理系统的设计与实现毕业论文.doc', '基于SpringMVC的汽车销售管理系统的设计与实现毕业论文.doc', '2', null);
INSERT INTO `understanding_report` VALUES ('6', '7', '2017-02-25 13:16:50', '2013', '2', '2', '14879998105562010091013583525.doc', 'E://gpmsUpload/2014051654/14879998105562010091013583525.doc', '2010091013583525.doc', '3', null);
INSERT INTO `understanding_report` VALUES ('7', '8', '2017-03-02 22:33:28', '2013', '3', '2', '1488465208498论文一稿.doc', 'E://gpmsUpload/2014051655/1488465208498论文一稿.doc', '论文一稿.doc', '2', '<font size=\"5\">1、测试；</font><div><font size=\"5\">2、测试；</font></div>');
INSERT INTO `understanding_report` VALUES ('8', '11', '2017-03-15 19:12:17', '2013', '7', '2', '1489576337523审题报告.doc', 'E://gpmsUpload/2014051629/1489576337523审题报告.doc', '审题报告.doc', '2', '恭喜通过');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '2');
INSERT INTO `user_role` VALUES ('3', '3');
INSERT INTO `user_role` VALUES ('4', '4');
INSERT INTO `user_role` VALUES ('5', '5');
INSERT INTO `user_role` VALUES ('2', '4');
INSERT INTO `user_role` VALUES ('3', '4');
INSERT INTO `user_role` VALUES ('7', '5');
INSERT INTO `user_role` VALUES ('8', '5');
INSERT INTO `user_role` VALUES ('10', '4');
INSERT INTO `user_role` VALUES ('11', '5');
