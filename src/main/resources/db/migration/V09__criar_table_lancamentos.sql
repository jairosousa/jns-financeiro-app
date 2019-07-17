CREATE TABLE lancamentos (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL ,
    descricao VARCHAR(80) NOT NULL ,
    valor DECIMAL(10,2) NOT NULL,
    data_lancamento DATE NOT NULL,
    id_fornecedor BIGINT(20) NOT NULL ,
    id_cliente BIGINT(20) NOT NULL ,
    KEY FK_LANCAMENTO_ID_FORNECEDOR (id_fornecedor),
    KEY FK_LANCAMENTO_ID_ID_CLIENTE (id_cliente),
    CONSTRAINT FK_LANCAMENTO_ID_FORNECEDOR FOREIGN KEY (id_fornecedor) REFERENCES fornecedores (id),
    CONSTRAINT FK_LANCAMENTO_ID_CLIENTE FOREIGN KEY (id_cliente) REFERENCES clientes( id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;