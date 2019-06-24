CREATE TABLE pagamentos (
    id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    data_vencimanto DATE NOT NULL ,
    data_pagamento DATE ,
    valor DECIMAL(10,2) NOT NULL,
    status VARCHAR(20) NOT NULL ,
    id_forma_pagamento BIGINT(20) ,
    id_lancamento BIGINT(20) NOT NULL,
    FOREIGN KEY (id_forma_pagamento) REFERENCES formas_pagamentos(id),
    FOREIGN KEY (id_lancamento) REFERENCES lancamentos(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;