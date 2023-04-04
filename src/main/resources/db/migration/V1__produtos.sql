create table if not exists produtos(

    id int auto_increment not null,
    nome varchar(50) not null,
    cultura_utilizada varchar(100) not null,
    descricao_produto varchar(10240) not null,
    area_suportada varchar(100) not null,
    image_url varchar(255) not null,
    data_cadastro date not null,
    status varchar(50) not null,

    primary key(id)
);