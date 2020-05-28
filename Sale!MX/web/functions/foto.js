/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function cargarFotoP() {
    var fileChooser = document.getElementById("txtFotoP");
    var foto = document.getElementById("imgFotoP");
    var base64 = document.getElementById("txtBase64P");
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

function cargarFotoP1() {
    var fileChooser = document.getElementById("txtFotoP2");
    var foto = document.getElementById("imgFotoP2");
    var base64 = document.getElementById("txtBase64P2");
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
