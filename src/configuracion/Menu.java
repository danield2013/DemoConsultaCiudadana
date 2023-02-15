package configuracion;

public class Menu {
	
	private String menu = "";
	private String url = "";
	private String icon = "";
	private MenuItem[] item = null;
	
	public Menu() {
		// TODO Auto-generated constructor stub
	}
	
	public String getMenu() {
		return menu;
	}
	
	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public MenuItem[] getItem() {
		return item;
	}

	public void setItem(MenuItem[] item) {
		this.item = item;
	}
}