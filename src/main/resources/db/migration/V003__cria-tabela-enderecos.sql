create table enderecos (
	id int not null auto_increment,
    logradouro varchar(255) not null,
    bairro varchar(50) not null,
    cidade varchar(50) not null,
    uf varchar(10),
    cep varchar(9),
    numero varchar(5),
    complemento varchar(30),
    
    primary key(id)
);