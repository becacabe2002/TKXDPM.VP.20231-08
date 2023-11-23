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
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`id`) REFERENCES `media` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `card`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cardCode` varchar(15) NOT NULL,
  `owner` varchar(45) NOT NULL,
  `cvvCode` varchar(3) NOT NULL,
  `dateExpired` varchar(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cd`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cd` (
  `id` int NOT NULL,
  `artist` varchar(45) NOT NULL,
  `recordLabel` varchar(45) NOT NULL,
  `musicType` varchar(45) NOT NULL,
  `releasedDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `cd_ibfk_1` FOREIGN KEY (`id`) REFERENCES `media` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `deliveryinfo`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deliveryinfo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
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
  `discType` enum('HD-DVD','Blu-ray') NOT NULL DEFAULT 'HD-DVD',
  `director` varchar(45) NOT NULL,
  `runtime` int NOT NULL,
  `studio` varchar(45) NOT NULL,
  `subtitle` varchar(45) NOT NULL,
  `releasedDate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `dvd_ibfk_1` FOREIGN KEY (`id`) REFERENCES `media` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invoice`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `id` int NOT NULL,
  `totalAmount` int NOT NULL,
  `orderId` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `orderId` (`orderId`),
  CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `orderinfo` (`id`)
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
  `imageUrl` varchar(45) DEFAULT NULL,
  `fastShipping` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orderhistory`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderhistory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` char(36) NOT NULL,
  `orderId` int NOT NULL,
  PRIMARY KEY (`id`,`uid`,`orderId`),
  KEY `uid` (`uid`),
  KEY `orderId` (`orderId`),
  CONSTRAINT `orderhistory_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `users` (`externalUID`),
  CONSTRAINT `orderhistory_ibfk_2` FOREIGN KEY (`orderId`) REFERENCES `orderinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orderinfo`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderinfo` (
  `id` int NOT NULL,
  `shippingFees` varchar(45) DEFAULT NULL,
  `DeliveryInfoId` int NOT NULL,
  PRIMARY KEY (`id`,`DeliveryInfoId`),
  KEY `DeliveryInfoId` (`DeliveryInfoId`),
  CONSTRAINT `orderinfo_ibfk_1` FOREIGN KEY (`DeliveryInfoId`) REFERENCES `deliveryinfo` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ordermedia`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordermedia` (
  `orderID` int NOT NULL,
  `price` int NOT NULL,
  `quantity` int NOT NULL,
  `mediaId` int NOT NULL,
  PRIMARY KEY (`orderID`,`mediaId`),
  KEY `mediaId` (`mediaId`),
  CONSTRAINT `ordermedia_ibfk_1` FOREIGN KEY (`orderID`) REFERENCES `orderinfo` (`id`),
  CONSTRAINT `ordermedia_ibfk_2` FOREIGN KEY (`mediaId`) REFERENCES `media` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `paymenttransaction`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paymenttransaction` (
  `id` int NOT NULL,
  `createAt` datetime NOT NULL,
  `content` varchar(45) NOT NULL,
  `method` varchar(45) DEFAULT NULL,
  `cardId` int NOT NULL,
  `invoiceId` int NOT NULL,
  PRIMARY KEY (`id`,`cardId`,`invoiceId`),
  KEY `cardId` (`cardId`),
  KEY `invoiceId` (`invoiceId`),
  CONSTRAINT `paymenttransaction_ibfk_1` FOREIGN KEY (`cardId`) REFERENCES `card` (`id`),
  CONSTRAINT `paymenttransaction_ibfk_2` FOREIGN KEY (`invoiceId`) REFERENCES `invoice` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rushdeliveryinfo`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rushdeliveryinfo` (
  `id` int NOT NULL,
  `shippingTime` datetime NOT NULL,
  `rushDeliveryInstructions` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `rushdeliveryinfo_ibfk_1` FOREIGN KEY (`id`) REFERENCES `deliveryinfo` (`id`)
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
  `createdAt` datetime DEFAULT NULL,
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
  ('20231123015502');
UNLOCK TABLES;
