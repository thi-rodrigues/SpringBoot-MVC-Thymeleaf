create table funcionarios (
	id int not null auto_increment,
    nome varchar(255) not null,
    salario decimal (10,2) not null DEFAULT '0.00',
    data_entrada date not null,
    data_saida date,
    endereco_id_fk int,
    cargo_id_fk int,
    
    primary key(id)
);

alter table funcionarios add constraint fk_endereco_id
foreign key (endereco_id_fk) references enderecos(id);

alter table funcionarios add constraint fk_cargo_id
foreign key (cargo_id_fk) references cargos(id);