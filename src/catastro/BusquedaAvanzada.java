package catastro;

public class BusquedaAvanzada {
	
	private int busav_codigo;
	private String busav_nombre = "";
	private String busav_descripcion = "";
	private String busav_sql = "";
	
	public BusquedaAvanzada() {
		// TODO Auto-generated constructor stub
	}

	public int getBusav_codigo() {
		return busav_codigo;
	}

	public void setBusav_codigo(int busav_codigo) {
		this.busav_codigo = busav_codigo;
	}

	public String getBusav_nombre() {
		return busav_nombre;
	}

	public void setBusav_nombre(String busav_nombre) {
		this.busav_nombre = busav_nombre;
	}

	public String getBusav_descripcion() {
		return busav_descripcion;
	}

	public void setBusav_descripcion(String busav_descripcion) {
		this.busav_descripcion = busav_descripcion;
	}

	public String getBusav_sql() {
		return busav_sql;
	}

	public void setBusav_sql(String busav_sql) {
		this.busav_sql = busav_sql;
	}
}