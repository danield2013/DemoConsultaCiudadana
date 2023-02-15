var btnAdd = document.getElementById('btn-add');
var formAdd = document.getElementById('form-add');
var editorEdit = document.getElementById('editor-edit');

btnAdd.addEventListener('click', function() {
	formAdd.classList.toggle('mostrar-form-add');
	editorEdit.classList.toggle('ocultar-editor-edit');
});

function seleccionarArchivo() {
	document.getElementById("file").click();
}

function verificarArchivo() {
	var fileInput = document.getElementById("file");
	var files = fileInput.files;

	if (files.length > 0) {
		if (files.length == 1){
			document.getElementById("text-upload").innerHTML = files[0].name;
			var filePath = fileInput.value;
			var allowedExtensions = /(.pdf)$/i;
			if(!allowedExtensions.exec(filePath)){
				fileInput.value = "";
				document.getElementById("text-upload").innerHTML = "Selecionar archivo pdf.";
			}
		} else {
			fileInput.value = "";
			document.getElementById("text-upload").innerHTML = "Seleccionar un solo archivo.";
		}
	}
}

function cancelarAdd() {
	document.getElementById("nombre").value = "";
	document.getElementById("descripcion").value = "";
	document.getElementById("file").value = "";
	document.getElementById("text-upload").innerHTML = "Ning\u00FAn archivo seleccionado.";
	
	btnAdd.click();
}