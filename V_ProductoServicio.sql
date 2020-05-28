USE salemx;

Create view getAllActivos as select ps.idProductoServicio,ps.nombre,ps.foto,ps.precio,ps.descripcion,ps.estatus,ps.idEmpresa,em.nombre as Empresa
from productoservicio ps inner join empresa em on ps.idEmpresa=em.idEmpresa where em.estatus=1 and ps.estatus=1;
select * from getAllActivos;
-- inactivos
Create view getAllIna as select ps.idProductoServicio,ps.nombre,ps.foto,ps.precio,ps.descripcion,ps.estatus,ps.idEmpresa,em.nombre as Empresa
from productoservicio ps inner join empresa em on ps.idEmpresa=em.idEmpresa where em.estatus=1 and ps.estatus=0;
select * from getAllIna;

DROP VIEW IF EXISTS vclientePS;
CREATE VIEW vclientePS AS (select ps.idProductoServicio,ps.nombre,ps.foto,ps.precio,ps.descripcion,ps.estatus,ps.idEmpresa,em.nombre as Empresa
from productoservicio ps inner join empresa em on ps.idEmpresa=em.idEmpresa where em.estatus=1 and ps.estatus=1);
SELECT * FROM vclientePS;
