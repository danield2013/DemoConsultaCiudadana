package configuracion;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuracion {
	
	private String host_catastro = "";
	private String host_geoserver = "";
	private String bd_catastro = "";
	private String usu_catastro = "";
	private String pass_catastro = "";
	private String bd_geo = "";
	private String usu_geo = "";
	private String pass_geo = "";
	private String url_demo = "";
	private String url_carpeta = "";
	private String url_subcarpeta = "";
	
	private boolean exportarReportes = true;//Exportar reportes jasper? Caso contrario mostrar en visor

	public Configuracion() {
		String proyecto = "";
		//proyecto = "_STACLARA";
		//proyecto = "_TENA";
		//proyecto = "_ORELLANA";
		//proyecto = "_ISIDRO";
		//proyecto = "_ARAJUNO";
		//proyecto = "_CAP";
		
		url_demo = System.getProperty("catalina.home") + File.separator + "webapps" + File.separator + "Demo" + proyecto + File.separator;
		url_carpeta = "nombrecarpeta" + File.separator;
		url_subcarpeta = "nombresubcarpeta" + File.separator;
		
		String so = System.getProperty("os.name");
		if (so.equals("Linux")) {
			url_demo = url_demo.replace("/usr/share", "/var/lib");
			url_carpeta = url_carpeta.replace("/usr/share", "/var/lib");
			url_subcarpeta = url_subcarpeta.replace("/usr/share", "/var/lib");
		}
		url_demo = url_demo.replace(File.separator, (File.separator + File.separator));
		url_carpeta = url_carpeta.replace(File.separator, (File.separator + File.separator));
		url_subcarpeta = url_subcarpeta.replace(File.separator, (File.separator + File.separator));
		
		//Direccionar workspace en caso de desarrollo
		url_demo = "C:\\Users\\User\\workspace\\Demo\\WebContent\\"; // LENOVO DEMO
		//url_demo = "C:\\Users\\Proactinfo Demo\\workspace\\Demo\\WebContent\\"; // VAIO
		
		Properties propiedades = new Properties();
	    InputStream entrada = null;
	    try {
	        entrada = new FileInputStream(url_demo + "configuracion.properties");

	        // cargamos el archivo de propiedades
	        propiedades.load(entrada);

	        // obtenemos las propiedades
	        host_catastro = propiedades.getProperty("host_catastro");
	        host_geoserver = propiedades.getProperty("host_geoserver");
	        // PARAMETROS BASE DE DATOS CATASTRO
	        bd_catastro = propiedades.getProperty("basedatos_catastro");
	        usu_catastro = propiedades.getProperty("usuario_catastro");
	        pass_catastro = propiedades.getProperty("pass_catastro");
	        // PARAMETROS BASE DE DATOS GEOGRAFICA
	        bd_geo = propiedades.getProperty("basedatos_geo");
	        usu_geo = propiedades.getProperty("usuario_geo");
	        pass_geo = propiedades.getProperty("pass_geo");
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        if (entrada != null) {
	            try {
	                entrada.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	public String getHost_catastro() {
		return host_catastro;
	}

	public void setHost_catastro(String host_catastro) {
		this.host_catastro = host_catastro;
	}

	public String getHost_geoserver() {
		return host_geoserver;
	}

	public void setHost_geoserver(String host_geoserver) {
		this.host_geoserver = host_geoserver;
	}

	public String getBd_catastro() {
		return bd_catastro;
	}

	public void setBd_catastro(String bd_catastro) {
		this.bd_catastro = bd_catastro;
	}

	public String getUsu_catastro() {
		return usu_catastro;
	}

	public void setUsu_catastro(String usu_catastro) {
		this.usu_catastro = usu_catastro;
	}

	public String getPass_catastro() {
		return pass_catastro;
	}

	public void setPass_catastro(String pass_catastro) {
		this.pass_catastro = pass_catastro;
	}

	public String getBd_geo() {
		return bd_geo;
	}

	public void setBd_geo(String bd_geo) {
		this.bd_geo = bd_geo;
	}

	public String getUsu_geo() {
		return usu_geo;
	}

	public void setUsu_geo(String usu_geo) {
		this.usu_geo = usu_geo;
	}

	public String getPass_geo() {
		return pass_geo;
	}

	public void setPass_geo(String pass_geo) {
		this.pass_geo = pass_geo;
	}

	public String getUrl_demo() {
		return url_demo;
	}

	public void setUrl_demo(String url_demo) {
		this.url_demo = url_demo;
	}

	public String getUrl_carpeta() {
		return url_carpeta;
	}

	public void setUrl_carpeta(String url_carpeta) {
		this.url_carpeta = url_carpeta;
	}
	
	public String getUrl_subcarpeta() {
		return url_subcarpeta;
	}

	public void setUrl_subcarpeta(String url_subcarpeta) {
		this.url_subcarpeta = url_subcarpeta;
	}

	public boolean isExportarReportes() {
		return exportarReportes;
	}

	public void setExportarReportes(boolean exportarReportes) {
		this.exportarReportes = exportarReportes;
	}
}