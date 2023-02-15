<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="configuracion.*, metodos.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>Configuraci�n - Gu�a de Tr�mite</title>
<link rel="stylesheet" href="../css/estilos.css">
<link rel="stylesheet" href="../css/fontello.css">
<style type="text/css">
	.logo {
		width: 290px;
	}
</style>
</head>

<%
	ConstruirMenu construirMenu = new ConstruirMenu("configuracion");
	Menu[] menu = construirMenu.getMenu();
	int idMenuSelected = 2;
%>

<body>
	<header>
		<div class="contenedor">
			<h1 class="logo"><a class="logo-link" href="menu.jsp">CONFIGURACION DEL SISTEMA</a></h1>
			<div class="iconos">
				<a class="icon-home" title="Ir a Inicio" href="../index.jsp"></a>
				<span class="icon-menu" id="btn-menu" title="Desplegar Men�"></span>
				<a class="icon-config" title="Ir a Configuraci�n" href="menu.jsp"></a>
				<a class="icon-login" title="Iniciar Sesi�n" href=""></a>
				<a class="icon-logout" title="Cerrar Sesi�n" href=""></a>
			</div>
			<nav class="nav" id="nav">
				<ul class="menu">
					<li class="menu-item">
						<a class="menu-link" href="menu.jsp">Configuraci�n</a>
					</li>
					<%
					if (menu != null) {
						for (int i = 0; i < menu.length; i++) {
							String selected = "";
							if (idMenuSelected == (i + 1)) {
								selected = " select";
							}
							%>
							<li class="menu-item">
								<a class="menu-link<%= selected %>" href="<%= menu[i].getUrl() %>"><%= menu[i].getMenu() %></a>
								<%
								if (menu[i].getItem() != null) {
									%> <ul class="submenu"> <%
									for (int j = 0; j < menu[i].getItem().length; j++) {
										%> <li><a class="submenu-link" href="<%= menu[i].getItem()[j].getUrl() %>">
											<%= menu[i].getItem()[j].getItem() %></a></li> <%
									}
									%> </ul> <%
								}
								%>
							</li>
							<%
						}
					}
					%>
				</ul>
			</nav>
		</div>
	</header>
	<main>
		<section class="section-editor">
			<div class="contenedor">
				<h3 class="text-prin">Gu�as de Tr�mites</h3>
				<p>Administrar gu�as de tr�mites publicadas en el sistema.</p>
				<div class="editor-add">
					<span class="icon-add" id="btn-add">
						<span class="text-accion">A�adir nueva gu�a</span>
					</span>
					<form class="form-add" id="form-add" method="post" enctype="multipart/form-data" action="../uploadGuiaTramite">
						<table>
							<tr>
								<td width="130px">Nombre:</td>
								<td width="400px"><input type="text" id="nombre" maxlength="30"></td>
							</tr>
							<tr>
								<td>Descripci�n:</td>
								<td><textarea id="descripcion" maxlength="200" rows="6"></textarea></td>
							</tr>
							<tr>
								<td colspan="2" align="center">
									<div class="upload-add">
										<input class="file-button" type="file" id="file" accept="application/pdf" onchange="verificarArchivo();">
										<span class="icon-upload" title="Seleccionar archivo" onclick="seleccionarArchivo();"></span>
										<span class="text-upload" id="text-upload" title="Seleccionar archivo" onclick="seleccionarArchivo();">Ning�n archivo seleccionado.</span>
									</div>
								</td>
							</tr>
						</table>
						<div class="botones-add">
							<span class="icon-save" title="Guardar"></span>
							<span class="icon-cancel" title="Cancelar" onclick="cancelarAdd();"></span>
						</div>
					</form>
				</div>
				<div class="editor-edit" id="editor-edit">
				
				</div>
			</div>
			<hr></hr>
		</section>
	</main>
	<footer></footer>
	<script type="text/javascript" src="../js/menu.js"></script>
	<script type="text/javascript" src="../js/editor.js"></script>
</body>
</html>