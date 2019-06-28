CREATE TABLE formas_pagamento (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL,
    id_cliente BIGINT(20) NOT NULL,
    UNIQUE KEY UK_NOME_ID_CLIENTE (nome, id_cliente),
    FOREIGN KEY (id_cliente) REFERENCES clientes(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
