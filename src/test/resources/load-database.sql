INSERT INTO pessoa (nome, sobrenome, sexo, status, cpf) VALUES ('Renato','Oliveira','MASCULINO','ATIVO','31005888000');
INSERT INTO pessoa (nome, sobrenome, sexo, status, cpf) VALUES ('Lucas','Soares','MASCULINO','ATIVO','55223069048');
INSERT INTO pessoa (nome, sobrenome, sexo, status, cpf) VALUES ('Fernanda','Gomes','FEMININA','ATIVO','43349353037');
INSERT INTO pessoa (nome, sobrenome, sexo, status, cpf) VALUES ('Bruno','Guimaraes','MASCULINO','INATIVO','48563760041');
INSERT INTO pessoa (nome, sobrenome, sexo, status, cpf) VALUES ('Marcela','Souza','FEMININA','ATIVO','72788740417');


INSERT INTO telefone (ddd, numero, pessoa_id, tipo) VALUES ('82', '99907006', (SELECT id FROM pessoa WHERE cpf = '31005888000'), 'COMERCIAL');
INSERT INTO telefone (ddd, numero, pessoa_id, tipo) VALUES ('41', '89945933', (SELECT id FROM pessoa WHERE cpf = '43349353037'), 'COMERCIAL');
INSERT INTO telefone (ddd, numero, pessoa_id, tipo) VALUES ('77', '36226330', (SELECT id FROM pessoa WHERE cpf = '55223069048'), 'COMERCIAL');
INSERT INTO telefone (ddd, numero, pessoa_id, tipo) VALUES ('75', '99853804', (SELECT id FROM pessoa WHERE cpf = '72788740417'), 'COMERCIAL');
INSERT INTO telefone (ddd, numero, pessoa_id, tipo) VALUES ('31', '98853800', (SELECT id FROM pessoa WHERE cpf = '48563760041'), 'COMERCIAL');


