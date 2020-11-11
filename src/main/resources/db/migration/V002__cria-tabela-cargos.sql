create table cargos (
	id int not null auto_increment,
    nome varchar(100) unique not null,
    id_departamento_fk int not null,
    
	primary key(id)
);

alter table cargos add constraint fk_departamento_id
foreign key (id_departamento_fk) references departamentos(id);
