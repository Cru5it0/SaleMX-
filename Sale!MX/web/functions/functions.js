/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function cargarFoto() {
    var fileChooser = document.getElementById("txtFoto");
    var foto = document.getElementById("imgFoto");
    var base64 = document.getElementById("txtBase64");
    if (fileChooser.files.length > 0) {
        var fr = new FileReader();
        fr.onload = function () {
            foto.src = fr.result;
            base64.value = foto.src.replace
                    (/^data:image\/(png|jpg|jpeg|gif);base64,/, '');
        }
        fr.readAsDataURL(fileChooser.files[0]);
    }
}

function cargarFoto1() {
    var fileChooser = document.getElementById("txtFoto2");
    var foto = document.getElementById("imgFoto2");
    var base64 = document.getElementById("txtBase642");
    if (fileChooser.files.length > 0) {
        var fr = new FileReader();
        fr.onload = function () {
            foto.src = fr.result;
            base64.value = foto.src.replace
                    (/^data:image\/(png|jpg|jpeg|gif);base64,/, '');
        }
        fr.readAsDataURL(fileChooser.files[0]);
    }
}

/*------------------------------ Sucursal --------------------------------------*/
var sucursales;
var sucursalActual;

function cargarModuloSucursales()
{
    $.ajax(
            {
                type: "POST",
                url: "sucursal/catalogo.html",
                async: true
            }

    ).done(
            function (data)
            {
                $('#divMainContainer').html(data);
                $('#txtModulo').html("Módulo Sucursales Activas");
                getAllSucursales();
            }

    );
}

function getAllSucursales()
{
    var t = localStorage.getItem("token");
    var iE=localStorage.getItem("idEmpresa");
    var data = {token: t, idEmpresa: iE};
    $.ajax(
            {
                type: 'GET',
                async: true,
                data: data,
                url: 'api/sucursal/getAll'
            }
    ).done(function (data)
    {
        if ($.isEmptyObject(data.error)) {
            sucursales = data;
            var str = '';
            console.log(data);
            for (var i = 0; i < sucursales.length; i++) {
                str += '<tr>' +
                        '<td>' + sucursales[i].nombre + '</td>' +
                        '<td>' + sucursales[i].domicilio + '</td>' +
                        '<td>' + sucursales[i].latitud + '</td>' +
                        '<td>' + sucursales[i].longitud + '</td>' +
                        '<td><img id="imgFoto" src="data:;base64,' + sucursales[i].foto + '" width="50" height="75">' +
                        '<input type="file" hidden="true" name="txtFoto" id="txtFoto" class="form-control text-black" onchange="cargarFoto();">' +
                        '<textarea hidden="true" class="form-control text-black" name="txtBase64" id="txtBase64"></textarea>' +
                        '</td>' +
                        '<td><button onclick="mostrarDetalleSucursal(' + i + ');"' +
                        ' class="btn btn-info btn-rounded" data-toggle="modal" data-target="#modalPoll-1"><i class="fas fa-info-circle"></i></button>' + '</td>' +
                        '</td>' +
                        '</td>' +
                        '<td><button onclick="eliminarSucursal(' + sucursales[i].idSucursal + ');"' +
                        ' class="btn btn-danger btn-reliminarSucursalounded"><i class="fas fa-info-circle"></i></button>' + '</td>' +
                        '</td>' +
                        '</tr>';
            }
            $('#tblSucursal').html(str);
            buildTableData();
        } else {
            logout();
        }
    });
}

function buildTableData() {

    $(document).ready(function () {
        $('#dtBasicExample').DataTable();
        $('.dataTables_length').addClass('bs-select');
    });

}

function insertSucursales() {

    var nombre = $('#txtNombre').val();
    var domicilio = $('#txtDomicilio').val();
    var lat = $('#txtLatitud').val();
    var log = $('#txtLongitud').val();
    var foto = $('#txtBase642').val();
    var idE = localStorage.getItem("idEmpresa");
    var t = localStorage.getItem("token");
    //alert(t);

    var data = {
        nom: nombre,
        dom: domicilio,
        lat: lat,
        lng: log,
        foto: foto,
        idE: idE,
        token: t
    };
    console.log(data);
    $.ajax(
            {
                type: 'POST',
                async: true,
                url: 'api/sucursal/insert',
                data: data
            }
    ).fail(function (data) {
        Swal.fire('Ha ocurrido un error', data.error, 'error');
    })
            .done(
                    function (data) {
                        if ($.isEmptyObject(data.error)) {
                            Swal.fire('Movimiento realizado', 'Sucursal Guardada', 'success');
                            getAllSucursales();
                            limpiarDetalleS();
                        } else {
                            if (data.error == "null")
                            {
                                logout();
                            } else
                            {
                                Swal.fire('Ha ocurrido un error', data.error, 'error');
                            }
                        }
                    }
            );
}

function mostrarDetalleSucursal(posicion)
{
    if (posicion >= 0 && sucursales != null && sucursales [posicion] != null)
    {
        sucursalActual = sucursales[posicion];
        $('#txtNombre').val(sucursales[posicion].nombre);
        $('#lable1').addClass('active');
        $('#txtDomicilio').val(sucursales[posicion].domicilio);
        $('#lable2').addClass('active');
        $('#txtLatitud').val(sucursales[posicion].latitud);
        $('#lable3').addClass('active');
        $('#txtLongitud').val(sucursales[posicion].longitud);
        $('#lable4').addClass('active');

        $('#txtIdSucursal').val(sucursales[posicion].idSucursal);
        $('#txtIdEmpresa').val(sucursales[posicion].idEmpresa);

        document.getElementById("imgFoto2").src = "data:;base64," + sucursalActual.foto;
        $('#txtBase642').val(sucursalActual.foto);

    } else
    {
        limpiarDetalleS();
    }
}

function decidirS() {
    if (sucursalActual === null || sucursales === null) {
        insertSucursales();
    } else {
        modificarSucursal();
    }
    getAllSucursales();
}

function modificarSucursal() {
    var idS = $('#txtIdSucursal').val();
    var nombre = $('#txtNombre').val();
    var domicilio = $('#txtDomicilio').val();
    var lat = $('#txtLatitud').val();
    var log = $('#txtLongitud').val();
    var foto = $('#txtBase642').val();
    var idE = localStorage.getItem("idEmpresa");
    var t = localStorage.getItem("token");

    var data = {
        idS: idS,
        nom: nombre,
        dom: domicilio,
        lat: lat,
        lng: log,
        foto: foto,
        idE: idE,
        token: t
    };
    $.ajax(
            {
                type: "POST",
                url: "api/sucursal/update",
                data: data,
                async: true

            }
    ).fail(function (data) {
        Swal.fire('Ha ocurrido un error', data.error, 'error');
    })
            .done(
                    function (data) {
                        if ($.isEmptyObject(data.error)) {
                            Swal.fire('Movimiento realizado', 'Sucursal Modificada', 'success');
                            getAllSucursales();
                            limpiarDetalleS();
                        } else {
                            if (data.error == "null")
                            {
                                logout();
                            } else
                            {
                                Swal.fire('Ha ocurrido un error', data.error, 'error');
                            }
                        }
                    }
            );

}

function eliminarSucursal(idS) {
    var t = localStorage.getItem("token");
    var datos = {
        id: idS,
        token: t
    };

    $.ajax(
            {
                type: 'POST',
                async: true,
                url: 'api/sucursal/delete',
                data: datos
            }
    ).done(
            function (data) {
                /*
                 toastr.success('Se elimino correctamente!', '', {positionClass: 'md-toast-bottom-left', progressBar: true, closeButton: true});
                 $('#toast-container').attr('class', 'md-toast-bottom-left');*/
                if ($.isEmptyObject(data.error)) {
                    Swal.fire('Movimiento realizado', 'Sucursal Eliminada', 'success');
                    getAllSucursales();
                    limpiarDetalleS();
                } else {
                    if (data.error == "null")
                    {
                        logout();
                    } else
                    {
                        Swal.fire('Ha ocurrido un error', data.error, 'error');
                    }
                }
            }
    );
}

function cargarModuloSucursalesInactivos()
{
    $.ajax(
            {
                type: "POST",
                url: "sucursal/catalogo.html",
                async: true
            }

    ).done(
            function (data)
            {
                $('#divMainContainer').html(data);
                $('#txtModulo').html("Módulo Sucursales Activas");
                getAllSucursalesInactivas();
            }

    );
}

function getAllSucursalesInactivas()
{
    var t = localStorage.getItem("token");
    var data = {token: t};
    $.ajax(
            {
                type: 'GET',
                async: true,
                data: data,
                url: 'api/sucursal/getInac'
            }
    ).done(function (data)
    {
        if ($.isEmptyObject(data.error)) {
            sucursales = data;
            var str = '';
            console.log(data);
            for (var i = 0; i < sucursales.length; i++) {
                str += '<tr>' +
                        '<td>' + sucursales[i].nombre + '</td>' +
                        '<td>' + sucursales[i].domicilio + '</td>' +
                        '<td>' + sucursales[i].latitud + '</td>' +
                        '<td>' + sucursales[i].longitud + '</td>' +
                        '<td><img id="imgFoto2" src="data:;base64,' + sucursales[i].foto + '" width="50" height="75">' +
                        '<input type="file" hidden="true" name="txtFoto2" id="txtFoto2" class="form-control text-black" onchange="cargarFoto1();">' +
                        '<textarea hidden="true" class="form-control text-black" name="txtBase642" id="txtBase642"></textarea>' +
                        '</td>' +
                        '<td><button onclick="activarSucursal(' + sucursales[i].idSucursal + ');"' +
                        ' class="btn btn-outline-success btn-reliminarSucursalounded"><i class="fas fa-info-circle"></i></button>' + '</td>' +
                        '</td>' +
                        '</tr>';
            }
            $('#tblSucursal').html(str);
            buildTableData();
        } else
        {
            logout();
        }

    });
}

function activarSucursal(idS) {
    var t = localStorage.getItem("token");
    var datos = {
        id: idS,
        token: t
    };

    $.ajax(
            {
                type: 'POST',
                async: true,
                url: 'api/sucursal/activar',
                data: datos
            }
    ).done(
            function (data) {
                console.log(data);
                if ($.isEmptyObject(data.error)) {
                    Swal.fire('Movimiento realizado', 'Sucursal Activada', 'success');
                    getAllSucursalesInactivas();
                    limpiarDetalleS();
                } else {
                    if (data.error == "null")
                    {
                        logout();
                    } else
                    {
                        Swal.fire('Ha ocurrido un error', data.error, 'error');
                    }
                }
            }
    );
}

function buscarTablaSucursales()
{
    var s = $("#txtSearch").val();
    var t = localStorage.getItem("token");

    //alert(s);
    var data = {
        s: s,
        token: t
    };
    $.ajax(
            {
                type: "GET",
                url: "api/sucursal/search",
                async: true,
                data: data
            }).done(
            function (data) {
                if ($.isEmptyObject(data.error)) {
                    sucursales = data;
                    var str = '';
                    for (var i = 0; i < sucursales.length; i++) {
                        str += '<tr>' +
                                '<td>' + sucursales[i].nombre + '</td>' +
                                '<td>' + sucursales[i].domicilio + '</td>' +
                                '<td>' + sucursales[i].latitud + '</td>' +
                                '<td>' + sucursales[i].longitud + '</td>' +
                                '<td><img id="imgFoto" src="data:;base64,' + sucursales[i].foto + '" width="50" height="75">' +
                                '<input type="file" hidden="true" name="txtFoto" id="txtFoto" class="form-control text-black" onchange="cargarFoto();">' +
                                '<textarea hidden="true" class="form-control text-black" name="txtBase64" id="txtBase64"></textarea>' +
                                '</td>' +
                                '<td><button onclick="mostrarDetalleSucursal(' + i + ');"' +
                                ' class="btn btn-info btn-rounded" data-toggle="modal" data-target="#modalPoll-1"><i class="fas fa-info-circle"></i></button>' + '</td>' +
                                '</td>' +
                                '</td>' +
                                '<td><button onclick="eliminarSucursal(' + sucursales[i].idSucursal + ');"' +
                                ' class="btn btn-danger btn-reliminarSucursalounded"><i class="fas fa-info-circle"></i></button>' + '</td>' +
                                '</td>' +
                                '</tr>';
                    }
                    $('#tblSucursal').html(str);
                } else {
                    logout();
                }

            }
    );

}

function limpiarDetalleS()
{
    $('#txtNombre').val("");
    $('#txtDomicilio').val("");
    $('#txtLatitud').val("");
    $('#txtLongitud').val("");
    document.getElementById("imgFoto2").src = "";
    $('#txtBase642').val("");
    $('#txtFoto2').val("");
    $('#txtIdEmpresa').val("");
    $('#txtIdSucursal').val("");
    sucursalActual = null;
}

/*-------------------------------Producto y Servicio --------------------------------*/
var proser;
var proSerActual;

function cargarModuloProductoServicio()
{
    $.ajax(
            {
                type: "POST",
                url: "producto/catalogo.html",
                async: true
            }

    ).done(
            function (data)
            {
                $('#divMainContainer').html(data);
                $('#txtModulo').html("Módulo Sucursales Activas");
                getAllProductoServicio();
            }

    );
}

function getAllProductoServicio()
{
    var t = localStorage.getItem("token");
    var idE = localStorage.getItem("idEmpresa");
    var data = {idEmpresa:idE};
    $.ajax(
            {
                type: 'GET',
                async: true,
                data: data,
                url: 'api/productoservicio/getAllps'
            }
    ).done(function (data)
    {
        if ($.isEmptyObject(data.error)) {
            proser = data;
            var str = '';
            console.log(proser);
            for (var i = 0; i < proser.length; i++) {
                str += '<tr>' +
                        '<td>' + proser[i].nombre + '</td>' +
                        '<td><img id="imgFotoP" src="data:;base64,' + proser[i].foto + '" width="50" height="75">' +
                        '<input type="file" hidden="true" name="txtFotoP" id="txtFotoP" class="form-control text-black" onchange="cargarFotoP();">' +
                        '<textarea hidden="true" class="form-control text-black" name="txtBase64P" id="txtBase64P"></textarea>' +
                        '<td>' + proser[i].precio + '</td>' +
                        '<td>' + proser[i].descripcion + '</td>' +
                        '<td>' + proser[i].empresa.nombre + '</td>' +
                        '<td><button onclick="mostrarDetalleProductoServicio(' + i + ');"' +
                        ' class="btn btn-info btn-rounded" data-toggle="modal" data-target="#modalPoll-1"><i class="fas fa-info-circle"></i></button>' + '</td>' +
                        '</td>' +
                        '</td>' +
                        '<td><button onclick="eliminarProductoSer(' + proser[i].idProductoServicio + ');"' +
                        ' class="btn btn-danger btn-rounded"><i class="fas fa-minus-circle"></i></button>' + '</td>' +
                        '</td>' +
                        '</tr>';
            }
            $('#tblProductoServicio').html(str);
            buildTableData();
        } else {
            logout();
        }
    });
}

function getAllProductoServicioInc()
{
    $('#eliminar').hide();
    $('#btnAgregar').hide();
    var t = localStorage.getItem("token");
    var idE = localStorage.getItem("idEmpresa");
    var data = {token: t, idEmpresa:idE};
    $.ajax(
            {
                type: 'GET',
                async: true,
                data: data,
                url: 'api/productoservicio/getAllInactivops'
            }
    ).done(function (data)
    {
        if ($.isEmptyObject(data.error)) {
            proser = data;
            var str = '';
            console.log(proser);
            for (var i = 0; i < proser.length; i++) {
                str += '<tr>' +
                        '<td>' + proser[i].nombre + '</td>' +
                        '<td><img id="imgFotoP" src="data:;base64,' + proser[i].foto + '" width="50" height="75">' +
                        '<input type="file" hidden="true" name="txtFotoP" id="txtFotoP" class="form-control text-black" onchange="cargarFotoP();">' +
                        '<textarea hidden="true" class="form-control text-black" name="txtBase64P" id="txtBase64P"></textarea>' +
                        '<td>' + proser[i].precio + '</td>' +
                        '<td>' + proser[i].descripcion + '</td>' +
                        '<td>' + proser[i].empresa.nombre + '</td>' +
                        '<td><button onclick="activateProSer(' + proser[i].idProductoServicio + ');"' +
                        ' class="btn btn-outline-success btn-reliminarSucursalounded"><i class="fas fa-info-circle"></i></button>' + '</td>' +
                        '</td>' +
                        '</tr>';
            }
            $('#tblProductoServicio').html(str);
            buildTableData();
        } else {
            logout();
        }
    });
}

function getAllProductoServicioCliente()
{
    var t = localStorage.getItem("token");
    var data = {token: t};
    $.ajax(
            {
                type: 'GET',
                async: true,
                data: data,
                url: 'api/productoservicio/getAllps'
            }
    ).done(function (data)
    {
        if ($.isEmptyObject(data.error)) {
            proser = data;
            var str = '';
            console.log(proser);
            for (var i = 0; i < proser.length; i++) {
                str += '<div class="card">' +
                            '<div class="view overlay">' +
                                '<img class="card-img-top" id="imgFoto2" src="data:image/png;base64,' + proser[i].foto + '" alt="' + proser[i].nombre + '" onchange="cargarFoto()">' +
                                '<input type="file" hidden="true" name="txtFoto2" id="txtFoto2" class="form-control text-black" onchange="cargarFoto()">' +
                                '<a>' +
                                    '<div class="mask rgba-white-slight"></div>' +
                                '</a>' +
                            '</div>' +
                            '<div class="card-body">' +
                                '<h4 class="card-title">' + proser[i].nombre + '</h4>' +
                                '<hr>' +
                                '<p class="card-text">' + proser[i].descripcion + '</p>' +
                            '</div>' +
                            '<div class="rounded-bottom mdb-color lighten-3 text-center pt-3">' +
                                '<ul class="list-unstyled list-inline font-small">' +
                                    '<li class="list-inline-item pr-2 white-text"><i class="far fa-clock pr-1"></i>' + proser[i].precio + '</li>' +
                                    '<li class="list-inline-item"><a href="#" class="white-text"><i class="fab fa-twitter pr-1"> </i>' + proser[i].empresa.nombre + '</a></li>' +
                                '</ul>' +
                            '</div>' +
                        '</div>';

            }
            $('#PSCliente').html(str);
            buildTableData();
        } else {
            logout();
        }
    });
}

function insertProductoServicio() {

    var nombre = $('#txtNombre').val();
    var foto = $('#txtBase64P2').val();
    var precio = $('#txtPrecio').val();
    var descripcion = $('#txtDescripcion').val();
    var idEm = localStorage.getItem("idEmpresa");
    var t = localStorage.getItem("token");

    var data = {
        nombre: nombre,
        foto: foto,
        precio: precio,
        descripcion: descripcion,
        idEmpresa: idEm,
        token: t
    };
    //alert(data);
    console.log(data);
    $.ajax(
            {
                type: 'POST',
                async: true,
                url: 'api/productoservicio/insertarps',
                data: data
            }
    ).fail(function (data) {
        Swal.fire('Ha ocurrido un error', data.error, 'error');
        console.log(data);
    })
            .done(
                    function (data) {
                        //console.log(data);
                        if ($.isEmptyObject(data.error)) {
                            Swal.fire('Movimiento realizado', 'Producto o servicio Guardado', 'success');
                            getAllProductoServicio()
                            limpiarDetallePS();
                        } else {
                            if (data.error == "null")
                            {
                                logout();
                            } else
                            {
                                Swal.fire('Ha ocurrido un error', data.error, 'error');
                            }
                        }
                    }
            );
}

function mostrarDetalleProductoServicio(posicion)
{
    if (posicion >= 0 && proser != null && proser [posicion] != null)
    {
         proSerActual = proser[posicion];
        $('#txtIdPS').val(proser[posicion].idProductoServicio);
        $('#txtNombre').val(proser[posicion].nombre);
        $('#lable1').addClass('active');
        $('#txtDescripcion').val(proser[posicion].descripcion);
        $('#lable2').addClass('active');
        $('#txtPrecio').val(proser[posicion].precio);
        $('#lable3').addClass('active');
        document.getElementById("imgFotoP2").src = "data:;base64," + proSerActual.foto;
        $('#txtBase64P2').val(proSerActual.foto);

    } else
    {
        limpiarDetallePS();
    }
}

function decidirPS(id) {
    if (proSerActual != null) {
        modificarProductoServicio(id);
    } else {
        insertProductoServicio();
    }
    getAllProductoServicio();
}

function modificarProductoServicio() {        
    var idPS = $('#txtIdPS').val();
    //alert(idPS);
    var nom = $('#txtNombre').val();
    var pre = $('#txtPrecio').val();
    var des = $('#txtDescripcion').val();
    var fot = $('#txtBase64P2').val();
    var idE = localStorage.getItem("idEmpresa");    
    var t = localStorage.getItem("token");

    var data = {
        idProductoServicio: idPS,
        nombre: nom,
        foto:fot,
        precio:pre,
        descripcion: des,
        idEmpresa: idE
        //,        token: t
    };
    console.log(data);
    $.ajax(
            {
                type: "POST",
                url: "api/productoservicio/updateps",
                data: data,
                async: true

            }
    ).fail(function (data) {
        alert(data);
        console.log(data);
        Swal.fire('Ha ocurrido un error', data.error, 'error');
    })
            .done(
                    function (data) {
                        if ($.isEmptyObject(data.error)) {
                            Swal.fire('Movimiento realizado', 'Producto o Servicio Modificado', 'success');
                            getAllProductoServicio();
                            limpiarDetallePS();
                        } else {
                            if (data.error == "null")
                            {
                                logout();
                            } else
                            {
                                Swal.fire('Ha ocurrido un error', data.error, 'error');
                            }
                        }
                    }
            );

}

function eliminarProductoSer(idPS)
{
    var idEm = localStorage.getItem("idEmpresa");
    var datos = {
        id: idPS,
        idEmpresa: idEm
    };

    $.ajax(
            {
                type: 'POST',
                async: true,
                url: 'api/productoservicio/deleteps',
                data: datos
            }
    ).done(
            function (data) {
                /*
                 toastr.success('Se elimino correctamente!', '', {positionClass: 'md-toast-bottom-left', progressBar: true, closeButton: true});
                 $('#toast-container').attr('class', 'md-toast-bottom-left');*/
                if ($.isEmptyObject(data.error)) {
                    Swal.fire('Movimiento realizado', 'Producto  o Servicio Eliminado', 'success');
                    getAllProductoServicio();
                    limpiarDetallePS();
                } else {
                    if (data.error == "null")
                    {
                        logout();
                    } else
                    {
                        Swal.fire('Ha ocurrido un error', data.error, 'error');
                    }
                }
            }
    );
}
function activateProSer(idProd) {
    var t = localStorage.getItem("token");
    //alert(idProd);
    var datos = {
        id: idProd
    };
    console.log(datos);
    $.ajax(
            {
                type: 'POST',
                async: true,
                url: 'api/productoservicio/activatePS',
                data: datos
            }
    )
    .fail(function (data){console.log(data)})
    .done(
            function (data) {
                console.log(data);
                /*
                 toastr.success('Se elimino correctamente!', '', {positionClass: 'md-toast-bottom-left', progressBar: true, closeButton: true});
                 $('#toast-container').attr('class', 'md-toast-bottom-left');*/
                if ($.isEmptyObject(data.error)) {
                    Swal.fire('Movimiento realizado', 'Producto  o Servicio Activado', 'success');
                    getAllProductoServicioInc();
                    limpiarDetallePS();
                } else {
                    if (data.error == "null")
                    {
                        logout();
                    } else
                    {
                        Swal.fire('Ha ocurrido un error', data.error, 'error');
                    }
                }
            }
    );
}

function limpiarDetallePS()
{
    $('#txtNombre').val("");
    $('#txtPrecio').val("");
    $('#txtDescripcion').val("");
    document.getElementById("imgFotoP2").src = "";
    $('#txtBase64P2').val("");
    $('#txtFotoP2').val("");
    $('#txtIdEmpresa').val("");
    $('#txtIdPS').val("");
    proSerActual = null;
}

/*------------------------------- Cleinte --------------------------------*/

function agregarCliente() {
    localStorage.clear();
    var nom = $('#txtNombre').val();
    var apa = $('#txtAPa').val();
    var ama = $('#txtAMa').val();
    var dom = $('#txtDomicilio').val();
    var tel = $('#txtTelefono').val();
    var gen = $('input:radio[name=group1]:checked').val();
    var nomUs = $('#txtUsuario').val();
    var contra = $('#txtContra').val();
    var mail = $('#txtCorreo').val();
    var data = {
        nom: nom,
        apepa: apa,
        apema: ama,
        gen: gen,
        dom: dom,
        tel: tel,
        usuario: nomUs,
        contra: contra,
        correo: mail
    };
    console.log(data);
//    localStorage.setItem("nombre", nom);
//    localStorage.setItem("ape1", apa);
//    localStorage.setItem("ape2", ama);
//    localStorage.setItem("dom", dom);
//    localStorage.setItem("tel", tel);
//    localStorage.setItem("gen", gen);
//    localStorage.setItem("usu", nomUs);
//    localStorage.setItem("pass", contra);
//    localStorage.setItem("mail", mail);
    $.ajax({
        type: 'POST',
        url: "api/cliente/insert",
        data: data,
        async: true
    }).done(
            function (data) {
                /*toastr.success('Se guardo correctamente!', '', {positionClass: 'md-toast-bottom-left', progressBar: true, closeButton: true});
                 $('#toast-container').attr('class', 'md-toast-bottom-left');*/
                if (data.error != null) {
                    Swal.fire('Ha ocurrido un error', data.error, 'error');
                    return;
                }
                if (data.error != null) {
                    Swal.fire('Ha ocurrido un excepción', data.excepción, 'error');
                    return;
                }

                Swal.fire('Realizado', 'Registro Exitoso', 'success');
                        //actualizarTablaClienteActivo();
                        loginC();
            }
    );
}

/*<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Empresa>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
var empresas;
var empresaActual;

function getAllEmpresas()
{
    $('#datos').show();
    var t = localStorage.getItem("token");
    var data = {token: t};
    $.ajax(
            {
                type: 'GET',
                async: true,
                data: data,
                url: 'api/empresa/getAll'
            }
    ).done(function (data)
    {
        if ($.isEmptyObject(data.error)) {
            alert(t);
            empresas = data;
            var str = '';
            console.log(data);
            for (var i = 0; i < empresas.length; i++) {
                str += '<tr>' +
                        '<td><img id="imgFoto" src="data:;base64,' + empresas[i].logo + '" width="50" height="75">' +
                        '<input type="file" hidden="true" name="txtFoto" id="txtFoto" class="form-control text-black" onchange="cargarFoto();">' +
                        '<textarea hidden="true" class="form-control text-black" name="txtBase64" id="txtBase64"></textarea>' +
                        '</td>' +
                        '<td>' + empresas[i].nombre + '</td>' +
                        '<td>' + empresas[i].contacto + '</td>' +
                        '<td>' + empresas[i].domicilio + '</td>' +
                        '<td>' + empresas[i].correo + '</td>' +
                        '<td>' + empresas[i].nomUsuario + '</td>' +
                        '<td>' + empresas[i].contrasenia + '</td>' +
                        '<td><button onclick="mostrarDetalleEmpresa(' + i + ');"' +
                        ' class="btn btn-info btn-rounded" data-toggle="modal" data-target="#modalPoll-1"><i class="fas fa-info-circle"></i></button>' + '</td>' +
                        '</td>' +
                        '</td>' +
                        '</td>' +
                        '</tr>';
            }
            $('#tblEmpresa').html(str);
            buildTableData();
        } else {
            logout();
        }
    });
}

function guardarEmpresa()
{
    localStorage.clear();
    var nom = $("#txtEmpresaNombre").val();
    var con = $("#txtEmpresaContacto").val();
    var dom = $("#txtEmpresaDomicilio").val();
    var cor = $("#txtEmpresaCorreo").val();
    var use = $("#txtEmpresaUsuario").val();
    var pas = $("#txtEmpresaContraseña").val();
    var fot = $("#txtBase642").val();
    var foto = "";
    var data = {
        nom: nom,
        con: con,
        dom: dom,
        cor: cor,
        use: use,
        pas: pas,
        fot: fot,
        foto: foto
    };
//    localStorage.setItem("nom", nom);
//    localStorage.setItem("con", con);
//    localStorage.setItem("dom", dom);
//    localStorage.setItem("cor", cor);
//    localStorage.setItem("use", use);
//    localStorage.setItem("pas", pas);
//    localStorage.setItem("fot", fot);
//    localStorage.setItem("foto", foto);
    $.ajax(
            {
                type: "POST",
                async: true,
                url: "api/empresa/insert",
                data: data
            }
    ).fail(function (data) {
        Swal.fire('Ha ocurrido un error', data.error, 'error');
        getAllEmpresas();
    })
            .done(
            function (data) {
                if (data.error != null) {
                    Swal.fire('Ha ocurrido un error', data.error, 'error');
                    return;
                }
                if (data.error != null) {
                    Swal.fire('Ha ocurrido un excepción', data.excepción, 'error');
                    return;
                }

                Swal.fire('Realizado', 'Registro Exitoso', 'success');
            }
    );
   location.href = "indexEmpresa.html"; 
}

function modificarEmpresa()
{
    var nom = $("#txtEmpresaNombre").val();
    var con = $("#txtEmpresaContacto").val();
    var dom = $("#txtEmpresaDomicilio").val();
    var cor = $("#txtEmpresaCorreo").val();
    var use = $("#txtEmpresaUsuario").val();
    var pas = $("#txtEmpresaContraseña").val();
    var fot = $("#txtBase642").val();
    var foto = $("#txtBase643").val();
    var id = $("#txtEmpresaId").val();
    var t = localStorage.getItem("token");
    var data = {
        id: id,
        nom: nom,
        con: con,
        dom: dom,
        cor: cor,
        use: use,
        pas: pas,
        fot: fot,
        foto: foto,
        token: t
    };
    $.ajax(
            {
                type: "POST",
                url: "api/empresa/update",
                data: data,
                async: true

            }
    ).fail(function (data) {
        Swal.fire('Ha ocurrido un error', data.error, 'error');
        getAllEmpresas();
    })
            .done(
                    function (data) {
                        if ($.isEmptyObject(data.error)) {
                            Swal.fire('Movimiento realizado', 'Sucursal Modificada', 'success');
                            getAllEmpresas();
                        } else {
                            if (data.error == "null")
                            {
                                Swal.fire('La sesión expiro', data.error, 'error');
                                logout();
                            } else
                            {
                                Swal.fire('Ha ocurrido un error', data.error, 'error');

                            }
                        }
                    }
            );

}

function eliminarEmpresa()
{
    var id = $("#txtEmpresaId").val();
    var t = localStorage.getItem("token");
    var data = {
        id: id,
        token: t
    };
    $.ajax(
            {
                type: 'POST',
                async: true,
                url: 'api/empresa/delete',
                data: data
            }
    ).done(
            function (data) {
                if ($.isEmptyObject(data.error))
                {
                    Swal.fire('Movimiento realizado', 'Empresa Eliminada', 'success');
                    logout();
                    if (data.error !== null) {
                        Swal.fire('Ha ocurrido un error', data.error, 'error');
                        return;
                    }
                    if (data.error !== null) {
                        Swal.fire('Ha ocurrido un excepción', data.excepción, 'error');
                        return;
                    }

                } else
                {
                    logout();
                }
            }
    );
}

function mostrarDetalleEmpresa()
{
        validEmp();
        document.getElementById("imgFoto2").src = "data:;base64," + empresaActual.logo;
        $('#txtBase642').val(empresaActual.logo);

        document.getElementById("imgFoto3").src = "data:;base64," + empresaActual.foto;
        $('#txtBase643').val(empresaActual.foto);

        $('#txtEmpresaNombre').val();
        $('#lable1').addClass('active');
        $('#txtEmpresaContacto').val(empresaActual.contacto);
        $('#lable2').addClass('active');
        $('#txtEmpresaDomicilio').val(empresaActual.domicilio);
        $('#lable3').addClass('active');
        $('#txtEmpresaCorreo').val(empresaActual.correo);
        $('#lable4').addClass('active');
        $('#txtEmpresaUsuario').val(empresaActual.nomUsuario);
        $('#lable5').addClass('active');
        $('#txtEmpresaContraseña').val(empresaActual.contrasenia);
        $('#lable6').addClass('active');

        $('#txtEmpresaId').val(empresaActual.idEmpresa);

}

function limpiarDetalleE()
{
    $('#txtEmpresaNombre').val("");
    $('#txtEmpresaContacto').val("");
    $('#txtEmpresaDomicilio').val("");
    $('#txtEmpresaCorreo').val("");
    $('#txtEmpresaUsuario').val("");
    $('#txtEmpresaContraseña').val("");
    $('#txtBase642').val("");
    $('#txtBase643').val("");
    $('#txtEmpresaId').val("");
    empresaActual = null;
}

/*-------------------------------Promociones --------------------------------*/
var promo;
var promoActual;
function cargarModuloPromociones()
{
    $.ajax(
            {
                type: "POST",
                url: "promocion/catalogo.html",
                async: true
            }

    ).done(
            function (data)
            {
                $('#divMainContainer').html(data);
                $('#txtModulo').html("Módulo Promociones Activas");
                getAllPromociones();
            }

    );
}
function getAllPromociones()
{
    var t = localStorage.getItem("token");
    var iE = localStorage.getItem("idEmpresa");
    var data = {token: t, idEmpresa: iE};
    //alert(data);
    $.ajax(
            {
                type: 'GET',
                async: true,
                data: data,
                url: 'api/promocion/getAll'
            }
    ).done(function (data)
    {
 //       console.log(data);
        if ($.isEmptyObject(data.error)) {
            promo = data;
            var str = '';
            console.log(promo);
            for (var i = 0; i < promo.length; i++) {
                str += '<tr>' +
                        '<td>' + promo[i].titulo + '</td>' +
                        '<td>' + promo[i].fechaInicio + '</td>' +
                        '<td>' + promo[i].fechaTermino + '</td>' +
                        '<td><img id="imgFoto" src="data:image/png;base64,' + promo[i].imagen + '" style="height: 128px; widht: 128px;" onchange="cargarFoto()">' +
                        '<input type="file" hidden="true" name="txtFoto2" id="txtFoto" class="form-control text-black" onchange="cargarFoto()">' +
                        '<td>' + promo[i].descripcion + '</td>' +
                        '<td>' + promo[i].precio + '</td>' +
                        '<td>' + promo[i].descuento + '</td>' +
                        '<td>' + promo[i].sucursal.nombre + '</td>' +
                        '<td>' + promo[i].productoservicio.nombre + '</td>' +
                        '<td><button onclick="mostrarDetallePomocion(' + i + ');"' +
                        ' class="btn btn-info btn-rounded" data-toggle="modal" data-target="#modalPoll-1"><i class="fas fa-info-circle"></i></button>' + '</td>' +
                        '</td>' +
                        '</td>' +
                        '<td><button onclick="eliminarPromo(' + promo[i].idPromocion + ');"' +
                        ' class="btn btn-danger btn-rounded"><i class="fas fa-minus-circle"></i></button>' + '</td>' +
                        '</td>' +
                        '</tr>';
            }
            $('#tblPromocion').html(str);
            buildTableData();
        } else {
            logout();
        }
    });
}

function getAllPromocionesIna()
{
    $('#eliminar').hide();
    $('#btnAgregar').hide();
    
    var t = localStorage.getItem("token");
    var iE = localStorage.getItem("idEmpresa");
    var data = {token: t, idEmpresa: iE};
    //alert(data);
    $.ajax(
            {
                type: 'GET',
                async: true,
                data: data,
                url: 'api/promocion/getAll2'
            }
    ).done(function (data)
    {

        if ($.isEmptyObject(data.error)) {
            promo = data;
            var str = '';
            console.log(promo);
            for (var i = 0; i < promo.length; i++) {
                str += '<tr>' +
                        '<td>' + promo[i].titulo + '</td>' +
                        '<td>' + promo[i].fechaInicio + '</td>' +
                        '<td>' + promo[i].fechaTermino + '</td>' +
                        '<td><img id="imgFoto2" src="data:image/png;base64,' + promo[i].imagen + '" style="height: 128px; widht: 128px;" onchange="cargarFoto()">' +
                        '<input type="file" hidden="true" name="txtFoto2" id="txtFoto" class="form-control text-black" onchange="cargarFoto()">' +
                        '<td>' + promo[i].descripcion + '</td>' +
                        '<td>' + promo[i].precio + '</td>' +
                        '<td>' + promo[i].descuento + '</td>' +
                        '<td>' + promo[i].sucursal.nombre + '</td>' +
                        '<td>' + promo[i].productoservicio.nombre + '</td>' +
                        '<td><button onclick="activarPromo(' + promo[i].idPromocion + ');"' +
                        ' class="btn btn-outline-success btn-reliminarSucursalounded"><i class="fas fa-info-circle"></i></button>' + '</td>' +
                        '</td>' +
                        ' class="btn btn-danger btn-rounded"><i class="fas fa-minus-circle"></i></button>' + '</td>' +
                        '</td>' +
                        '</tr>';
            }
            $('#tblPromocion').html(str);
            buildTableData();
        } else {
            logout();
        }
    });
}

function mostrarProSer() {
    var t = localStorage.getItem("token");
    var iE = localStorage.getItem("idEmpresa");
    var data = {idEmpresa: iE};
    $.ajax(
            {
                type: 'GET',
                url: 'api/productoservicio/getAllForPromo',
                async: true,
                data: data
            }).done(
            function (data) {
                var sol = "";
                proser = data;
                for (var i = 0; i < proser.length; i++) {
                    //console.log(proser[i].idProductoServicio);
                    //console.log(salas[i].sucursal.nombre);
                    sol += '<option  value="' + proser[i].idProductoServicio + '">' + proser[i].nombre + '</option>\n';


                }
                $('#lstIdProducto').html(sol);
            });
}

function mostrarSucursal() {
    var t = localStorage.getItem("token");
    var iE = localStorage.getItem("idEmpresa");
    var data = {token: t,
        idEmpresa: iE};
    $.ajax(
            {
                type: 'GET',
                async: true,
                data: data,
                url: 'api/sucursal/getAllForPromo'
            }
    ).done(function (data) {
        var sol = "";
        sucursales = data;
        console.log(sucursales);
        for (var i = 0; i < sucursales.length; i++) {
            //console.log(sucursales[i].idSucursal);
            //console.log(salas[i].sucursal.nombre);
            sol += '<option  value="' + sucursales[i].idSucursal + '">' + sucursales[i].nombre + '</option>\n';


        }
        $('#lstIdSucursal').html(sol);
    });
}

function llenarListas() {
    mostrarProSer();

    mostrarSucursal();
}

function mostrarDetallePomocion(posicion)
{
    llenarListas();
    if (posicion >= 0 && promo != null && promo [posicion] != null)
    {
        console.log(promo[posicion]);
        promoActual = promo[posicion];
        $('#txtTitulo').val(promo[posicion].titulo);
        $('#lable1').addClass('active');
        $('#txtFechaInicio').val(promo[posicion].fechaInicio);
        $('#lable2').addClass('active');
        $('#txtFechaTermino').val(promo[posicion].fechaTermino);
        $('#lable3').addClass('active');
        $('#txtDescripcion').val(promo[posicion].descripcion);
        $('#lable4').addClass('active');
        $('#txtPrecio').val(promo[posicion].precio);
        $('#lable5').addClass('active');
        $('#txtDescuento').val(promo[posicion].descuento);
        $('#lable6').addClass('active');
        console.log((promo[posicion].idProductoServicio));
        $('#lstProducto').val(promo[posicion].idProductoServicio);
        $('#lstIdSucursal').val(promo[posicion].idSucusal);

        $('#txtIdPromocion').val(promo[posicion].idPromocion);

        document.getElementById("imgFoto").src = "data:;base64," + promoActual.imagen;
        $('#txtBase64').val(promoActual.imagen);

    } else
    {
        limpiarDetallePromo();
    }
}

function insertPromociones() {

    var titulo = $('#txtTitulo').val();
    var fechaI = $('#txtFechaInicio').val();
    var fechaT = $('#txtFechaTermino').val();
    var imagen = $('#txtBase64').val();
    var descrip = $('#txtDescripcion').val();
    var precio = $('#txtPrecio').val();
    var descuento = $('#txtDescuento').val();
    var idPSPromo = document.getElementById("lstIdProducto").value;
    //alert(idPsPromo);
    var idSPromo = document.getElementById("lstIdSucursal").value;
    var t = localStorage.getItem("token");
    //alert(t);

    var data = {
        titulo: titulo,
        fechaInicio: fechaI,
        fechaTermino: fechaT,
        imagen: imagen,
        descripcion: descrip,
        precio: precio,
        descuento: descuento,
        idPS: idPSPromo,
        idS: idSPromo,
        token: t
    };
    console.log(data);
    $.ajax(
            {
                type: 'POST',
                async: true,
                url: 'api/promocion/insertPromo',
                data: data
            }
    ).fail(function (data) {
        Swal.fire('Ha ocurrido un error', data.error, 'error');
        console.log(data);
    })
            .done(
                    function (data) {
                        console.log(data);
                        if ($.isEmptyObject(data.error)) {
                            Swal.fire('Movimiento realizado', 'Sucursal Guardada', 'success');
                            getAllPromociones();
                            limpiarDetalleS();
                        } else {
                            if (data.error == "null")
                            {
                                logout();
                            } else
                            {
                                Swal.fire('Ha ocurrido un error', data.error, 'error');
                            }
                        }
                    }
            );
}

function decidirPromo(id) {
    if (promoActual != null) {
        modificarPromo(id);
    } else {
        insertPromociones();
    }
    getAllPromociones();
}

function modificarPromo()
{
    var idPromo = $('#txtIdPromocion').val();
    var titulo = $('#txtTitulo').val();
    var fechaI = $('#txtFechaInicio').val();
    var fechaT = $('#txtFechaTermino').val();
    var imagen = $('#txtBase64P2').val();
    var descrip = $('#txtDescripcion').val();
    var precio = $('#txtPrecio').val();
    var descuento = $('#txtDescuento').val();
    var idPSPromo = document.getElementById("lstIdProducto").value;
    //alert(idPsPromo);
    var idSPromo = document.getElementById("lstIdSucursal").value;
    var t = localStorage.getItem("token");
    //alert(t);

    var data = {
        idPromocion: idPromo,
        titulo: titulo,
        fechaInicio: fechaI,
        fechaTermino: fechaT,
        imagen: imagen,
        descripcion: descrip,
        precio: precio,
        descuento: descuento,
        idPS: idPSPromo,
        idS: idSPromo,
        token: t
    };
    console.log(data);
    $.ajax(
            {
                type: "GET",
                url: "api/promocion/updatePromo",
                async: true
            }).fail(function (data) {
        console.log(data);
        Swal.fire('Ha ocurrido un error', data.error, 'error');
    })
            .done(
                    function (data) {
                        console.log(data);
                        if ($.isEmptyObject(data.error)) {
                            Swal.fire('Movimiento realizado', 'Modificado', 'success');
                            getAllPromociones();
                        } else {
                            Swal.fire('Ha ocurrido un error', data.error, 'error');
                            console.log(data.error);
                        }
                    }

            );
}

function eliminarPromo(id) {
    //id = $('#txtIdPS').val();
    var t = localStorage.getItem("token");
    console.log(id);
    var datos = {
        idPromocion: id,
        token: t
    };
    
    //alert(id);
    $.ajax(
            {
                type: 'POST',
                async: true,
                url: 'api/promocion/deletePromo',
                data: datos
            }
    ).done(function (data)
    {
        console.log(data);
        Swal.fire({
            type: 'success',
            title: 'Movimiento realizado',
            text: 'Promocion eliminada con éxito',
            showConfirmButton: false,
            timer: 1500
        });

        getAllPromociones();

    });
}

function activarPromo(id) {
    //id = $('#txtIdPS').val();
    alert(id);
    var t = localStorage.getItem("token");
    var datos = {
        idPromocion: id,
        token: t
    };
    //alert(id);
    $.ajax(
            {
                type: 'POST',
                async: true,
                url: 'api/promocion/activatePromo',
                data: datos
            }
    ).done(function (data)
    {
        console.log(data);
        Swal.fire({
            type: 'success',
            title: 'Movimiento realizado',
            text: 'Promocion activada con éxito',
            showConfirmButton: false,
            timer: 1500
        });

        getAllPromocionesIna();

    });
}

function limpiarDetallePromo()
{
    $('#txtTitulo').val("");
    $('#txtFechaInicio').val("");
    $('#txtFechaTermino').val("");
    $('#txtDescripcion').val("");
    $('#txtPrecio').val('');
    $('#txtDescuento').val('');
    $('#lstProducto').val('');
    $('#lstIdSucursal').val('');
    $("#txtFoto").val('');
    $("#imgFoto").val('');
    $('#txtBase64').val('');
    promoActual = null;
}

