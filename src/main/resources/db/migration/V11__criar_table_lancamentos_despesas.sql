CREATE TABLE lancamentos_despesas (
    data_pagamento DATE DEFAULT NULL,
    data_vencimento DATE DEFAULT NULL,
    parcelado boolean DEFAULT FALSE,
    gasto varchar(20) DEFAULT NULL,
    qtd_parcelas INT DEFAULT NULL,
    numero_parcela INT DEFAULT NULL,
    id_categoria BIGINT(20) NOT NULL ,
    id_forma_pagamento BIGINT(20) NOT NULL,
    lancamento_id BIGINT(20) NOT NULL,
    PRIMARY KEY (lancamento_id),
    KEY FK_LANCAMENTO_ID_CATEGORIA (id_categoria),
    CONSTRAINT FK_LANCAMENTO_DESPESAS_ID_CATEGORIA FOREIGN KEY (id_categoria) REFERENCES categorias (id),
    CONSTRAINT FK_LANCAMENTO_DESPESAS_ID_FORMA_PAGAMENTO_ID FOREIGN KEY (id_forma_pagamento) REFERENCES formas_pagamento(id),
    CONSTRAINT FK_LANCAMENTOS_DESPESAS FOREIGN KEY (lancamento_id) REFERENCES lancamentos (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;