USE salemx;

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
CALL deleteTolkenC (1);
DROP VIEW IF EXISTS TokenC;
CREATE VIEW TokenC AS (SELECT * FROM cliente WHERE nombreUsuario = "cruzito" AND contrasenia = "cruz123" AND token IS NOT NULL);
SELECT * FROM TokenC;
SELECT token FROM cliente WHERE idCliente = 1;