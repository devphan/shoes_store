/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : shoes_store

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 27/06/2022 20:48:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `thumbnail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES (1, 'Nike', 'imgNike.png');
INSERT INTO `brand` VALUES (2, 'Adidas', 'imgAdidas.png');
INSERT INTO `brand` VALUES (3, 'Jordan', 'imgJordan.png');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_46ccwnsi9409t36lurvtyljak`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, 'Nam');
INSERT INTO `category` VALUES (2, 'Nữ');
INSERT INTO `category` VALUES (3, 'Trẻ em');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `create_at` datetime(6) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `price` float NOT NULL,
  `brand_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKs6cydsualtsrprvlf2bb3lcam`(`brand_id`) USING BTREE,
  CONSTRAINT `FKs6cydsualtsrprvlf2bb3lcam` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '2022-06-23 16:30:24.806000', 'Giày Nike', 'imageNike001.img', 'Nike 001', 99000, 1);
INSERT INTO `product` VALUES (2, '2022-06-23 16:30:35.383000', 'Giày Nike', 'imageNike002.img', 'Nike 002', 99000, 1);
INSERT INTO `product` VALUES (3, '2022-06-23 16:31:05.985000', 'Giày Adidas', 'imageAdidas001.img', 'Adidas 001', 99000, 2);
INSERT INTO `product` VALUES (4, '2022-06-23 16:31:14.027000', 'Giày Adidas', 'imageAdidas002.img', 'Adidas 002', 99000, 2);
INSERT INTO `product` VALUES (5, '2022-06-23 16:31:33.530000', 'Giày Jordan', 'imageJordan001.img', 'Jordan 001', 99000, 3);
INSERT INTO `product` VALUES (6, '2022-06-23 16:31:48.773000', 'Giày Jordan', 'imageJordan002.img', 'Jordan 002', 99000, 3);

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`  (
  `product_id` bigint(0) NOT NULL,
  `category_id` int(0) NOT NULL,
  INDEX `FKkud35ls1d40wpjb5htpp14q4e`(`category_id`) USING BTREE,
  INDEX `FK2k3smhbruedlcrvu6clued06x`(`product_id`) USING BTREE,
  CONSTRAINT `FK2k3smhbruedlcrvu6clued06x` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKkud35ls1d40wpjb5htpp14q4e` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (1, 1);
INSERT INTO `product_category` VALUES (1, 2);
INSERT INTO `product_category` VALUES (2, 1);
INSERT INTO `product_category` VALUES (2, 2);
INSERT INTO `product_category` VALUES (3, 1);
INSERT INTO `product_category` VALUES (3, 2);
INSERT INTO `product_category` VALUES (4, 1);
INSERT INTO `product_category` VALUES (4, 2);
INSERT INTO `product_category` VALUES (5, 1);
INSERT INTO `product_category` VALUES (5, 2);
INSERT INTO `product_category` VALUES (6, 1);
INSERT INTO `product_category` VALUES (6, 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `full_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role` enum('USER','ADMIN') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_ob8kqyqqgmefl0aco34akdtpe`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, NULL, NULL, 'admin@gmail.com', NULL, '$2a$10$XZTn5Y7TPREnorsFwmdEyO77A6TeHGWS45hyrLDWseoBLwnNJK95q', NULL, 'ADMIN');
INSERT INTO `user` VALUES (2, NULL, NULL, 'longpv@gmail.com', NULL, '$2a$10$Y35..zkhj0cdLdOTzlnlPOrPBEHB6GgC6aOroe1ah52pruAzM6B.S', NULL, 'USER');

SET FOREIGN_KEY_CHECKS = 1;
