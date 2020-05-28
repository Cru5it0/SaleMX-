/*************************************************
 *      BASE DE DATOS sale!MX                    *
 *                                               *
 *      Lenguaje de Definicion de Datos (DDL)    *
 ************************************************/
/*
    Version:        1.0
    Fecha:          20/febrero/2020 14:00:00
    Autor:          Angel Efren
    Email:          
    Comentarios:    Esta es la primera version de la base de datos
                    con las instrucciones necesarias para
                    generar las tablas.
*/

/*
    Version:        1.2
    Fecha:          02/marzo/2020 10:40:00
    Autor:          Cruz Isaac Aranda Cervantes
    Email:          cruzisaacarandaervantes@gmail.com
    Comentarios:    Se modifico el nombre de la base de datos,
					se agrego la tabla de sucursales, se modifico
                    la tabla persona.
 */
 
 /*
    Version:        1.3
    Fecha:          04/marzo/2020 10:40:00
    Autor:          Carlos Ernesto Zapien Diaz
    Email:          zapienernesto5@gmail.com
    Comentarios:    Se modifico el nombre de la base de datos,
					se modifico la tala de poromocion,
					se modifico el nombre de producto por productoservicio,
					se agrega un nuevo rol en el usuario
 */
 
  /*
    Version:        1.4
    Fecha:          17/marzo/2020 18:17:58
    Autor:          Alan Usiel Gómez Hernandez
    Email:          nosewe123noteama@gmail.com
    Comentarios:    Se modificaron los datos de la tabla empresa
					se añadio contacto a empresa
                    se modifico domiclio por calle, colonia y numero
 */
 
DROP DATABASE IF EXISTS salemx;

CREATE DATABASE IF NOT EXISTS salemx;

USE salemx;

-- Esta tabla se encarga de guardar los datos del cliente:
CREATE TABLE cliente (
    idCliente 			INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    /*Persona*/
	nombre 				VARCHAR(64) NOT NULL DEFAULT '',
    apellidoPaterno 	VARCHAR(64) NOT NULL DEFAULT '',
    apellidoMaterno 	VARCHAR(64) NOT NULL DEFAULT '',
    genero 				VARCHAR(2) NOT NULL DEFAULT 'O', -- M: masculino; F: femenino
    domicilio			VARCHAR(50) NOT NULL DEFAULT '',
    telefono 			VARCHAR(25) NOT NULL DEFAULT '',
    /*Usuario*/
	nombreUsuario 		VARCHAR(48) NOT NULL DEFAULT '',
    correo 				VARCHAR(200) NOT NULL DEFAULT '',
    contrasenia 		VARCHAR(48) NOT NULL DEFAULT '',
    rol 				VARCHAR(24) NOT NULL DEFAULT 'Cliente',-- INT NOT NULL DEFAULT 1 -- 1:Admin; 2: Empresa; 3: Cliente;3;
    estatus         	INT NOT NULL DEFAULT 1, -- 1: Activo; 2: Inactivo
    token				LONGTEXT
);

-- Esta tabla se encarga de guardar los datos de empresa:
CREATE TABLE empresa (
    idEmpresa 			INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre		 		VARCHAR(100) NOT NULL DEFAULT '',
    contacto	 		VARCHAR(100) NOT NULL DEFAULT '',
    domicilio		 	VARCHAR(100) NOT NULL DEFAULT '',
    logo 				LONGTEXT,
    foto				LONGTEXT,
    /*Usuario*/
	nombreUsuario 		VARCHAR(48) NOT NULL DEFAULT '',
    correo 				VARCHAR(200) NOT NULL DEFAULT '',
    contrasenia 		VARCHAR(48) NOT NULL DEFAULT '',
    rol 				VARCHAR(24) NOT NULL DEFAULT 'Empresa',-- INT NOT NULL DEFAULT 1 -- 1:Admin; 2: Empresa; 3: Cliente;3;
    estatus				INT NOT NULL DEFAULT 1 -- 1 = Activo; 2 = Inactiva;
);

-- Esta tabla se encarga de guardar los datos de las sucursales:
CREATE TABLE sucursal (
	idSucursal 			INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre		 		VARCHAR(100) NOT NULL DEFAULT '',
    domicilio		 	VARCHAR(100) NOT NULL DEFAULT '',
    latitud 			DOUBLE NOT NULL DEFAULT 0.0, 
    longitud 			DOUBLE NOT NULL DEFAULT 0.0,    
    foto		 		LONGTEXT,
    rutafoto			TEXT,
    estatus				INT NOT NULL DEFAULT 1, -- 1 = Activo; 2 = Inactiva;
    idEmpresa 			INT NOT NULL,
    CONSTRAINT fk_empresa_sucursal FOREIGN KEY (idEmpresa)
				REFERENCES empresa (idEmpresa) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Esta tabla se encarga de guardar los datos del producto:
CREATE TABLE productoservicio (
    idProductoServicio			INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    nombre		 				VARCHAR(100) NOT NULL DEFAULT '',
    foto			   		 	LONGTEXT,
    rutafoto					TEXT,
    precio 						DOUBLE NOT NULL DEFAULT 0,
    descripcion					VARCHAR(100) NOT NULL DEFAULT '',
    estatus						INT NOT NULL DEFAULT 1, -- 1: Activo; 2: Inactivo; 3: Eliminación fisica
    idEmpresa 					INT NOT NULL,
    CONSTRAINT fk_idEmpresa_producto FOREIGN KEY (idEmpresa)
				REFERENCES Empresa (idEmpresa) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Esta tabla se encarga de guardar los datos de las promociones:
CREATE TABLE promocion (
	idPromocion				INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    titulo 					VARCHAR(100) NOT NULL DEFAULT '',
    fechaInicio 			DATE NOT NULL, 
    fechaTermino 			DATE, -- Recordando que puede tener fecha sin termino
    imagen 					LONGTEXT,
    rutaimagen				TEXT,
    descripcion 			VARCHAR(200),
    precio 					DOUBLE NOT NULL DEFAULT 0,
    descuento 				DOUBLE NOT NULL DEFAULT 0,
    estatus					INT NOT NULL DEFAULT 1, -- 1: Activo; 2: Inactivo; 3: Eliminación fisica
    idProductoServicio		INT NOT NULL,
    idSucursal 				INT NOT NULL,
    CONSTRAINT fk_promocion_producto FOREIGN KEY (idProductoServicio)
				REFERENCES productoservicio (idProductoServicio) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_sucursal_producto FOREIGN KEY (idSucursal)
				REFERENCES sucursal (idSucursal) ON DELETE CASCADE ON UPDATE CASCADE                
);

CREATE TABLE sucursal_cliente (
	idClienteSucursal				INT NOT NULL PRIMARY KEY,
	idSucursal 						INT NOT NULL,
    idCliente						INT NOT NULL,
	calificacion					INT,
    comentaris						VARCHAR(200),
    CONSTRAINT fk_sucursal_cliente FOREIGN KEY (idSucursal)
				REFERENCES sucursal (idSucursal) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT fk_cliente_sucursal FOREIGN KEY (idCliente)
				REFERENCES cliente (idCliente) ON DELETE CASCADE ON UPDATE CASCADE
);




