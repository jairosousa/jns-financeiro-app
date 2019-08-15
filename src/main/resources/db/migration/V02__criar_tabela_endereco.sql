CREATE TABLE enderecos (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT NOT NULL,
    logradouro VARCHAR(80) NOT NULL ,
    numero VARCHAR(15),
    complemento VARCHAR(80),
    bairro VARCHAR(45),
    cep VARCHAR(10),
    cidade VARCHAR(30),
    uf CHAR(2),
    KEY IDX_TELEFONE_ID (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;