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