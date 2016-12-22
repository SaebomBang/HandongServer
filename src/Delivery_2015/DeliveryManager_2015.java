package Delivery_2015;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import database.MySQLConnector;

public class DeliveryManager_2015 {
	public Vector<delivery_2015> getAllDelivery() {
		Connection con = MySQLConnector.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;

		Vector<delivery_2015> deliveryVector = new Vector<delivery_2015>();

		try {
			st = con.prepareStatement("SELECT * FROM 18th.delivery_2015");
			rs = st.executeQuery();

			while (rs.next()) {
				delivery_2015 pro = new delivery_2015();
				
				pro.setId(rs.getString(1));
				pro.setName(rs.getString(2));
				pro.setPhone(rs.getString(3));
				pro.setRunTime(rs.getString(4));
				pro.setCategory(rs.getString(5));

				deliveryVector.addElement(pro);
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
		return deliveryVector;
	}
}
