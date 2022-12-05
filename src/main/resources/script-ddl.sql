create database app;

use app;

create table cliente(
	codigo int not null primary key auto_increment,
	nome varchar(80)
);

create table categoria_produto(
	codigo int not null primary key auto_increment,
	descricao varchar(120)
);

insert into categoria_produto (codigo,  descricao) values (null, 'Categoria 1');
insert into categoria_produto (codigo,  descricao) values (null, 'Categoria 2');
insert into categoria_produto (codigo,  descricao) values (null, 'Categoria 3');

create table produto(
	codigo int not null primary key auto_increment,
	descricao varchar(120),
	preco double,
	codigo_categoria_produto int,
	foreign key (codigo_categoria_produto) references categoria_produto(codigo)
);

insert into produto (codigo,  descricao, preco) values (null, 'Macarrão a bolonheza', 100, 1);
insert into produto (codigo,  descricao, preco) values (null, 'Bife com fritas', 200, 2);
insert into produto (codigo,  descricao, preco) values (null, 'Coca-cola', 8, 3);

create table pedido (
	codigo int not null primary key auto_increment,
	data date,
	cliente_cod int,
	foreign key (cliente_cod) references cliente(codigo)
);

create table itens_pedido (
	codigo int not null primary key auto_increment,
	pedido_cod int,
	produto_cod int,
	foreign key (pedido_cod) references pedido(codigo),
	foreign key (produto_cod) references produto(codigo)
);

insert into pedido (codigo, data, cliente_cod) values (null, '2022-10-22', 4);

insert into itens_pedido (codigo, pedido_cod, produto_cod) value (null, 1, 1);
insert into itens_pedido (codigo, pedido_cod, produto_cod) value (null, 1, 2);
insert into itens_pedido (codigo, pedido_cod, produto_cod) value (null, 1, 3);

select p.codigo, p.data, c.nome, pr.descricao, pr.preco from pedido p 
	join cliente c
	join itens_pedido i
	join produto pr
	on p.cliente_cod = c.codigo
	and p.codigo = i.pedido_cod
	and pr.codigo = i.produto_cod;