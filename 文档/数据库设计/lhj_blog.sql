/*
Navicat MySQL Data Transfer

Source Server         : lhj_blog
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : lhj_blog

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2020-03-19 08:29:11
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
INSERT INTO `sys_account` VALUES ('1002', 'admin', '928bfd2577490322a6e19b793691467e', 'zh_CN', '1', '1', '1990-03-04 00:00:00', '3000-04-30 00:00:00', null, null, '超级管理员', 'lhj', '2020-03-05 20:35:47', 'admin', '2020-03-15 20:40:04');
INSERT INTO `sys_account` VALUES ('1003', 'lhj', 'aa79ab0a38d0b940b203a4ccc470257c', 'zh_CN', '1', '1', '1990-03-04 00:00:00', '3000-04-30 00:00:00', null, null, '超级管理员', 'lhj', '2020-03-05 20:35:47', 'administrator', '2020-03-17 00:55:52');
INSERT INTO `sys_account` VALUES ('2225411835612006982332019', 'a', 'c0d8209da5b684c66e135a15460c2a05', null, '1', '1', '2020-03-14 00:00:00', '2999-12-30 00:00:00', null, null, null, 'administrator', '2020-03-15 11:33:50', 'administrator', '2020-03-15 11:33:50');
INSERT INTO `sys_account` VALUES ('2268647121683001497627145', '1', '3a3c6ca24f6e565c26100b01b5c3fc91', null, '1', '1', '2020-03-14 00:00:00', '2999-12-30 00:00:00', null, null, null, 'administrator', '2020-03-15 12:45:53', 'administrator', '2020-03-15 12:45:53');
INSERT INTO `sys_account` VALUES ('2268789406807001595427921', '2', '56c9af8201fdca98f558baaf4cd5658a', null, '1', '1', '2020-03-14 00:00:00', '2999-12-30 00:00:00', null, null, null, 'administrator', '2020-03-15 12:46:07', 'administrator', '2020-03-15 12:46:07');
INSERT INTO `sys_account` VALUES ('2268876146456007246770922', '3', 'cf6b4266f3af6939e410baea17bc9353', null, '1', '1', '2020-02-27 00:00:00', '2999-12-14 00:00:00', null, null, null, 'administrator', '2020-03-15 12:46:16', 'admin', '2020-03-15 20:39:25');

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
  `category_id` varchar(100) DEFAULT NULL COMMENT '类别id',
  `locale_id` varchar(20) DEFAULT NULL COMMENT '区域',
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
-- Records of sys_item
-- ----------------------------
INSERT INTO `sys_item` VALUES ('803651314127001271957723', '2', null, 'r', null, '1', null, null, null, null, null, null);
INSERT INTO `sys_item` VALUES ('804050806411003196808563', '3', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `sys_item` VALUES ('804050858699003195132838', '3', null, null, null, '1', null, null, null, null, null, null);
INSERT INTO `sys_item` VALUES ('816873422651001368391610', '2', null, null, null, '1', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_item_category
-- ----------------------------
DROP TABLE IF EXISTS `sys_item_category`;
CREATE TABLE `sys_item_category` (
  `sid` varchar(50) NOT NULL COMMENT '主键',
  `parent_id` varchar(50) DEFAULT NULL COMMENT '父节点Id',
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
-- Records of sys_item_category
-- ----------------------------
INSERT INTO `sys_item_category` VALUES ('1', '-1', '', 'sys-item', '系统字典', '', '1', '0', '', 'lhj', '2020-03-17 22:54:15', 'lhj', '2020-03-17 22:54:23');
INSERT INTO `sys_item_category` VALUES ('2', '1', '', 'isActive', '有效无效', '', '1', '2', '呵呵', 'lhj', '2020-03-17 22:54:15', 'administrator', '2020-03-19 00:03:06');
INSERT INTO `sys_item_category` VALUES ('3', '1', '', 'yesno', '是/否', '', '1', '2', '', 'lhj', '2020-03-17 23:41:24', 'administrator', '2020-03-18 23:27:36');

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
  `type` varchar(2) DEFAULT NULL COMMENT '类型：0=菜单，1=iframe，2=页面 ,3=父菜单',
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
INSERT INTO `sys_menu` VALUES ('001', '', '-1', 'zh_CN', 'webSite', '个人主页', '', '', '', 'site', '', '1', '0', '', 'lhj', '2020-03-08 20:50:50', 'lhj', '2020-03-08 20:50:55');
INSERT INTO `sys_menu` VALUES ('002', '', '001', 'zh_CN', 'index', '首页', '/', 'websit/Index.vue', '', 'site-top', '', '1', '1', '', 'lhj', '2020-03-08 20:50:50', 'lhj', '2020-03-08 20:50:55');
INSERT INTO `sys_menu` VALUES ('003', '', '001', 'zh_CN', 'project', '项目作品', '/river/project', 'websit/Project.vue', '', 'site-top', '', '1', '2', '', 'lhj', '2020-03-08 20:50:50', 'lhj', '2020-03-08 20:50:55');
INSERT INTO `sys_menu` VALUES ('004', '', '001', 'zh_CN', 'skills', '专业技能', '/river/skill', 'websit/Skill.vue', '', 'site-top', '', '1', '3', '', 'lhj', '2020-03-08 20:50:50', 'lhj', '2020-03-08 20:50:55');
INSERT INTO `sys_menu` VALUES ('005', '', '001', 'zh_CN', 'tool', '实用工具', '/river/tool', 'websit/Tool.vue', '', 'site-top', '', '1', '4', '', 'lhj', '2020-03-08 20:50:50', 'lhj', '2020-03-08 20:50:55');
INSERT INTO `sys_menu` VALUES ('006', '', '001', 'zh_CN', 'comments', '留言意见', '/river/comments', 'websit/Comments.vue', '', 'site-top', '', '1', '5', '', 'lhj', '2020-03-08 20:50:50', 'lhj', '2020-03-08 20:50:55');
INSERT INTO `sys_menu` VALUES ('007', '', '001', 'zh_CN', 'manage', '后台管理', '/welcome', '', '', 'site-top', '', '1', '6', '', 'lhj', '2020-03-08 20:50:50', 'lhj', '2020-03-08 20:50:55');
INSERT INTO `sys_menu` VALUES ('101', null, '-1', 'zh_CN', 'manage', '后台管理', '/manage', 'manage/Home.vue', null, 'manage', '3', '1', '1', null, 'lhj', '2020-03-08 20:50:50', 'lhj', '2020-03-08 20:50:55');
INSERT INTO `sys_menu` VALUES ('102', '', '101', 'zh_CN', 'manage-index', '首页', '/welcome', 'manage/Welcome.vue', 'ios-home-outline', 'manage-top', '', '1', '1', '', 'lhj', '2020-03-08 20:50:50', 'lhj', '2020-03-08 20:50:55');
INSERT INTO `sys_menu` VALUES ('103', '', '101', 'zh_CN', 'manage-syatem', '系统管理', '/content/systemManage', '', 'ios-home-outline', 'manage-top', '3', '1', '2', '', 'lhj', '2020-03-08 20:50:50', 'administrator', '2020-03-17 00:17:45');
INSERT INTO `sys_menu` VALUES ('104', '', '103', 'zh_CN', 'manage-menu', '菜单管理', '/content/systemManage/menusManage', 'manage/systemManage/menuManage/MenusManage.vue', 'ios-photos-outline', 'manage-left', '', '1', '3', '', 'lhj', '2020-03-08 20:50:50', 'administrator', '2020-03-16 00:05:28');
INSERT INTO `sys_menu` VALUES ('106', '', '103', 'zh_CN', 'manage-user-list', '用户管理', '/content/systemManage/userManage', 'manage/systemManage/userManage/List.vue', 'ios-photos-outline', 'manage-left', '', '1', '1', '', 'lhj', '2020-03-08 20:50:50', 'lhj', '2020-03-08 20:50:55');
INSERT INTO `sys_menu` VALUES ('108', '', '101', 'zh_CN', 'manage-vue', 'vue', '/content/vue', '', 'ios-photos-outline', 'manage-top', '3', '1', '3', '', 'lhj', '2020-03-08 20:50:50', 'administrator', '2020-03-17 00:18:31');
INSERT INTO `sys_menu` VALUES ('109', '', '108', 'zh_CN', 'manage-1', 'test1', '/content/vue/myRouter', 'manage/vue/testRender.vue', 'ios-photos-outline', 'manage-left', '', '1', '2', '', 'lhj', '2020-03-08 20:50:50', 'administrator', '2020-03-15 23:29:51');
INSERT INTO `sys_menu` VALUES ('110', '', '108', 'zh_CN', 'manage-2', 'test2', '/content/vue/other', 'manage/vue/rederDemo.vue', 'ios-photos-outline', 'manage-left', '', '1', '5', '', 'lhj', '2020-03-08 20:50:50', 'administrator', '2020-03-17 22:34:25');
INSERT INTO `sys_menu` VALUES ('111', '', '103', 'zh_CN', 'manage-userRoel', '角色管理', '', '', 'ios-photos-outline', 'manage-left', '3', '1', '2', '', 'lhj', '2020-03-08 20:50:50', 'administrator', '2020-03-17 00:18:25');
INSERT INTO `sys_menu` VALUES ('112', '', '103', 'zh_CN', 'manage-sysItem', '字典管理', '/content/systemManage/itemManage', 'manage/systemManage/itemManage/ItemManage.vue', 'ios-photos-outline', 'manage-left', '', '1', '4', '', 'lhj', '2020-03-08 20:50:50', 'administrator', '2020-03-17 23:38:06');
INSERT INTO `sys_menu` VALUES ('3476439027097001628041293', null, '111', null, 'manage-roel', '角色管理', '/content/systemManage/roleManage', 'manage/systemManage/roleManage/List.vue', '', 'manage-left', null, '1', '1', '', 'administrator', '2020-03-16 22:18:49', 'administrator', '2020-03-16 22:18:49');
INSERT INTO `sys_menu` VALUES ('3477973209449001615327970', null, '111', null, 'manage-roleMenu', '角色授权', '/content/systemManage/roleMenuManage', 'manage/systemManage/roleManage/RoleMenu.vue', '', 'manage-left', null, '1', '2', '', null, null, 'administrator', '2020-03-16 22:21:56');

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
INSERT INTO `sys_role` VALUES ('001', 'zh_CN', 'base', '基本角色', null, '1', '1', '测试', 'lhj', '2020-03-15 11:38:02', 'administrator', '2020-03-15 14:19:31');
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
INSERT INTO `sys_role_menu` VALUES ('3566672731586009687472827', '002', '101', '1', null, 'administrator', '2020-03-17 00:49:13', 'administrator', '2020-03-17 00:49:13');
INSERT INTO `sys_role_menu` VALUES ('3566672822613009686020017', '002', '103', '1', null, 'administrator', '2020-03-17 00:49:13', 'administrator', '2020-03-17 00:49:13');
INSERT INTO `sys_role_menu` VALUES ('3566672854174009687296455', '002', '106', '1', null, 'administrator', '2020-03-17 00:49:13', 'administrator', '2020-03-17 00:49:13');
INSERT INTO `sys_role_menu` VALUES ('3566672889173009688784921', '002', '111', '1', null, 'administrator', '2020-03-17 00:49:13', 'administrator', '2020-03-17 00:49:13');
INSERT INTO `sys_role_menu` VALUES ('3566672934358009683782802', '002', '3476439027097001628041293', '1', null, 'administrator', '2020-03-17 00:49:13', 'administrator', '2020-03-17 00:49:13');
INSERT INTO `sys_role_menu` VALUES ('3566672977811009685467107', '002', '3477973209449001615327970', '1', null, 'administrator', '2020-03-17 00:49:13', 'administrator', '2020-03-17 00:49:13');
INSERT INTO `sys_role_menu` VALUES ('3566673012528009687821132', '002', '104', '1', null, 'administrator', '2020-03-17 00:49:13', 'administrator', '2020-03-17 00:49:13');
INSERT INTO `sys_role_menu` VALUES ('720823885500001893621001', '001', '101', '1', null, 'administrator', '2020-03-17 22:14:47', 'administrator', '2020-03-17 22:14:47');
INSERT INTO `sys_role_menu` VALUES ('720824159345001893281270', '001', '102', '1', null, 'administrator', '2020-03-17 22:14:47', 'administrator', '2020-03-17 22:14:47');
INSERT INTO `sys_role_menu` VALUES ('720824195224001894805354', '001', '108', '1', null, 'administrator', '2020-03-17 22:14:47', 'administrator', '2020-03-17 22:14:47');
INSERT INTO `sys_role_menu` VALUES ('720824235238001891569433', '001', '109', '1', null, 'administrator', '2020-03-17 22:14:47', 'administrator', '2020-03-17 22:14:47');
INSERT INTO `sys_role_menu` VALUES ('720824284787001898138658', '001', '110', '1', null, 'administrator', '2020-03-17 22:14:47', 'administrator', '2020-03-17 22:14:47');

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
INSERT INTO `sys_user` VALUES ('1001', 'administrator', 'zh_CN', '超级管理员', '超级管理员', '1', '中国', '上海', '17665057480', '1782221277@qq.com', '', '0', '', 'lhj', '2020-03-05 20:43:40', 'lhj', '2020-03-05 20:43:38');
INSERT INTO `sys_user` VALUES ('1002', 'admin', 'zh_CN', '管理员', '管理员', '1', '中国', '上海', '17665057480', '1782221277@qq.com', '', '0', '', 'lhj', '2020-03-05 20:43:40', 'admin', '2020-03-15 20:40:04');
INSERT INTO `sys_user` VALUES ('1003', 'lhj', 'zh_CN', '刘海江', '刘海江', '1', '中国', '上海', '17665057480', '1782221277@qq.com', '1', '0', '', 'lhj', '2020-03-05 20:43:40', 'administrator', '2020-03-17 00:55:52');
INSERT INTO `sys_user` VALUES ('2225411591447006986452080', 'a', null, 'a', null, null, null, null, null, null, null, null, null, 'administrator', '2020-03-15 11:33:50', 'administrator', '2020-03-15 11:33:50');
INSERT INTO `sys_user` VALUES ('2268646906373001495448429', '1', null, '1', null, null, null, null, null, null, null, null, null, 'administrator', '2020-03-15 12:45:53', 'administrator', '2020-03-15 12:45:53');
INSERT INTO `sys_user` VALUES ('2268789339794001595730803', '2', null, '2', null, null, null, null, null, null, null, null, null, 'administrator', '2020-03-15 12:46:07', 'administrator', '2020-03-15 12:46:07');
INSERT INTO `sys_user` VALUES ('2268876095093007246319007', '3', null, '3', null, null, null, null, null, null, null, null, null, 'administrator', '2020-03-15 12:46:16', 'admin', '2020-03-15 20:39:25');

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
INSERT INTO `sys_user_role` VALUES ('001', 'admin', '001', '1', null, null, null, null, null, 'admin', '2020-03-15 20:40:04');
INSERT INTO `sys_user_role` VALUES ('002', 'lhj', '001', '1', null, null, null, null, null, 'administrator', '2020-03-17 00:55:52');
INSERT INTO `sys_user_role` VALUES ('2511367327496006403325173', '3', '001', '1', null, null, null, null, null, 'admin', '2020-03-15 20:39:25');
INSERT INTO `sys_user_role` VALUES ('2530678075898006406971889', '3', '003', '0', null, null, null, null, null, 'admin', '2020-03-15 20:39:25');
INSERT INTO `sys_user_role` VALUES ('2553188372202001739127346', 'admin', '002', '1', null, null, null, 'admin', '2020-03-15 20:40:04', 'admin', '2020-03-15 20:40:04');
INSERT INTO `sys_user_role` VALUES ('3570668918024001551966948', 'lhj', '002', '1', null, null, null, 'administrator', '2020-03-17 00:55:52', 'administrator', '2020-03-17 00:55:52');
