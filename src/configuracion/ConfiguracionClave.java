package configuracion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConfiguracionClave {
	
	private int concl_provincia = 0;
	private int concl_canton = 0;
	private int concl_parroquia = 0;
	private int concl_zona = 0;
	private int concl_sector = 0;
	private int concl_manzana = 0;
	private int concl_predio = 0;
	private int concl_ph_bloque = 0;
	private int concl_ph_piso = 0;
	private int concl_ph_unidad = 0;
	//Longitud máxima de la clave catastral
	private int max_length = 30;
	//Tamaño en px por dígito de la clave catastral
	private double px_digito = 8;

	public ConfiguracionClave() {
		int configuracionClave[] = buscarConfiguracionClave();
		
		if (configuracionClave != null) {
			this.concl_provincia = 2;
			this.concl_canton = 2;
			this.concl_parroquia = 2;
			this.concl_zona = configuracionClave[0] ;
			this.concl_sector = configuracionClave[1];
			this.concl_manzana = configuracionClave[2];
			this.concl_predio = configuracionClave[3];
			this.concl_ph_bloque = configuracionClave[4];
			this.concl_ph_piso = configuracionClave[5];
			this.concl_ph_unidad = configuracionClave[6];
		}
	}
	
	public int[] buscarConfiguracionClave() {
		int configuracion_clave[] = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			ConexionCatastro conexion = new ConexionCatastro();
			conn = conexion.getConn();
			
			String sql = "SELECT concl_zona, concl_sector, concl_manzana, concl_predio,"
					+ " concl_ph_bloque, concl_ph_piso, concl_ph_unidad"
					+ " FROM configuracion_clave LIMIT 1;";
			
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				configuracion_clave = new int[7];
				
				for (int i = 0; i < 7; i++) {
					configuracion_clave[i] = rs.getInt(i + 1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SQLUtils.closeQuietly(rs);
			SQLUtils.closeQuietly(stmt);
			SQLUtils.closeQuietly(conn);
		}
		return configuracion_clave;
	}
	
	public int getLengthProvincia() {
		return getConcl_provincia();
	}
	
	public int getLengthCanton() {
		return getLengthProvincia() + getConcl_canton();
	}
	
	public int getLengthParroquia() {
		return getLengthCanton() + getConcl_parroquia();
	}
	
	public int getLengthZona() {
		return getLengthParroquia() + getConcl_zona();
	}
	
	public int getLengthSector() {
		return getLengthZona() + getConcl_sector();
	}
	
	public int getLengthManzana() {
		return getLengthSector() + getConcl_manzana();
	}
	
	public int getLengthPredio() {
		return getLengthManzana() + getConcl_predio();
	}
	
	public int getLengthBloque() {
		return getLengthPredio() + getConcl_ph_bloque();
	}
	
	public int getLengthPiso() {
		return getLengthBloque() + getConcl_ph_piso();
	}
	
	public int getLengthUnidad() {
		return getLengthPiso() + getConcl_ph_unidad();
	}
	
	public int getLengthPH() {
		return getConcl_ph_bloque() + getConcl_ph_piso() + getConcl_ph_unidad();
	}
	
	public int getLengthClaveCatastral() {
		return getLengthPredio() + getLengthPH();
	}

	public int getConcl_provincia() {
		return concl_provincia;
	}

	public void setConcl_provincia(int concl_provincia) {
		this.concl_provincia = concl_provincia;
	}

	public int getConcl_canton() {
		return concl_canton;
	}

	public void setConcl_canton(int concl_canton) {
		this.concl_canton = concl_canton;
	}

	public int getConcl_parroquia() {
		return concl_parroquia;
	}

	public void setConcl_parroquia(int concl_parroquia) {
		this.concl_parroquia = concl_parroquia;
	}

	public int getConcl_zona() {
		return concl_zona;
	}

	public void setConcl_zona(int concl_zona) {
		this.concl_zona = concl_zona;
	}

	public int getConcl_sector() {
		return concl_sector;
	}

	public void setConcl_sector(int concl_sector) {
		this.concl_sector = concl_sector;
	}

	public int getConcl_manzana() {
		return concl_manzana;
	}

	public void setConcl_manzana(int concl_manzana) {
		this.concl_manzana = concl_manzana;
	}

	public int getConcl_predio() {
		return concl_predio;
	}

	public void setConcl_predio(int concl_predio) {
		this.concl_predio = concl_predio;
	}

	public int getConcl_ph_bloque() {
		return concl_ph_bloque;
	}

	public void setConcl_ph_bloque(int concl_ph_bloque) {
		this.concl_ph_bloque = concl_ph_bloque;
	}

	public int getConcl_ph_piso() {
		return concl_ph_piso;
	}

	public void setConcl_ph_piso(int concl_ph_piso) {
		this.concl_ph_piso = concl_ph_piso;
	}

	public int getConcl_ph_unidad() {
		return concl_ph_unidad;
	}

	public void setConcl_ph_unidad(int concl_ph_unidad) {
		this.concl_ph_unidad = concl_ph_unidad;
	}

	public int getMax_length() {
		return max_length;
	}

	public void setMax_length(int max_length) {
		this.max_length = max_length;
	}

	public double getPx_digito() {
		return px_digito;
	}

	public void setPx_digito(double px_digito) {
		this.px_digito = px_digito;
	}
}