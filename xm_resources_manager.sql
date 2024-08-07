/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : xm_resources_manager

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 07/08/2024 17:42:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色标识',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '管理员' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'admin', '管理员', 'http://localhost:9090/files/1722934306299-logo1.png', 'ADMIN', '13677889922', 'admin@xm.com');

-- ----------------------------
-- Table structure for askapply
-- ----------------------------
DROP TABLE IF EXISTS `askapply`;
CREATE TABLE `askapply`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID\r\n',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '请假事由',
  `employee_id` int(10) NULL DEFAULT NULL COMMENT '员工ID',
  `department_id` int(10) NULL DEFAULT NULL COMMENT '部门ID',
  `start` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '开始日期',
  `end` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '结束日期',
  `process` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '当前进度',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审批状态',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审批说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '请假信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of askapply
-- ----------------------------
INSERT INTO `askapply` VALUES (1, '发烧', 1, 2, '2024-06-02', '2024-06-04', '审批结束', '主管审批不通过', '发烧不上班，怎么显得我剥削');
INSERT INTO `askapply` VALUES (4, '带车位四方达', 1, 2, '2024-08-05', '2024-08-16', '主管审批中', '待主管审批', NULL);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '部门名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '部门描述',
  `employee_id` int(10) NULL DEFAULT NULL COMMENT '主管ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '部门信息表\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '水果部', '这是水果部', 4);
INSERT INTO `department` VALUES (2, '姓氏部', '这是姓氏部', 5);
INSERT INTO `department` VALUES (3, '路人部', '这是路人部101', 3);
INSERT INTO `department` VALUES (4, '运营部', NULL, 6);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '密码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电话',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '身份',
  `department_id` int(10) NULL DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '员工信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (1, 'zhangsan', '123', '张三', 'http://localhost:9090/files/1722934291042-logo1.png', 'USER', '16662222111', 'zhangsan@xm.com', '员工', 2);
INSERT INTO `employee` VALUES (2, 'lisi', '12', '李四', 'http://localhost:9090/files/1717068805223-2022-3-28_0-43-13.png', 'USER', '12345', '1234567891@123', '员工', 2);
INSERT INTO `employee` VALUES (3, 'wangwu', '10086', '王五', NULL, 'USER', '10086', '10086@123', '主管', 1);
INSERT INTO `employee` VALUES (4, 'ichigo', '123', '草莓', 'http://localhost:9090/files/1722934277221-logo1.png', 'USER', '1385978', '142955@qq.com', '主管', 1);
INSERT INTO `employee` VALUES (5, 'linshi', '123', '林氏', 'http://localhost:9090/files/1717132703700-2021-9-28_14-14-26.png', 'USER', '123456', '54687@qq.com', '主管', 2);
INSERT INTO `employee` VALUES (6, '1354', '123', '运营主管', NULL, 'USER', NULL, NULL, '主管', 4);
INSERT INTO `employee` VALUES (7, '38786', '123', '运营员工', NULL, 'USER', NULL, NULL, '员工', 4);

-- ----------------------------
-- Table structure for financial
-- ----------------------------
DROP TABLE IF EXISTS `financial`;
CREATE TABLE `financial`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID\r\n',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '支出说明\r\n',
  `price` double NULL DEFAULT NULL COMMENT '支出全部',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '支出时间',
  `department_id` int(10) NULL DEFAULT NULL COMMENT '支出部门',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '财务支出表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of financial
-- ----------------------------
INSERT INTO `financial` VALUES (1, '买水果', 1000, '2024-06-02 20:24:11', 1);
INSERT INTO `financial` VALUES (2, '发工资2', 20000, '2024-06-02 20:28:45', 2);
INSERT INTO `financial` VALUES (3, NULL, 80, '2024-08-01 00:00:00', 3);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建时间',
  `user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '公告信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '今天系统正式上线，开始内测', '今天系统正式上线，开始内测', '2023-09-05', 'admin');
INSERT INTO `notice` VALUES (2, '所有功能都已完成，可以正常使用', '所有功能都已完成，可以正常使用', '2023-09-05', 'admin');
INSERT INTO `notice` VALUES (3, '今天天气很不错，可以出去一起玩了', '今天天气很不错，可以出去一起玩了', '2023-09-05', 'admin');

-- ----------------------------
-- Table structure for resourceapply
-- ----------------------------
DROP TABLE IF EXISTS `resourceapply`;
CREATE TABLE `resourceapply`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `employee_id` int(10) NULL DEFAULT NULL COMMENT '员工ID',
  `department_id` int(10) NULL DEFAULT NULL COMMENT '部门ID',
  `resources_id` int(10) NULL DEFAULT NULL COMMENT '资产ID',
  `num` int(10) NULL DEFAULT NULL COMMENT '申请数量',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '申请时间',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审批状态',
  `process` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审批进展',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审批说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '资产申请审批表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resourceapply
-- ----------------------------
INSERT INTO `resourceapply` VALUES (1, 1, 2, 1, 3, NULL, '待主管审批', '主管审批中', NULL);
INSERT INTO `resourceapply` VALUES (2, 1, 2, 1, 33, NULL, '待主管审批', '主管审批中', NULL);
INSERT INTO `resourceapply` VALUES (3, 1, 2, 1, 5, '2024-06-04 18:16:16', '审批不通过', '主管审批中', '没钱');
INSERT INTO `resourceapply` VALUES (4, 1, 2, 1, 2, '2024-06-04 18:34:34', '审批通过', '主管审批中', NULL);
INSERT INTO `resourceapply` VALUES (5, 1, 2, 1, 1, '2024-06-04 21:43:52', '待主管审批', '主管审批中', NULL);
INSERT INTO `resourceapply` VALUES (6, 1, 2, 1, 1, '2024-06-04 21:48:54', '管理员审批不通过', '审批结束', NULL);
INSERT INTO `resourceapply` VALUES (7, 1, 2, 1, 2, '2024-06-04 23:09:39', '待主管审批', '主管审批中', NULL);
INSERT INTO `resourceapply` VALUES (8, 1, 2, 1, 26, '2024-06-04 23:09:51', '待管理员审批', '管理员审批中', NULL);
INSERT INTO `resourceapply` VALUES (9, 1, 2, 1, 12, '2024-06-04 23:19:42', '待管理员审批', '管理员审批中', NULL);
INSERT INTO `resourceapply` VALUES (10, 1, 2, 1, 12, '2024-06-04 23:26:14', '主管审批不通过', '审批结束', NULL);
INSERT INTO `resourceapply` VALUES (11, 1, 2, 1, 1, '2024-06-04 23:28:56', '主管审批不通过', '审批结束', NULL);
INSERT INTO `resourceapply` VALUES (12, 1, 2, 1, 23, '2024-06-04 23:33:26', '审批通过', '审批结束', NULL);
INSERT INTO `resourceapply` VALUES (13, 1, 2, 1, 11, '2024-06-05 14:42:07', '待主管审批', '主管审批中', NULL);

-- ----------------------------
-- Table structure for resources
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '资产名称',
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '资产图片',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '资产价格',
  `num` int(10) NULL DEFAULT 0 COMMENT '剩余数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '资产信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO `resources` VALUES (1, '笔记本', 'http://localhost:9090/files/1717487311856-VRChat_2023-06-15_20-35-03.480_1920x1080.png', 4999.00, 9);
INSERT INTO `resources` VALUES (2, '1', 'http://localhost:9090/files/1717487292603-VRChat_2023-06-02_00-52-30.147_1920x1080_Player.png', 1.00, 1);

-- ----------------------------
-- Table structure for salary
-- ----------------------------
DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `employee_id` int(10) NULL DEFAULT NULL COMMENT '员工ID',
  `department_id` int(10) NULL DEFAULT NULL COMMENT '部门ID',
  `year` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '年月',
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发放时间',
  `price` double NULL DEFAULT NULL COMMENT '发放薪资',
  `note` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '薪资信息表\r\n' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of salary
-- ----------------------------
INSERT INTO `salary` VALUES (1, 1, 2, '2024-07', '2024-06-10 00:00:00', 10000, '无');
INSERT INTO `salary` VALUES (2, 1, 2, '2024-03', '2024-06-02 15:13:25', 10500, '奖金500');
INSERT INTO `salary` VALUES (3, 2, 2, '2023-01', '2023-01-15 15:00:00', 7000, '无');
INSERT INTO `salary` VALUES (4, 2, 2, '2024-06', '2024-06-15 15:42:41', 10000, '奖金200');
INSERT INTO `salary` VALUES (5, 3, 1, '2024-03', '2024-03-15 00:00:00', 8000, NULL);
INSERT INTO `salary` VALUES (6, 7, 4, '2024-08', '2024-08-01 00:00:00', 82677852, NULL);
INSERT INTO `salary` VALUES (7, 4, 1, '2024-07', '2024-08-01 00:00:00', 200000, NULL);

SET FOREIGN_KEY_CHECKS = 1;
