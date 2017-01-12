INSERT INTO `pedido-venda`.categoria (descricao) VALUES ('Informática');
INSERT INTO `pedido-venda`.categoria (descricao) VALUES ('Eletrodomésticos');
INSERT INTO `pedido-venda`.categoria (descricao) VALUES ('Móveis');

INSERT INTO `pedido-venda`.categoria (descricao, categoria_pai_id) VALUES ('Computadores', 1);
INSERT INTO `pedido-venda`.categoria (descricao, categoria_pai_id) VALUES ('Notebooks', 1);
INSERT INTO `pedido-venda`.categoria (descricao, categoria_pai_id) VALUES ('Tablets', 1);
INSERT INTO `pedido-venda`.categoria (descricao, categoria_pai_id) VALUES ('Monitores', 1);
INSERT INTO `pedido-venda`.categoria (descricao, categoria_pai_id) VALUES ('Impressoras', 1);
INSERT INTO `pedido-venda`.categoria (descricao, categoria_pai_id) VALUES ('Acessórios', 1);

INSERT INTO `pedido-venda`.categoria (descricao, categoria_pai_id) VALUES ('Ar condicionados', 2);
INSERT INTO `pedido-venda`.categoria (descricao, categoria_pai_id) VALUES ('Fogões', 2);
INSERT INTO `pedido-venda`.categoria (descricao, categoria_pai_id) VALUES ('Fornos elétricos', 2);
INSERT INTO `pedido-venda`.categoria (descricao, categoria_pai_id) VALUES ('Microondas', 2);
INSERT INTO `pedido-venda`.categoria (descricao, categoria_pai_id) VALUES ('Refrigeradores', 2);

INSERT INTO `pedido-venda`.categoria (descricao, categoria_pai_id) VALUES ('Cadeiras', 3);
INSERT INTO `pedido-venda`.categoria (descricao, categoria_pai_id) VALUES ('Mesas', 3);
INSERT INTO `pedido-venda`.categoria (descricao, categoria_pai_id) VALUES ('Racks', 3);
INSERT INTO `pedido-venda`.categoria (descricao, categoria_pai_id) VALUES ('Sofás', 3);

INSERT INTO `pedido-venda`.usuario (id, email, nome, senha) VALUES (1, 'lucas@barros', 'Lucas Barros', '123');
INSERT INTO `pedido-venda`.usuario (id, email, nome, senha) VALUES (2, 'joane@amorim', 'Joane Amorim', '123');
INSERT INTO `pedido-venda`.usuario (id, email, nome, senha) VALUES (3, 'marcus@vinicios', 'Marcus Vinícios', '123');

INSERT INTO `pedido-venda`.grupo (id, descricao, nome) VALUES (1, 'Administrador do sistema', 'Administradores');
INSERT INTO `pedido-venda`.grupo (id, descricao, nome) VALUES (2, 'Vendedores do sistema', 'Vendedores');
INSERT INTO `pedido-venda`.grupo (id, descricao, nome) VALUES (3, 'Auxiliares do sistema', 'Auxiliares');

INSERT INTO `pedido-venda`.usuario_grupo (usuario_id, grupo_id) VALUES (1,1);
INSERT INTO `pedido-venda`.usuario_grupo (usuario_id, grupo_id) VALUES (2,2);
INSERT INTO `pedido-venda`.usuario_grupo (usuario_id, grupo_id) VALUES (3,3);