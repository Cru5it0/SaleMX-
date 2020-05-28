/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var empresa;
var cliente;

function login()
{
    var u = $("#txtUser").val();
    var c = $("#txtPassword").val();
    //alert(u);
    //alert(c);
    var data = {
        u: u,
        c: c
    };
    //Se valida que los campos no esten vacios
    if (u == null || c == null || u == "" || c == "") {
        alert("Debes llenar los campos");
    } else {
        $.ajax(
                {
                    type: "POST",
                    url: "api/loginE/validar",
                    async: true,
                    data: data
                }).done(
                function (data) {
                    empresa = data;
                    console.log(data);
                    if (empresa.length > 0) {
                        if (!(empresa[0].token) == ("")) {
                            //Guardo el id en localstore por si se necesita
                            localStorage.setItem("idEmpresa", empresa[0].idEmpresa);
                            localStorage.setItem("token", empresa[0].token);

                            location.href = "indexEmpresa.html";
                        } else {
                            Swal.fire('Error', 'Sesión ya iniciada', 'error');
                            return;
                            //alert("Sesion ya iniciada");
                        }
                    }
                    if (empresa.length === 0) {
                        loginC();
                    }
                }
        );
    }
}

function loginC()
{
    var u = $("#txtUser").val();
    var c = $("#txtPassword").val();
    //alert(u);
    //alert(c);
    var data = {
        u: u,
        c: c
    };

    $.ajax(
            {
                type: "POST",
                url: "api/loginC/validar",
                async: true,
                data: data
            }).done(
            function (data) {
                cliente = data;
                console.log(data);
                if (cliente.length > 0) {
                    if (!(cliente[0].token) == ("")) {
                        //Guardo el id en localstore por si se necesita
                        localStorage.setItem("idCliente", cliente[0].idCliente);
                        localStorage.setItem("token", cliente[0].token);

                        location.href = "indexCliente.html";
                    } else {
                        Swal.fire('Error', 'Sesión ya iniciada', 'error');
                        return;
                        //alert("Sesion ya iniciada");
                    }
                } else {
                    Swal.fire('Error', 'Usuario y contrasela incorrectos', 'error');
                    return;
                }
            }
    );

}

function logout() {

    var id = localStorage.getItem("idEmpresa");
    var data = {
        id: id
    };
    $.ajax(
            {
                type: "POST",
                url: "api/loginE/deleteToken",
                async: true,
                data: data
            }).done(
            function (data) {
                console.log(data);
                Swal.fire('Movimiento realizado', 'Sesión cerrada', 'success');
                //alert("Sesion cerrada");
                location.href = "index.html";
                localStorage.clear();
            }
    );


}

function logoutC() {

    var id = localStorage.getItem("idCliente");
    var data = {
        id: id
    };
    $.ajax(
            {
                type: "POST",
                url: "api/loginC/deleteToken",
                async: true,
                data: data
            }).done(
            function (data) {
                console.log(data);
                Swal.fire('Movimiento realizado', 'Sesión cerrada', 'success');
                //alert("Sesion cerrada");
                location.href = "index.html";
                localStorage.clear();
            }
    );
}

function loginCAndroid()
{
    var u = $("#txtUser").val();
    var c = $("#txtPassword").val();
    //alert(u);
    //alert(c);
    var data = {
        u: u,
        c: c
    };

    $.ajax(
            {
                type: "GET",
                url: "api/loginC/validarAndroid",
                async: true,
                data: data
            }).done(
            function (data) {
                cliente = data;
                console.log(data);
                if (cliente.length > 0) {
                    if (!(cliente[0].token) == ("")) {
                        //Guardo el id en localstore por si se necesita
                        localStorage.setItem("idCliente", cliente[0].idCliente);
                        localStorage.setItem("token", cliente[0].token);

                        location.href = "indexCliente.html";
                    } else {
                        Swal.fire('Error', 'Sesión ya iniciada', 'error');
                        return;
                        //alert("Sesion ya iniciada");
                    }
                } else {
                    Swal.fire('Error', 'Usuario y contraseña incorrectos', 'error');
                    return;
                }
            }
    );

}
