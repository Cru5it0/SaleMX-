USE salemx;
/*Empresa*/
DROP VIEW IF EXISTS consultaEmpresaA;
CREATE VIEW consultaEmpresaA AS (SELECT idEmpresa,nombre,contacto,domicilio,logo,correo,nombreUsuario,contrasenia FROM empresa WHERE estatus = 1);
SELECT * FROM consultaEmpresaA;
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