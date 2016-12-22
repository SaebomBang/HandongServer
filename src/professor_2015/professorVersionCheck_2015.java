package professor_2015;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.MySQLConnector;

public class professorVersionCheck_2015 {
	public String getVersionByDB() {
		String version = null;
		
		Connection con = MySQLConnector.getConnection();

		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = con.prepareStatement("SELECT vResult FROM 18th.professor");
			rs = st.executeQuery();

			if (rs.next()) 
				return rs.getString(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return version;
	}
}
