/*
Navicat MySQL Data Transfer

Source Server         : lhj_blog
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : lhj_blog

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2020-03-08 23:53:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_account`;
CREATE TABLE `sys_account` (
  `sid` varchar(50) NOT NULL COMMENT '主键',
  `user_cd` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `locale_id` varchar(20) DEFAULT NULL COMMENT '区域',
  `licence` varchar(100) DEFAULT NULL COMMENT '许可证（0=无效效，1=有效）',
  `type` varchar(10) DEFAULT NULL COMMENT '账户类型:（0=超级管理员，1=普通用户，2=管理员）',
  `start_date` datetime DEFAULT NULL COMMENT '有效开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '有效结束时间',
  `lock_date` datetime DEFAULT NULL COMMENT '锁住时间',
  `login_failure_count` decimal(10,0) DEFAULT NULL COMMENT '登录失败次数',
  `notes` varchar(255) DEFAULT NULL COMMENT '备注',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人cd',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人cd',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统账户表';

-- ----------------------------
-- Records of sys_account
-- ----------------------------
INSERT INTO `sys_account` VALUES ('1001', 'administrator', '20ac2904c1d2d6bba133ab8f3d4fdcfc', 'zh_CN', '1', '1', '1990-03-05 20:34:12', '3000-05-01 20:34:34', null, null, '超级管理员', 'lhj', '2020-03-05 20:35:47', 'lhj', '2020-03-05 20:35:53');
INSERT INTO `sys_account` VALUES ('1002', 'admin', '928bfd2577490322a6e19b793691467e', 'zh_CN', '1', '1', '1990-03-05 20:34:12', '3000-05-01 20:34:34', null, null, '超级管理员', 'lhj', '2020-03-05 20:35:47', 'lhj', '2020-03-05 20:35:53');
INSERT INTO `sys_account` VALUES ('1003', 'lhj', 'aa79ab0a38d0b940b203a4ccc470257c', 'zh_CN', '1', '1', '1990-03-05 20:34:12', '3000-05-01 20:34:34', null, null, '超级管理员', 'lhj', '2020-03-05 20:35:47', 'lhj', '2020-03-05 20:35:53');

-- ----------------------------
-- Table structure for sys_account_attr
-- ----------------------------
DROP TABLE IF EXISTS `sys_account_attr`;
CREATE TABLE `sys_account_attr` (
  `sid` varchar(50) NOT NULL COMMENT '主键',
  `user_cd` varchar(100) DEFAULT NULL COMMENT '用户名',
  `attr_name` varchar(255) DEFAULT NULL COMMENT '密码',
  `attr_value` varchar(255) DEFAULT NULL COMMENT '区域',
  `notes` varchar(255) DEFAULT NULL COMMENT '备注',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人cd',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人cd',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户属性表';

-- ----------------------------
-- Records of sys_account_attr
-- ----------------------------

-- ----------------------------
-- Table structure for sys_app
-- ----------------------------
DROP TABLE IF EXISTS `sys_app`;
CREATE TABLE `sys_app` (
  `sid` varchar(50) NOT NULL COMMENT '主键',
  `parent_id` varchar(50) DEFAULT NULL COMMENT '父节点Id',
  `locale_id` varchar(20) DEFAULT NULL COMMENT '区域',
  `app_name` varchar(255) DEFAULT NULL COMMENT '应用名称',
  `display_name` varchar(255) DEFAULT NULL COMMENT '显示名称',
  `url` varchar(512) DEFAULT NULL COMMENT '应用url',
  `status` varchar(2) DEFAULT NULL COMMENT '状态：0=不显示，1=显示',
  `sort_key` decimal(10,0) DEFAULT NULL COMMENT '排序',
  `notes` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人cd',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人cd',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统应用表';

-- ----------------------------
-- Records of sys_app
-- ----------------------------
INSERT INTO `sys_app` VALUES ('1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2020-02-18 23:35:33', '12', '2020-03-20 23:35:38');

-- ----------------------------
-- Table structure for sys_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_item`;
CREATE TABLE `sys_item` (
  `sid` varchar(50) NOT NULL COMMENT '主键',
  `category_cd` varchar(100) DEFAULT NULL COMMENT '类别CD',
  `item_cd` varchar(100) DEFAULT NULL COMMENT '字典CD',
  `status` varchar(2) DEFAULT NULL COMMENT '状态：0=不显示，1=显示',
  `sort_key` decimal(10,0) DEFAULT NULL COMMENT '排序',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人cd',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人cd',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of sys_item
-- ----------------------------

-- ----------------------------
-- Table structure for sys_item_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_item_category`;
CREATE TABLE `sys_item_category` (
  `sid` varchar(50) NOT NULL COMMENT '主键',
  `parent_id` varchar(50) DEFAULT NULL COMMENT '父节点Id',
  `category_cd` varchar(100) DEFAULT NULL COMMENT '类别cd',
  `status` varchar(2) DEFAULT NULL COMMENT '状态：0=不显示，1=显示',
  `sort_key` decimal(10,0) DEFAULT NULL COMMENT '排序',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人cd',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人cd',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典类别表';

-- ----------------------------
-- Records of sys_item_category
-- ----------------------------

-- ----------------------------
-- Table structure for sys_item_category_location
-- ----------------------------
DROP TABLE IF EXISTS `sys_item_category_location`;
CREATE TABLE `sys_item_category_location` (
  `sid` varchar(50) NOT NULL COMMENT '主键',
  `category_cd` varchar(100) DEFAULT NULL COMMENT '类别CD',
  `locale_id` varchar(20) DEFAULT NULL COMMENT '区域',
  `category_name` varchar(255) DEFAULT NULL COMMENT '类别名称',
  `display_name` varchar(255) DEFAULT NULL COMMENT '类别显示名',
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `status` varchar(2) DEFAULT NULL COMMENT '状态：0=不显示，1=显示',
  `sort_key` decimal(10,0) DEFAULT NULL COMMENT '排序',
  `notes` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人cd',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人cd',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典类别(多语言)表';

-- ----------------------------
-- Records of sys_item_category_location
-- ----------------------------

-- ----------------------------
-- Table structure for sys_item_location
-- ----------------------------
DROP TABLE IF EXISTS `sys_item_location`;
CREATE TABLE `sys_item_location` (
  `sid` varchar(50) NOT NULL COMMENT '主键',
  `locale_id` varchar(20) DEFAULT NULL COMMENT '区域',
  `item_cd` varchar(100) DEFAULT NULL COMMENT '字典CD',
  `item_key` varchar(255) DEFAULT NULL COMMENT '字典key',
  `item_value` varchar(512) DEFAULT NULL COMMENT '字典值',
  `status` varchar(2) DEFAULT NULL COMMENT '状态：0=不显示，1=显示',
  `sort_key` decimal(10,0) DEFAULT NULL COMMENT '排序',
  `notes` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人cd',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人cd',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典(多语言)表';

-- ----------------------------
-- Records of sys_item_location
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `sid` varchar(50) NOT NULL COMMENT '主键',
  `app_id` varchar(50) DEFAULT NULL COMMENT '应用ID',
  `parent_id` varchar(50) DEFAULT NULL COMMENT '父节点Id',
  `locale_id` varchar(20) DEFAULT NULL COMMENT '区域',
  `menu_name` varchar(100) DEFAULT NULL COMMENT '菜单名',
  `display_name` varchar(255) DEFAULT NULL COMMENT '菜单显示名',
  `url` varchar(512) DEFAULT NULL COMMENT 'url',
  `page_path` varchar(512) DEFAULT NULL COMMENT '页面文件路径',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `category` varchar(20) DEFAULT NULL COMMENT '类别',
  `type` varchar(2) DEFAULT NULL COMMENT '类型：0=菜单，1=iframe，2=按钮',
  `status` varchar(2) DEFAULT NULL COMMENT '状态：0=不显示，1=显示',
  `sort_key` decimal(10,0) DEFAULT NULL COMMENT '排序',
  `notes` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人cd',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人cd',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('001', '', '-1', 'zh_CN', 'webSite', '个人主页', '', '', '', 'site', '', '0', null, '', 'lhj', '2020-03-08 20:50:50', 'lhj', '2020-03-08 20:50:55');
INSERT INTO `sys_menu` VALUES ('002', '', '001', 'zh_CN', 'index', '首页', '/', 'websit/Index.vue', '', 'site-top', '', '1', '1', '', 'lhj', '2020-03-08 20:50:50', 'lhj', '2020-03-08 20:50:55');
INSERT INTO `sys_menu` VALUES ('003', '', '001', 'zh_CN', 'project', '项目作品', '/project', 'websit/Project.vue', '', 'site-top', '', '1', '2', '', 'lhj', '2020-03-08 20:50:50', 'lhj', '2020-03-08 20:50:55');
INSERT INTO `sys_menu` VALUES ('004', '', '001', 'zh_CN', 'skills', '专业技能', '/skill', 'websit/Skill.vue', '', 'site-top', '', '1', '3', '', 'lhj', '2020-03-08 20:50:50', 'lhj', '2020-03-08 20:50:55');
INSERT INTO `sys_menu` VALUES ('005', '', '001', 'zh_CN', 'tool', '实用工具', '/tool', 'websit/Tool.vue', '', 'site-top', '', '1', '4', '', 'lhj', '2020-03-08 20:50:50', 'lhj', '2020-03-08 20:50:55');
INSERT INTO `sys_menu` VALUES ('006', '', '001', 'zh_CN', 'comments', '留言意见', '/comments', 'websit/Comments.vue', '', 'site-top', '', '1', '5', '', 'lhj', '2020-03-08 20:50:50', 'lhj', '2020-03-08 20:50:55');
INSERT INTO `sys_menu` VALUES ('007', '', '001', 'zh_CN', 'manage', '后台管理', '/manage/welcome', 'manage/Welcome.vue', '', 'site-top', '', '1', '6', '', 'lhj', '2020-03-08 20:50:50', 'lhj', '2020-03-08 20:50:55');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `sid` varchar(50) NOT NULL COMMENT '主键',
  `locale_id` varchar(20) DEFAULT NULL COMMENT '区域',
  `role_code` varchar(100) DEFAULT NULL COMMENT '角色code',
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名',
  `category` varchar(50) DEFAULT NULL COMMENT '类别',
  `status` varchar(2) DEFAULT NULL COMMENT '状态1=有效，0=无效',
  `sort_key` decimal(10,0) DEFAULT NULL COMMENT '排序',
  `notes` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人cd',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人cd',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('001', 'zh_CN', 'base', '基本角色', null, '1', '1', null, null, null, null, null);
INSERT INTO `sys_role` VALUES ('002', 'zh_CN', 'admin', '管理员', '', '1', '1', '', 'lhj', '2020-03-08 12:32:03', 'lhj', '2020-03-08 12:32:09');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `sid` varchar(50) NOT NULL COMMENT '主键',
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色ID',
  `menu_id` varchar(50) DEFAULT NULL COMMENT '菜单ID',
  `status` varchar(2) DEFAULT NULL COMMENT '状态0=有效，1=无效',
  `notes` varchar(255) DEFAULT NULL COMMENT '备注',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人cd',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人cd',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单关联表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `sid` varchar(50) NOT NULL COMMENT '主键',
  `user_cd` varchar(100) DEFAULT NULL COMMENT '用户名',
  `locale_id` varchar(20) DEFAULT NULL COMMENT '区域',
  `user_name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `user_search_name` varchar(100) DEFAULT NULL COMMENT '搜索名',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别（1=男，2=女）',
  `country_cd` varchar(100) DEFAULT NULL COMMENT '国家',
  `address` varchar(512) DEFAULT NULL COMMENT '地址',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `sort_key` decimal(10,0) DEFAULT NULL COMMENT '排序',
  `notes` varchar(512) DEFAULT NULL COMMENT '备注',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人cd',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人cd',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1001', 'administrator', 'zh_CN', '超级管理员', '超级管理员', '1', '中国', '上海', '17665057480', '1782221277@qq.com', '', '0', null, 'lhj', '2020-03-05 20:43:40', 'lhj', '2020-03-05 20:43:38');
INSERT INTO `sys_user` VALUES ('76207294332008293087920', 'admin_1', null, null, null, null, null, null, null, null, null, null, null, 'lhj', '2020-03-02 21:34:04', null, null);
INSERT INTO `sys_user` VALUES ('76221562447008297633494', '阿阿发阿尔给你', null, '阿斯蒂芬', null, null, null, null, null, null, null, null, null, 'lhj', '2020-03-02 21:34:05', null, null);
INSERT INTO `sys_user` VALUES ('76221611816008291074638', 'admin_3', null, null, null, null, null, null, null, null, null, null, null, 'lhj', '2020-03-02 21:34:05', null, null);
INSERT INTO `sys_user` VALUES ('76221714687008294362186', 'admin_4', null, null, null, null, null, null, null, null, null, null, null, 'lhj', '2020-03-02 21:34:05', null, null);
INSERT INTO `sys_user` VALUES ('76221762177008297359844', 'admin_5', null, null, null, null, null, null, null, null, null, null, null, 'lhj', '2020-03-02 21:34:05', null, null);
INSERT INTO `sys_user` VALUES ('76221800618008295993325', 'admin_6', null, null, null, null, null, null, null, null, null, null, null, 'lhj', '2020-03-02 21:34:05', null, null);
INSERT INTO `sys_user` VALUES ('76221851755008296539241', 'admin_7', null, null, null, null, null, null, null, null, null, null, null, 'lhj', '2020-03-02 21:34:06', null, null);
INSERT INTO `sys_user` VALUES ('76221892209008298141724', 'admin_8', null, null, null, null, null, null, null, null, null, null, null, 'lhj', '2020-03-02 21:34:06', null, null);
INSERT INTO `sys_user` VALUES ('76221928033008298174871', 'admin_9', null, null, null, null, null, null, null, null, null, null, null, 'lhj', '2020-03-02 21:34:06', null, null);
INSERT INTO `sys_user` VALUES ('76221978927008299634414', 'admin_10', null, null, null, null, null, null, null, null, null, null, null, 'lhj', '2020-03-02 21:34:06', null, null);
INSERT INTO `sys_user` VALUES ('81617642799001772461924', 'admin_11', null, null, null, null, null, null, null, null, null, null, null, 'lhj', '2020-03-02 21:43:05', null, null);
INSERT INTO `sys_user` VALUES ('81630135768001775250109', 'admin_12', null, null, null, null, null, null, null, null, null, null, null, 'lhj', '2020-03-02 21:43:06', null, null);
INSERT INTO `sys_user` VALUES ('81630180590001775387063', 'admin_13', null, null, null, null, null, null, null, null, null, null, null, 'lhj', '2020-03-02 21:43:06', null, null);
INSERT INTO `sys_user` VALUES ('81630226223001771190509', 'admin_14', null, null, null, null, null, null, null, null, null, null, null, 'lhj', '2020-03-02 21:43:06', null, null);
INSERT INTO `sys_user` VALUES ('81630307651001776482287', 'admin_15', null, null, null, null, null, null, null, null, null, null, null, 'lhj', '2020-03-02 21:43:06', null, null);
INSERT INTO `sys_user` VALUES ('81630388846001774492869', 'admin_16', null, null, null, null, null, null, null, null, null, null, null, 'lhj', '2020-03-02 21:43:06', null, null);
INSERT INTO `sys_user` VALUES ('81630433527001771507675', 'admin_17', null, null, null, null, null, null, null, null, null, null, null, 'lhj', '2020-03-02 21:43:06', null, null);
INSERT INTO `sys_user` VALUES ('81630470087001777938202', 'admin_18', null, null, null, null, null, null, null, null, null, null, null, 'lhj', '2020-03-02 21:43:06', null, null);
INSERT INTO `sys_user` VALUES ('81630502207001776182019', 'admin_19', null, null, null, null, null, null, null, null, null, null, null, 'lhj', '2020-03-02 21:43:06', null, null);
INSERT INTO `sys_user` VALUES ('81630539980001774243680', 'admin_20', null, null, null, null, null, null, null, null, null, null, null, 'lhj', '2020-03-02 21:43:06', null, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `sid` varchar(50) NOT NULL COMMENT '主键',
  `user_cd` varchar(100) DEFAULT NULL COMMENT '用户名',
  `role_id` varchar(50) DEFAULT NULL COMMENT '角色ID',
  `status` varchar(2) DEFAULT NULL COMMENT '状态1=有效，0=无效',
  `start_date` datetime DEFAULT NULL COMMENT '有效开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '有效结束时间',
  `notes` varchar(255) DEFAULT NULL COMMENT '备注',
  `created_by` varchar(100) DEFAULT NULL COMMENT '创建人cd',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(100) DEFAULT NULL COMMENT '更新人cd',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('001', 'admin', '001', '1', '2020-01-29 12:29:58', '2020-06-25 12:30:12', null, null, null, null, null);
