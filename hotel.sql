create database hotel;
use hotel;
create table usuarios(
idlog int primary key auto_increment,
usuario varchar(100) not null,
login varchar (50) not null,
senha varchar (250) not null,
perfil varchar (250) not null 
);
create table clientes(
idcli int primary key auto_increment,
nome varchar (100) not null  ,
idade varchar (3) not null,
doc char (11) not null,
cep char (8)
);

create table personagem (
idperso  int primary key auto_increment, 
altura varchar (10) not null ,
força varchar (10) not null,
roupa varchar (100) not null ,
cabelo varchar (100) not null ,
cordapele  varchar (100) not null,
olhos  varchar (100) not null,
bracos varchar (50) not null,
pernas varchar (50) not null,
pelos varchar (50) not null ,
idcli int not null ,
foreign key(idcli) references clientes(idcli)
);
create table personalidade (
idpersonalidade int primary key auto_increment ,
ambicao varchar (100) not null ,
criatividade varchar (100) not null ,
compaixao varchar (100)  not null,
conciencia varchar (100) not null,
coragem varchar (100) not null ,
flexibilidade  varchar (100) not null,
honestidade varchar (100) not null ,
humildade varchar (100) not null,
integridade varchar (100) not null, 
lealdade varchar (100) not null,
paciencia varchar (100) not null ,
persistencia varchar (100) not null ,
resiliencia varchar (100) not null,
disciplina varchar (100) not null,
idperso  int not null,
foreign key(idperso) references personagem (idperso)

);

create table moderadores (
idmod int primary key auto_increment ,
nome varchar (100) not null,
idade char (2) not null,
endereco varchar (100) not null,
numero varchar (10) not null,
cidade varchar (100) not null ,
cep char (8) not null,
doc varchar (20) not null,
idlog int not null,
foreign key (idlog)references usuarios(idlog)
);