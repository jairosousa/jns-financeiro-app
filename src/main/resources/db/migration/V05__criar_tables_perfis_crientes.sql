CREATE TABLE perfis (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    descricao varchar(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY `UK_PERFIL_DESCRICAO` (descricao)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

INSERT INTO perfis VALUES (1,'ADMIN'),(2,'CLIENTE');

CREATE TABLE usuarios (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    nome varchar(255) NOT NULL,
    ativo tinyint(1) NOT NULL,
    email varchar(255) NOT NULL,
    senha varchar(255) NOT NULL,
    codigo_verificador varchar(6) DEFAULT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY UK_USUARIO_EMAIL (email),
    KEY IDX_USUARIO_EMAIL (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuarios_tem_perfis (
    usuario_id bigint(20) NOT NULL,
    perfil_id bigint(20) NOT NULL,
    KEY FK_USUARIO_TEM_PERFIL_ID (perfil_id),
    KEY FK_PERFIL_TEM_USUARIO_ID (usuario_id),
    CONSTRAINT FK_USUARIO_TEM_PERFIL_ID FOREIGN KEY (perfil_id) REFERENCES perfis (id),
    CONSTRAINT FK_PERFIL_TEM_USUARIO_ID FOREIGN KEY (usuario_id) REFERENCES usuarios (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;