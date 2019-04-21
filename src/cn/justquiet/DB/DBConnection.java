package cn.justquiet.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private String driver = "";
	private String dbURL = "";
	private String user = "";
	private String password = "";
	private static DBConnection factory = null;

	private DBConnection() throws Exception {
		driver = "com.mysql.jdbc.Driver";
		dbURL = "jdbc:mysql://localhost:3306/livejq?useUnicode=true&characterEncoding=UTF-8";
		user = "gzhmt";
		password = "rj17208zhl";
	}

	public static Connection getConnection() {
		Connection conn = null;
		if (factory == null) {
			try {
				factory = new DBConnection();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		try {
			Class.forName(factory.driver);
			conn = DriverManager.getConnection(factory.dbURL, factory.user,
					factory.password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
