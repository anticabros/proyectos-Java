docker-compose up -d;

CREATE TABLE usuarios (
    id_usuario INT PRIMARY KEY,
    nombre VARCHAR(255),
    estado VARCHAR(255),
    telefono VARCHAR(15),
    direccion VARCHAR(255),
    email VARCHAR(255),
    cedula VARCHAR(15),
    password VARCHAR(255)
);

CREATE TABLE roles (
    id_rol INT PRIMARY KEY,
    estado  VARCHAR(255),
    nombre VARCHAR(255),
    descripcion VARCHAR(255)
);

CREATE TABLE roles_usuarios (
    id_usuario INT,
    id_rol INT,
    estado  VARCHAR(255),
    PRIMARY KEY (id_usuario, id_rol),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
    FOREIGN KEY (id_rol) REFERENCES roles(id_rol)
);
