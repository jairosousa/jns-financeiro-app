INSERT INTO usuarios(email, senha, ativo)
values ('jns@email.com', '$2a$10$dIu/8uXEv8qYqAgpMl.ybuCqPwuejsNbi3o6LDPhjbQpxsb2AxzjG', 1);

INSERT INTO usuarios_tem_perfis(usuario_id, perfil_id)
values (1, 1);