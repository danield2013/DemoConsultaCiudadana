package configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionCatastro {
	
	private Connection conn = null;

	public ConexionCatastro() {
		try {
			Configuracion configuracion = new Configuracion();
			String url = "jdbc:postgresql://" + configuracion.getBd_catastro();
			
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, configuracion.getUsu_catastro(), configuracion.getPass_catastro());
			
			if (conn != null) {
				setConn(conn);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}