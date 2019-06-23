CREATE TABLE lancamentos (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL ,
    descricao VARCHAR(80) NOT NULL ,
    tipo VARCHAR(20) NOT NULL ,
    valor DECIMAL(10,2) NOT NULL,
    data_lancamento DATE NOT NULL,
    id_categoria BIGINT(20) NOT NULL ,
    id_fornecedor BIGINT(20) NOT NULL ,
    id_usuario BIGINT(20) NOT NULL ,
    FOREIGN KEY (id_categoria) REFERENCES categorias(id),
    FOREIGN KEY (id_fornecedor) REFERENCES fornecedores(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;