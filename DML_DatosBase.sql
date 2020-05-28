/****************************************************
 *	BASE DE DATOS sale!MX                           *
 *                                                  *
 *	Archivo de Datos Base para pruebas (DML)        *
 ***************************************************/
 
 /*
    Version:        1.0
    Fecha:          10/03/2020 10:58:00
    Autor:          Cruz Isaac Aranda Cervantes
    Email:          cruzisaacarandaervantes@gmail.com
    Comentarios:    Datos basicos para hacer pruebas
                    de consultas con la Base de Datos.
 */
 
USE salemx;

 -- Datos de Cliente [cliente]:
INSERT INTO cliente(idCliente, nombre, apellidoPaterno, apellidoMaterno, genero, domicilio, telefono, nombreUsuario, correo, contrasenia, rol, estatus) VALUE (1, 'Isaac', 'Aranda', 'Cervantes', 'M', 'Faro del Carmen 239', '4773991236','cruzito', 'cruzito@gmail.com', 'cruz123', 'Cliente', 1);
INSERT INTO cliente(idCliente, nombre, apellidoPaterno, apellidoMaterno, genero, domicilio, telefono, nombreUsuario, correo, contrasenia, rol, estatus) VALUE (2, 'Carlos', 'Hernandez', 'Zapien', 'M', 'Vivar 502', '47739393828','carlos', 'carlos@gmail.com', 'u123', 'Cliente', 1);
INSERT INTO cliente(idCliente, nombre, apellidoPaterno, apellidoMaterno, genero, domicilio, telefono, nombreUsuario, correo, contrasenia, rol, estatus) VALUE (3, 'Paulina Joseline', 'Cervantes', 'Parra', 'F', 'Faro de la costa', '47714564675','paulina', 'paulina@gmail.com', 'u234', 'Cliente', 1);
INSERT INTO cliente(idCliente, nombre, apellidoPaterno, apellidoMaterno, genero, domicilio, telefono, nombreUsuario, correo, contrasenia, rol, estatus) VALUE (4, 'Alan', 'Uciel', 'Gomez', 'M', 'Barcelona 303', '4773939834','alan', 'alan@gmail.com', 'u345', 'Cliente', 1);
INSERT INTO cliente(idCliente, nombre, apellidoPaterno, apellidoMaterno, genero, domicilio, telefono, nombreUsuario, correo, contrasenia, rol, estatus) VALUE (5, 'Angel', 'Martinez', 'Cervantes', 'M', 'Vista hermosa 203', '4771467389','angel', 'angel@gmail.com', 'u456', 'Cliente', 1);
INSERT INTO cliente(idCliente, nombre, apellidoPaterno, apellidoMaterno, genero, domicilio, telefono, nombreUsuario, correo, contrasenia, rol, estatus) VALUE (6, 'Sofia Saharai', 'Gomez', 'Martinez', 'F', 'Faro del Carmen 239', '4773991236','sofia', 'sofia@gmail.com', 'u567', 'Cliente', 1);
INSERT INTO cliente(idCliente, nombre, apellidoPaterno, apellidoMaterno, genero, domicilio, telefono, nombreUsuario, correo, contrasenia, rol, estatus) VALUE (7, 'Karol', 'Hernandez', 'Hernandez', 'F', 'Faro viejo', '4771023223','karol', 'karol@gmail.com', 'u678', 'Cliente', 1);
INSERT INTO cliente(idCliente, nombre, apellidoPaterno, apellidoMaterno, genero, domicilio, telefono, nombreUsuario, correo, contrasenia, rol, estatus) VALUE (8, 'José', 'Carreras', 'Peres', 'M', 'Santa rita 210', '4775003290','jose', 'jose@gmail.com', 'cruz123', 'Cliente', 1);
INSERT INTO cliente(idCliente, nombre, apellidoPaterno, apellidoMaterno, genero, domicilio, telefono, nombreUsuario, correo, contrasenia, rol, estatus) VALUE (9, 'Angelica', 'Martinez', '', 'F', 'San juan bosco 213', '4772913289','angelica', 'angelica@gmail.com', 'cruz123', 'Cliente', 1);
INSERT INTO cliente(idCliente, nombre, apellidoPaterno, apellidoMaterno, genero, domicilio, telefono, nombreUsuario, correo, contrasenia, rol, estatus) VALUE (10, 'Jodé de Jesus', 'Pedro', 'Carrera', 'M', 'Faro del Carmen 239', '4771828373','pedro', 'pedro@gmail.com', 'cruz123', 'Cliente', 1);
 
 -- Datos de Empresa [empresa]:
INSERT INTO empresa (idEmpresa, nombre, domicilio, logo, foto, nombreUsuario, correo, contrasenia, rol) VALUES(1, 'Zara', '', '', '', 'ZaraShop', 'zaraShop@gmail.com', 'u123', 'Empresa');
INSERT INTO empresa (idEmpresa, nombre, domicilio, logo, foto, nombreUsuario, correo, contrasenia, rol) VALUES(2, 'Burger King', '', '', '', 'BurguerKing:D', 'burguerking@gmail.com', 'u2123', 'Empresa');
INSERT INTO empresa (idEmpresa, nombre, domicilio, logo, foto, nombreUsuario, correo, contrasenia, rol) VALUES(3, 'Mallet', '', '', '', 'Mallet Bar', 'mallet@gmail.com', 'u3123', 'Empresa');

 -- Datos de Sucursales [sucursal]:
INSERT INTO sucursal (idSucursal, nombre, domicilio, longitud, latitud, foto, rutafoto, idEmpresa) VALUES(1, 'Zara Altacia', 'Blvd. Aeropuerto 104, Cerrito de Jerez Nte., 37530 León, Gto.', 21.0913548, -101.626498, '', '', 1);
INSERT INTO sucursal (idSucursal, nombre, domicilio, longitud, latitud, foto, rutafoto, idEmpresa) VALUES(2, 'Zara Plaza Mayor', 'Blvd. Juan Alonso de Torres Pte. 2002, Valle del Campestre, 37150 Mexico, Gto.', 21.1582689, 21.123116, '', '', 1);

INSERT INTO sucursal (idSucursal, nombre, domicilio, longitud, latitud, foto, rutafoto, idEmpresa) VALUES(3, 'Burger King Suc 10403', 'Blvd. Juan Alonso de Torres Pte. 2002, Valle del Campestre, 37150 León, Gto.', 21.113359, -101.6802565, '', '', 2);
INSERT INTO sucursal (idSucursal, nombre, domicilio, longitud, latitud, foto, rutafoto, idEmpresa) VALUES(4, 'Burger King Insurgente', 'Paseo de los Insurgentes 210, Jardines del Moral, 37160 León, Gto.', 21.1412578, -101.6843637, '', '', 2);
INSERT INTO sucursal (idSucursal, nombre, domicilio, longitud, latitud, foto, rutafoto, idEmpresa) VALUES(5, 'Burger King Leon Las Torres', 'Blvd. Juan Alonso de Torres Pte. 1315, 37200 León, Gto.', 21.1502035, -101.669043, '', '', 2);
INSERT INTO sucursal (idSucursal, nombre, domicilio, longitud, latitud, foto, rutafoto, idEmpresa) VALUES(6, 'Burger King Benito Juarez', 'Benito Juárez 108, Centro, 37000 León, Gto.', 21.1502035,-101.669043, '', '', 2);
INSERT INTO sucursal (idSucursal, nombre, domicilio, longitud, latitud, foto, rutafoto, idEmpresa) VALUES(7, 'Burger King Bolerama', 'Blvd. Mariano Escobedo Ote. 2719, El Tlacuache Poniente, 37500 León, Gto.', 21.153179,-101.6727543, '', '', 2);
INSERT INTO sucursal (idSucursal, nombre, domicilio, longitud, latitud, foto, rutafoto, idEmpresa) VALUES(8, 'Burger King LEON SILAO', 'Blvd. Aeropuerto 1406, Autopista Poniente, 37860 León, Gto.', 21.1547562,-101.6682727, '', '', 2);

INSERT INTO sucursal (idSucursal, nombre, domicilio, longitud, latitud, foto, rutafoto, idEmpresa) VALUES(9, 'Mallet León', 'Av Paseo del Moral 330, Jardines del Moral, 37160 León, Gto.', 21.1464441, -101.692472, '', '', 3);

 -- Datos de Producto o Servicio [productoservicio]:
INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(1, 'Abrigo', '', '', 1299, 'Hermoso abrigo zara De calidad material Corte muy favorecedor', 1, 1);
INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(2, 'Blusa', '', '', 150, 'Blusa de colores de temporada muy original', 1, 1);
INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(3, 'Jumps', '', '', 480, 'Bonito jumps nuevo', 1, 1);
INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(4, 'Vestido', '', '', 300, 'Vestido corto blanco Zara. Tela gruesa, ideal para vestir con mallas. Manga corta. ', 1, 1);
INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(5, 'Sueter', '', '', 300, 'Para esta temporada de frio', 1, 1);

INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(6, 'Hamburguesa', '', '', 25, 'Hamburguesa sencilla con queso amarillo', 1, 2);
INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(7, 'Hamburguesa hot', '', '', 30, 'Hamburguesa super picante', 1, 2);
INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(8, 'Hamburguesa res', '', '', 35, 'Hamburguesa de res', 1, 2);
INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(9, 'Hamburguesa doble', '', '', 50, 'Hamburguesa con doble carne', 1, 2);
INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(10, 'CocaCola', '', '', 15, 'CocaCola de 500ml', 1, 2);
INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(11, 'Fanta', '', '', 15, 'Fanta multi multi sabores de 500ml', 1, 2);
INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(12, 'Papas fritas', '', '', 25, 'Papas fritas a la francesa', 1, 2);
INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(13, 'Papas fritas hot', '', '', 30, 'Papas fritas a la francesa extra picantes', 1, 2);
INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(14, 'Cono sencillo', '', '', 10, 'Helado cono sencillo', 1, 2);

INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(15, 'Bacardi', '', '', 1000, 'Botella de bacardi de 1L', 1, 3);
INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(16, 'Refrescos', '', '', 150, 'Servicio de 5 refrescos de lata', 1, 3);
INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(17, 'Cerveza Victoria', '', '', 40,  'Una cerveza Victoria de 500ml', 1, 3);
INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(18, 'Vodka Oso negro', '', '', 1200, 'Botella de vodka Oso negro de 1L', 1, 3);
INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(19, 'Centenario de plata', '', '', 1500, 'Botella de tequila centenario de 1L', 1, 3);
INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(20, 'Smirnof', '', '', 1500, 'Botella de Smirnof de 1L', 1, 3);
INSERT INTO productoservicio (idProductoServicio, nombre, foto, rutafoto, precio, descripcion, estatus, idEmpresa) VALUES(21, 'Cerveza Corona', '', '', 50, 'Una cerveza corona de 500ml', 1, 3);

 -- Datos de Promocion [promocion]:
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(1, 'Ropa para invierno', 20191220, 20200320, '', '', '50% de descuento en toda la ropa de invierno', '1299', '600', 1, 1, 1);
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(2, 'Ropa de verano', 20200403, 20200420, '', '', '20% de descuento en toda la ropa de verano', '150', '150', 1, 2, 1);
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(3, 'Ropa para invierno', 20191220, 20200320, '', '', '50% de descuento en toda la ropa de invierno', '1299', '600', 1, 1, 2);
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(4, 'Ropa de verano', 20200403, 20200420, '', '', '20% de descuento en toda la ropa de verano', '150', '150', 1, 2, 2);

INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(5, 'Hamburguesa al 2x1', 20200403, 20210420, '', '', '2x1 en hamburguesas sensillas todos los viernes', '30', '25', 1, 6, 3);
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(6, 'Super Como amigo', 20200403, 20200404, '', '', '2x1 en hamburguesas doble carne más un refresco', '130', '75', 1, 9, 3);
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(7, 'Combo papas', 20200404, 20200405, '', '', '2 paquetes de papas fritas más una hamburguesa', '55', '45', 1, 12, 3);
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(8, 'Rodeo Burger o Crispy Chicken', 20200705, 20201205, '', '', '5 por 50 Rodeo Burger o Crispy Chicken más papas y refresco regular, nuggets 3 piezas, cono sencillo', '125', '55', 1, 12, 3);
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(9, 'Hamburguesa al 2x1', 20200403, 20210420, '', '', '2x1 en hamburguesas sensillas todos los viernes', '30', '25', 1, 6, 4);
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(10, 'Super Como amigo', 20200403, 20200404, '', '', '2x1 en hamburguesas doble carne más un refresco', '130', '75', 1, 9, 4);
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(11, 'Combo papas', 20200404, 20200405, '', '', '2 paquetes de papas fritas más una hamburguesa', '55', '40', 1, 12, 4);
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(12, 'Rodeo Burger o Crispy Chicken', 20200705, 20201205, '', '', '5 por 50 Rodeo Burger o Crispy Chicken más papas y refresco regular, nuggets 3 piezas, cono sencillo', '125', '55', 1, 12, 4);
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(13, 'Hamburguesa al 2x1', 20200403, 20210420, '', '', '2x1 en hamburguesas sensillas todos los viernes', '30', '25', 1, 6, 5);
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(14, 'Super Como amigo', 20200403, 20200404, '', '', '2x1 en hamburguesas doble carne más un refresco', '130', '75', 1, 9, 5);
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(15, 'Combo papas', 20200404, 20200405, '', '', '2 paquetes de papas fritas más una hamburguesa', '100', '55', 1, 12, 5);
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(16, 'Rodeo Burger o Crispy Chicken', 20200705, 20201205, '', '', '5 por 50 Rodeo Burger o Crispy Chicken más papas y refresco regular, nuggets 3 piezas, cono sencillo', '125', '55', 1, 12, 5);
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(17, 'Hamburguesa al 2x1', 20200403, 20200404, '', '', '2x1 en hamburguesas sensillas todos los viernes', '30', '25', 1, 6, 6);
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(18, 'Super Como amigo', 20200403, 20200404, '', '', '2x1 en hamburguesas doble carne más un refresco', '130', '75', 1, 9, 6);
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(19, 'Combo papas', 20200404, 20200405, '', '', '2 paquetes de papas fritas más una hamburguesa', '100', '55', 1, 12, 6);
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(20, 'Rodeo Burger o Crispy Chicken', 20200705, 20201205, '', '', '5 por 50 Rodeo Burger o Crispy Chicken más papas y refresco regular, nuggets 3 piezas, cono sencillo', '125', '55', 1, 12, 6);

INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(21, 'Cumpleañero', 20200705, 20250705, '', '', '2x1 en botellas el dia del cumpleañero más un servicio de refrescos gratis', '1500', '1000', 1, 12, 9);
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(22, 'Cerveza al 2x1', 20200705, 2020005, '', '', 'Cervezas 2x1 toda la noche', '50', '50', 1, 12, 9);
INSERT INTO promocion (idPromocion, titulo, fechaInicio, fechaTermino, imagen, rutaimagen, descripcion, precio, descuento, estatus, idProductoServicio, idSucursal) VALUES(23, 'Tequlaso', 20200705, 20200705, '', '', 'Oferta de 20% en todos los tequilas', '1500', '1200', 1, 12, 9);

 -- Datos de Sucursal Cliente [sucursalcliente]:
 
 