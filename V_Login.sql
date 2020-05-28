USE salemx;

DROP VIEW IF EXISTS loginE;
CREATE VIEW loginE AS (SELECT idEmpresa, correo, contrasenia, token from empresa e 
					WHERE correo = correo 
                    AND contrasenia = contrasenia);

SELECT * FROM loginE WHERE correo = "burguerking@gmail.com" AND contrasenia = "u2123";
-- insert into empresa(correo,contrasenia) values("cruz@gmail.com","cruz");

DROP VIEW IF EXISTS loginC;
CREATE VIEW loginC AS (SELECT idCliente, correo, contrasenia, token from cliente c 
					WHERE correo = correo 
                    AND contrasenia = contrasenia);

SELECT * FROM loginC WHERE correo = "cruzito@gmail.com" AND contrasenia = "cruz123";
-- insert into cliente(correo,contrasenia) values("cruzito@gmail.com","cruz123");
