<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="configuracion.*, catastro.*, metodos.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Información geográfica</title>
<link rel="stylesheet" href="css/estilos.css">
<link rel="stylesheet" href="css/fontello.css">
<script type="text/javascript" src="js/general.js"></script>
<!-- Botones openlayer -->
<link type="text/css" rel="stylesheet" href="openlayers3/ol.css"/>
<!-- Mostrar mapas -->
<script type="text/javascript" src="openlayers3/ol.js"></script>
</head>

<script type="text/javascript">

	var host_geoserver = "";
	var version_wms = "1.1.0";
	var codigo_srs = "EPSG:32717";
	var bounds_minx = 867514.625;
	var bounds_miny = 9862454;
	var bounds_maxx = 870373.875;
	var bounds_maxy = 9864476;
	var extent = new Array();
	var mostrarUbicacion = false;
	
	var workspace = "Arajuno_WEB";
	var ortofoto = "ortofoto_ar";
	var layer = "Arajuno_WEB";
    var layers = [];
    
</script>

<%
	ConstruirMenu construirMenu = new ConstruirMenu("inicio");
	Menu[] menu = construirMenu.getMenu();
	int idMenuSelected = 4;

	Configuracion configuracion = new Configuracion();
	if (configuracion != null) {
		%> <script> host_geoserver = '<%= configuracion.getHost_geoserver() %>'; </script> <%
	}
	
	int LengthClaveCatastral = 18;
	double px_digito = 10;
	
	String codigoCatastral = request.getParameter("clave_catastral") != null ? request.getParameter("clave_catastral") : "";
	boolean sinResultados = false;
	
	String[] extent = null;
	
	boolean isOrtofoto = true;
	String nombre_ortofoto = "ortofoto_ar";
	String nombre_capas = "Arajuno_WEB";
	
	InfoGeo infoGeo = new InfoGeo();
	
	if (codigoCatastral != null && !codigoCatastral.isEmpty()) {
		extent = infoGeo.buscarClave(codigoCatastral);
		
		if (extent.length > 0) {
			%>
			<script>
				extent[0] = '<%= extent[0] %>';
				extent[1] = '<%= extent[1] %>';
				extent[2] = '<%= extent[2] %>';
				extent[3] = '<%= extent[3] %>';
				extent[4] = '<%= extent[4] %>';
				extent[5] = '<%= extent[5] %>';
				var ubicacion = '<%= extent[6] %>';
				extent[6] = '<%= extent[7] %>';
				extent[7] = '<%= extent[8] %>';
				
				if (ubicacion != null && (ubicacion == "ClaveCatastral" || ubicacion == "Predio")) {
					mostrarUbicacion = true;
		    	}
			</script>
			<%
		} else {
			sinResultados = true;
		}
	}
	
	BusquedaAvanzada[] busav = infoGeo.busquedaAvanzada();
%>

<script type="text/javascript">

	layers[0] = new ol.layer.Tile({
		style : ortofoto,
		visible: true,
		source : new ol.source.TileWMS({
			url : 'http://' + host_geoserver + '/' + workspace + '/wms',
			params : {
				VERSION : version_wms,
				tiled : true,
				LAYERS : workspace + ':' + ortofoto
			}
		})
	});

    layers[1] = new ol.layer.Image({
		style : layer,
		visible: true,
		source : new ol.source.ImageWMS({
			url : 'http://' + host_geoserver + '/' + workspace + '/wms',
			params : {
				VERSION : version_wms,
				tiled : true,
				LAYERS : workspace + ':' + layer
			}
		})
	});
	
</script>

<body>
	<header>
		<div class="contenedor">
			<h1 class="logo"><a class="logo-link" href="index.jsp">SERVICIOS EN LINEA</a></h1>
			<div class="iconos">
				<a class="icon-home" title="Ir a Inicio" href="index.jsp"></a>
				<span class="icon-menu" id="btn-menu" title="Desplegar Menú"></span>
				<a class="icon-config" title="Ir a Configuración" href="configuracion/menu.jsp"></a>
				<a class="icon-login" title="Iniciar Sesión" href=""></a>
				<a class="icon-logout" title="Cerrar Sesión" href=""></a>
			</div>
			<nav class="nav" id="nav">
				<ul class="menu">
					<li class="menu-item">
						<a class="menu-link" href="index.jsp">Inicio</a>
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
		<section class="section-geo">
			<div class="contenedor flex-wrap">
				<div class="menu-geo">
					<h3 class="text-prin">Buscar Predio</h3>
					<p>Ingrese clave catastral de predio urbano para ubicar en el mapa:</p>
					<div class="buscador">
						<form class="form-buscar" name="form_buscar" method="post" autocomplete="off">
							<input type="text" name="clave_catastral" style="width: <%= (LengthClaveCatastral * px_digito) %>px" maxlength="<%= LengthClaveCatastral %>"
								placeholder="Clave catastral" value="<%= codigoCatastral %>" onkeypress="pulsarBoton(event, 'btn_buscar'); return campoEntero(event);" autofocus>
								<span class="icon-search" id="btn_buscar" title="Buscar clave catastral" onclick="buscarClave();"></span>
						</form>
						<font id="mensaje" class="mensaje" style="float: right; padding-top: 15px;"></font>
					</div>
					<table id="mostrar-ocultar" width="100%" style="padding-top: 30px;" border="0">
						<tr>
							<td colspan="4"><h4 class="text-prin">Mostrar/Ocultar Capas</h4></td>
						</tr>
						<tr>
							<td width="8px"></td>
							<td align="right" width="20px">
								<input type="checkbox" id="check_capas" value="<%= nombre_ortofoto %>" style="display: inline;" onchange="mostrarCapa(checked, value);" checked="checked">
							</td>
							<td width="20px"><span class="icon-pin" onclick="clickCapa('check_capas');"></span></td>
							<td><span class="font-accion" onclick="clickCapa('check_capas');">Ortofoto</span></td>
						</tr>
						<tr>
							<td></td>
							<td align="right">
								<input type="checkbox" id="check_ortofoto" value="<%= nombre_capas %>" style="display: inline;" onchange="mostrarCapa(checked, value);" checked="checked">
							</td>
							<td><span class="icon-pin" onclick="clickCapa('check_ortofoto');"></span></td>
							<td><span class="font-accion" onclick="clickCapa('check_ortofoto');">Capas</span></td>
						</tr>
					</table>
					<%
					if (busav != null && busav.length > 0) {
						%>
						<table id="busqueda-avanzada" width="100%" style="padding-top: 30px;" border="0">
							<tr>
								<td><h4 class="text-prin">Consultas de interés</h4></td>
							</tr>
							<%
							for (int i = 0; i < busav.length; i++) {
								%>
								<tr>
									<td>
										<span class="font-accion" style="padding-left: 15px;" title="<%= busav[i].getBusav_descripcion() %>"
											onclick="ejecutarBA(<%= busav[i].getBusav_codigo() %>);"><%= busav[i].getBusav_nombre() %></span>
									</td>
								</tr>
								<%
							}
							%>
						</table>
						<%
					}
					%>
					
				</div>
				<div class="visor-geo">
					<div id="map"></div>
					<div id="orientacion">
						<img src="img/orientacion.png">
					</div>
					<div id="scale"></div>
					<div id="location">
						<font style="padding-right: 44px;">Longitud:</font>
						<font style="float: right; padding-right: 58px;">Latitud:</font>
					</div>
				</div>
			</div>
		</section>
	</main>
	<footer></footer>
	<script type="text/javascript" src="js/menu.js"></script>
	<script type="text/javascript">
		
		var bounds = [bounds_minx, bounds_miny, bounds_maxx, bounds_maxy];
		
		var mousePositionControl = new ol.control.MousePosition({
			className : 'custom-mouse-position',
			target : document.getElementById('location'),
			coordinateFormat : ol.coordinate.createStringXY(5),
			undefinedHTML : '&nbsp;'
		});
		
		var point = [Number(0), Number(0)];
		var radio = 0;
	   
	    if (extent.length > 0 && mostrarUbicacion) {
	    	point = [Number(extent[0]), Number(extent[1])];
	    	
	    	var xmin = Number(extent[2]);
	    	var ymin = Number(extent[3]);
	    	var xmax = Number(extent[4]);
	    	var ymax = Number(extent[5]);
	    	//radio = Number(extent[6]); /*largo del predio*/
	    	radio = Number(extent[7]) + 80; /*segmento mas largo en relacion al punto centro del predio*/
	    }
	    
	    var circleFeature = new ol.Feature({
	        geometry: new ol.geom.Circle(point, radio),
	    });
	    /*
	    var circleStyle = new ol.style.Style({
	    	image: new ol.style.Circle({
	            fill: new ol.style.Fill({color: 'red'})
	        })
	    });
	    
	    var circleStyle = new ol.style.Style({
			image: new ol.style.Circle({
		        fill: new ol.style.Fill({ color: [255,0,0,1] }),
		        stroke: new ol.style.Stroke({ color: [0,0,0,1] })
		    })
	    })
	    
	    circleFeature.setStyle(circleStyle);
	    
	    Circle:new p.c({stroke:new u.a({color:"red",width:2}),fill:new d.a({color:"rgba(255,0,0,0.2)"})})
	    */
	    layers[2] = new ol.layer.Vector({
	        source: new ol.source.Vector({
		        features: [circleFeature]
			})
	    });
	    
	    var iconFeature = new ol.Feature({
	        geometry: new ol.geom.Point(point),
	    });
	   	
	    var iconStyle = new ol.style.Style({
	        image: new ol.style.Icon({
	        	anchor: [15, 0],
	            anchorXUnits: 'pixels',
	            anchorYUnits: 'pixels',
	            anchorOrigin: 'bottom-left',
	        	src: 'img/location.png',
	            scale: 1
	        })
	    });
	    iconFeature.setStyle(iconStyle);
	    
	    layers[3] = new ol.layer.Vector({
	        source: new ol.source.Vector({
		        features: [iconFeature]
			}),
			style: new ol.style.Style({
		    	image: new ol.style.Circle({
		            fill: new ol.style.Fill({color: '#1b465a'})
		        })
			})
	    });

		var projection = new ol.proj.Projection({
			code : codigo_srs,
			units : 'm',
			axisOrientation : 'neu'
		});
		
		var map = new ol.Map({
			controls : ol.control.defaults({
				attribution : false
			}).extend([mousePositionControl]),
			target : 'map',
			layers : layers,
			view : new ol.View({
				projection : projection
			})
		});

		map.getView().on('change:resolution', function(evt) {
			var resolution = evt.target.get('resolution');
			var units = map.getView().getProjection().getUnits();
			var dpi = 25.4 / 0.28;
			var mpu = ol.proj.METERS_PER_UNIT[units];
			var scale = resolution * mpu * 39.37 * dpi;
			
			if (scale >= 9500 && scale <= 950000) {
				scale = Math.round(scale / 1000) + "K";
			} else if (scale >= 950000) {
				scale = Math.round(scale / 1000000) + "M";
			} else {
				scale = Math.round(scale);
			}
			document.getElementById('scale').innerHTML = "Escala = 1 : " + scale;
		});
		
		var height = screen.height;
		var width = screen.width;
		
		if (extent.length > 0) {
			height = screen.height / 12;
			width = screen.width / 12;
			
			map.getView().fitExtent([Number(extent[2]), Number(extent[3]), Number(extent[4]), Number(extent[5])], [width, height]);
		} else {
			map.getView().fitExtent(bounds, [width, height]);
		}
		
		//METODOS
		function buscarClave() {
			document.form_buscar.submit();
		}
		
		function mostrarCapa(checked, value) {
			for (var i = 0; i < layers.length; ++i) {
				if (layers[i].get('style') == value) {
					layers[i].set('visible', checked);
				}
			}
		}
		
		function clickCapa(capa) {
			var checked = document.getElementById(capa).checked;
			var value = document.getElementById(capa).value;
			
			document.getElementById(capa).checked = !checked;
			mostrarCapa(!checked, value);
		}
		
		function ejecutarBA(codigo){
			alert("ejecutar BA " + codigo);
		}
	</script>
	<%
		if (!isOrtofoto) {
			%> <script> document.getElementById("mostrar-ocultar").style.display = "none"; </script> <%
		}
	
		if (sinResultados) {
			%> <script> mensaje('Búsqueda sin resultados.', 'mensaje'); </script> <%
		}
	%>
</body>
</html>