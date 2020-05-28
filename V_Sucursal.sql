USE salemx;

DROP VIEW IF EXISTS consultaSucursalA;
CREATE VIEW consultaSucursalA AS (SELECT idSucursal,nombre,domicilio,latitud,longitud,foto,idEmpresa FROM sucursal WHERE estatus = 1);

DROP VIEW IF EXISTS consultaSucursalI;
CREATE VIEW consultaSucursalI AS (SELECT idSucursal,nombre,domicilio,latitud,longitud,foto,idEmpresa FROM sucursal WHERE estatus = 0);

DROP VIEW IF EXISTS consultaSucursalCliente;
CREATE VIEW consultaSucursalCliente AS (SELECT idSucursal,nombre,domicilio,latitud,longitud,foto,idEmpresa FROM sucursal WHERE estatus = 1);

SELECT * FROM consultaSucursalI;
SELECT * FROM consultaSucursalA where idEmpresa = 2;
SELECT * FROM consultaSucursalCliente;