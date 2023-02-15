package metodos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import catastro.BusquedaAvanzada;
import configuracion.ConexionCatastro;
import configuracion.ConexionGeo;
import configuracion.ConfiguracionClave;
import configuracion.SQLUtils;

public class InfoGeo {
	
	public String[] buscarClave(String codigoCatastral) {
		String[] extent = new String[0];
		
		if (codigoCatastral != null && !codigoCatastral.isEmpty()) {
			String tabla_predios = "predios_urb_ar";
			String campo_cc = "cc";
			
			if (tabla_predios != null && campo_cc != null) {
				Connection conn = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				try {
					ConexionGeo conexion = new ConexionGeo();
					conn = conexion.getConn();
					
					ConfiguracionClave claveCatastral = new ConfiguracionClave();
					
					String sql = "";
					if (codigoCatastral.length() >= claveCatastral.getLengthClaveCatastral()) {
						sql = "SELECT st_x(st_centroid(geom)), st_y(st_centroid(geom)),"
								+ " st_xmin(st_extent(geom)), st_ymin(st_extent(geom)), st_xmax(st_extent(geom)), st_ymax(st_extent(geom)),"
								+ " st_distance((st_point(st_xmin(st_extent(geom)), st_ymin(st_extent(geom)))),"
								+ " st_point(st_xmax(st_extent(geom)), st_ymax(st_extent(geom)))),"
								+ " CASE WHEN st_distance(st_point(st_x(st_centroid(geom)), st_y(st_centroid(geom))),"
								+ " (st_point(st_xmin(st_extent(geom)), st_ymin(st_extent(geom)))))"
								+ " > st_distance(st_point(st_x(st_centroid(geom)), st_y(st_centroid(geom))),"
								+ " (st_point(st_xmax(st_extent(geom)), st_ymax(st_extent(geom)))))"
								+ " THEN st_distance(st_point(st_x(st_centroid(geom)), st_y(st_centroid(geom))),"
								+ " (st_point(st_xmin(st_extent(geom)), st_ymin(st_extent(geom)))))"
								+ " ELSE st_distance(st_point(st_x(st_centroid(geom)), st_y(st_centroid(geom))),"
								+ " (st_point(st_xmax(st_extent(geom)), st_ymax(st_extent(geom))))) END"
								+ " FROM " + tabla_predios + " WHERE " + campo_cc + " = '" + codigoCatastral + "'"
								+ " GROUP BY geom ORDER BY st_area(geom) DESC LIMIT 1;";
					} else {
						sql = "WITH UBI AS(SELECT st_x(st_centroid(geom)), st_y(st_centroid(geom)),"
								+ " st_xmin(st_extent(geom)), st_ymin(st_extent(geom)), st_xmax(st_extent(geom)), st_ymax(st_extent(geom))"
								+ " FROM " + tabla_predios + " WHERE " + campo_cc + " LIKE '" + codigoCatastral + "%' GROUP BY geom)"
								+ " SELECT AVG(st_x), AVG(st_y), MIN(st_xmin), MIN(st_ymin), MAX(st_xmax), MAX(st_ymax),"
								+ " st_distance(st_point(MIN(st_xmin), MIN(st_ymin)), st_point(MAX(st_xmax), MAX(st_ymax))),"
								+ " CASE WHEN st_distance(st_point(AVG(st_x), AVG(st_y)), st_point(MIN(st_xmin), MIN(st_ymin)))"
								+ " > st_distance(st_point(AVG(st_x), AVG(st_y)), st_point(MIN(st_xmax), MIN(st_ymax)))"
								+ " THEN st_distance(st_point(AVG(st_x), AVG(st_y)), st_point(MIN(st_xmin), MIN(st_ymin)))"
								+ " ELSE st_distance(st_point(AVG(st_x), AVG(st_y)), st_point(MIN(st_xmax), MIN(st_ymax))) END FROM UBI;";
					}
					stmt = conn.prepareStatement(sql);
					rs = stmt.executeQuery();
					
					if (rs.next()) {
						if (rs.getString(1) != null) {
							extent = new String[9];
							
							extent[0] = rs.getString(1);
							extent[1] = rs.getString(2);
							extent[2] = rs.getString(3);
							extent[3] = rs.getString(4);
							extent[4] = rs.getString(5);
							extent[5] = rs.getString(6);
							if (codigoCatastral.length() == claveCatastral.getLengthClaveCatastral()) {
								extent[6] = "ClaveCatastral";
								extent[7] = rs.getString(7);
								extent[8] = rs.getString(8);
							} else if (codigoCatastral.length() >= claveCatastral.getLengthPredio()) {
								extent[6] = "Predio";
								extent[7] = rs.getString(7);
								extent[8] = rs.getString(8);
							} else {
								extent[6] = "Parcial";
								extent[7] = "0";
								extent[8] = "0";
							}
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
			}
		}
		return extent;
	}
	
	public BusquedaAvanzada[] busquedaAvanzada() {
		BusquedaAvanzada[] busavs = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			ConexionCatastro conexion = new ConexionCatastro();
			conn = conexion.getConn();
			
			String sql = "SELECT busav_codigo, busav_nombre, busav_descripcion, busav_sql"
					+ " FROM busqueda_avanzada WHERE busav_web = 'SI' ORDER BY busav_nombre, busav_codigo;";
			
			stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery();
			
			int rows = 0;
			int count = 0;
			
			if (rs.last()) {
				rows = rs.getRow();
				rs.beforeFirst();
			}
			
			busavs = new BusquedaAvanzada[rows];
			
			while (rs.next()) {
				BusquedaAvanzada busav = new BusquedaAvanzada();
				
				busav.setBusav_codigo(rs.getInt(1));
				busav.setBusav_nombre(rs.getString(2));
				busav.setBusav_descripcion(rs.getString(3));
				busav.setBusav_sql(rs.getString(4));
				
				busavs[count] = busav;
				count++;
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
		return busavs;
	}
}