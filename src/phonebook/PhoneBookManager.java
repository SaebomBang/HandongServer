package phonebook;

import database.*;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author YoungKwang Han
 * @email juanaevv@nate.com
 * @classname PhoneBookManager.java
 * @package HandongServer.src.phonebook
 * @date 2014. 12. 29. 7:04 pm
 * @purpose : 
 *
 * @comment : 
 * 
 * 
 *
 */

public class PhoneBookManager {
	public Vector<PhoneBook> getPhoneBook() {

		Connection con = null;

		con = MySQLConnector.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;

		Vector<PhoneBook> pbVector = new Vector<PhoneBook>();

		try {

			st = con.prepareStatement("SELECT name, phone, category, version FROM 18th.phonebook ;");
			rs = st.executeQuery();

			while (rs.next()) {
				pbVector.addElement(new PhoneBook(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
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
		String query = "SELECT version FROM 18th.phonebook WHERE id = 3;";

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