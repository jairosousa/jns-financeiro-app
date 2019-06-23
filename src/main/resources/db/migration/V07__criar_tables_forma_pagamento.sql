CREATE TABLE formas_pagamentos (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(80) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO formas_pagamentos(nome) VALUES ('Dinheiro');
INSERT INTO formas_pagamentos(nome) VALUES ('Cartão Caixa Visa - Débito');
INSERT INTO formas_pagamentos(nome) VALUES ('Cartão Itau Master - Crédito');
