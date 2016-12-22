package database;

import java.sql.Connection;

import common.GlobalVariables;

public class MySQLConnector {
	static public Connection getConnection() {
		ConnectionMaker con = new ConnectionMaker();
		return con.getConnection(GlobalVariables.CLASSNAME_MYSQL, GlobalVariables.URL_MYSQL, 
				GlobalVariables.USERNAME_MYSQL, GlobalVariables.PASSWORD_MYSQL);
	}
}
