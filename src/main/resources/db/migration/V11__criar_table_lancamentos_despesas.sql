CREATE TABLE lancamentos_despesas (
    data_pagamento DATE DEFAULT NULL,
    data_vencimento DATE DEFAULT NULL,
    gasto_fixo boolean DEFAULT FALSE,
    pagamento varchar(20) DEFAULT NULL,
    qtd_parcelas INT DEFAULT NULL,
    valor_parcela DECIMAL(10,2) DEFAULT NULL,
    numero_parcela INT DEFAULT NULL,
    id_categoria BIGINT(20) NOT NULL ,
    id_forma_pagamento BIGINT(20) NOT NULL,
    id BIGINT(20) NOT NULL,
    PRIMARY KEY (id),
    KEY FK_LANCAMENTO_ID_CATEGORIA (id_categoria),
    CONSTRAINT FK_LANCAMENTO_DESPESAS_ID_CATEGORIA FOREIGN KEY (id_categoria) REFERENCES categorias (id),
    CONSTRAINT FK_LANCAMENTO_DESPESAS_ID_FORMA_PAGAMENTO_ID FOREIGN KEY (id_forma_pagamento) REFERENCES formas_pagamento(id),
    CONSTRAINT FK_LANCAMENTOS_DESPESAS_ID_LANCAMENTO FOREIGN KEY (id) REFERENCES lancamentos (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;