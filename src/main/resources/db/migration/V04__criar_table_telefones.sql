CREATE TABLE telefones(
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    numero VARCHAR(15) NOT NULL ,
    id_fornecedor BIGINT(20) ,
    CONSTRAINT FK_TELEFONE_FORNECEDOR_ID FOREIGN KEY (id_fornecedor) REFERENCES fornecedores(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO telefones(numero, id_fornecedor) VALUES ('(91) 3223-5252', 1);
INSERT INTO telefones(numero, id_fornecedor) VALUES ('(91) 98263-5244', 1);
INSERT INTO telefones(numero, id_fornecedor) VALUES ('(91) 4005-6385', 2);
INSERT INTO telefones(numero, id_fornecedor) VALUES ('(91) 99152-7400', 2);