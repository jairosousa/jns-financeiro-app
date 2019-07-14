CREATE TABLE lancamentos_receitas (
    data_recebimento DATE DEFAULT NULL,
    lancamento_id BIGINT(20) NOT NULL,
    PRIMARY KEY (lancamento_id),
    CONSTRAINT FK_LANCAMENTOS_RECEITAS FOREIGN KEY (lancamento_id) REFERENCES lancamentos (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;