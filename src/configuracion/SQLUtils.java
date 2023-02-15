package configuracion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLUtils {
	
	public static void closeQuietly(Connection connection) {
	    try {
	    	if (connection != null) {
	    		connection.close();
	    	}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	}
	
	public static void closeQuietly(Statement statement) {
	    try {
	    	if (statement != null) {
	    		statement.close();
	    	}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	}
	
	public static void closeQuietly(ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
	    } catch (SQLException e) {
	    	e.printStackTrace();
	    }
	}
}