package hguShop;

import database.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class HguShopManager {
	public Vector<HguShop> getHguShop() {

		Connection con = null;

		con = MySQLConnector.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;

		Vector<HguShop> hsVector = new Vector<HguShop>();

		try {

			st = con.prepareStatement("SELECT location, category, name, contents, coordinate, img, phone FROM 18th.hgushop ;");
			rs = st.executeQuery();

			while (rs.next()) {
				hsVector.addElement(new HguShop(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
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
		
		return hsVector;
	}

	public String getVersion(){
		Connection con = MySQLConnector.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "SELECT version FROM 18th.hgushop WHERE id = 1;";

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
