package yasickMenu_2015;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import database.MySQLConnector;

public class YasickMenuList {
	public Vector<yasickMenu_2015> getAllYasickMenu(String storeName) {
		
		Connection con = MySQLConnector.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;

		Vector<yasickMenu_2015> yasickVector = new Vector<yasickMenu_2015>();

		try {
			st = con.prepareStatement("SELECT * FROM yasick."+storeName);
			rs = st.executeQuery();

			while (rs.next()) {
				yasickMenu_2015 yasic = new yasickMenu_2015();

				yasic.setHoliday(rs.getString(1));
				yasic.setRunTime(rs.getString(2));
				yasic.setSpecial(rs.getString(3));
				yasic.setName(rs.getString(4));
				yasic.setPrice(rs.getString(5));
				yasic.setSets(rs.getString(6));

				yasickVector.addElement(yasic);
			}
		} catch (SQLException e) {
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {

			}
		}
		return yasickVector;
	}
	
	public String getVersionByDB(String storeName) {
		String version = null;
		
		Connection con = MySQLConnector.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = con.prepareStatement("SELECT vResult FROM yasick." + storeName+ " WHERE id=1");
			rs = st.executeQuery();

			if (rs.next()) 
				return rs.getString(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return version;
	}
}
