create database bd_sistema_ventas;

use bd_sistema_ventas;

-- crear tabla usuario
create table tb_usuario(
idusuario int (11) auto_increment primary key,
nombre varchar (30) not null,
apellido varchar (30) not null,
usuario varchar (15) not null,
telefono varchar (15) not null,
password varchar (15) not null,
estado int (1) not null
);

insert into tb_usuario(nombre, apellido, usuario, password, telefono, estado)
VALUES ("admin", "admin", "admin", "12345", "0987654321", 1);
truncate table tb_usuario;

select * from tb_usuario;
select usuario, password from tb_usuario where usuario = "admin" and password = "12345";

-- crear tabla cliente
create table tb_cliente(
idcliente int (11) auto_increment primary key,
nombre varchar (30) not null,
apellido varchar (30) not null,
cedula varchar (15) not null,
telefono varchar (15) not null,
direccion varchar (100) not null,
estado int (1) not null
);

-- crear tabla categoria
create table tb_categoria(
idCategoria int (11) auto_increment primary key,
descripcion varchar (200) not null,
estado int (1) not null
);

select * from tb_categoria;
select descripcion from tb_categoria where descripcion = '';

-- crear tabla producto
create table tb_producto(
idProducto int (11) auto_increment primary key,
nombre varchar (100) not null,
cantidad int (11) not null,
precio double (10,2) not null,
descripcion varchar (200) not null,
porcentajeIVA int (2) not null,
idCategoria int (11) not null,
estado int (1) not null
);

-- crear tabla cabeceradv
create table tb_cabeceradv(
idCabeceraVenta int (11) auto_increment primary key,
idCliente int (11) not null,
valorpagar double (10,2) not null,
fechaventa date not null,
estado int (1) not null
);

-- crear tabla detalledv
create table tb_detalledv(
idDetalledeVenta int (11) auto_increment primary key,
idCabeceraVenta int (11) not null,
idProducto int (11) not null,
cantidad int (11) not null,
preciounitario double (10,2) not null,
subtotal double (10,2) not null,
descuento double (10,2) not null,
iva double (10,2) not null,
totalpagar double (10,2) not null,
estado int (1) not null
);

show tables;

