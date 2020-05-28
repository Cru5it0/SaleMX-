USE salemx;

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
call deleteProSer(23);
drop procedure if exists activateProSer;
delimiter &&
create procedure activateProSer(id int)
begin
update productoservicio set estatus=1 where idProductoServicio=id;
end &&
delimiter ;
call activateProSer(6);
