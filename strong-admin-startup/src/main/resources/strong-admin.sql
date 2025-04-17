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
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
                        `id` varchar(64) NOT NULL COMMENT '主键',
                        `name` varchar(100) NOT NULL COMMENT '菜单名称',
                        `path` varchar(200) DEFAULT NULL COMMENT '路径',
                        `parent_id` bigint DEFAULT NULL COMMENT '父级菜单',
                        `sort` tinyint DEFAULT NULL COMMENT '排序',
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
INSERT INTO `menu` VALUES ('1349042316480565257','权限管理','/permission',NULL,1,NULL,NULL,NULL,NULL,0),('1349042316480565258','页面权限','/permission/page',1349042316480565257,0,NULL,NULL,NULL,NULL,0),('1349042316480565259','指令权限','/permission/directive',1349042316480565257,1,NULL,NULL,NULL,NULL,0),('1349042316480565260','角色权限','/permission/role',1349042316480565257,2,NULL,NULL,NULL,NULL,0),('1349042316480565262','外部链接','/external-link',NULL,4,NULL,NULL,NULL,NULL,0),('1349042316480565263','首页','/',NULL,0,NULL,NULL,NULL,NULL,0),('1352283695319044096','用户管理','/user',NULL,2,NULL,NULL,NULL,NULL,0),('1352283695319044098','用户列表','/user/list',1352283695319044096,0,NULL,NULL,NULL,NULL,0),('1352627502413729792','菜单管理','/menu',NULL,3,NULL,NULL,NULL,NULL,0),('1352627604008161280','菜单列表','/menu/list',1352627502413729792,0,NULL,NULL,NULL,NULL,0);
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
INSERT INTO `role` VALUES ('1347614852273360898','Admin','admin','Super Administrator. Have access to view all pages.',NULL,NULL,NULL,NULL,0),('1352668941877202944','Editor','editor','Normal Editor. Can see all pages except permission page.',NULL,NULL,NULL,NULL,0);
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
INSERT INTO `role_menu` VALUES ('1352628029482553344','1347614852273360898','1349042316480565263',NULL,NULL,NULL,NULL,0),('1352628029482553345','1347614852273360898','1349042316480565257',NULL,NULL,NULL,NULL,0),('1352628029482553346','1347614852273360898','1349042316480565258',NULL,NULL,NULL,NULL,0),('1352628029482553347','1347614852273360898','1349042316480565259',NULL,NULL,NULL,NULL,0),('1352628029482553348','1347614852273360898','1349042316480565260',NULL,NULL,NULL,NULL,0),('1352628029482553349','1347614852273360898','1352283695319044096',NULL,NULL,NULL,NULL,0),('1352628029482553350','1347614852273360898','1352283695319044098',NULL,NULL,NULL,NULL,0),('1352628029482553351','1347614852273360898','1352627502413729792',NULL,NULL,NULL,NULL,0),('1352628029482553352','1347614852273360898','1352627604008161280',NULL,NULL,NULL,NULL,0),('1352628029482553353','1347614852273360898','1349042316480565262',NULL,NULL,NULL,NULL,0),('1352676994013941760','1352668941877202944','1349042316480565263',NULL,NULL,NULL,NULL,0),('1352676994013941761','1352668941877202944','1349042316480565257',NULL,NULL,NULL,NULL,0),('1352676994013941762','1352668941877202944','1349042316480565258',NULL,NULL,NULL,NULL,0),('1352676994013941763','1352668941877202944','1349042316480565259',NULL,NULL,NULL,NULL,0),('1352676994013941764','1352668941877202944','1349042316480565260',NULL,NULL,NULL,NULL,0),('1352676994013941765','1352668941877202944','1352283695319044096',NULL,NULL,NULL,NULL,0),('1352676994013941766','1352668941877202944','1352283695319044098',NULL,NULL,NULL,NULL,0),('1352676994013941767','1352668941877202944','1352627502413729792',NULL,NULL,NULL,NULL,0),('1352676994013941768','1352668941877202944','1352627604008161280',NULL,NULL,NULL,NULL,0);
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
INSERT INTO `user` VALUES ('1337099190876628888','admin','$2a$10$wG6s3Cba0wW3wWR9/RCLMekri1hObqryKabOfU6snjKT33BoFunT.','admin','file/2025-03-28/7ce7f41796ee460999463a85a9a73a63.gif',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0),('1352676616581107712','editor','$2a$10$goci3X3rVFHnJAgyVKUs6eA1dXOby5KqV82iExX.ssN8d9Y5E9Lpe','Editor','file/2025-03-26/8bae8c510b1c4dccadc7c9fc476240d2.gif',NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,0);
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
INSERT INTO `user_role` VALUES ('1352676625703718912','1352676616581107712','1352668941877202944',NULL,NULL,NULL,NULL,0),('1355187562083995648','1337099190876628888','1347614852273360898',NULL,NULL,NULL,NULL,0);
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

-- Dump completed on 2025-04-17 16:45:40
