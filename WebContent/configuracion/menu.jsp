<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="configuracion.*, metodos.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
<title>Menú - Configuración</title>
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
%>

<body>
	<header>
		<div class="contenedor">
			<h1 class="logo"><a class="logo-link" href="menu.jsp">CONFIGURACION DEL SISTEMA</a></h1>
			<div class="iconos">
				<a class="icon-home" title="Ir a Inicio" href="../index.jsp"></a>
				<span class="icon-menu" id="btn-menu" title="Desplegar Menú"></span>
				<a class="icon-config" title="Ir a Configuración" href="menu.jsp"></a>
				<a class="icon-login" title="Iniciar Sesión" href=""></a>
				<a class="icon-logout" title="Cerrar Sesión" href=""></a>
			</div>
			<nav class="nav" id="nav">
				<ul class="menu">
					<li class="menu-item">
						<a class="menu-link select" href="menu.jsp">Configuración</a>
					</li>
					<%
					if (menu != null) {
						for (int i = 0; i < menu.length; i++) {
							%>
							<li class="menu-item">
								<a class="menu-link" href="<%= menu[i].getUrl() %>"><%= menu[i].getMenu() %></a>
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
		<section class="section-seleccion">
			<div class="contenedor flex-wrap">
				<%
				if (menu != null) {
					for (int i = 0; i < menu.length; i++) {
						%>
						<div class="seleccion-item" onclick="direccionar('<%= menu[i].getUrl() %>');">
							<img src="<%= menu[i].getIcon() %>" alt="">
							<h4><%= menu[i].getMenu() %></h4>
						</div>
						<%
					}
				}
				%>
			</div>
			<hr></hr>
		</section>
	</main>
	<footer></footer>
	<script type="text/javascript" src="../js/menu.js"></script>
</body>
</html>