package professor_2015;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import database.MySQLConnector;

public class professorManager_2015 {
	public Vector<professor_2015> getAllProfessor() {
		Connection con = MySQLConnector.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;

		Vector<professor_2015> professorVector = new Vector<professor_2015>();

		try {
			st = con.prepareStatement("SELECT * FROM 18th.professor");
			rs = st.executeQuery();

			while (rs.next()) {
				professor_2015 pro = new professor_2015();

				pro.setName(rs.getString(1));
				pro.setMajor(rs.getString(2));
				pro.setPhone(rs.getString(3));
				pro.setEMail(rs.getString(4));
				pro.setOffice(rs.getString(5));

				professorVector.addElement(pro);
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
		return professorVector;
	}
}
