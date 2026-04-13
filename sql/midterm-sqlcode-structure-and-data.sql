-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: goods
-- ------------------------------------------------------
-- Server version	8.4.3

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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `productName` varchar(100) DEFAULT NULL,
  `description` text,
  `price` int DEFAULT NULL,
  `image` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (2,'IPad Air M1','IPad',10900000,'https://www.techone.vn/wp-content/uploads/2022/10/ipad-2022-256gb-4g-mau-bac.jpg'),(4,'IPad Mini 7','iPad mini 7 (2024) là máy tính bảng nhỏ gọn 8.3 inch, nổi bật với chip A17 Pro mạnh mẽ hỗ trợ Apple Intelligence, RAM 8GB và bộ nhớ cơ sở từ 128GB. Thiết bị giữ thiết kế nhôm nguyên khối, cổng USB-C, hỗ trợ Apple Pencil Pro, đáp ứng tốt nhu cầu làm việc và giải trí di động cao.',12000000,'https://cdn.tgdd.vn/Products/Images/522/331229/ipad-mini-7-wifi-purple-thumb-600x600.jpg'),(5,'iPhone 17 Pro Max 256GB','Released in September 2025, the iPhone 17 Pro Max features a 6.9-inch ProMotion OLED display, a heat-forged aluminum unibody, and an A19 Pro chip with 12GB RAM. Key features include a 48MP triple rear camera system, an 18MP front camera with Center Stage, improved battery life, and up to 2TB storage. It offers 8x optical-quality zoom and a durable Ceramic Shield.',38970000,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRUpEHXupXpMN6NlalznrMdzODvpA-dMrEj8w&s'),(6,'iPhone 17 128GB','Released in September 2025, the Apple iPhone 17 features a 6.3-inch Super Retina XDR display with 120Hz ProMotion, upgraded A19 chipset, 8GB RAM, and a 48MP Dual Fusion camera system. It boasts a more durable Ceramic Shield 2, thinner borders, and a 24MP selfie camera, providing a refined, fast, and highly capable \"standard\" flagship experience.',23490000,'https://www.digimap.co.id/cdn/shop/files/0788-APPMG6J4ID-A-1.jpg?v=1759804337&width=823'),(7,'Mac Mini M4','The 2024 Mac mini with M4 chip is a redesigned, ultra-compact desktop ($499–$599 base) featuring 16GB RAM, 256GB SSD, and 10-core CPU/GPU. It delivers high-end performance with extreme efficiency and minimal noise in a much smaller footprint (5x5 inches), with options for the M4 Pro chip.',12970000,'https://www.apple.com/newsroom/images/2024/10/apples-new-mac-mini-apples-new-mac-mini-is-more-mighty-more-mini-and-built-for-apple-intelligence/tile/Apple-Mac-mini-hero-lp.jpg.news_app_ed.jpg'),(8,'iMac M4','The 2024 24-inch iMac with M4 chip is a powerful, ultra-thin all-in-one desktop optimized for Apple Intelligence, featuring a 4.5K Retina display and 12MP Center Stage camera. Starting at 16GB of unified memory, it delivers faster CPU/GPU performance (8-core or 10-core options), four Thunderbolt 4 ports, and color-matched accessories.',32890000,'https://store.storeimages.cdn-apple.com/1/as-images.apple.com/is/imac-color-unselect-202601-gallery-1_FMT_WHH?wid=690&hei=720&fmt=jpeg&qlt=90&.v=1765317364886'),(9,'iPad Pro 13 inch M4','iPad Pro 13 inch M4 512GB | Thế Giới A LôThe 13-inch iPad Pro (M4), released in May 2024, is an ultra-thin (5.1mm) and lightweight tablet featuring the powerful M4 chip, a vibrant Tandem OLED \"Ultra Retina XDR\" display, and support for Apple Intelligence. It boasts 256GB–2TB storage, 12MP front/back cameras, and exceptional performance for creative professionals.',34490000,'https://product.hstatic.net/200000348419/product/ipad-pro-m4-m_oi_3527ca36b0d4469a9f0bb16ca4ec7d7a_master.png');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-13  9:12:54
