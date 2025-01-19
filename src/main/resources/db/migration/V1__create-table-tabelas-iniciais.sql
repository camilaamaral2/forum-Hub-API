create table Perfil (
    id SERIAL PRIMARY KEY,
    nome varchar(20) not null
);

create table Curso (
    id SERIAL PRIMARY KEY,
    nome varchar(30) not null,
    categoria varchar(30) not null
);

create table Usuario (
    id SERIAL PRIMARY KEY,
    nome varchar(30) not null,
    email varchar(100) not null,
    senha varchar(20) not null,
    perfil_id bigint not null,
    FOREIGN KEY (perfil_id) REFERENCES Perfil(id)
);


create table Topico (
   id SERIAL PRIMARY KEY,
   titulo varchar(100) not null,
   mensagem varchar(500) not null,
   dataCriacao date not null,
   status varchar(20) not null,
   usuario_id bigint not null,
   curso_id bigint not null,
   FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
   FOREIGN KEY (curso_id) REFERENCES Curso(id)
);

create table Resposta (
    id SERIAL PRIMARY KEY,
    mensagem varchar(500) not null,
    topico_id bigint not null,
    dataCriacao date not null,
    usuario_id bigint not null,
    solucao varchar(20),
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id),
    FOREIGN KEY (topico_id) REFERENCES Topico(id)
);