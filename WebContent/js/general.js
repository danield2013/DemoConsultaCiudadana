function campoEntero(e) {
	var keynum = window.event ? window.event.keyCode : e.which;
	//key 8 -> Borrar
	if ((keynum == 0) || (keynum == 8)) {
		return true;
	}
	return /\d/.test(String.fromCharCode(keynum));
}

function pulsarBoton(e, btn) {
	var keynum = (document.all) ? e.keyCode : e.which;
	//key 13 -> Enter
	if(keynum == 13){
		document.getElementById(btn).click();
	}
}

// MENSAJES

var mensaje_color = "#2471A3";
var red = "red";

function mensaje(mensaje, id) {
	if (id == undefined) id = "mensaje";
	
	document.getElementById(id).style.color = mensaje_color;
	document.getElementById(id).innerHTML = mensaje;
}
function mensajeError(mensaje, id) {
	if (id == undefined) id = "mensaje";
	
	document.getElementById(id).style.color = red;
	document.getElementById(id).innerHTML = mensaje;
}