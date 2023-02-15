package metodos;

import configuracion.Menu;
import configuracion.MenuItem;

public class ConstruirMenu {
	
	private Menu[] menus = null;
	
	public ConstruirMenu(String modulo) {
		if (modulo != null) {
			if (modulo.equals("inicio")) {
				construirMenuInicio();
			} else if (modulo.equals("configuracion")) {
				construirMenuConfiguracion();
			}
		}
	}
	
	public void construirMenuInicio() {
		menus = new Menu[4];
		
		//CONSULTAS
		Menu menu = new Menu();
		menu.setMenu("Consultas");
		menu.setUrl("");
		menu.setIcon("img/consulta.png");
		menu.setItem(buscarItemsConsultas());
		menus[0] = menu;
		
		//GUIA TRAMITES
		menu = new Menu();
		menu.setMenu("Guía de Trámites");
		menu.setUrl("");
		menu.setIcon("img/guia.png");
		menu.setItem(buscarItemsGuiaTramites());
		menus[1] = menu;
		
		//TRAMITES
		menu = new Menu();
		menu.setMenu("Trámites");
		menu.setUrl("");
		menu.setIcon("img/tramite.jpg");
		menu.setItem(buscarItemsTramites());
		menus[2] = menu;
		
		//INFORMACION GEOGRAFICA
		menu = new Menu();
		menu.setMenu("Información Geográfica");
		menu.setUrl("infogeo.jsp");
		menu.setIcon("img/mapa.jpg");
		menu.setItem(null);
		menus[3] = menu;
		
		menu = null;
	}
	
	public void construirMenuConfiguracion() {
		menus = new Menu[3];
		//URL configuracion/
		
		//ACCESO
		Menu menu = new Menu();
		menu.setMenu("Acceso");
		menu.setUrl("");
		menu.setIcon("../img/acceso.jpg");
		menu.setItem(buscarItemsAcceso());
		menus[0] = menu;
		
		//CONFIGURAR ENTRADA
		menu = new Menu();
		menu.setMenu("Configurar Entrada");
		menu.setUrl("");
		menu.setIcon("../img/entrada.jpg");
		menu.setItem(buscarItemsEntrada());
		menus[1] = menu;
		
		//INFORMACION GEOGRAFICA
		menu = new Menu();
		menu.setMenu("Información Geográfica");
		menu.setUrl("");
		menu.setIcon("../img/capas.jpg");
		menu.setItem(null);
		menus[2] = menu;
		
		menu = null;
	}
	
	public MenuItem[] buscarItemsConsultas() {
		MenuItem[] items = new MenuItem[6];
		
		MenuItem item = new MenuItem();
		item.setItem("Consulta 1");
		item.setUrl("");
		items[0] = item;
		
		item = new MenuItem();
		item.setItem("Consulta 2");
		item.setUrl("");
		items[1] = item;
		
		item = new MenuItem();
		item.setItem("Consulta 3");
		item.setUrl("");
		items[2] = item;
		
		item = new MenuItem();
		item.setItem("Consulta 4");
		item.setUrl("");
		items[3] = item;
		
		item = new MenuItem();
		item.setItem("Consulta 5");
		item.setUrl("");
		items[4] = item;
		
		item = new MenuItem();
		item.setItem("Consulta 6");
		item.setUrl("");
		items[5] = item;
		
		item = null;
		return items;
	}
	
	public MenuItem[] buscarItemsGuiaTramites() {
		MenuItem[] items = new MenuItem[4];
		
		MenuItem item = new MenuItem();
		item.setItem("Guía de Trámite 1");
		item.setUrl("");
		items[0] = item;
		
		item = new MenuItem();
		item.setItem("Guía de Trámite 2");
		item.setUrl("");
		items[1] = item;
		
		item = new MenuItem();
		item.setItem("Guía de Trámite 3");
		item.setUrl("");
		items[2] = item;
		
		item = new MenuItem();
		item.setItem("Guía de Trámite 4");
		item.setUrl("");
		items[3] = item;
		
		item = null;
		return items;
	}
	
	public MenuItem[] buscarItemsTramites() {
		MenuItem[] items = new MenuItem[4];
		
		MenuItem item = new MenuItem();
		item.setItem("Trámite 1");
		item.setUrl("");
		items[0] = item;
		
		item = new MenuItem();
		item.setItem("Trámite 2");
		item.setUrl("");
		items[1] = item;
		
		item = new MenuItem();
		item.setItem("Trámite 3");
		item.setUrl("");
		items[2] = item;
		
		item = new MenuItem();
		item.setItem("Trámite 4");
		item.setUrl("");
		items[3] = item;
		
		return items;
	}
	
	public MenuItem[] buscarItemsAcceso() {
		MenuItem[] items = new MenuItem[2];
		
		MenuItem item = new MenuItem();
		item.setItem("Perfil");
		item.setUrl("");
		items[0] = item;
		
		item = new MenuItem();
		item.setItem("Usuario");
		item.setUrl("");
		items[1] = item;
		
		item = null;
		return items;
	}
	
	public MenuItem[] buscarItemsEntrada() {
		MenuItem[] items = new MenuItem[3];
		
		MenuItem item = new MenuItem();
		item.setItem("Consulta");
		item.setUrl("consulta.jsp");
		items[0] = item;
		
		item = new MenuItem();
		item.setItem("Trámite");
		item.setUrl("");
		items[1] = item;
		
		item = new MenuItem();
		item.setItem("Guía de Trámite");
		item.setUrl("guia_tramite.jsp");
		items[2] = item;
		
		return items;
	}

	public Menu[] getMenu() {
		return menus;
	}

	public void setMenu(Menu[] menus) {
		this.menus = menus;
	}
}