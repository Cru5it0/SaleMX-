use salemx;

drop view if exists promo2;

create view promo2 as select ps.idProductoServicio,ps.nombre,e.idEmpresa from productoservicio as ps inner join empresa e on ps.idEmpresa=e.idEmpresa;
select * from promo2 where idEmpresa=1;

drop view if exists promosucursal;

create view promosucursal as select s.idSucursal,s.nombre,e.idEmpresa from sucursal s inner join empresa e on s.idEmpresa=e.idEmpresa;
select * from promosucursal where idEmpresa=4;

drop view if exists consultaPromocionAc;
CREATE VIEW consultaPromocionAc AS (
SELECT p.idPromocion,p.titulo,p.fechaInicio,p.fechaTermino,p.imagen,p.descripcion,p.precio,p.descuento,p.idSucursal,s.nombre as Sucursal,p.idProductoServicio, ps.nombre as ProductoServicio,e.idEmpresa
FROM promocion p inner join Sucursal s on p.idSucursal=s.idSucursal inner join productoservicio ps on p.idProductoServicio=ps.idProductoServicio inner join Empresa e on e.idEmpresa=s.idEmpresa 
WHERE p.estatus = 1);
select * from consultaPromocionAc where idEmpresa=3;

drop view if exists consultaPromocionIna;
CREATE VIEW consultaPromocionIna AS (
SELECT p.idPromocion,p.titulo,p.fechaInicio,p.fechaTermino,p.imagen,p.descripcion,p.precio,p.descuento,p.idSucursal,s.nombre as Sucursal,p.idProductoServicio, ps.nombre as ProductoServicio,e.idEmpresa
FROM promocion p inner join Sucursal s on p.idSucursal=s.idSucursal inner join productoservicio ps on p.idProductoServicio=ps.idProductoServicio inner join Empresa e on e.idEmpresa=s.idEmpresa 
WHERE p.estatus = 0);
select * from consultaPromocionIna where idEmpresa=4;

DROP VIEW IF EXISTS vclienteP;
CREATE VIEW vclienteP AS ( SELECT p.idPromocion,p.titulo,p.fechaInicio,p.fechaTermino,p.imagen,p.descripcion,p.precio,p.descuento,p.idSucursal,s.nombre as Sucursal,p.idProductoServicio, ps.nombre as ProductoServicio,e.idEmpresa
		FROM promocion p inner join Sucursal s on p.idSucursal=s.idSucursal inner join productoservicio ps on p.idProductoServicio=ps.idProductoServicio inner join Empresa e on e.idEmpresa=s.idEmpresa 
		WHERE p.estatus = 1);
SELECT * FROM vclienteP where idPromocion = 1;
