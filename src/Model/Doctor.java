package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Doctor extends Users {

	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public Doctor() {
		super();
	}

	public Doctor(int id, String tcno, String psw, String name, String type) {
		super(id, tcno, psw, name, type);
	}

	public ArrayList<wHour> getWHourList(int doctor_id) throws SQLException {
		ArrayList<wHour> list = new ArrayList<>();
		wHour obj;

		Connection con = conn.conDb();

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM wHour WHERE status = 'true' AND doctor_id=" + doctor_id);
			while (rs.next()) {
				obj = new wHour(rs.getInt("id"), rs.getInt("doctor_id"), rs.getString("doctor_name"),
						rs.getString("wDate"), rs.getString("status"));

				list.add(obj);

				/*
				 * obj.setId(rs.getInt("id")); obj.setDoctor_id(rs.getInt("doctor_id"));
				 * obj.setDoctor_name(rs.getString("doctor_name"));
				 * obj.setwDate(rs.getString("wDate")); obj.setStatus(rs.getString("status"));
				 * 
				 * list.add(obj);
				 */
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}

	public boolean addwHour(int doctor_id, String doctor_name, String wDate) throws SQLException {
		int key = 0;
		int count = 0;
		Connection con = conn.conDb();

		String query = "INSERT INTO wHour" + "(doctor_id, doctor_name, wDate) VALUES " + "(?,?,?)";

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM wHour WHERE status = 'true' AND doctor_id=" + doctor_id + " AND wDate='"
					+ wDate + "'");

			while (rs.next()) {
				count++;
				break;
			}

			if (count == 0) {
				ps = con.prepareStatement(query);
				ps.setInt(1, doctor_id);
				ps.setString(2, doctor_name);
				ps.setString(3, wDate);
				ps.executeUpdate();
			}

			key = 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (key == 1)
			return true;
		else
			return false;

	}

	public boolean deleteWHour(int id) throws SQLException {
		String query = "DELETE FROM wHour WHERE id = ?";
		Connection con = conn.conDb();
		boolean key = false;

		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);

			ps.executeUpdate();

			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;
	}
}
