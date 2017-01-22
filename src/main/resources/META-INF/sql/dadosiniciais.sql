INSERT INTO `pedido-venda`.categoria (id, descricao) VALUES (1, 'Informática');
INSERT INTO `pedido-venda`.categoria (id, descricao) VALUES (2, 'Eletrodomésticos');
INSERT INTO `pedido-venda`.categoria (id, descricao) VALUES (3, 'Móveis');

INSERT INTO `pedido-venda`.categoria (id, descricao, categoria_pai_id) VALUES (4, 'Computadores', 1);
INSERT INTO `pedido-venda`.categoria (id, descricao, categoria_pai_id) VALUES (5, 'Notebooks', 1);
INSERT INTO `pedido-venda`.categoria (id, descricao, categoria_pai_id) VALUES (6, 'Tablets', 1);
INSERT INTO `pedido-venda`.categoria (id, descricao, categoria_pai_id) VALUES (7, 'Monitores', 1);
INSERT INTO `pedido-venda`.categoria (id, descricao, categoria_pai_id) VALUES (8, 'Impressoras', 1);
INSERT INTO `pedido-venda`.categoria (id, descricao, categoria_pai_id) VALUES (9, 'Acessórios', 1);

INSERT INTO `pedido-venda`.categoria (id, descricao, categoria_pai_id) VALUES (10, 'Ar condicionados', 2);
INSERT INTO `pedido-venda`.categoria (id, descricao, categoria_pai_id) VALUES (11, 'Fogões', 2);
INSERT INTO `pedido-venda`.categoria (id, descricao, categoria_pai_id) VALUES (12, 'Fornos elétricos', 2);
INSERT INTO `pedido-venda`.categoria (id, descricao, categoria_pai_id) VALUES (13, 'Microondas', 2);
INSERT INTO `pedido-venda`.categoria (id, descricao, categoria_pai_id) VALUES (14, 'Refrigeradores', 2);

INSERT INTO `pedido-venda`.categoria (id, descricao, categoria_pai_id) VALUES (15, 'Cadeiras', 3);
INSERT INTO `pedido-venda`.categoria (id, descricao, categoria_pai_id) VALUES (16, 'Mesas', 3);
INSERT INTO `pedido-venda`.categoria (id, descricao, categoria_pai_id) VALUES (17, 'Racks', 3);
INSERT INTO `pedido-venda`.categoria (id, descricao, categoria_pai_id) VALUES (18, 'Sofás', 3);

INSERT INTO `pedido-venda`.usuario (id, email, nome, senha) VALUES (1, 'lucas@barros', 'Lucas Barros', '123');
INSERT INTO `pedido-venda`.usuario (id, email, nome, senha) VALUES (2, 'joane@amorim', 'Joane Amorim', '123');
INSERT INTO `pedido-venda`.usuario (id, email, nome, senha) VALUES (3, 'marcus@vinicios', 'Marcus Vinícios', '123');

INSERT INTO `pedido-venda`.grupo (id, descricao, nome) VALUES (1, 'Administrador do sistema', 'Administradores');
INSERT INTO `pedido-venda`.grupo (id, descricao, nome) VALUES (2, 'Vendedores do sistema', 'Vendedores');
INSERT INTO `pedido-venda`.grupo (id, descricao, nome) VALUES (3, 'Auxiliares do sistema', 'Auxiliares');

INSERT INTO `pedido-venda`.usuario_grupo (usuario_id, grupo_id) VALUES (1,1);
INSERT INTO `pedido-venda`.usuario_grupo (usuario_id, grupo_id) VALUES (2,2);
INSERT INTO `pedido-venda`.usuario_grupo (usuario_id, grupo_id) VALUES (3,3);