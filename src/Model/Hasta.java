package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Helper.*;

public class Hasta extends Users {

	Statement st = null;
	ResultSet rs = null;
	Connection con = conn.conDb();
	PreparedStatement ps = null;

	public Hasta() {

	}

	public Hasta(int id, String tcno, String psw, String name, String type) {
		super(id, tcno, psw, name, type);
	}

	public boolean register(String tcno, String psw, String name, String type) throws SQLException {
		int key = 0;
		int count = 0;

		String query = "INSERT INTO users" + "(tcno, psw, name, type) VALUES " + "(?,?,?,?)";

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM users WHERE tcno = '" + tcno + "' ");

			while (rs.next()) {
				count++;
				Helper.showMsg("Bu TC numarasına ait bir kayıt bulunmaktadır.");
				break;
			}

			if (count == 0) {
				ps = con.prepareStatement(query);
				ps.setString(1, tcno);
				ps.setString(2, psw);
				ps.setString(3, name);
				ps.setString(4, "hasta");
				ps.executeUpdate();
				key = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (key == 1)
			return true;
		else
			return false;

	}

	public boolean addAppointment(int doctor_id, int hasta_id, String doctor_name, String hasta_name, String app_date)
			throws SQLException {
		int key = 0;

		String query = "INSERT INTO appointment" + "(doctor_id, doctor_name, hasta_id, hasta_name, app_date) VALUES " + "(?,?,?,?,?)";

		try {
			ps = con.prepareStatement(query);
			ps.setInt(1, doctor_id);
			ps.setString(2, doctor_name);
			ps.setInt(3, hasta_id);
			ps.setString(4, hasta_name);
			ps.setString(5, app_date);
			ps.executeUpdate();
			key = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (key == 1)
			return true;
		else
			return false;

	}

	public boolean updateWHourStatus(int doctor_id, String w_date) throws SQLException {
		int key = 0;

		String query = "UPDATE whour SET status = ? WHERE doctor_id = ? AND wdate = ? ";

		try {
			ps = con.prepareStatement(query);
			ps.setBoolean(1, false);
			ps.setInt(2, doctor_id);
			ps.setString(3, w_date);
			ps.executeUpdate();
			key = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (key == 1)
			return true;
		else
			return false;

	}

}
