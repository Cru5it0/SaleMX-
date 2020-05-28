use salemx;
/*Empresa*/
DROP VIEW IF EXISTS consultaEmpresaA;
CREATE VIEW consultaEmpresaA AS (SELECT idEmpresa,nombre,contacto,domicilio,logo,correo,nombreUsuario,contrasenia FROM empresa WHERE estatus = 1);
select * from consultaEmpresaA;
ALTER TABLE empresa ADD COLUMN token LONGTEXT;

DROP PROCEDURE IF EXISTS insertTokenE;
DELIMITER $$
CREATE PROCEDURE insertTokenE(
	in idE_ INT,
    in token_ LONGTEXT
    )
BEGIN
	START TRANSACTION;
UPDATE empresa 
	SET 
		token = token_	
	WHERE
		idEmpresa = idE_;
    COMMIT;
END $$
delimiter ;


DROP PROCEDURE IF EXISTS deleteTolkenE;
DELIMITER $
CREATE PROCEDURE deleteTolkenE(in idE INT)
BEGIN
	UPDATE empresa
		SET token = NULL OR token = ''
	WHERE idEmpresa = idE;
END $
delimiter ;


DROP VIEW IF EXISTS TokenE;
CREATE VIEW TokenE AS (SELECT * FROM empresa WHERE nombreUsuario = "Mallet Bar" AND contrasenia = "u3123");
SELECT * FROM TokenE;

SELECT token FROM empresa WHERE idEmpresa = 3;

DROP PROCEDURE IF EXISTS insertEmpresa;
delimiter $
CREATE PROCEDURE insertEmpresa(
	in nomEmpresa_ VARCHAR(100),
    in domicilio_ varchar(100),
    in logo_ longtext,
    in foto_ longtext,
    in nomContacto_ VARCHAR(100),
    in nomUsuario_ varchar(48),
    in correo_ varchar(200),
	in contrasenia_ varchar(48),
    in rol_ varchar(24),
    in estatus_ int,
    out idS INT)
BEGIN
    INSERT INTO empresa(idEmpresa, nombre, domicilio, logo, foto, contacto, nombreUsuario, correo, contrasenia, rol, estatus)
    VALUES(DEFAULT,nomEmpresa_,domicilio_ ,logo_, foto_, nomContacto_,nomUsuario_,correo_,contrasenia_,rol_,estatus_);
    set idS= last_insert_id();
    COMMIT;
END; $
delimiter ;

DROP PROCEDURE IF EXISTS updateEmpresa;
delimiter $
CREATE PROCEDURE updateEmpresa(
	in idE INT,
    in nomEmpresa_ VARCHAR(100),
    in domicilio_ varchar(100),
    in logo_ longtext,
    in foto_ longtext,
    in nomContacto_ VARCHAR(100),
    in nomUsuario_ varchar(48),
    in correo_ varchar(200),
	in contrasenia_ varchar(48))
BEGIN
	START TRANSACTION;
UPDATE empresa
SET 
    nombre = nomEmpresa_,
    domicilio = domicilio_,
    logo = logo_,
    foto = foto_,
    contacto = nomContacto_,
    nombreUsuario = nomUsuario_,
    correo = correo_,
    contrasenia = contrasenia_ 
WHERE
    idEmpresa = idE;
    COMMIT;
END; $
delimiter ;


DROP PROCEDURE IF EXISTS deleteEmpresa;
delimiter $
CREATE PROCEDURE deleteEmpresa(in idE INT)
BEGIN
	UPDATE empresa
	SET estatus='2'
	WHERE idEmpresa=idE;
END;$
delimiter ;

/*Sucursal*/
USE salemx;

DROP PROCEDURE IF EXISTS insertSucursal;
DELIMITER $$
CREATE PROCEDURE insertSucursal(
	IN nombre_ VARCHAR(100),
    IN domicilio_ VARCHAR(100),
    IN latitud_ DOUBLE,
    IN longitud_ DOUBLE,
    IN foto_ LONGTEXT,
    IN idE INT,
    OUT idS INT
)
BEGIN
	INSERT INTO sucursal VALUES (0, nombre_, domicilio_, latitud_,longitud_, foto_, "", 1, idE);
    SET idS = last_insert_id();
	COMMIT;
END $$
delimiter ;


DROP PROCEDURE IF EXISTS updateSucursal;
DELIMITER $$
CREATE PROCEDURE updateSucursal (
	IN idS INT,
    IN nombre_ VARCHAR(100),
    IN domicilio_ VARCHAR(100),
    IN latitud_ DOUBLE,
    IN longitud_ DOUBLE,
    IN foto_ LONGTEXT,
    IN idE INT
)
BEGIN
	UPDATE sucursal
		SET nombre = nombre_,
            domicilio = domicilio_,
            latitud = latitud_,
            longitud = longitud_,
            foto = foto_,
            estatus = 1,
            idEmpresa = idE
		WHERE idSucursal = idS;
	COMMIT;
END $$
delimiter ;


DROP PROCEDURE IF EXISTS deleteSucursal;
DELIMITER $$
CREATE PROCEDURE deleteSucursal(IN idS INT)
BEGIN
	UPDATE sucursal
		SET estatus = 0
    WHERE idSucursal = idS;
	COMMIT;
END $$
delimiter ;


DROP PROCEDURE IF EXISTS activateSucursal;
DELIMITER $$
CREATE PROCEDURE activateSucursal(IN idS INT)
BEGIN
	UPDATE sucursal
		SET estatus = 1
    WHERE idSucursal = idS;
	COMMIT;
END $$
delimiter ;

DROP VIEW IF EXISTS consultaSucursalA;
CREATE VIEW consultaSucursalA AS (SELECT idSucursal,nombre,domicilio,latitud,longitud,foto,idEmpresa FROM sucursal WHERE estatus = 1);
DROP VIEW IF EXISTS consultaSucursalI;
CREATE VIEW consultaSucursalI AS (SELECT idSucursal,nombre,domicilio,latitud,longitud,foto,idEmpresa FROM sucursal WHERE estatus = 0);
SELECT * FROM consultaSucursalI;
SELECT * FROM consultaSucursalA where idEmpresa = 3;

/*Cliente*/

DROP PROCEDURE IF EXISTS insertCliente;
DELIMITER $$
CREATE PROCEDURE insertCliente(
	in nombre_ varchar(64),
    in ape1_ varchar(64),
    in ape2_ varchar(64),
    in genero_ varchar(2),
    in domicilio_ varchar(200),
    in telefono_ varchar(25),
    in nusuario_ varchar(48),
    in correo_ varchar(200),
    in contraseña_ varchar(48),
    in rol_ varchar(24),
    in estatus_ int,
    out idC int
)
begin
    insert into cliente (nombre,apellidoPaterno,apellidoMaterno,genero,domicilio,telefono,nombreUsuario,correo,contrasenia,rol,estatus) values (nombre_,ape1_,ape2_,genero_,domicilio_,telefono_,nusuario_,correo_,contraseña_,rol_,estatus_);
    select @idC := last_insert_id() from cliente;
end $$
DELIMITER ;

DROP PROCEDURE IF EXISTS updateCliente;
DELIMITER $$
CREATE PROCEDURE updateCliente(
	in id int,
	in nombre_ varchar(64),
    in ape1_ varchar(64),
    in ape2_ varchar(64),
    in genero_ varchar(2),
    in domicilio_ varchar(200),
    in telefono_ varchar(25),
    in nusuario_ varchar(48),
    in correo_ varchar(200),
    in contraseña_ varchar(48),
    in rol_ varchar(24),
    in estatus_ int
)
begin
	update cliente set nombre=nombre_,apellidoPaterno=ape1_,apellidoMaterno=ape2_,
    genero=genero_,domicilio=domicilio_,telefono=telefono_,nombreUsuario=nusuario_,
    correo=correo_,contrasenia=contraseña_,rol=rol_,estatus=estatus_ where idCliente = id;
end $$
DELIMITER ;

drop view if exists consulta_Cli_ac;
create view consulta_Cli_ac as (select * from cliente where estatus = 1);

drop view if exists consulta_Cli_in;
create view consulta_Cli_in as (select * from cliente where estatus = 0);

DROP PROCEDURE IF EXISTS insertTokenC;
DELIMITER $$
CREATE PROCEDURE insertTokenC(
	in idC_ INT,
    in token_ LONGTEXT
    )
BEGIN
	START TRANSACTION;
UPDATE cliente 
	SET 
		token = token_	
	WHERE
		idCliente = idC_;
    COMMIT;
END; 
$$

DROP PROCEDURE IF EXISTS deleteTolkenC;
DELIMITER $
CREATE PROCEDURE deleteTolkenC(in idC INT)
BEGIN
	UPDATE cliente
		SET token = NULL OR token = ''
	WHERE idCliente = idC;
END;
$

DROP VIEW IF EXISTS TokenC;
CREATE VIEW TokenC AS (SELECT * FROM cliente WHERE nombreUsuario = "cruzito" AND contrasenia = "cruz123" AND token IS NOT NULL);
SELECT * FROM TokenC;
SELECT token FROM cliente WHERE token = 1;

/*producto*/
-- activos
Create view getAllActivos as select ps.idProductoServicio,ps.nombre,ps.foto,ps.precio,ps.descripcion,ps.estatus,ps.idEmpresa,em.nombre as Empresa
from productoservicio ps inner join empresa em on ps.idEmpresa=em.idEmpresa where em.estatus=1 and ps.estatus=1;
select * from getAllActivos;
-- inactivos
Create view getAllIna as select ps.idProductoServicio,ps.nombre,ps.foto,ps.precio,ps.descripcion,ps.estatus,ps.idEmpresa,em.nombre as Empresa
from productoservicio ps inner join empresa em on ps.idEmpresa=em.idEmpresa where em.estatus=1 and ps.estatus=0;
select * from getAllIna;

-- insertar producto servicio
drop procedure if exists insertProSer;
delimiter %%
create procedure insertProSer(nom varchar(100),fotografia longtext,pre double,descrip varchar(100),idEm int,out idPs int)
begin
INSERT INTO productoservicio (nombre, foto, precio, descripcion, estatus, idEmpresa) 
VALUES(nom,fotografia,pre,descrip,1,idEm);
set idPs=last_insert_id();
end %%
delimiter ;
call insertProSer("m","",35,"m",3,@idPs);

-- update producto servicio
drop procedure if exists updateProSer;
delimiter &&
create procedure updateProSer(in idP int, in nom_ varchar(100), in foto_ longtext, in precio_ double, in des varchar(100), in idE int)
begin
update productoservicio set nombre=nom_,foto=foto_,precio=precio_,descripcion=des,estatus = 1,idEmpresa=idE where idProductoServicio=idP;
end &&
delimiter ;
call updateProSer (37,"m","",35,"m",2);
select * from getAllActivos;

drop procedure if exists deleteProSer;
delimiter &&
create procedure deleteProSer(id int)
begin
update productoservicio set estatus=0 where idProductoServicio=id;
end &&
delimiter ;

drop procedure if exists activateProSer;
delimiter &&
create procedure activateProSer(id int)
begin
update productoservicio set estatus=1 where idProductoServicio=id;
end &&
delimiter ;
call activateProSer(6);
select * from getAllActivos;
select * from getAllIna;

/*Login*/
USE salemx;

DROP VIEW IF EXISTS loginE;
CREATE VIEW loginE AS (SELECT idEmpresa, correo, contrasenia, token from empresa e 
					WHERE nombreUsuario = nombreUsuario 
                    AND contrasenia = contrasenia);

SELECT * FROM loginE WHERE correo = "cruz@gmail.com" AND contrasenia = "cruz";
-- insert into empresa(correo,contrasenia) values("cruz@gmail.com","cruz");
select * from empresa;
DROP VIEW IF EXISTS loginC;
CREATE VIEW loginC AS (SELECT idCliente, nombreUsuario, contrasenia, token from cliente c 
					WHERE nombreUsuario = nombreUsuario 
                    AND contrasenia = contrasenia);

SELECT * FROM loginC WHERE nombreUsuario = "cruzito" AND contrasenia = "cruz123";
