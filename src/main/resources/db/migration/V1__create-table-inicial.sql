-- 1. Tabela de Usuários
CREATE TABLE usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

-- 2. Tabela de Cursos
CREATE TABLE cursos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    categoria VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

-- 3. Tabela de Tópicos
CREATE TABLE topicos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    mensagem VARCHAR(255) NOT NULL,
    data_criacao DATETIME NOT NULL,
    status VARCHAR(50) NOT NULL, -- O Enum será salvo como String aqui
    autor_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_topicos_autor_id FOREIGN KEY (autor_id) REFERENCES usuarios (id),
    CONSTRAINT fk_topicos_curso_id FOREIGN KEY (curso_id) REFERENCES cursos (id)
);