drop database if exists pastrymonica;
create database if not exists pastrymonica;
use pastrymonica;

-- Creacion de tablas
drop table if exists `tb_categoria`;
create table `tb_categoria` (
  `cod_cat` int  auto_increment,
  `descrip_cat` varchar(20) not null,
  primary key (`cod_cat`)
)  ;

drop table if exists `tb_marca`;
create table `tb_marca` (
  `cod_marca` int auto_increment,
  `descr_marca` varchar(20) not null,
  `vendedor` varchar(45) default null,
  `telefono` varchar(13) default null,
  `correo` varchar(45) default null,
  primary key (`cod_marca`)
) ;

drop table if exists `tb_producto`;
create table `tb_producto` (
  `cod_prod` int  auto_increment,
  `descrip_prod` varchar(60) not null,
  `cod_cat` int not null,
  `cod_marca` int not null,
  `precio_prod` double not null,
  `stock_prod` int not null,
  primary key (`cod_prod`),
  constraint `cod_cat` foreign key (`cod_cat`) references `tb_categoria` (`cod_cat`),
  constraint `cod_marca` foreign key (`cod_marca`) references `tb_marca` (`cod_marca`)
)  ;

drop table if exists `tb_tipo`;
create table `tb_tipo` (
  `id_tipo` int not null auto_increment,
  `descripcion` varchar(45) default null,
  primary key (`id_tipo`)
) ;

drop table if exists `tb_usuario`;
create table `tb_usuario` (
  `codigo` int auto_increment,
  `nombre` varchar(25) default null,
  `apellido` varchar(25) default null,
  `usuario` varchar(15) default null,
  `pass` varchar(128) default null,
  `correo` varchar(45) default null,
  `id_tipo` int default 2 null,
  primary key (`codigo`),
  constraint `id_tipo` foreign key (`id_tipo`) references `tb_tipo` (`id_tipo`)
)  ;

drop table if exists `tb_orden`;
create table `tb_orden` (
  `id` int auto_increment,
  `fecha` datetime default localtime,
  `numero` varchar(255) default null,
  `total` double default null,
  `usuarioid` int default null,
  primary key (`id`),
  constraint `usuarioid` foreign key (`usuarioid`) references `tb_usuario` (`codigo`)
)  ;

drop table if exists `tb_detalle`;
create table `tb_detalle` (
  `id` int not null auto_increment,
  `cantidad`double default null,
  `nombre` varchar(25) default null,
  `precio` double default null,
  `total` double default null,
  `orden_id` int default null,
  `producto_id` int default  null,
  primary key (`id`),
  constraint `orden_id` foreign key (`orden_id`) references `tb_orden` (`id`),
  constraint `producto_id` foreign key (`producto_id`) references `tb_producto` (`cod_prod`)
)  ;





-- --------------------------------------
-- --------------------------------------
-- insert tb_categoria
insert into tb_marca values('1','Propio','-','-', '-');
insert into tb_marca values('2','Coca Cola','Cesar Arevalo','951236547', 'carevalo@gmail.com');
insert into tb_marca values('3','San Jorge','Sandro Jimenez','987412364', 'sjimenez@gmail.com');
insert into tb_marca values('4','Frito Lay','Silvia Cornejo','923658963', 'scornejo@gmail.com');

-- Insert Categoria
insert into `tb_categoria` values (1,'Postres caseros'),(2,'Bebidas frias'),(3,'Galletas'),(4,'Snacks'),(5, 'Bebidas calientes');
                                
-- Postres
insert into tb_producto values('1','Leche asada','1', '1','2.2', 50);
insert into tb_producto values('2','Torta de chocolate','1', '1','2.80', 50);
insert into tb_producto values('3','Pay de manzana','1', '1','2.80', 50);
insert into tb_producto values('4','Crema volteada','1', '1','2.50', 50);
insert into tb_producto values('5','Chiffon de naranja','1', '1','2.50', 50);
insert into tb_producto values('6','Alfajor','1', '1','1.80', 50);
insert into tb_producto values('7','Trufa de chocolate','1', '1','1.00', 50);
insert into tb_producto values('8','Merengue','1', '1','0.50', 50);
-- Bebidas frias
insert into tb_producto values('9','Coca Cola 1L','2', '2','3.70', 50);
insert into tb_producto values('0','Inca Kola 1L','2', '2','3.70', 50);
insert into tb_producto values('11','Sprite 500mL','2', '2','2.15', 50);
insert into tb_producto values('12','San Luis 1L','2', '2','2.70', 50);
insert into tb_producto values('13','Powerade 473mL','2', '2','1.99', 50);
insert into tb_producto values('14','Frugos Durazno 1L','2', '2','3.59', 50);
insert into tb_producto values('15','Schweppes 500mL','2', '2','2.40', 50);
insert into tb_producto values('16','Red Bull 250mL','2', '2','7.50', 50);
insert into tb_producto values('17','Fanta 500mL','2', '2','2.15', 50);
-- Galletas
insert into tb_producto values('18','Galleta Soda San Jorge Paquete 75g','3', '3','1.30', 50);
insert into tb_producto values('19','Galleta San Jorge Blackout Vainilla 60g','3', '3','0.80', 50);
insert into tb_producto values('20','Wafer San Jorge Chocogol','3', '3','4.90', 50);
insert into tb_producto values('21','Galletas de Agua San Jorge Nubbe Bolsa 110g','3', '3','1.90', 50);
insert into tb_producto values('22','Galletas Animalitos San Jorge Vainilla Bolsa 150g','3', '3','1.40', 50);
insert into tb_producto values('23','Galletas San Jorge Fruta Mixta Bolsa 60g','3', '3','1.00', 50);
insert into tb_producto values('24','Galletas Sabor Vainilla MuniciÃ³n San Jorge Bolsa 450g','3', '3','5.00', 50);
-- Bebidas calientes
insert into tb_producto values('25','Te','5', '4','2.50', 50);
insert into tb_producto values('26','Cafe','5', '4','3.50', 50);
                               
                                
insert into `tb_tipo` values (1,'Administrativo'),(2,'Cliente');

-- User: contraseña=user
-- Admin: contraseña=admin
insert into `tb_usuario` values								
                                (1,'Admin','Admin','Admin','$2a$10$1nosCamRQ5n59aPHJ1mswO.wNS8sUuReNra5YJRZOJDNHlBMgIozW','MNak@gmail.com',1),
                                (2,'User','User','User','$2a$10$fn1awK/14Ay.pw91He2HsOHpY9GAE8qRtBRbs5vV7Uk0irr0N8qum','User',2);


