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

/*------------------------------ Sucursal --------------------------------------*/
var sucursales;
var sucursalActual;

function cargarModuloSucursalesCliente()
{
    $.ajax(
            {
                type: "POST",
                url: "sucursal/catalogoCliente.html",
                async: true
            }

    ).done(
            function (data)
            {
                $('#divMainContainer').html(data);
                $('#txtModulo').html("Módulo Sucursales Activas");
                getAllClienteCard();
            }

    );
}

function getAllClienteCard() {
    var t = localStorage.getItem("token");
    var data = {token: t};
    $.ajax(
            {
                type: 'GET',
                async: true,
                data: data,
                url: 'api/sucursal/getAllCliente'
            }
    ).done(function (data)
    {
        if ($.isEmptyObject(data.error)) {
            sucursales = data;
            var str = '';
            console.log(data);
            for (var i = 0; i < sucursales.length; i++) {
                str += '<div class="card mb-4 col-md-4">' +
                        '<img id="imgFoto" src="data:;base64,' + sucursales[i].foto + '" width="350" height="300">' +
                        '<input type="file" hidden="true" name="txtFoto" id="txtFoto" class="form-control text-black" onchange="cargarFoto();">' +
                        '<textarea hidden="true" class="form-control text-black" name="txtBase64" id="txtBase64"></textarea>' +
                        '<div class="card-body">' +
                        '<h4 class="card-title">' + sucursales[i].nombre + '</h4>' +
                        '<h3 class="card-text">' + sucursales[i].domicilio + '</h3>' +
                        '<button type="button" class="btn btn-light-blue btn-md">Read more</button>' +
                        '</div>' + '</div>';
            }
            $('#superCard').html(str);
        } else {
            logoutC();
        }
    });
}

/*-------------------------------Producto y Servicio --------------------------------*/
var proser;
var proSerActual;

function cargarModuloProductoServicioC()
{
    $.ajax(
            {
                type: "POST",
                url: "producto/catalogoCliente.html",
                async: true
            }

    ).done(
            function (data)
            {
                $('#divMainContainer').html(data);
                $('#txtModulo').html("Módulo Sucursales Activas");
                getAllProductoServicioCliente();
            }

    );
}

function getAllProductoServicioCliente()
{
    var t = localStorage.getItem("token");
    data = {token: t};
    $.ajax(
            {
                type: 'GET',
                async: true,
                data: data,
                url: 'api/productoservicio/getAllpsCliente'
            }
    ).done(function (data)
    {
        if ($.isEmptyObject(data.error)) {
            proser = data;
            var str = '';
            console.log(proser);
            for (var i = 0; i < proser.length; i++) {
                str +=  '<div class="card mb-4 col-md-4">' +
                        '<img id="imgFoto" src="data:;base64,' + proser[i].foto + '" width="350" height="300">' +
                        '<input type="file" hidden="true" name="txtFoto" id="txtFoto" class="form-control text-black" onchange="cargarFoto();">' +
                        '<textarea hidden="true" class="form-control text-black" name="txtBase64" id="txtBase64"></textarea>' +
                        '<div class="card-body">' +
                        '<h4 class="card-title">' + proser[i].nombre + '</h4>' +
                        '<h3 class="card-text">' + proser[i].descripcion + '</h3>' +
                        '<p class="text-center">' + 'Precio de: $' + proser[i].precio + '</p>' +
                        '<button type="button" class="btn btn-light-blue btn-md">Read more</button>' +                                  
                        '</div>' +
                        '</div>';
            }
            $('#superCard').html(str);
        } else {
            logoutC();
        }
    });
}

/*-------------------------------Promociones --------------------------------*/
var promo;
var promoActual;
function cargarModuloPromocionesC()
{
    $.ajax(
            {
                type: "POST",
                url: "promocion/catalogoCliente.html",
                async: true
            }

    ).done(
            function (data)
            {
                $('#divMainContainer').html(data);
                $('#txtModulo').html("Módulo Promociones Activas");
                getAllPromocionesCliente();
            }

    );
}
function getAllPromocionesCliente()
{
    var t = localStorage.getItem("token");
    var data = {token: t};
    //alert(data);
    $.ajax(
            {
                type: 'GET',
                async: true,
                data: data,
                url: 'api/promocion/getAllCliente'
            }
    ).done(function (data)
    {
 //       console.log(data);
        if ($.isEmptyObject(data.error)) {
            promo = data;
            var str = '';
            console.log(promo);
            for (var i = 0; i < promo.length; i++) {
                str += '<div class="card mb-4 col-md-4">' +
                        '<img id="imgFoto" src="data:;base64,' + promo[i].foto + '" width="350" height="300">' +
                        '<input type="file" hidden="true" name="txtFoto" id="txtFoto" class="form-control text-black" onchange="cargarFoto();">' +
                        '<textarea hidden="true" class="form-control text-black" name="txtBase64" id="txtBase64"></textarea>' +
                        '<div class="card-body">' +
                        '<h4 class="card-title">' + promo[i].titulo + '</h4>' +
                        '<h3 class="card-text">' + promo[i].descripcion + '</h3>' +
                        '<p class="text-center">' + 'Precio de: $' + promo[i].precio + ' con descuento de: %'+ promo[i].descuento + '</p>' +
                        '<button type="button" class="btn btn-light-blue btn-md">Read more</button>' +                                  
                        '</div>' +
                        '</div>';
            }
            $('#superCard').html(str);
            buildTableData();
        } else {
            logoutC();
        }
    });
}

