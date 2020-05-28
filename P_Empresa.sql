USE salemx;

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
