-- 1. Creación y uso de la Base de Datos
CREATE DATABASE IF NOT EXISTS bd_sistema_ventas;
USE bd_sistema_ventas;


DROP TABLE IF EXISTS tb_detalledv;
DROP TABLE IF EXISTS tb_cabeceradv;
DROP TABLE IF EXISTS tb_producto;
DROP TABLE IF EXISTS tb_categoria;
DROP TABLE IF EXISTS tb_cliente;
DROP TABLE IF EXISTS tb_usuario;
DROP TABLE IF EXISTS historial;

-- =========================================================
-- CREACIÓN DE TABLAS
-- =========================================================

-- Crear tabla usuario
CREATE TABLE tb_usuario (
    idusuario INT(11) AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    apellido VARCHAR(30) NOT NULL,
    usuario VARCHAR(15) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    password VARCHAR(15) NOT NULL,
    estado INT(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Crear tabla cliente
CREATE TABLE tb_cliente (
    idcliente INT(11) AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL,
    apellido VARCHAR(30) NOT NULL,
    cedula VARCHAR(15) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    direccion VARCHAR(100) NOT NULL,
    estado INT(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Crear tabla categoria
CREATE TABLE tb_categoria (
    idCategoria INT(11) AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(200) NOT NULL,
    estado INT(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Crear tabla producto
CREATE TABLE tb_producto (
    idProducto INT(11) AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    cantidad INT(11) NOT NULL,
    precio DOUBLE(10,2) NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    porcentajeIVA INT(2) NOT NULL,
    idCategoria INT(11) NOT NULL,
    estado INT(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Crear tabla cabeceradv (Cabecera de Venta)
CREATE TABLE tb_cabeceradv (
    idCabeceraVenta INT(11) AUTO_INCREMENT PRIMARY KEY,
    idCliente INT(11) NOT NULL,
    valorpagar DOUBLE(10,2) NOT NULL,
    fechaventa DATE NOT NULL,
    estado INT(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Crear tabla detalledv (Detalle de Venta)
CREATE TABLE tb_detalledv (
    idDetalledeVenta INT(11) AUTO_INCREMENT PRIMARY KEY,
    idCabeceraVenta INT(11) NOT NULL,
    idProducto INT(11) NOT NULL,
    cantidad INT(11) NOT NULL,
    preciounitario DOUBLE(10,2) NOT NULL,
    subtotal DOUBLE(10,2) NOT NULL,
    descuento DOUBLE(10,2) NOT NULL,
    iva DOUBLE(10,2) NOT NULL,
    totalpagar DOUBLE(10,2) NOT NULL,
    estado INT(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Crear tabla historial 
CREATE TABLE historial (
    id_historial INT AUTO_INCREMENT PRIMARY KEY,
    accion VARCHAR(255) NOT NULL,
    modulo VARCHAR(100) NOT NULL, -- Ej: "Ventas", "Productos", "Clientes"
    fecha_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



-- Insertar usuario administrador por defecto
INSERT INTO tb_usuario (nombre, apellido, usuario, password, telefono, estado)
VALUES ("admin", "admin", "admin", "12345", "0987654321", 1);


SHOW TABLES;
SELECT 
    *
FROM
    tb_usuario;