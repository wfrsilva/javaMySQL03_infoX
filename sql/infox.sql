-- cria banco de dados
-- create DATABASE dbinfox;
-- use dbinfox;
create table tbusuarios (
iduser int primary key,
usuario varchar(50) not null,
fone varchar (15),
login varchar(15) not null unique,
senha varchar (15) not null
);

describe tbusuarios;
-- CRUD
insert into tbusuarios (iduser, usuario, fone, login, senha)
values(1, 'Wendel Silva', '9999-0000', 'wfrsilva', '123456');

insert into tbusuarios (iduser, usuario, fone, login, senha)
values(2, 'codorna Silvassauro', '9999-2222', 'codorna', '654321');

insert into tbusuarios (iduser, usuario, fone, login, senha)
values(3, 'administrador', '9999-3333', 'adm', '123');

insert into tbusuarios (iduser, usuario, fone, login, senha)
values(4, 'admin', '9999-4444', 'admin', 'admin');

-- exibe tabela Read -> select
select * from tbusuarios;

-- modificar (Update CRUD) 
update tbusuarios set fone = '9999-1111' where iduser=1;

-- exibe tabela Read -> select
select * from tbusuarios;

-- apaga uma tupla da tabela
delete from tbusuarios where iduser=2;


create table tbclientes(
idcli int primary key auto_increment,
nomecli varchar(50) not null,
endcli varchar(100),
fonecli varchar(50) not null,
emailcli varchar(50)
);

describe tbclientes;


insert into tbclientes(nomecli, endcli, fonecli, emailcli)
values('Linus Trovalds', 'Rua Tux, 2020', '9999-9999', 'linus@linux.com');

select * from tbclientes;

use dbinfox;
create table tbos(
os int primary key auto_increment,
data_os timestamp default current_timestamp,
equipamento varchar(150) not null,
defeito varchar(150) not null,
servico varchar(150),
tecnico varchar(30),
valor decimal(10,2),
idcli int not null,
foreign key(idcli) references tbclientes(idcli)
);

describe tbos;

insert into tbos (equipamento, defeito, servico, tecnico, valor,idcli)
values ('notebook', 'nao liga', 'troca de fonte', 'zé', 87.50, 1);
use dbinfox;
select * from tbos;

-- inner join - informaçoes de duas tabelas
select
O.os, data_os, equipamento, defeito, servico, valor,
C.nomecli, fonecli
from tbos as O
inner join tbclientes as C
on (O.idcli = C.idcli);

select * from tbusuarios;
select * from tbusuarios where login='admin' and senha='admin';
