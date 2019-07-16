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
-- Table structure for table `categorias`
--

DROP TABLE IF EXISTS `categorias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categorias` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--

LOCK TABLES `categorias` WRITE;
/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;
INSERT INTO `categorias` VALUES (1,'Lazer'),(2,'Alimentação'),(3,'Saúde'),(4,'Outros');
/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;
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
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` VALUES (1,'01','criar e registrar categorias','SQL','V01__criar_e_registrar_categorias.sql',-1865310929,'root','2019-07-15 10:42:43',248,1),(2,'02','criar tabela endereco','SQL','V02__criar_tabela_endereco.sql',1732803904,'root','2019-07-15 10:42:44',274,1),(3,'03','criar table fornecedores','SQL','V03__criar_table_fornecedores.sql',20741923,'root','2019-07-15 10:42:44',422,1),(4,'04','criar table telefones','SQL','V04__criar_table_telefones.sql',-87850562,'root','2019-07-15 10:42:44',297,1),(5,'05','criar tables perfis usuarios','SQL','V05__criar_tables_perfis_usuarios.sql',1307828406,'root','2019-07-15 10:42:45',836,1),(6,'06','criar table cliente','SQL','V06__criar_table_cliente.sql',-1350369053,'root','2019-07-15 10:42:46',272,1),(7,'07','criar usuario admin','SQL','V07__criar_usuario_admin.sql',-1382352805,'root','2019-07-15 10:42:46',10,1),(8,'08','criar tables forma pagamento','SQL','V08__criar_tables_forma_pagamento.sql',1001515877,'root','2019-07-15 10:42:46',242,1),(9,'09','criar table lancamentos','SQL','V09__criar_table_lancamentos.sql',1091494928,'root','2019-07-15 10:42:46',270,1),(10,'10','criar table lancamentos receita','SQL','V10__criar_table_lancamentos_receita.sql',-1187589722,'root','2019-07-15 10:42:46',190,1),(11,'11','criar table lancamentos despesas','SQL','V11__criar_table_lancamentos_despesas.sql',1705319196,'root','2019-07-15 10:42:47',254,1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
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
-- Table structure for table `lancamentos`
--

DROP TABLE IF EXISTS `lancamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lancamentos` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) NOT NULL,
  `descricao` varchar(80) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `data_lancamento` date NOT NULL,
  `id_fornecedor` bigint(20) NOT NULL,
  `id_cliente` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_LANCAMENTO_ID_FORNECEDOR` (`id_fornecedor`),
  KEY `FK_LANCAMENTO_ID_ID_CLIENTE` (`id_cliente`),
  CONSTRAINT `FK_LANCAMENTO_ID_CLIENTE` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`),
  CONSTRAINT `FK_LANCAMENTO_ID_FORNECEDOR` FOREIGN KEY (`id_fornecedor`) REFERENCES `fornecedores` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lancamentos`
--

LOCK TABLES `lancamentos` WRITE;
/*!40000 ALTER TABLE `lancamentos` DISABLE KEYS */;
INSERT INTO `lancamentos` VALUES (1,'Salario mês','Maio/2019',1470.00,'2019-07-15',3,1),(4,'Compra Pão','3 pães careca',2.14,'2019-07-15',4,1);
/*!40000 ALTER TABLE `lancamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lancamentos_despesas`
--

DROP TABLE IF EXISTS `lancamentos_despesas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lancamentos_despesas` (
  `data_pagamento` date DEFAULT NULL,
  `data_vencimento` date DEFAULT NULL,
  `parcelado` tinyint(1) DEFAULT '0',
  `gasto` varchar(20) DEFAULT NULL,
  `qtd_parcelas` int(11) DEFAULT NULL,
  `numero_parcela` int(11) DEFAULT NULL,
  `id_categoria` bigint(20) NOT NULL,
  `id_forma_pagamento` bigint(20) NOT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_LANCAMENTO_ID_CATEGORIA` (`id_categoria`),
  KEY `FK_LANCAMENTO_DESPESAS_ID_FORMA_PAGAMENTO_ID` (`id_forma_pagamento`),
  CONSTRAINT `FK_LANCAMENTOS_DESPESAS_ID_LANCAMENTO` FOREIGN KEY (`id`) REFERENCES `lancamentos` (`id`),
  CONSTRAINT `FK_LANCAMENTO_DESPESAS_ID_CATEGORIA` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id`),
  CONSTRAINT `FK_LANCAMENTO_DESPESAS_ID_FORMA_PAGAMENTO_ID` FOREIGN KEY (`id_forma_pagamento`) REFERENCES `formas_pagamento` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lancamentos_despesas`
--

LOCK TABLES `lancamentos_despesas` WRITE;
/*!40000 ALTER TABLE `lancamentos_despesas` DISABLE KEYS */;
INSERT INTO `lancamentos_despesas` VALUES ('2019-07-15','2018-07-15',0,NULL,NULL,NULL,2,1,4);
/*!40000 ALTER TABLE `lancamentos_despesas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lancamentos_receitas`
--

DROP TABLE IF EXISTS `lancamentos_receitas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lancamentos_receitas` (
  `data_recebimento` date DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_LANCAMENTOS_RECEITAS_ID_LANCAMENTO` FOREIGN KEY (`id`) REFERENCES `lancamentos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lancamentos_receitas`
--

LOCK TABLES `lancamentos_receitas` WRITE;
/*!40000 ALTER TABLE `lancamentos_receitas` DISABLE KEYS */;
INSERT INTO `lancamentos_receitas` VALUES ('2019-05-05',1);
/*!40000 ALTER TABLE `lancamentos_receitas` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefones`
--

LOCK TABLES `telefones` WRITE;
/*!40000 ALTER TABLE `telefones` DISABLE KEYS */;
INSERT INTO `telefones` VALUES (1,'(91) 3223-5252',1),(2,'(91) 98263-5244',1),(3,'(91) 4005-6385',2),(4,'(91) 99152-7400',2),(5,'(91) 3222-2222',3),(6,'(91) 3242-7414',4),(7,'(91) 3242-8844',5);
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
-- Dumping routines for database 'financysdb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-16 15:47:34
