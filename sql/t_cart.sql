/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : ry-cloud

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 10/08/2024 22:00:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_cart
-- ----------------------------
DROP TABLE IF EXISTS `t_cart`;
CREATE TABLE `t_cart`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `merchandise_id` int NULL DEFAULT NULL COMMENT '商品id',
  `user_id` bigint NULL DEFAULT NULL COMMENT '用户id',
  `num` int NULL DEFAULT NULL COMMENT '购买数量',
  `time` date NULL DEFAULT NULL COMMENT '添加数量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `goods`(`merchandise_id`) USING BTREE,
  CONSTRAINT `goods` FOREIGN KEY (`merchandise_id`) REFERENCES `t_merchandise` (`merchandise_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '购物车' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
