/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : traveloka

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 12/07/2021 01:54:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for billing
-- ----------------------------
DROP TABLE IF EXISTS `billing`;
CREATE TABLE `billing`  (
  `customer_id` int(11) NULL DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `month` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `year` int(255) NULL DEFAULT NULL,
  `billing` int(255) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of billing
-- ----------------------------
INSERT INTO `billing` VALUES (110, 'netplik', 'september', 2002, 200, 'on');
INSERT INTO `billing` VALUES (110, 'netplik', 'march', 2002, 2000, 'on');
INSERT INTO `billing` VALUES (112, 'netplik', 'march', 2002, 200, 'on');
INSERT INTO `billing` VALUES (113, 'disiney', 'march', 2002, 200, 'on');
INSERT INTO `billing` VALUES (111, 'netplik', 'november', 2002, 210, 'expired');
INSERT INTO `billing` VALUES (111, 'netplik', 'juni', 2002, 210, 'on');
INSERT INTO `billing` VALUES (111, 'disiney', 'agustus', 2002, 210, 'expired');
INSERT INTO `billing` VALUES (113, 'disiney', 'april', 2002, 900, 'on');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `customer_id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES (110, 'malih', 'malih@go.co');
INSERT INTO `customer` VALUES (111, 'joko', 'xxx@go.com');
INSERT INTO `customer` VALUES (112, 'karyo', 'zzz@gmail.com');
INSERT INTO `customer` VALUES (113, 'mumun', 'mumun@mun.com');

-- ----------------------------
-- Table structure for pascabayar
-- ----------------------------
DROP TABLE IF EXISTS `pascabayar`;
CREATE TABLE `pascabayar`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paket_a` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `harga_paket_a` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paket_b` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `harga_paket_b` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paket_c` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `harga_paket_c` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1224 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pascabayar
-- ----------------------------
INSERT INTO `pascabayar` VALUES (1, 'indiavision', '1 bulan', '5000', '2 bulan', '9000', '3 bulan', '13000');
INSERT INTO `pascabayar` VALUES (1217, 'bisnet', '1 bulan', '6000', '2 bulan', '11000', '3 bulan', '15000');
INSERT INTO `pascabayar` VALUES (1223, 'orentipi', '1 bulan', '4000', '2 bulan', '7000', '3 bulan', '11000');

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NULL DEFAULT NULL,
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payment_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `packet` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` int(20) NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of payment
-- ----------------------------
INSERT INTO `payment` VALUES (1, 110, 'orentipi', 'pascabayar', '2 bulan', 200, 'done');
INSERT INTO `payment` VALUES (14, 1178, 'mola', 'prabayar', '2 bulan', 700, 'done');
INSERT INTO `payment` VALUES (15, 119, 'netplik', 'pascabayar', '2 bulan', 700, 'done');
INSERT INTO `payment` VALUES (33, 118, 'netplik', 'pascabayar', '2 bulan', 700, 'done');

-- ----------------------------
-- Table structure for prabayar
-- ----------------------------
DROP TABLE IF EXISTS `prabayar`;
CREATE TABLE `prabayar`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paket_a` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `harga_paket_a` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paket_b` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `harga_paket_b` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paket_c` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `harga_paket_c` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1224 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of prabayar
-- ----------------------------
INSERT INTO `prabayar` VALUES (1, 'netplik', '1 bulan', '400', '2 bulan', '700', '3 bulan', '1000');
INSERT INTO `prabayar` VALUES (1217, 'mola', '1 bulan', '200', '2 bulan', '350', '3 bulan', '500');
INSERT INTO `prabayar` VALUES (1223, 'disiney', '1 bulan', '300', '2 bulan', '550', '3 bulan', '700');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (25, 'joko@go.co', 'as12345', 'joko', NULL);
INSERT INTO `user` VALUES (26, 'karyo@go.co', '1131', 'karyo', NULL);

SET FOREIGN_KEY_CHECKS = 1;
