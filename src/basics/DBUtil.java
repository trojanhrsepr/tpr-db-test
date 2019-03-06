package basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

	private static final String USERNAME = "trojanhrse";
	private static final String PASSWORD = "qweasdrf";
	private static final String H_CONN_STRING =
			"jdbc:hsqldb:data/explorecalifornia";
	private static final String M_CONN_STRING =
			"jdbc:mysql://localhost/explorecalifornia";

	public static Connection getConnection(DBType dbType) throws SQLException {
		
		switch (dbType) {
		case MYSQL:
			return DriverManager.getConnection(M_CONN_STRING, USERNAME, PASSWORD);
		case HSQLDB:
			return DriverManager.getConnection(H_CONN_STRING, USERNAME, PASSWORD);
		default:
			return null;
		}
		
	}
	
}
