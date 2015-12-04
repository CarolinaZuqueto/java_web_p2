CREATE TABLE produto(
codigo integer PRIMARY KEY autoincrement,
descricao varchar (150),
nome varchar(100),
quantidade integer,
valor real
);

CREATE TABLE usuario(
login varchar (150) PRIMARY KEY,
email varchar(100),
senha varchar(6)
);