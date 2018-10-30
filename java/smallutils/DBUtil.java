package javawebframework;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

	private static String url = "jdbc:mysql://10.10.9.94:3306/imes_db_web?characterEncoding=utf8&allowMultiQueries=true&autoReconnect=true";
	private static String user = "root";
	private static String password = "admin@123";
	private static Connection conn = null;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return conn;
	}
}
