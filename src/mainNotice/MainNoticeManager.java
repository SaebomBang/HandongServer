package mainNotice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.MySQLConnector;

public class MainNoticeManager {
	public MainNotice getMainNotice() {

		Connection con = null;

		con = MySQLConnector.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;

		MainNotice not = null;

		try {

			st = con.prepareStatement("SELECT date FROM 18th.mainnotice ;");
			rs = st.executeQuery();

			while (rs.next()) {
				not = new MainNotice(rs.getString(1));
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
		
		return not;
	}

}
