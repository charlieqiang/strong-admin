-- MySQL dump 10.13  Distrib 8.0.25, for Linux (x86_64)
--
-- Host: localhost    Database: strong_admin
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dept`
--

DROP TABLE IF EXISTS `dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dept` (
                        `id` varchar(64) NOT NULL COMMENT '主键',
                        `parent_id` varchar(64) DEFAULT NULL COMMENT '父级部门',
                        `name` varchar(50) NOT NULL COMMENT '部门名称',
                        `leader_user_id` varchar(200) DEFAULT NULL COMMENT '部门负责人id',
                        `sort` bigint DEFAULT NULL COMMENT '序号',
                        `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                        `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
                        `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
                        `deleted` smallint NOT NULL DEFAULT '0' COMMENT '逻辑删除，0否，1是',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dept`
--

LOCK TABLES `dept` WRITE;
/*!40000 ALTER TABLE `dept` DISABLE KEYS */;
/*!40000 ALTER TABLE `dept` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
                        `id` varchar(64) NOT NULL COMMENT '主键',
                        `name` varchar(100) NOT NULL COMMENT '菜单名称',
                        `code` varchar(50) NOT NULL COMMENT '编码',
                        `path` varchar(200) DEFAULT NULL COMMENT '路径',
                        `parent_id` bigint DEFAULT NULL COMMENT '父级菜单',
                        `sort` tinyint DEFAULT NULL COMMENT '排序',
                        `use_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '使用状态:1启用,2禁用  ',
                        `type` tinyint NOT NULL COMMENT '类型:1菜单,2按钮',
                        `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                        `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
                        `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
                        `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除，0否，1是',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES ('1349042139699040256','Example','example','/example',NULL,1,1,1,NULL,NULL,NULL,NULL,0),('1349042316480565248','Table','table','/example/table',1349042139699040256,2,1,1,NULL,NULL,NULL,NULL,0),('1349042316480565249','Tree','tree','/example/tree',1349042139699040256,3,1,1,NULL,NULL,NULL,NULL,0),('1349042316480565250','Form','form','/form',NULL,4,1,1,NULL,NULL,NULL,NULL,0),('1349042316480565251','Nested','nested','/nested',NULL,5,1,1,NULL,NULL,NULL,NULL,0),('1349042316480565252','Menu1','menu1','/nested/menu1',1349042316480565251,6,1,1,NULL,NULL,NULL,NULL,0),('1349042316480565253','Menu1-1','menu1-1','/nested/menu1/menu1-1',1349042316480565252,7,1,1,NULL,NULL,NULL,NULL,0),('1349042316480565254','Menu1-2','menu1-2','/nested/menu1/menu1-2',1349042316480565252,8,1,1,NULL,NULL,NULL,NULL,0),('1349042316480565255','Menu1-2-2','menu1-2-2','/nested/menu1/menu1-2/menu1-2-2',1349042316480565254,10,1,1,NULL,NULL,NULL,NULL,0),('1349042316480565256','Menu1-3','menu1-3','/nested/menu1/menu1-3',1349042316480565252,11,1,1,NULL,NULL,NULL,NULL,0),('1349042316480565257','Permission','permission','/permission',NULL,13,1,1,NULL,NULL,NULL,NULL,0),('1349042316480565258','PagePermission','permission-page','/permission/page',1349042316480565257,14,1,1,NULL,NULL,NULL,NULL,0),('1349042316480565259','DirectivePermission','permission-directive','/permission/directive',1349042316480565257,15,1,1,NULL,NULL,NULL,NULL,0),('1349042316480565260','RolePermission','permission-role','/permission/role',1349042316480565257,16,1,1,NULL,NULL,NULL,NULL,0),('1349042316480565262','External Link','external-link','/external-link',NULL,17,1,1,NULL,NULL,NULL,NULL,0),('1349042316480565263','Dashboard','dashboard','/',NULL,0,1,1,NULL,NULL,NULL,NULL,0),('1349042316480565265','Menu2','menu2','/nested/menu2',1349042316480565251,12,1,1,NULL,NULL,NULL,NULL,0),('1349042316480565267','Menu1-2-1','menu1-2-1','/nested/menu1/menu1-2/menu1-2-1',1349042316480565254,9,1,1,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
                        `id` varchar(64) NOT NULL COMMENT '主键',
                        `name` varchar(100) NOT NULL COMMENT '角色名称',
                        `code` varchar(100) NOT NULL COMMENT '角色编码',
                        `description` varchar(200) DEFAULT NULL COMMENT '角色描述',
                        `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                        `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
                        `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
                        `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除，0否，1是',
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('1347614852273360898','Admin','admin','Super Administrator. Have access to view all pages.',NULL,NULL,NULL,NULL,0),('1349007709605416968','editor','Editor','Normal Editor. Can see all pages except permission page',NULL,NULL,NULL,NULL,0),('1349007709605416969','Visitor','visitor','Just a visitor. Can only see the home page and the document page',NULL,NULL,NULL,NULL,0),('1349735800782934016','Operator','operator','Operator is calm under pressure, analytical, and highly resourceful.',NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_menu`
--

DROP TABLE IF EXISTS `role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_menu` (
                             `id` varchar(64) NOT NULL COMMENT '主键',
                             `role_id` varchar(64) NOT NULL COMMENT '角色主键',
                             `menu_id` varchar(64) NOT NULL COMMENT '菜单主键',
                             `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
                             `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
                             `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除，0否，1是',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_menu`
--

LOCK TABLES `role_menu` WRITE;
/*!40000 ALTER TABLE `role_menu` DISABLE KEYS */;
INSERT INTO `role_menu` VALUES ('1349736074402549760','1349735800782934016','1349042316480565263',NULL,NULL,NULL,NULL,0),('1349736137308721152','1347614852273360898','1349042316480565263',NULL,NULL,NULL,NULL,0),('1349736137308721153','1347614852273360898','1349042139699040256',NULL,NULL,NULL,NULL,0),('1349736137308721154','1347614852273360898','1349042316480565248',NULL,NULL,NULL,NULL,0),('1349736137308721155','1347614852273360898','1349042316480565249',NULL,NULL,NULL,NULL,0),('1349736137308721156','1347614852273360898','1349042316480565250',NULL,NULL,NULL,NULL,0),('1349736137308721157','1347614852273360898','1349042316480565251',NULL,NULL,NULL,NULL,0),('1349736137308721158','1347614852273360898','1349042316480565252',NULL,NULL,NULL,NULL,0),('1349736137308721159','1347614852273360898','1349042316480565253',NULL,NULL,NULL,NULL,0),('1349736137308721160','1347614852273360898','1349042316480565254',NULL,NULL,NULL,NULL,0),('1349736137308721161','1347614852273360898','1349042316480565267',NULL,NULL,NULL,NULL,0),('1349736137308721162','1347614852273360898','1349042316480565255',NULL,NULL,NULL,NULL,0),('1349736137308721163','1347614852273360898','1349042316480565256',NULL,NULL,NULL,NULL,0),('1349736137308721164','1347614852273360898','1349042316480565265',NULL,NULL,NULL,NULL,0),('1349736137308721165','1347614852273360898','1349042316480565257',NULL,NULL,NULL,NULL,0),('1349736137308721166','1347614852273360898','1349042316480565258',NULL,NULL,NULL,NULL,0),('1349736137308721167','1347614852273360898','1349042316480565259',NULL,NULL,NULL,NULL,0),('1349736137308721168','1347614852273360898','1349042316480565260',NULL,NULL,NULL,NULL,0),('1349736137308721169','1347614852273360898','1349042316480565262',NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `id` varchar(64) NOT NULL COMMENT '主键',
                        `account` varchar(255) NOT NULL COMMENT '账号',
                        `password` varchar(255) NOT NULL COMMENT '密码（加密存储）',
                        `username` varchar(50) DEFAULT NULL COMMENT '用户名',
                        `avatar` varchar(500) DEFAULT NULL COMMENT '头像',
                        `dept_id` varchar(64) DEFAULT NULL COMMENT '部门 ID',
                        `phone` varchar(50) DEFAULT NULL COMMENT '手机号',
                        `hired_date` datetime DEFAULT NULL COMMENT '入职时间',
                        `use_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '启用状态（1-启用，0-禁用）',
                        `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
                        `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                        `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
                        `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
                        `deleted` smallint NOT NULL DEFAULT '0' COMMENT '逻辑删除，0否，1是',
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `idx_account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1337099190876628888','admin','$2a$10$wG6s3Cba0wW3wWR9/RCLMekri1hObqryKabOfU6snjKT33BoFunT.','admin','http://cdn.chenlinqiang.cn/placeholder/avatar.gif',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),('1349053682197082118','editor','$2a$10$wG6s3Cba0wW3wWR9/RCLMekri1hObqryKabOfU6snjKT33BoFunT.','editor','http://cdn.chenlinqiang.cn/placeholder/avatar.gif',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),('1349053682197082119','visitor','$2a$10$wG6s3Cba0wW3wWR9/RCLMekri1hObqryKabOfU6snjKT33BoFunT.','visitor','http://cdn.chenlinqiang.cn/placeholder/avatar.gif',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
                             `id` varchar(64) NOT NULL COMMENT '主键',
                             `user_id` varchar(64) NOT NULL COMMENT '用户主键',
                             `role_id` varchar(64) NOT NULL COMMENT '角色主键',
                             `creator` varchar(64) DEFAULT NULL COMMENT '创建人',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `modifier` varchar(64) DEFAULT NULL COMMENT '修改人',
                             `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
                             `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除，0否，1是',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('1348644702941491208','1337099190876628888','1347614852273360898',NULL,NULL,NULL,NULL,0),('1349053682197082121','1349053682197082118','1349007709605416968',NULL,NULL,NULL,NULL,0),('1349053682197082122','1349053682197082119','1349007709605416969',NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-13 16:13:54