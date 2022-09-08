CREATE DATABASE  IF NOT EXISTS `vmo-note` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `vmo-note`;
-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: vmo-note
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `check_box`
--

DROP TABLE IF EXISTS `check_box`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `check_box` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `selected` bit(1) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `check_box_note_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmhnhaccgrl118dmbs22f6o2em` (`check_box_note_id`),
  CONSTRAINT `FKmhnhaccgrl118dmbs22f6o2em` FOREIGN KEY (`check_box_note_id`) REFERENCES `check_box_note` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check_box`
--

LOCK TABLES `check_box` WRITE;
/*!40000 ALTER TABLE `check_box` DISABLE KEYS */;
INSERT INTO `check_box` VALUES (28,'2022-09-08 09:19:44.029127',1,NULL,1,'checkbox1',_binary '\0','value',29),(29,'2022-09-08 09:19:44.033125',1,NULL,1,'checkbox2',_binary '','value',29),(30,'2022-09-08 09:19:44.035122',1,NULL,1,'checkbox3',_binary '\0','value',29),(31,'2022-09-08 09:25:10.015233',2,NULL,2,'checkbox1',_binary '\0','value',30),(32,'2022-09-08 09:25:10.019234',2,NULL,2,'checkbox2',_binary '','value',30),(33,'2022-09-08 09:25:10.022230',2,NULL,2,'checkbox3',_binary '\0','value',30);
/*!40000 ALTER TABLE `check_box` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `check_box_note`
--

DROP TABLE IF EXISTS `check_box_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `check_box_note` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `check_box_status` varchar(255) DEFAULT NULL,
  `note_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKieoqcn0ox9drim8lo72yky7op` (`note_id`),
  CONSTRAINT `FKieoqcn0ox9drim8lo72yky7op` FOREIGN KEY (`note_id`) REFERENCES `note` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `check_box_note`
--

LOCK TABLES `check_box_note` WRITE;
/*!40000 ALTER TABLE `check_box_note` DISABLE KEYS */;
INSERT INTO `check_box_note` VALUES (29,'2022-09-08 09:19:44.014134',1,'2022-09-08 09:19:55.208352',1,NULL,48),(30,'2022-09-08 09:25:09.993265',2,NULL,2,NULL,52);
/*!40000 ALTER TABLE `check_box_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `image_note`
--

DROP TABLE IF EXISTS `image_note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image_note` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `note_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1wswidqp6qtxnmc1ys8elj3dg` (`note_id`),
  CONSTRAINT `FK1wswidqp6qtxnmc1ys8elj3dg` FOREIGN KEY (`note_id`) REFERENCES `note` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image_note`
--

LOCK TABLES `image_note` WRITE;
/*!40000 ALTER TABLE `image_note` DISABLE KEYS */;
INSERT INTO `image_note` VALUES (12,'2022-09-08 09:24:42.697403',2,NULL,2,'data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdH',51);
/*!40000 ALTER TABLE `image_note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `note`
--

DROP TABLE IF EXISTS `note`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `note` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `note_type` varchar(255) DEFAULT NULL,
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `completed` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmoddtnuw3yy6ct34xnw6u0boh` (`user_id`),
  CONSTRAINT `FKmoddtnuw3yy6ct34xnw6u0boh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `note`
--

LOCK TABLES `note` WRITE;
/*!40000 ALTER TABLE `note` DISABLE KEYS */;
INSERT INTO `note` VALUES (46,'2022-09-08 09:18:03.153227',1,NULL,1,'Go to the market tonight','BASIC_NOTE','Go market',1,_binary '\0'),(47,'2022-09-08 09:18:57.184759',1,NULL,1,'Go to the gym','BASIC_NOTE','Go gym',1,_binary '\0'),(48,'2022-09-08 09:19:44.015147',1,'2022-09-08 09:19:55.208352',1,'What do you want to eat?','CHECKBOX_NOTE','Eating tonight',1,_binary ''),(49,'2022-09-08 09:22:08.805086',2,NULL,2,'Linh like a beer','BASIC_NOTE','Linh eating',2,_binary '\0'),(50,'2022-09-08 09:22:50.994486',2,'2022-09-08 09:25:21.997892',2,'Linh like a gym','BASIC_NOTE','Linh gym',2,_binary ''),(51,'2022-09-08 09:24:42.697403',2,NULL,2,'Linh want to drink beer','IMAGE_NOTE','Linh like beer',2,_binary '\0'),(52,'2022-09-08 09:25:09.993265',2,NULL,2,'Linh want ?','CHECKBOX_NOTE','Linh eating',2,_binary '\0');
/*!40000 ALTER TABLE `note` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `description` varchar(254) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8sewwnpamngi6b1dwaa88askk` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'2022-09-07 15:01:58.156762',NULL,NULL,NULL,'user','USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `created_by` bigint DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` bigint DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(254) DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2022-02-24 11:42:04.815692',NULL,NULL,NULL,'$2a$10$AqVBptHHbxuJnqq9E2UR3eHXnp48YW.3o74DZ7YayFLyCBgO0M2xy','daitt',NULL,NULL),(2,'2022-09-08 00:07:03.859376',NULL,NULL,NULL,'$2a$10$AqVBptHHbxuJnqq9E2UR3eHXnp48YW.3o74DZ7YayFLyCBgO0M2xy','linhpv','jonydang@gmail.com','Hoang Thuy Linh');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,1);
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

-- Dump completed on 2022-09-08  9:48:18
