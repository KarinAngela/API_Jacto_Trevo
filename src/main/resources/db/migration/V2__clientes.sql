create table if not exists clientes(
    id int auto_increment not null,
    nome varchar (50) not null,
    email varchar (100) not null unique,
    telefone varchar (15) not null,
    produto_interesse varchar (80) not null,

    primary key(id)


);