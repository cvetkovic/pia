-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pia
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `asocijacija`
--

DROP TABLE IF EXISTS `asocijacija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `asocijacija` (
  `IdAsocijacija` int(11) NOT NULL AUTO_INCREMENT,
  `IdGrupa1` int(11) NOT NULL,
  `IdGrupa2` int(11) NOT NULL,
  `IdGrupa3` int(11) NOT NULL,
  `IdGrupa4` int(11) NOT NULL,
  `ResenjeAsocijacije` varchar(200) NOT NULL,
  PRIMARY KEY (`IdAsocijacija`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asocijacija`
--

LOCK TABLES `asocijacija` WRITE;
/*!40000 ALTER TABLE `asocijacija` DISABLE KEYS */;
INSERT INTO `asocijacija` VALUES (1,26,27,28,29,'stručni'),(2,30,31,32,33,'država'),(3,34,35,36,37,'aleksandar'),(4,38,39,40,41,'smisao'),(5,42,43,44,45,'muzika,klasika'),(6,46,47,48,49,'evropa'),(7,50,51,52,53,'sneg'),(8,54,55,56,57,'švedska'),(9,58,59,60,61,'zastava'),(10,62,63,64,65,'pablo pikaso'),(11,66,67,68,69,'sneg'),(12,70,71,72,73,'koncert'),(13,74,75,76,77,'atlantida'),(14,78,79,80,81,'mesečina'),(15,82,83,84,85,'sto, astal');
/*!40000 ALTER TABLE `asocijacija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asocijacija_grupa`
--

DROP TABLE IF EXISTS `asocijacija_grupa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `asocijacija_grupa` (
  `IdGrupa` int(11) NOT NULL AUTO_INCREMENT,
  `Rec1` varchar(45) NOT NULL,
  `Rec2` varchar(45) NOT NULL,
  `Rec3` varchar(45) NOT NULL,
  `Rec4` varchar(45) NOT NULL,
  `ResenjeGrupe` varchar(200) NOT NULL,
  PRIMARY KEY (`IdGrupa`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asocijacija_grupa`
--

LOCK TABLES `asocijacija_grupa` WRITE;
/*!40000 ALTER TABLE `asocijacija_grupa` DISABLE KEYS */;
INSERT INTO `asocijacija_grupa` VALUES (26,'skup','usavršavanje','rad','naučni','seminar'),(27,'ocenjivanje','kviz','izbor','članovi','žiri'),(28,'igrači','uigrani','snovi','vođa','tim'),(29,'jezički','ustaljeni','termin','lice','izraz'),(30,'francuska','kralj','sunce','versaj','luj xiv'),(31,'sedište','dablin','otava','kanbera','prestonica'),(32,'karakteristično','amblem','beleg','znamen','obeležje'),(33,'u trendu','umetnost','vremena','savremena','moderna'),(34,'igor','večera','pevač','semberija','knez'),(35,'bitka','reka','prospekt','sankt peterburg','neva'),(36,'elvis prisli','artur','ivica','lav','kralj'),(37,'antika','solun','denar','sever','makedonija'),(38,'crni','skeč','komedija','satira','humor'),(39,'energija','pitanje','put','drvo','život'),(40,'objekat','reč','zarez','tačka','rečenica'),(41,'mladi','ripli','šou','lovac','talenat'),(42,'poljska','pariz','varšava','kompozitor','šopen,frederik šopen'),(43,'muzika','njujork','rusija','pijanista','rahmanjinov'),(44,'austrija','sifilis','ave maria','klasičar','šubert'),(45,'devet simfonija','gluv','32 sonate','5 klavirskih koncerata','betoven, ludvig'),(46,'bog','rim','planeta','džin','jupiter'),(47,'putanja','antena','navođenje','gps','satelit'),(48,'australija','afrika','azija','antarktik','kontinent'),(49,'udruženje','savez','u','skup','unija'),(50,'vez','pesak','novac','mali','sitan'),(51,'miš','orao','dvor','medved','beli'),(52,'građa','glava','peglanje','pod','daska'),(53,'krstarenje','internet','jahanje','talasi','surfovanje'),(54,'fudbal','tenis','lampa','okrugli','sto'),(55,'park','škola','skupština','grejanje','klupa'),(56,'kralj','karijera','zub','svedok','kruna'),(57,'alfred','institut','nagrada','dinamit','nobel'),(58,'panda','torino','fabrika','bravo','fijat'),(59,'veselje','ruzmarin','kum','zlatna','svadba'),(60,'opera','stepen','zemlja','temperatura','skala'),(61,'svečanost','stajanje','državna','sveti sava','himna'),(62,'rog','knjiga','oči','torba','glava'),(63,'crveno','korida','snaga','horoskop','bik'),(64,'mlade','milostive','neudate','devojke','gospođice'),(65,'papa','sedište','grad','francuska','avinjon'),(66,'vez','pesak','novac','mali','sitan'),(67,'miš','orao','dvor','medved','beli'),(68,'građa','glava','peglanje','pod','daska'),(69,'krstarenje','internet','jahanje','talasi','surfovanje'),(70,'tempo','pravac','tuš','pratnja','muzika'),(71,'škola','skečevi','recitacija','pesmice','priredba'),(72,'doček','pokloni','repriza','noć','nova godina'),(73,'kongres','šnicla','šenbrun','dunav','beč'),(74,'filozof','atina','parnid','ljubav','platon'),(75,'koral','vulkan','pešak','blago','ostrvo'),(76,'urbana','ohrid','mit','predanje','legenda'),(77,'1999','besnilo','vreme čuda','zlatno runo','borislav pekić'),(78,'bogojavljenje','stranac','ptica','tiha','noć'),(79,'jačina','brzina','fluks','kandela','svetlost'),(80,'dragana','prozor','ljubavna','pesma','serenada'),(81,'gitara','alkohol','kalašnjikov','sarajevo','goran bregović'),(82,'istina','podrum','mušica','bure','vino'),(83,'skandinavija','kraljevina','nobel','oslo','švedska'),(84,'signal','aladin','petrolej','baterija','lampa'),(85,'točak','lopta','obli','zaokružen','okrugli');
/*!40000 ALTER TABLE `asocijacija_grupa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `igra_dana`
--

DROP TABLE IF EXISTS `igra_dana`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `igra_dana` (
  `IdIgraDana` int(11) NOT NULL AUTO_INCREMENT,
  `Slagalica` varchar(24) NOT NULL,
  `MojBroj` varchar(100) NOT NULL,
  `Skocko` int(11) NOT NULL,
  `IdSpojnica` int(11) NOT NULL,
  `IdAsocijacija` int(11) NOT NULL,
  `Datum` datetime NOT NULL,
  PRIMARY KEY (`IdIgraDana`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `igra_dana`
--

LOCK TABLES `igra_dana` WRITE;
/*!40000 ALTER TABLE `igra_dana` DISABLE KEYS */;
INSERT INTO `igra_dana` VALUES (7,'L,O,E,Ć,Ž,Ć,F,K,V,L,V,V','119,8,9,7,2,20,100',5304,24,5,'2019-06-07 00:00:00'),(8,'M,O,B,M,E,E,D,B,Ž,Č,D,Đ','301,6,2,5,6,20,50',4441,19,2,'2019-06-08 00:00:00'),(9,'K,S,H,K,H,O,R,D,O,O,NJ,H','540,8,7,6,4,15,50',1445,15,2,'2019-06-09 00:00:00'),(10,'I,O,Ž,M,S,D,N,A,K,Ž,M,P','307,4,7,6,5,10,100',1053,15,1,'2019-06-18 00:00:00'),(11,'N,O,K,Z,G,N,S,F,K,Š,NJ,T','232,6,9,8,3,15,25',1532,17,3,'2019-06-20 00:00:00'),(12,'A,J,D,Đ,M,E,B,U,O,E,I,DŽ','455,4,9,9,3,20,25',2452,18,4,'2019-06-19 00:00:00'),(13,'E,F,Đ,Ž,F,LJ,K,R,U,E,Ć,N','519,9,5,6,2,10,25',2112,15,6,'2019-06-21 00:00:00'),(14,'A,LJ,Đ,H,U,F,J,Š,M,Ć,K,B','34,5,6,6,8,20,50',5121,24,12,'2019-06-22 00:00:00');
/*!40000 ALTER TABLE `igra_dana` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `korisnici`
--

DROP TABLE IF EXISTS `korisnici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `korisnici` (
  `IdKorisnik` int(11) NOT NULL AUTO_INCREMENT,
  `Ime` varchar(45) DEFAULT NULL,
  `Prezime` varchar(45) DEFAULT NULL,
  `Email` varchar(45) NOT NULL,
  `Zanimanje` varchar(45) DEFAULT NULL,
  `KorisnickoIme` varchar(45) NOT NULL,
  `Lozinka` varchar(256) NOT NULL,
  `Pol` int(11) DEFAULT NULL,
  `DatumRodenja` date DEFAULT NULL,
  `Slika` varchar(70) DEFAULT NULL,
  `Privilegija` int(11) NOT NULL COMMENT '0 -> obican korisnik;\n1 -> supervizor;\n2 -> administrator;',
  `Odobren` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IdKorisnik`),
  UNIQUE KEY `KorisnickoIme_UNIQUE` (`KorisnickoIme`),
  UNIQUE KEY `IdKorisnik_UNIQUE` (`IdKorisnik`),
  UNIQUE KEY `Email_UNIQUE` (`Email`),
  UNIQUE KEY `Slika_UNIQUE` (`Slika`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnici`
--

LOCK TABLES `korisnici` WRITE;
/*!40000 ALTER TABLE `korisnici` DISABLE KEYS */;
INSERT INTO `korisnici` VALUES (42,'Administrator','Broj1','admin@slagalica.rs','admin','admin','fb8e69df72ba11b744f90248cfe907a3dcfec0ae',0,'2019-06-04',NULL,2,1),(45,'Igrac','Broj 1','igrac1@slagalica.rs','igrac1','igrac1','fb8e69df72ba11b744f90248cfe907a3dcfec0ae',0,'2019-06-02',NULL,0,1),(46,'Igrac','Broj 2','igrac2@slagalica.rs','igrac 2','igrac2','fb8e69df72ba11b744f90248cfe907a3dcfec0ae',0,'2019-06-03',NULL,0,1),(47,'Igrac','Broj 3','igrac3@slagalica.rs','igrac slagalice','igrac3','fb8e69df72ba11b744f90248cfe907a3dcfec0ae',0,'2019-06-04',NULL,0,1),(48,'Igrac','Broj 4','igrac4@slagalica.rs','igrac slagalice','igrac4','fb8e69df72ba11b744f90248cfe907a3dcfec0ae',0,'2019-06-05',NULL,0,1),(49,'Igrac','Broj 5','igrac5@slagalica.rs','igrac slagalice','igrac5','fb8e69df72ba11b744f90248cfe907a3dcfec0ae',0,'2019-06-06',NULL,0,1),(50,'Igrac','Broj 6','igrac6@slagalica.rs','igrac slagalice','igrac6','fb8e69df72ba11b744f90248cfe907a3dcfec0ae',0,'2019-06-07',NULL,0,1),(51,'Supervizor','Slagalice','supervizor@slagalica.rs','supervizor slagalice','supervizor','fb8e69df72ba11b744f90248cfe907a3dcfec0ae',0,'2019-06-01',NULL,1,1),(52,'Igrac ','Broj 7','igrac7@etf.rs','igrac slagalice','igrac7','fb8e69df72ba11b744f90248cfe907a3dcfec0ae',0,'2019-06-06',NULL,0,1),(53,'Igrac','Broj 8','igrac8@slagalica.rs','danguba','igrac8','fb8e69df72ba11b744f90248cfe907a3dcfec0ae',0,'2019-06-07',NULL,0,1),(54,'Igrac','Broj 9','igrac9@slagalica.rs','danguba','igrac9','fb8e69df72ba11b744f90248cfe907a3dcfec0ae',0,'2019-06-01',NULL,0,1);
/*!40000 ALTER TABLE `korisnici` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partija`
--

DROP TABLE IF EXISTS `partija`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `partija` (
  `IdPartija` int(11) NOT NULL AUTO_INCREMENT,
  `PlaviTakmicar` int(11) DEFAULT NULL,
  `CrveniTakmicar` int(11) DEFAULT '-1',
  `Samostalno` tinyint(4) NOT NULL,
  `Datum` datetime DEFAULT NULL,
  PRIMARY KEY (`IdPartija`),
  UNIQUE KEY `IdPartija_UNIQUE` (`IdPartija`)
) ENGINE=InnoDB AUTO_INCREMENT=319 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partija`
--

LOCK TABLES `partija` WRITE;
/*!40000 ALTER TABLE `partija` DISABLE KEYS */;
INSERT INTO `partija` VALUES (293,45,NULL,1,'2019-06-18 16:50:28'),(294,46,NULL,1,'2019-06-18 16:54:17'),(295,48,NULL,1,'2019-06-18 16:55:55'),(296,49,NULL,1,'2019-06-18 16:57:20'),(297,47,NULL,1,'2019-06-18 17:04:24'),(298,45,46,0,'2019-06-18 18:34:27'),(299,50,NULL,1,'2019-06-18 18:41:41'),(301,52,NULL,1,'2019-06-18 18:47:20'),(314,45,46,0,'2019-06-18 19:46:59'),(316,53,NULL,1,'2019-06-18 20:00:01'),(317,45,53,0,'2019-06-18 20:13:44'),(318,54,NULL,1,'2019-06-18 20:20:28');
/*!40000 ALTER TABLE `partija` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `pregledrezultatapodanu`
--

DROP TABLE IF EXISTS `pregledrezultatapodanu`;
/*!50001 DROP VIEW IF EXISTS `pregledrezultatapodanu`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `pregledrezultatapodanu` AS SELECT 
 1 AS `IdKorisnik`,
 1 AS `Ime`,
 1 AS `Prezime`,
 1 AS `Datum`,
 1 AS `SumaPoDanu`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `pregledrezultatapodanu_subquery`
--

DROP TABLE IF EXISTS `pregledrezultatapodanu_subquery`;
/*!50001 DROP VIEW IF EXISTS `pregledrezultatapodanu_subquery`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `pregledrezultatapodanu_subquery` AS SELECT 
 1 AS `IdKorisnik`,
 1 AS `ime`,
 1 AS `prezime`,
 1 AS `datum`,
 1 AS `zbir`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `rezultat`
--

DROP TABLE IF EXISTS `rezultat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `rezultat` (
  `IdRezultat` int(11) NOT NULL AUTO_INCREMENT,
  `IdPartija` int(11) NOT NULL,
  `Igra` int(11) NOT NULL,
  `PoenaPlavi` int(11) NOT NULL DEFAULT '0',
  `PoenaCrveni` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`IdRezultat`)
) ENGINE=InnoDB AUTO_INCREMENT=349 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rezultat`
--

LOCK TABLES `rezultat` WRITE;
/*!40000 ALTER TABLE `rezultat` DISABLE KEYS */;
INSERT INTO `rezultat` VALUES (228,293,0,10,0),(229,293,1,10,0),(230,293,2,0,0),(231,293,3,10,0),(232,293,4,75,0),(233,293,4,10,0),(234,294,0,10,0),(235,294,1,10,0),(236,294,2,0,0),(237,294,3,1,0),(238,294,4,75,0),(239,294,4,10,0),(240,295,0,0,0),(241,295,1,10,0),(242,295,2,10,0),(243,295,3,9,0),(244,295,4,75,0),(245,295,4,10,0),(246,296,0,10,0),(247,296,1,10,0),(248,296,2,10,0),(249,296,3,10,0),(250,296,4,75,0),(251,296,4,10,0),(252,297,0,10,0),(253,297,1,10,0),(254,297,2,10,0),(255,297,3,10,0),(256,297,4,75,0),(257,297,4,10,0),(258,298,0,0,0),(259,298,0,0,0),(260,298,1,0,0),(261,298,1,0,0),(262,298,3,2,0),(263,298,3,0,7),(264,298,3,0,2),(265,298,3,2,0),(266,298,4,75,0),(267,298,4,10,0),(268,298,4,0,70),(269,298,4,0,10),(270,299,0,10,0),(271,299,1,10,0),(272,299,2,10,0),(273,299,3,1,0),(274,299,4,75,0),(275,299,4,10,0),(276,301,0,0,0),(277,301,1,10,0),(278,301,2,0,0),(279,301,3,0,0),(280,306,0,0,0),(281,306,0,0,0),(282,306,1,0,0),(283,306,1,0,0),(284,307,0,0,0),(285,307,0,0,0),(286,307,1,0,0),(287,307,1,0,0),(288,308,0,0,0),(289,308,0,0,0),(290,308,1,0,0),(291,308,1,0,0),(292,308,1,0,0),(293,308,1,0,0),(294,309,0,0,0),(295,309,0,0,0),(296,309,1,0,0),(297,309,1,0,0),(298,310,0,0,0),(299,310,0,0,0),(300,310,1,0,0),(301,310,1,0,0),(302,311,0,0,0),(303,311,0,0,0),(304,311,1,0,0),(305,311,1,0,0),(306,312,0,0,0),(307,312,0,0,0),(308,312,1,0,0),(309,312,1,0,0),(310,313,0,0,0),(311,313,0,0,0),(312,313,1,0,0),(313,313,1,0,0),(314,314,0,0,0),(315,314,0,0,6),(316,314,1,0,10),(317,314,1,5,5),(318,314,3,10,0),(319,314,3,0,3),(320,314,3,7,0),(321,314,4,0,60),(322,314,4,0,10),(323,314,4,0,75),(324,314,4,0,10),(325,316,0,12,0),(326,316,1,10,0),(327,316,2,0,0),(328,316,3,1,0),(329,316,4,75,0),(330,316,4,10,0),(331,317,0,0,0),(332,317,0,0,0),(333,317,1,0,0),(334,317,1,0,0),(335,317,3,1,0),(336,317,3,0,1),(337,317,3,0,2),(338,317,3,2,0),(339,317,4,65,0),(340,317,4,10,0),(341,317,4,0,75),(342,317,4,0,10),(343,318,0,10,0),(344,318,1,10,0),(345,318,2,10,0),(346,318,3,1,0),(347,318,4,75,0),(348,318,4,10,0);
/*!40000 ALTER TABLE `rezultat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `slagalica`
--

DROP TABLE IF EXISTS `slagalica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `slagalica` (
  `IdRecSlagalica` int(11) NOT NULL AUTO_INCREMENT,
  `Rec` varchar(24) NOT NULL,
  PRIMARY KEY (`IdRecSlagalica`),
  UNIQUE KEY `IdSlagalica_UNIQUE` (`IdRecSlagalica`)
) ENGINE=InnoDB AUTO_INCREMENT=243 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `slagalica`
--

LOCK TABLES `slagalica` WRITE;
/*!40000 ALTER TABLE `slagalica` DISABLE KEYS */;
INSERT INTO `slagalica` VALUES (17,'proba'),(18,'probaj'),(19,'ćebe'),(20,'zid'),(21,'prozor'),(22,'kuća'),(23,'tavan'),(24,'sto'),(25,'astal'),(26,'miš'),(27,'tastatura'),(28,'dugme'),(29,'slovo'),(30,'laptop'),(31,'ekran'),(32,'sveska'),(33,'posteljina'),(34,'televizor'),(35,'vrata'),(36,'peškir'),(37,'fasada'),(38,'farba'),(39,'kreč'),(40,'jastuk'),(41,'jastučnica'),(42,'dušek'),(43,'ormar'),(44,'šifonjer'),(45,'vratanca'),(46,'majica'),(47,'pantalone'),(48,'čarape'),(49,'šorc'),(50,'papuče'),(51,'patike'),(52,'cipele'),(53,'patofne'),(54,'odelo'),(55,'košulja'),(56,'kravata'),(57,'igla'),(58,'brijač'),(59,'lek'),(60,'plazma'),(61,'kamera'),(62,'papir'),(63,'tegla'),(64,'poklopac'),(65,'avokado'),(66,'saksija'),(67,'zemlja'),(68,'kaktus'),(69,'staklo'),(70,'radijator'),(71,'kredenac'),(72,'termometar'),(73,'stepenice'),(74,'daska'),(75,'drvo'),(76,'koren'),(77,'list'),(78,'stablo'),(79,'cvet'),(80,'plod'),(81,'seme'),(82,'jagoda'),(83,'paprika'),(84,'paradajz'),(85,'trešnja'),(86,'višnja'),(87,'kajsija'),(88,'breskva'),(89,'grožđe'),(90,'lubenica'),(91,'bostan'),(92,'oko'),(93,'uši'),(94,'uvo'),(95,'oči'),(96,'nos'),(97,'ruka'),(98,'noga'),(99,'rame'),(100,'lakat'),(101,'nokat'),(102,'prst'),(103,'šaka'),(104,'stomak'),(105,'mozak'),(106,'vrat'),(107,'srce'),(108,'jetra'),(109,'bubreg'),(110,'želudac'),(111,'gušterača'),(112,'pankreas'),(113,'crevo'),(114,'kičma'),(115,'kost'),(116,'boranija'),(117,'grašak'),(118,'pasulj'),(119,'jabuka'),(120,'kruška'),(121,'dunja'),(122,'dinja'),(123,'fotelja'),(124,'ambalaža'),(125,'kućište'),(126,'procesor'),(127,'disk'),(128,'napajanje'),(129,'indeks'),(130,'flaša'),(131,'kesa'),(132,'šampon'),(133,'krema'),(134,'kvaka'),(135,'punjač'),(136,'dvorište'),(137,'avlija'),(138,'ulica'),(139,'automobil'),(140,'kapija'),(141,'prolaz'),(142,'komšinica'),(143,'komšija'),(144,'ograda'),(145,'radio'),(146,'beograd'),(147,'niš'),(148,'kruševac'),(149,'kragujevac'),(150,'užice'),(151,'kraljevo'),(152,'raška'),(153,'loznica'),(154,'valjevo'),(155,'čačak'),(156,'šabac'),(157,'ljubovija'),(158,'sjenica'),(159,'prokuplje'),(160,'kuršumlija'),(161,'parunovac'),(162,'stalać'),(163,'mezgraja'),(164,'mozgovo'),(165,'kragujevac'),(166,'mladenovac'),(167,'topola'),(168,'oplenac'),(169,'obrenovac'),(170,'paraćin'),(171,'ćuprija'),(172,'jagodina'),(173,'despotovac'),(174,'zaječar'),(175,'bor'),(176,'majdanpek'),(177,'požarevac'),(178,'smederevo'),(179,'kladovo'),(180,'negotin'),(181,'svrljig'),(182,'leskovac'),(183,'vranje'),(184,'surdulica'),(185,'vlasina'),(186,'sokobanja'),(187,'petrovac'),(188,'boljevac'),(189,'knjaževac'),(190,'pirot'),(191,'babušnica'),(192,'preševo'),(193,'bujanovac'),(194,'trgvište'),(195,'bosilegrad'),(196,'peć'),(197,'planeja'),(198,'dečani'),(199,'uroševac'),(200,'prizren'),(201,'priština'),(202,'mitrovica'),(203,'đakovica'),(204,'gnjilane'),(205,'kačanik'),(206,'ruma'),(207,'palanka'),(208,'pančevo'),(209,'zrenjanin'),(210,'kikinda'),(211,'orlovat'),(212,'subotica'),(213,'sombor'),(214,'vršac'),(215,'bečej'),(216,'senta'),(217,'kula'),(218,'vrbas'),(219,'srbobran'),(220,'lazar'),(221,'marija'),(222,'uroš'),(223,'ivana'),(224,'jovana'),(225,'ana'),(226,'smiljana'),(227,'ljiljana'),(228,'stanija'),(229,'dejan'),(230,'miljko'),(231,'dragana'),(232,'vladimir'),(233,'vladan'),(234,'stefan'),(235,'miloš'),(236,'ivan'),(237,'broj'),(238,'taster'),(239,'hol'),(240,'neven'),(241,'smaži'),(242,'osnaži');
/*!40000 ALTER TABLE `slagalica` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spojnice`
--

DROP TABLE IF EXISTS `spojnice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `spojnice` (
  `IdSpojnica` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`IdSpojnica`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spojnice`
--

LOCK TABLES `spojnice` WRITE;
/*!40000 ALTER TABLE `spojnice` DISABLE KEYS */;
INSERT INTO `spojnice` VALUES (15),(16),(17),(18),(19),(20),(21),(22),(23),(24),(25),(26);
/*!40000 ALTER TABLE `spojnice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spojnice_rec`
--

DROP TABLE IF EXISTS `spojnice_rec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `spojnice_rec` (
  `Id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `IdSpojnica` int(11) NOT NULL,
  `LevaRec` varchar(45) NOT NULL,
  `DesnaRec` varchar(45) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spojnice_rec`
--

LOCK TABLES `spojnice_rec` WRITE;
/*!40000 ALTER TABLE `spojnice_rec` DISABLE KEYS */;
INSERT INTO `spojnice_rec` VALUES (35,15,'energija','džul'),(36,15,'količina supstance','mol'),(37,15,'naelektrisanje','kulon'),(38,15,'električni potencijal','volt'),(39,15,'provodljivost','simens'),(40,15,'kapacitet','farad'),(41,15,'sila','njutn'),(42,15,'pritisak','paskal'),(43,15,'jačina električnog polja','volt po metru'),(44,15,'magnetni fluks','veber'),(45,16,'sad','vašington'),(46,16,'kanada','otava'),(47,16,'brazil','brazilija'),(48,16,'venecuela','karaks'),(49,16,'peru','lima'),(50,16,'argentina','buenos ajres'),(51,16,'francuska','pariz'),(52,16,'španija','madrid'),(53,16,'nemačka','berlin'),(54,16,'austrija','beč'),(55,17,'srbija','beograd'),(56,17,'bugarska','sofija'),(57,17,'rumunija','bukurešt'),(58,17,'mađarska','budimpešta'),(59,17,'slovenija','ljubljana'),(60,17,'grčka','atina'),(61,17,'kipar','limasol'),(62,17,'kazakhstan','astana'),(63,17,'rusija','moskva'),(64,17,'belorusija','minsk'),(65,18,'kosovska bitka','1389'),(66,18,'marička bitka','1371'),(67,18,'berlinski kongres','1878'),(68,18,'proglašenje shs','1918'),(69,18,'prvi srpski ustanak','1804'),(70,18,'drugi srpski ustanak','1815'),(71,18,'bitka kod angore','1402'),(72,18,'pad carigrada','1453'),(73,18,'velika šizma','1053'),(74,18,'prvi balkanski rat','1912'),(75,19,'Đuzepe','Verdi'),(76,19,'Đoakino','Rosini'),(77,19,'Antonio','Vivaldi'),(78,19,'Ruđero','Leonkavalo'),(79,19,'Nikolo','Paganini'),(80,19,'Gaetano','Doniceti'),(81,19,'Enio','Morikone'),(82,19,'Tomazo','Albinoni'),(83,19,'Klaudio','Monteverdi'),(84,19,'Đakomo','Pučini'),(85,20,'četiri godišnja doba','vivaldi'),(86,20,'godišnja doba','čajkovski'),(87,20,'mala noćna muzika','mocart'),(88,20,'asturija','albeniz'),(89,20,'adađo g-moll','albinoni'),(90,20,'slike sa izložbe','musorgski'),(91,20,'bumbarov let','rimski-korsakov'),(92,20,'karmen','bize'),(93,20,'mađarske igre','brams'),(94,20,'karmina burana','orf'),(95,21,'limun','citrus x lemoni'),(96,21,'trnjina','prunus spinosa'),(97,21,'dunja','cydonia oblonga'),(98,21,'crni biber','piper negrum'),(99,21,'peršun','petroselinum crispum'),(100,21,'šumska jagoda','fragaria vesca'),(101,21,'grožđe','vitis vinifera'),(102,21,'godži','lycium barbarum'),(103,21,'dren','cornus mas'),(104,21,'jabuka','malus domestica'),(105,22,'crvena zastava','srbija'),(106,22,'dačija','rumunija'),(107,22,'škoda','češka'),(108,22,'folksvagen','nemačka'),(109,22,'reno','francuska'),(110,22,'jaguar','engleska'),(111,22,'leksus','sad'),(112,22,'tojota','japan'),(113,22,'lada','rusija'),(114,22,'tam','slovenija'),(115,23,'majica','t-shirt'),(116,23,'džemper','sweater'),(117,23,'patike','sneakers'),(118,23,'trenerka','trousers'),(119,23,'košulja','shirt'),(120,23,'kaput','coat'),(121,23,'kravata','tie'),(122,23,'suknja','skirt'),(123,23,'farmerice','jeans'),(124,23,'šal','scarf'),(125,24,'kuća','house'),(126,24,'dvorište','backyard'),(127,24,'bazen','pool'),(128,24,'garaža','garage'),(129,24,'automobil','car'),(130,24,'kombi','van'),(131,24,'ograda','fence'),(132,24,'stepenište','stairs'),(133,24,'travnjak','lawn'),(134,24,'ulica','street'),(135,25,'tripoli','libija'),(136,25,'kairo','egipat'),(137,25,'tel aviv','izrael'),(138,25,'bagdad','irak'),(139,25,'teheran','iran'),(140,25,'astana','kazahstan'),(141,25,'islamabad','pakistan'),(142,25,'peking','kina'),(143,25,'ulan bator','mongolija'),(144,25,'pjongjang','severna koreja'),(145,26,'rasina','kruševac'),(146,26,'zapadna morava','trstenik'),(147,26,'ibar','kraljevo'),(148,26,'mlava','petrovac'),(149,26,'nišava','niš'),(150,26,'južna morava','leskovac'),(151,26,'dunav','smederevo'),(152,26,'sava','šabac'),(153,26,'kolubara','valjevo'),(154,26,'drina','loznica');
/*!40000 ALTER TABLE `spojnice_rec` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `pregledrezultatapodanu`
--

/*!50001 DROP VIEW IF EXISTS `pregledrezultatapodanu`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`laptop`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `pregledrezultatapodanu` AS select `z`.`IdKorisnik` AS `IdKorisnik`,`z`.`ime` AS `Ime`,`z`.`prezime` AS `Prezime`,`z`.`datum` AS `Datum`,`z`.`zbir` AS `SumaPoDanu` from `pregledrezultatapodanu_subquery` `z` where (`z`.`zbir` is not null) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `pregledrezultatapodanu_subquery`
--

/*!50001 DROP VIEW IF EXISTS `pregledrezultatapodanu_subquery`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`laptop`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `pregledrezultatapodanu_subquery` AS select `k1`.`IdKorisnik` AS `IdKorisnik`,`k1`.`Ime` AS `ime`,`k1`.`Prezime` AS `prezime`,`p1`.`Datum` AS `datum`,sum(`r1`.`PoenaPlavi`) AS `zbir` from ((`korisnici` `k1` join `partija` `p1` on((`k1`.`IdKorisnik` = `p1`.`PlaviTakmicar`))) join `rezultat` `r1` on((`p1`.`IdPartija` = `r1`.`IdPartija`))) group by `p1`.`Datum` union select `k2`.`IdKorisnik` AS `IdKorisnik`,`k2`.`Ime` AS `ime`,`k2`.`Prezime` AS `prezime`,`p2`.`Datum` AS `datum`,sum(`r2`.`PoenaPlavi`) AS `zbir` from ((`korisnici` `k2` join `partija` `p2` on((`k2`.`IdKorisnik` = `p2`.`CrveniTakmicar`))) join `rezultat` `r2` on((`p2`.`IdPartija` = `r2`.`IdPartija`))) group by `p2`.`Datum` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-18 21:09:18
