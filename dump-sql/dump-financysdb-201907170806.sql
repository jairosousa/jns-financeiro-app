-- MySQL dump 10.13  Distrib 5.6.41, for Win64 (x86_64)
--
-- Host: localhost    Database: financysdb
-- ------------------------------------------------------
-- Server version	5.6.41-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `enderecos`
--

DROP TABLE IF EXISTS `enderecos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `enderecos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `logradouro` varchar(80) NOT NULL,
  `numero` varchar(15) DEFAULT NULL,
  `complemento` varchar(80) DEFAULT NULL,
  `bairro` varchar(45) DEFAULT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `cidade` varchar(30) DEFAULT NULL,
  `uf` char(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_TELEFONE_ID` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enderecos`
--

LOCK TABLES `enderecos` WRITE;
/*!40000 ALTER TABLE `enderecos` DISABLE KEYS */;
INSERT INTO `enderecos` VALUES (1,'Rua Maracacuera','100',NULL,'Icoaraci','66.000-000','Belém','PA'),(2,'Quarta rua','200',NULL,'Icoaraci','66.000-000','Belém','PA'),(3,'Travessa L-6','223','(Cj COHAB)','Campina de Icoaraci (Icoaraci)','66.813-700','Belém','PA'),(4,'Avenida Senador Lemos','435','SL 605 607 EDIF Village Boulevard','Umarizal','66.050-000','Belém','PA'),(5,'Avenida Presidente Vargas','325','até 379/380','Campina','66.010-000','Belém','PA'),(6,'Avenida Nazaré','140','Manoel Pinto','Nazaré','66.035-115','Belém','PA');
/*!40000 ALTER TABLE `enderecos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perfis`
--

DROP TABLE IF EXISTS `perfis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfis` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_PERFIL_DESCRICAO` (`descricao`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perfis`
--

LOCK TABLES `perfis` WRITE;
/*!40000 ALTER TABLE `perfis` DISABLE KEYS */;
INSERT INTO `perfis` VALUES (1,'ADMIN'),(2,'USUARIO');
/*!40000 ALTER TABLE `perfis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_tem_perfis`
--

DROP TABLE IF EXISTS `usuarios_tem_perfis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios_tem_perfis` (
  `usuario_id` bigint(20) NOT NULL,
  `perfil_id` bigint(20) NOT NULL,
  KEY `FK_USUARIO_TEM_PERFIL_ID` (`perfil_id`),
  KEY `FK_PERFIL_TEM_USUARIO_ID` (`usuario_id`),
  CONSTRAINT `FK_PERFIL_TEM_USUARIO_ID` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FK_USUARIO_TEM_PERFIL_ID` FOREIGN KEY (`perfil_id`) REFERENCES `perfis` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_tem_perfis`
--

LOCK TABLES `usuarios_tem_perfis` WRITE;
/*!40000 ALTER TABLE `usuarios_tem_perfis` DISABLE KEYS */;
INSERT INTO `usuarios_tem_perfis` VALUES (1,1),(2,2),(3,2);
/*!40000 ALTER TABLE `usuarios_tem_perfis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedores`
--

DROP TABLE IF EXISTS `fornecedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fornecedores` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `data_cadastro` date DEFAULT NULL,
  `atividade` varchar(45) DEFAULT NULL,
  `id_endereco` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_endereco` (`id_endereco`),
  CONSTRAINT `FK_FORNECEDOR_ENDERECO_ID` FOREIGN KEY (`id_endereco`) REFERENCES `enderecos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedores`
--

LOCK TABLES `fornecedores` WRITE;
/*!40000 ALTER TABLE `fornecedores` DISABLE KEYS */;
INSERT INTO `fornecedores` VALUES (1,'Sup. Armazem','2019-06-23','Supermercado',1),(2,'Sup. Lider','2019-06-23','Supermercado',2),(3,'Stefanini Consul Assess Inform','2019-07-15','Serviços',4),(4,'Delicidade','2019-07-15','Mercado e Panificadora',5),(5,'Yamada Espress','2019-07-16','Supermercado',6);
/*!40000 ALTER TABLE `fornecedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_nascimento` date NOT NULL,
  `nome` varchar(255) NOT NULL,
  `id_usuario` bigint(20) DEFAULT NULL,
  `id_endereco` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_endereco` (`id_endereco`),
  KEY `FK_CLIENTE_USUARIO_ID` (`id_usuario`),
  CONSTRAINT `FK_CLIENTE_ENDERECO_ID` FOREIGN KEY (`id_endereco`) REFERENCES `enderecos` (`id`),
  CONSTRAINT `FK_CLIENTE_USUARIO_ID` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'1973-02-12','Jairo Nascimento de Sousa',2,3);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formas_pagamento`
--

DROP TABLE IF EXISTS `formas_pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formas_pagamento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) NOT NULL,
  `id_cliente` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_NOME_ID_CLIENTE` (`nome`,`id_cliente`),
  KEY `FK_FORMA_PAGAMENTO_CLIENTE_ID` (`id_cliente`),
  CONSTRAINT `FK_FORMA_PAGAMENTO_CLIENTE_ID` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formas_pagamento`
--

LOCK TABLES `formas_pagamento` WRITE;
/*!40000 ALTER TABLE `formas_pagamento` DISABLE KEYS */;
INSERT INTO `formas_pagamento` VALUES (1,'Alelo Alimentação',1),(2,'Itaú Credito',1),(3,'Itaú Débito',1);
/*!40000 ALTER TABLE `formas_pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefones`
--

DROP TABLE IF EXISTS `telefones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefones` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `numero` varchar(15) NOT NULL,
  `id_fornecedor` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_TELEFONE_FORNECEDOR_ID` (`id_fornecedor`),
  CONSTRAINT `FK_TELEFONE_FORNECEDOR_ID` FOREIGN KEY (`id_fornecedor`) REFERENCES `fornecedores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefones`
--

LOCK TABLES `telefones` WRITE;
/*!40000 ALTER TABLE `telefones` DISABLE KEYS */;
INSERT INTO `telefones` VALUES (1,'(91) 3223-5252',1),(2,'(91) 98263-5244',1),(3,'(91) 4005-6385',2),(4,'(91) 99152-7400',2);
/*!40000 ALTER TABLE `telefones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ativo` tinyint(1) NOT NULL,
  `email` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `codigo_verificador` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_USUARIO_EMAIL` (`email`),
  KEY `IDX_USUARIO_EMAIL` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,1,'jns@email.com','$2a$10$dIu/8uXEv8qYqAgpMl.ybuCqPwuejsNbi3o6LDPhjbQpxsb2AxzjG',NULL),(2,1,'jaironsousa@gmail.com','$2a$10$MsQnAyWUkObEaz.5aslCiuoPgDk1NljPDCcgwqFrF14tp9GlqTu0S',NULL),(3,1,'jnsousa@stefanini.com','$2a$10$2VMwzlErC8AnzICr1Y5Wzup1PuM4X0oS.QMxA6VWuoq9ETXqf1BWm',NULL);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-17  8:06:04
