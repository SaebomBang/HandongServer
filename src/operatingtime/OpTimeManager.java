package operatingtime;

import database.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * @author YoungKwang Han
 * @email juanaevv@nate.com
 * @classname OperatingTimeManager.java
 * @package HandongServer.src.operatingtime
 * @date 2014. 12. 30. 3:19 pm
 * @purpose : 
 *
 * @comment : 
 * 
 * 
 *
 */

public class OpTimeManager {
	public Vector<OpTime> getOpTime() {

		Connection con = null;

		con = MySQLConnector.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;

		Vector<OpTime> otVector = new Vector<OpTime>();

		try {

			st = con.prepareStatement("SELECT name, runtime, note, version FROM 18th.operatingtime WHERE version = '1521' Order by name ;");
			rs = st.executeQuery();

			while (rs.next()) {
				otVector.addElement(new OpTime(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException sqex) {
			System.out.println("SQLException: " + sqex.getMessage());
			System.out.println("SQLState: " + sqex.getSQLState());
		} finally{
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(con != null) con.close();
			} catch(SQLException e) {
				//예외처리 필요
			}
		}
		
		return otVector;
	}

	public String getVersion(){
		Connection con = MySQLConnector.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		String query = "SELECT version FROM 18th.operatingtime WHERE id = 2;";

		try{
			st = con.prepareStatement(query);
			rs = st.executeQuery();

			if(rs.next()){
				return rs.getString(1);
			}

		} catch (SQLException e) {
		} finally {
			try {
				if(rs != null) rs.close();
				if(st != null) st.close();
				if(con != null) con.close();
			} catch(SQLException e) {
			}
		}
		return "none";
	}
}