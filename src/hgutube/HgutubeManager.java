package hgutube;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import database.MySQLConnector;

public class HgutubeManager {
	public Vector<Hgutube> getHgutube() {

		Connection con = null;

		con = MySQLConnector.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;

		Vector<Hgutube> pbVector = new Vector<Hgutube>();

		try {

			st = con.prepareStatement("SELECT category, url, title, writer, time FROM 18th.hgutube ORDER BY id DESC ;");
			rs = st.executeQuery();

			while (rs.next()) {
				pbVector.addElement(new Hgutube(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		}finally{
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(con != null) con.close();
			} catch(SQLException e) {
				//예외처리 필요
			}
		}
		
		return pbVector;
	}

	public String getVersion(){
		Connection con = MySQLConnector.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "SELECT version FROM 18th.hgutube WHERE id = 1;";

		try{
			st = con.prepareStatement(query);
			rs = st.executeQuery();

			if(rs.next()){
				return rs.getString(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "null";
	}
}
