package yasickMenu_2015;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import database.MySQLConnector;

public class callCount {
	public callCount() {
	}

	// 기존에 저장되어있던 count수를 측정
	public int bringBeforeCount(String storeName) {
		Connection con = MySQLConnector.getConnection();
		PreparedStatement st = null;
		ResultSet rs = null;
		int beforeCount = 0;
		try {
			st = con.prepareStatement("SELECT * FROM yasick.callcount");
			rs = st.executeQuery();
			while (rs.next()) {
				beforeCount = rs.getInt(storeName);
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
		return beforeCount;
	}

	/*
	 * public static void main(String[] args) { callCount cc = new callCount();
	 * int ccc = cc.bringBeforeCount("boore");
	 * 
	 * System.out.println(ccc); }
	 */
	public Vector<cCount> countCall(String storeName, int countNum) {
		Connection con = MySQLConnector.getConnection();
		Statement stmt = null;
		PreparedStatement prest = null;
		ResultSet rs = null;

		int beforecount = bringBeforeCount(storeName);
		int newcount = beforecount + countNum;

		Vector<cCount> countVector = new Vector<cCount>();
		try {

			stmt = con.createStatement();

			String dbCommand = "UPDATE yasick.callcount SET " + storeName + "="
					+ newcount + " where id=1";

			stmt.executeUpdate(dbCommand);

			rs = stmt.executeQuery("SELECT * FROM yasick.callcount");

			rs.beforeFirst();

			while (rs.next()) {
				cCount ccount = new cCount();

				ccount.setBurgerhills(rs.getString("burgerhills"));
				ccount.setChiko(rs.getString("chiko"));
				ccount.setMinsungbanjum(rs.getString("minsungbanjum"));
				ccount.setDdangddang(rs.getString("ddangddang"));
				ccount.setBoore(rs.getString("boore"));
				ccount.setSamchonne(rs.getString("samchonne"));
				ccount.setYukgane(rs.getString("yukgane"));
				ccount.setJibung(rs.getString("jibung"));
				ccount.setDondoni(rs.getString("dondoni"));
				ccount.setSsal(rs.getString("ssal"));
				ccount.setTiba(rs.getString("tiba"));
				ccount.setOlive(rs.getString("olive"));
				ccount.setHosiki(rs.getString("hosiki"));
				ccount.setMinsungbabjip(rs.getString("minsungbabjip"));
				ccount.setThejjim(rs.getString("thejjim"));
				ccount.setMichu(rs.getString("michu"));

				countVector.add(ccount);
			}
		} catch (SQLException e) {
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
				if (prest != null)
					prest.close();
			} catch (SQLException e) {

			}
		}
		return countVector;
	}
}