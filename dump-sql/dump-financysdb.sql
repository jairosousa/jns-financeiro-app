
DROP TABLE IF EXISTS `perfis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perfis` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_PERFIL_DESCRICAO` (`descricao`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lancamentos`

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
-- Dumping data for table `enderecos

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
-- Table structure for table `lancamentos_despesas`
--

DROP TABLE IF EXISTS `lancamentos_despesas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lancamentos_despesas` (
  `data_pagamento` date DEFAULT NULL,
  `data_vencimento` date DEFAULT NULL,
  `gasto_fixo` tinyint(1) DEFAULT '0',
  `pagamento` varchar(20) DEFAULT NULL,
  `qtd_parcelas` int(11) DEFAULT NULL,
  `valor_parcela` decimal(10,2) DEFAULT NULL,
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

