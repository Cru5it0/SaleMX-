USE salemx;

DROP PROCEDURE IF EXISTS insertpromocion;
DELIMITER $$
CREATE PROCEDURE insertpromocion(
	IN titulo_ VARCHAR(100),
    IN fechaInicio_ varchar(100),
    IN fechaTermino_ varchar(100),
    IN imagen_ LONGTEXT,
    IN descripcion_ VARCHAR(200),
    IN precio_ DOUBLE,
    IN descuento_ DOUBLE,
    IN idPS INT,
    IN idS INT,
    OUT idP INT
)
BEGIN
	INSERT INTO promocion(titulo,fechaInicio,fechaTermino,imagen,descripcion,precio,descuento,estatus,idProductoServicio,idSucursal) 
    VALUES(titulo_,fechaInicio_, fechaTermino_,imagen_, descripcion_,  precio_, descuento_, 1, idPS, idS);
    SET idP = last_insert_id();
END $$
delimiter ;

CALL insertpromocion ('Super oferta','2020-05-05','2020-05-07','', 'Bien bara', 500, 250, 15, 9, @idP);


DROP PROCEDURE IF EXISTS updatepromocion;
DELIMITER $$
CREATE PROCEDURE updatepromocion(
    IN idP INT,
	IN titulo_ VARCHAR(100),
    IN fechaInicio_ DATE,
    IN fechaTermino_ DATE,
    IN imagen_ LONGTEXT,
    IN descripcion_ VARCHAR(200),
    IN precio_ DOUBLE,
    IN descuento_ DOUBLE,
    IN idPS INT,
    IN idS INT
)
BEGIN
	UPDATE promocion
		SET titulo = titulo_,
            fechaInicio = fechaInicio_,
            fechaTermino = fechaTermino_,
            imagen = imagen_,
            descripcion = descripcion_,
            precio = precio_,
            descuento = descuento_,
            estatus = 1,
            idProductoServicio = idPS,
            idSucursal = idS
		WHERE idPromocion = idP;
END $$
delimiter ;
CALL updatepromocion (1, 'Super oferta', 20200710, 20200715, '', 'Bien bara', 500, 250, 1, 1);

DROP PROCEDURE IF EXISTS deletepromocion;
DELIMITER $$
CREATE PROCEDURE deletepromocion(IN idP INT)
BEGIN
	UPDATE promocion
		SET estatus = 0
    WHERE idPromocion = idP;
END $$
delimiter ;
CALL deletepromocion(24);

DROP PROCEDURE IF EXISTS activatepromocion;
DELIMITER $$
CREATE PROCEDURE activatepromocion(IN idP INT)
BEGIN
	UPDATE promocion
		SET estatus = 1
    WHERE idPromocion = idP;
END $$
delimiter ;
CALL activatepromocion(24);

DROP VIEW IF EXISTS consultaPromocionA;
CREATE VIEW consultaPromocionA AS (
		SELECT idPromocion,titulo,fechaInicio,fechaTermino,imagen,descripcion,precio,descuento,idSucursal,idProductoServicio 
			FROM promocion WHERE estatus = 1);

DROP VIEW IF EXISTS consultaPromocionI;
CREATE VIEW consultaPromocionI AS (
		SELECT idPromocion,titulo,fechaInicio,fechaTermino,imagen,descripcion,precio,descuento,idSucursal,idProductoServicio 
			FROM promocion WHERE estatus = 0);

SELECT * FROM consultaPromocionA;
drop view if exists promo2;

create view promo2 as select ps.idProductoServicio,ps.nombre,e.idEmpresa from productoservicio as ps inner join empresa e on ps.idEmpresa=e.idEmpresa;
select * from promo2 where idEmpresa=1;

select ps.idProductoServicio,ps.nombre from productoservicio as ps inner join empresa e on ps.idEmpresa=e.idEmpresa where e.idEmpresa=4;

SELECT * FROM consultaPromocionI;
select * from promocion;
CREATE VIEW consultaPromocionAc AS (
		SELECT p.idPromocion,p.titulo,p.fechaInicio,p.fechaTermino,p.imagen,p.descripcion,p.precio,p.descuento,p.idSucursal,s.nombre as Sucursal,p.idProductoServicio, ps.nombre as ProductoServicio
			FROM promocion p inner join Sucursal s on p.idSucursal=s.idSucursal inner join productoservicio ps on p.idProductoServicio=ps.idProductoServicio inner join Empresa e on e.idEmpresa=s.idSucursal  WHERE p.estatus = 1);
            select * from consultaPromocionAc;
