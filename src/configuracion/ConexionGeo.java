package configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionGeo {
	
	private Connection conn = null;

	public ConexionGeo() {
		try {
			Configuracion configuracion = new Configuracion();
			String url = "jdbc:postgresql://" + configuracion.getBd_geo();
			
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, configuracion.getUsu_geo(), configuracion.getPass_geo());
			
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