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
-- Table structure for table `book`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int NOT NULL,
  `author` varchar(45) NOT NULL,
  `coverType` enum('hardcover','paperback') NOT NULL DEFAULT 'hardcover',
  `publisher` varchar(45) NOT NULL,
  `publishDate` date NOT NULL,
  `numOfPages` int NOT NULL,
  `language` varchar(45) NOT NULL,
  `bookCategory` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`id`) REFERENCES `Media` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `CD`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CD` (
  `id` int NOT NULL,
  `artist` varchar(45) NOT NULL,
  `recordLabel` varchar(45) NOT NULL,
  `musicType` varchar(45) NOT NULL,
  `releasedDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `cd_ibfk_1` FOREIGN KEY (`id`) REFERENCES `Media` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `DeliveryInfo`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DeliveryInfo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `province` varchar(45) DEFAULT NULL,
  `instructions` varchar(200) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `dvd`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dvd` (
  `id` int NOT NULL,
  `discType` enum('HD_DVD','Blu_ray') NOT NULL DEFAULT 'HD_DVD',
  `director` varchar(45) NOT NULL,
  `runtime` int NOT NULL,
  `studio` varchar(45) NOT NULL,
  `subtitle` varchar(45) NOT NULL,
  `releasedDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `dvd_ibfk_1` FOREIGN KEY (`id`) REFERENCES `Media` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Invoice`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Invoice` (
  `id` int NOT NULL,
  `totalAmount` int NOT NULL,
  `orderId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `orderId` (`orderId`),
  CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `OrderInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `media`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `media` (
  `id` int NOT NULL AUTO_INCREMENT,
  `category` enum('Book','CD','DVD') NOT NULL DEFAULT 'Book',
  `price` int NOT NULL,
  `quantity` int NOT NULL,
  `title` varchar(45) NOT NULL,
  `value` int NOT NULL,
  `imageUrl` varchar(200) DEFAULT NULL,
  `fastShipping` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orderHistory`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderHistory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` char(36) NOT NULL,
  `orderId` int NOT NULL,
  `paid` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`uid`,`orderId`),
  KEY `uid` (`uid`),
  KEY `orderId` (`orderId`),
  CONSTRAINT `orderhistory_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `Users` (`externalUID`),
  CONSTRAINT `orderhistory_ibfk_2` FOREIGN KEY (`orderId`) REFERENCES `OrderInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `OrderInfo`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `OrderInfo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `shippingFees` int DEFAULT NULL,
  `DeliveryInfoId` int NOT NULL,
  PRIMARY KEY (`id`,`DeliveryInfoId`),
  KEY `DeliveryInfoId` (`DeliveryInfoId`),
  CONSTRAINT `orderinfo_ibfk_1` FOREIGN KEY (`DeliveryInfoId`) REFERENCES `DeliveryInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `OrderMedia`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `OrderMedia` (
  `orderID` int NOT NULL,
  `price` int NOT NULL,
  `quantity` int NOT NULL,
  `mediaId` int NOT NULL,
  PRIMARY KEY (`orderID`,`mediaId`),
  KEY `mediaId` (`mediaId`),
  CONSTRAINT `ordermedia_ibfk_1` FOREIGN KEY (`orderID`) REFERENCES `OrderInfo` (`id`),
  CONSTRAINT `ordermedia_ibfk_2` FOREIGN KEY (`mediaId`) REFERENCES `Media` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rushdeliveryinfo`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rushdeliveryinfo` (
  `id` int NOT NULL,
  `shippingTime` date DEFAULT NULL,
  `rushDeliveryInstructions` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `rushdeliveryinfo_ibfk_1` FOREIGN KEY (`id`) REFERENCES `DeliveryInfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `schema_migrations`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `schema_migrations` (
  `version` varchar(128) NOT NULL,
  PRIMARY KEY (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uname` varchar(45) NOT NULL,
  `createdAt` date NOT NULL DEFAULT (curdate()),
  `role` enum('admin','user') NOT NULL DEFAULT 'user',
  `externalUID` char(36) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `externalUID` (`externalUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'aims'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed

--
-- Dbmate schema migrations
--

LOCK TABLES `schema_migrations` WRITE;
INSERT INTO `schema_migrations` (version) VALUES
  ('20231123012025'),
  ('20231123013445'),
  ('20231123015502'),
  ('20231123033418'),
  ('20231123141144'),
  ('20231127092441'),
  ('20231127094154');
UNLOCK TABLES;
