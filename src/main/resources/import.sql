INSERT INTO cidade (id, nome, estado, ativo) VALUES
-- Tocantins
(1, 'Palmas', 26, true),
(2, 'Araguaína', 26, true),
(3, 'Gurupi', 26, true),
(4, 'Porto Nacional', 26, true),
(5, 'Paraíso do Tocantins', 26, true),
(6, 'Araguatins', 26, true),
(7, 'Colinas do Tocantins', 26, true),
(8, 'Guaraí', 26, true),
(9, 'Tocantinópolis', 26, true),
(10, 'Dianópolis', 26, true),

-- São Paulo
(11, 'São Paulo', 24, true),
(12, 'Campinas', 24, true),
(13, 'Santos', 24, true),
(14, 'São José dos Campos', 24, true),
(15, 'Ribeirão Preto', 24, true),

-- Pará
(16, 'Belém', 13, true),
(17, 'Ananindeua', 13, true),
(18, 'Santarém', 13, true),
(19, 'Marabá', 13, true),
(20, 'Castanhal', 13, true);



insert into empresa(ativo, id, nome) VALUES (true, 1, 'Empresa 1'),(true, 2, 'Empresa 2'),(true, 3, 'Empresa 3'),(true, 4, 'Empresa 4');


INSERT INTO categoria (empresa, id, nome, ativo) VALUES
                                                     (1, 1, 'Categoria 1', true),
                                                     (1, 2, 'Categoria 2', true),
                                                     (3, 3, 'Categoria 3', true),
                                                     (2, 4, 'Categoria 4', true),
                                                     (2, 5, 'Categoria 5', true),
                                                     (2, 6, 'Categoria 6', true),
                                                     (3, 7, 'Categoria 7', true),
                                                     (4, 8, 'Categoria 8', true),
                                                     (2, 9, 'Categoria 9', true),
                                                     (2, 10, 'Categoria 10', true),
                                                     (1, 11, 'Categoria 11', true),
                                                     (1, 12, 'Categoria 12', true),
                                                     (1, 13, 'Categoria 13', true),
                                                     (2, 14, 'Categoria 14', true),
                                                     (1, 15, 'Categoria 15', true),
                                                     (1, 16, 'Categoria 16', true),
                                                     (1, 17, 'Categoria 17', true),
                                                     (1, 18, 'Categoria 18', true),
                                                     (1, 19, 'Categoria 19', true),
                                                     (1, 20, 'Categoria 20', true);

INSERT INTO cliente
(empresa, id, nomeempresa, cnpj, nomecliente, cpfCliente, cidade_cliente, endereco, ativo) VALUES
                                                                                               (1, 1, 'Empresa 1', '01.639.420/0001-92', 'Cliente 1', '000000001-01', 1, 'Rua A, 1', True),
                                                                                               (1, 2, 'Empresa 2', '02.639.420/0001-92', 'Cliente 2', '000000002-02', 2, 'Rua A, 2', True),
                                                                                               (1, 3, 'Empresa 3', '03.639.420/0001-92', 'Cliente 3', '000000003-03', 3, 'Rua A, 3', True),
                                                                                               (1, 4, 'Empresa 4', '04.639.420/0001-92', 'Cliente 4', '000000004-04', 4, 'Rua A, 4', True),
                                                                                               (1, 5, 'Empresa 5', '05.639.420/0001-92', 'Cliente 5', '000000005-05', 5, 'Rua A, 5', True),
                                                                                               (1, 6, 'Empresa 6', '06.639.420/0001-92', 'Cliente 6', '000000006-06', 6, 'Rua A, 6', True),
                                                                                               (1, 7, 'Empresa 7', '07.639.420/0001-92', 'Cliente 7', '000000007-07', 7, 'Rua A, 7', True),
                                                                                               (1, 8, 'Empresa 8', '08.639.420/0001-92', 'Cliente 8', '000000008-08', 8, 'Rua A, 8', True),
                                                                                               (1, 9, 'Empresa 9', '09.639.420/0001-92', 'Cliente 9', '000000009-09', 9, 'Rua A, 9', True),
                                                                                               (1, 10, 'Empresa 10', '10.639.420/0001-92', 'Cliente 10', '000000010-10', 10, 'Rua A, 10', True),
                                                                                               (1, 11, 'Empresa 11', '11.639.420/0001-92', 'Cliente 11', '000000011-11', 11, 'Rua A, 11', True),
                                                                                               (1, 12, 'Empresa 12', '12.639.420/0001-92', 'Cliente 12', '000000012-12', 12, 'Rua A, 12', True),
                                                                                               (1, 13, 'Empresa 13', '13.639.420/0001-92', 'Cliente 13', '000000013-13', 13, 'Rua A, 13', True),
                                                                                               (1, 14, 'Empresa 14', '14.639.420/0001-92', 'Cliente 14', '000000014-14', 14, 'Rua A, 14', True),
                                                                                               (1, 15, 'Empresa 15', '15.639.420/0001-92', 'Cliente 15', '000000015-15', 15, 'Rua A, 15', True),
                                                                                               (1, 16, 'Empresa 16', '16.639.420/0001-92', 'Cliente 16', '000000016-16', 16, 'Rua A, 16', True),
                                                                                               (1, 17, 'Empresa 17', '17.639.420/0001-92', 'Cliente 17', '000000017-17', 17, 'Rua A, 17', True),
                                                                                               (1, 18, 'Empresa 18', '18.639.420/0001-92', 'Cliente 18', '000000018-18', 18, 'Rua A, 18', True),
                                                                                               (1, 19, 'Empresa 19', '19.639.420/0001-92', 'Cliente 19', '000000019-19', 19, 'Rua A, 19', True),
                                                                                               (1, 20, 'Empresa 20', '20.639.420/0001-92', 'Cliente 20', '000000020-20', 20, 'Rua A, 20', True),
                                                                                               (1, 21, 'Empresa 21', '21.639.420/0001-92', 'Cliente 21', '000000021-21', 1, 'Rua A, 21', True),
                                                                                               (1, 22, 'Empresa 22', '22.639.420/0001-92', 'Cliente 22', '000000022-22', 1, 'Rua A, 22', True),
                                                                                               (1, 23, 'Empresa 23', '23.639.420/0001-92', 'Cliente 23', '000000023-23', 1, 'Rua A, 23', True),
                                                                                               (1, 24, 'Empresa 24', '24.639.420/0001-92', 'Cliente 24', '000000024-24', 1, 'Rua A, 24', True),
                                                                                               (1, 25, 'Empresa 25', '25.639.420/0001-92', 'Cliente 25', '000000025-25', 1, 'Rua A, 25', True),
                                                                                               (1, 26, 'Empresa 26', '26.639.420/0001-92', 'Cliente 26', '000000026-26', 1, 'Rua A, 26', True),
                                                                                               (1, 27, 'Empresa 27', '27.639.420/0001-92', 'Cliente 27', '000000027-27', 1, 'Rua A, 27', True),
                                                                                               (1, 28, 'Empresa 28', '28.639.420/0001-92', 'Cliente 28', '000000028-28', 1, 'Rua A, 28', True),
                                                                                               (1, 29, 'Empresa 29', '29.639.420/0001-92', 'Cliente 29', '000000029-29', 1, 'Rua A, 29', True),
                                                                                               (1, 30, 'Empresa 30', '30.639.420/0001-92', 'Cliente 30', '000000030-30', 1, 'Rua A, 30', True),
                                                                                               (1, 31, 'Empresa 31', '31.639.420/0001-92', 'Cliente 31', '000000031-31', 1, 'Rua A, 31', True),
                                                                                               (1, 32, 'Empresa 32', '32.639.420/0001-92', 'Cliente 32', '000000032-32', 1, 'Rua A, 32', True),
                                                                                               (1, 33, 'Empresa 33', '33.639.420/0001-92', 'Cliente 33', '000000033-33', 1, 'Rua A, 33', True),
                                                                                               (1, 34, 'Empresa 34', '34.639.420/0001-92', 'Cliente 34', '000000034-34', 1, 'Rua A, 34', True),
                                                                                               (1, 35, 'Empresa 35', '35.639.420/0001-92', 'Cliente 35', '000000035-35', 1, 'Rua A, 35', True),
                                                                                               (1, 36, 'Empresa 36', '36.639.420/0001-92', 'Cliente 36', '000000036-36', 1, 'Rua A, 36', True),
                                                                                               (1, 37, 'Empresa 37', '37.639.420/0001-92', 'Cliente 37', '000000037-37', 1, 'Rua A, 37', True),
                                                                                               (1, 38, 'Empresa 38', '38.639.420/0001-92', 'Cliente 38', '000000038-38', 1, 'Rua A, 38', True),
                                                                                               (1, 39, 'Empresa 39', '39.639.420/0001-92', 'Cliente 39', '000000039-39', 1, 'Rua A, 39', True),
                                                                                               (1, 40, 'Empresa 40', '40.639.420/0001-92', 'Cliente 40', '000000040-40', 1, 'Rua A, 40', True),
                                                                                               (1, 41, 'Empresa 41', '41.639.420/0001-92', 'Cliente 41', '000000041-41', 1, 'Rua A, 41', True),
                                                                                               (1, 42, 'Empresa 42', '42.639.420/0001-92', 'Cliente 42', '000000042-42', 1, 'Rua A, 42', True),
                                                                                               (1, 43, 'Empresa 43', '43.639.420/0001-92', 'Cliente 43', '000000043-43', 1, 'Rua A, 43', True),
                                                                                               (1, 44, 'Empresa 44', '44.639.420/0001-92', 'Cliente 44', '000000044-44', 1, 'Rua A, 44', True),
                                                                                               (1, 45, 'Empresa 45', '45.639.420/0001-92', 'Cliente 45', '000000045-45', 1, 'Rua A, 45', True),
                                                                                               (1, 46, 'Empresa 46', '46.639.420/0001-92', 'Cliente 46', '000000046-46', 1, 'Rua A, 46', True),
                                                                                               (1, 47, 'Empresa 47', '47.639.420/0001-92', 'Cliente 47', '000000047-47', 1, 'Rua A, 47', True),
                                                                                               (1, 48, 'Empresa 48', '48.639.420/0001-92', 'Cliente 48', '000000048-48', 1, 'Rua A, 48', True),
                                                                                               (1, 49, 'Empresa 49', '49.639.420/0001-92', 'Cliente 49', '000000049-49', 1, 'Rua A, 49', True),
                                                                                               (1, 50, 'Empresa 50', '50.639.420/0001-92', 'Cliente 50', '000000050-50', 1, 'Rua A, 50', True);

INSERT INTO fornecedor
(empresa, id, nome, descricao, cnpj, ativo) VALUES
                                                (1, 1, 'Fornecedor 1', 'Descrição 1', '22.222.222/0001-01', true),
                                                (1, 2, 'Fornecedor 2', 'Descrição 2', '33.333.333/0001-02', true),
                                                (1, 3, 'Fornecedor 3', 'Descrição 3', '44.444.444/0001-03', true),
                                                (1, 4, 'Fornecedor 4', 'Descrição 4', '44.444.444/0001-03', true),
                                                (1, 5, 'Fornecedor 5', 'Descrição 5', '44.444.444/0001-03', true),
                                                (1, 6, 'Fornecedor 6', 'Descrição 6', '44.444.444/0001-03', true),
                                                (1, 7, 'Fornecedor 7', 'Descrição 7', '44.444.444/0001-03', true),
                                                (1, 8, 'Fornecedor 8', 'Descrição 8', '44.444.444/0001-03', true),
                                                (1, 9, 'Fornecedor 9', 'Descrição 9', '44.444.444/0001-03', true),
                                                (1, 10, 'Fornecedor 10', 'Descrição 10', '11.111.111/0001-10', true);

INSERT INTO marca
(empresa, id, ativo, nome) VALUES
                               (1, 1, true, 'Marca 1'),
                               (2, 2, true, 'Marca 2'),
                               (3, 3, true, 'Marca 3'),
                               (3, 4, true, 'Marca 4'),
                               (1, 5, true, 'Marca 5'),
                               (1, 6, true, 'Marca 6'),
                               (2, 7, true, 'Marca 7'),
                               (1, 8, true, 'Marca 8'),
                               (2, 9, true, 'Marca 9'),
                               (1, 10, true, 'Marca 10');

INSERT INTO produto(
    empresa, ativo, estoque, estoque_minimo, valor_compra, valor_venda, datainclusao, fornecedor_id, id, marca_produto, descricao, nome)
VALUES
    (1, true, 100, 10, 10.00, 20.00, '2023-01-01', 1, 1, 1, 'Produto 1 - Descrição', 'Produto 1'),
    (1, true, 100, 10, 15.00, 25.00, '2023-01-01', 1, 2, 2, 'Produto 2 - Descrição', 'Produto 2'),
    (1, true, 100, 10, 20.00, 30.00, '2023-01-01', 2, 3, 3, 'Produto 3 - Descrição', 'Produto 3'),
    (1, true, 100, 10, 50.00, 110.00, '2023-01-01', 1, 4, 1, 'Produto 4 - Descrição', 'Produto 4'),
    (1, true, 100, 10, 15.00, 70.00, '2023-01-01', 1, 5, 2, 'Produto 5 - Descrição', 'Produto 5');

INSERT INTO lista_categorias(produto_id, categorias_id) VALUES
                                                             (1, 1),
                                                             (1, 2),
                                                             (1, 3),
                                                             (2, 1),
                                                             (2, 2),
                                                             (2, 3),
                                                             (3, 1),
                                                             (3, 2),
                                                             (3, 3),
                                                             (4, 1),
                                                             (5, 2);

insert into usuario(ativo, id, nome, cpf, login, senha, empresa_usuario) VALUES
                                                                             (true, 1, 'kaio', '05562849259', 'kaio', 'cBz32i3RfBAIaqKNkTfdDZLrqih7z94jKllRAMiOW+U+b3GTkGAVUJhWsP6LK8KfVkkei6cekKUJpS2bU7VqvQ==', 1),
                                                                             (true, 2, 'kaio2', '05562849259', 'kaio2', 'cBz32i3RfBAIaqKNkTfdDZLrqih7z94jKllRAMiOW+U+b3GTkGAVUJhWsP6LK8KfVkkei6cekKUJpS2bU7VqvQ==', 2),
                                                                             (true, 3, 'kaio3', '05562849259', 'kaio3', 'cBz32i3RfBAIaqKNkTfdDZLrqih7z94jKllRAMiOW+U+b3GTkGAVUJhWsP6LK8KfVkkei6cekKUJpS2bU7VqvQ==', 3),
                                                                             (true, 4, 'joao', '05562849259', 'joao', 'cBz32i3RfBAIaqKNkTfdDZLrqih7z94jKllRAMiOW+U+b3GTkGAVUJhWsP6LK8KfVkkei6cekKUJpS2bU7VqvQ==', 4);

insert into usuario_perfil (perfil, id_usuario) VALUES
                                                    (1, 1),
                                                    (1, 2),
                                                    (1, 3),
                                                    (1, 4);