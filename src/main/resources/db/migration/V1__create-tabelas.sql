create table permissao (
                           idpermissao serial not null primary key,
                           nome_permissao varchar(50) not null

);


create table usuario (
                         idusuario serial not null primary key,
                         uuid UUID DEFAULT gen_random_uuid(),
                         nome varchar(50) not null,
                         email varchar(100) not null unique,
                         senha varchar(50) not null,
                         ativo boolean,
                         idpermissao int not null,
                         foreign key(idpermissao) references permissao(idpermissao)

);





create table produto(
                        idproduto serial not null primary key ,
                        nome_produto varchar(50) not null ,
                        preco numeric not null,
                        descricao varchar(300),
                        img varchar(300)

);

create table venda (
                       idvenda serial not null primary key ,
                       vendedor int not null ,
                       comprador int not null ,
                       idproduto int not null ,
                       valor numeric not null ,
                       quantia int not null ,
                       foreign key(vendedor) references usuario(idusuario),
                       foreign key(comprador) references usuario(idusuario),
                       foreign key(idproduto) references produto(idproduto)
);

