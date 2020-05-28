USE salemx;

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