CREATE TABLE fornecedores (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL ,
    data_cadastro DATE,
    atividade VARCHAR(45),
    id_endereco BIGINT(20) NOT NULL UNIQUE ,
    FOREIGN KEY (id_endereco) REFERENCES enderecos(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO fornecedores(nome, data_cadastro, atividade, id_endereco) VALUES ('Sup. Armazem', '2019-06-23', 'Supermercado', 1);
INSERT INTO fornecedores(nome, data_cadastro, atividade, id_endereco) VALUES ('Sup. Lider', '2019-06-23', 'Supermercado', 2);