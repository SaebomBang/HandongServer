package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMaker {
	public Connection getConnection(String className, String url,
			String username, String password) {
		Connection con = null;

		try {
			Class.forName(className);
			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			System.out.println("Do not find driver");
		} catch (SQLException ex) {
			System.out.println("Uncomplete Connection");
		}
		return con;
	}
}
