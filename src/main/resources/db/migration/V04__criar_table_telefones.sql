CREATE TABLE telefones(
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    tipo VARCHAR(15) NOT NULL ,
    numero VARCHAR(14) NOT NULL ,
    id_fornecedor BIGINT(20) NOT NULL ,
    FOREIGN KEY (id_fornecedor) REFERENCES fornecedores(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO telefones(tipo, numero, id_fornecedor) VALUES ('COM', '(91)3223-5252', 1);
INSERT INTO telefones(tipo, numero, id_fornecedor) VALUES ('CEL', '(91)98263-5244', 1);
INSERT INTO telefones(tipo, numero, id_fornecedor) VALUES ('COM', '(91)4005-6385', 2);
INSERT INTO telefones(tipo, numero, id_fornecedor) VALUES ('CEL', '(91)99152-7400', 2);