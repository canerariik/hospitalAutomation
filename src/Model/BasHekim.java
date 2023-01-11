package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BasHekim extends Users {

	public BasHekim(int id, String tcno, String psw, String name, String type) {
		super(id, tcno, psw, name, type);
	}

	public BasHekim() {

	}

	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;

	public ArrayList<Users> getDoctorList() throws SQLException {
		ArrayList<Users> list = new ArrayList<>();
		Connection con = conn.conDb();
		Users obj;

		try {

			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM users WHERE type = 'doktor'");
			while (rs.next()) {
				obj = new Users(rs.getInt("id"), rs.getString("tcno"), rs.getString("psw"), rs.getString("name"),
						rs.getString("type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean addDoktor(String tcno, String psw, String name) throws SQLException {
		String query = "INSERT INTO users" + "(tcno,psw,name,type) VALUES" + "(?,?,?,?)";
		Connection con = conn.conDb();
		boolean key = false;

		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setString(1, tcno);
			ps.setString(2, psw);
			ps.setString(3, name);
			ps.setString(4, "doktor");
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

	public boolean deleteDoktor(int id) throws SQLException {
		String query = "DELETE FROM users WHERE id = ?";
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

	public boolean updateDoktor(int id, String tcno, String psw, String name) throws SQLException {
		String query = "UPDATE users SET name = ?, tcno = ?, psw = ? WHERE id = ?";
		Connection con = conn.conDb();
		boolean key = false;

		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setString(1, name);
			ps.setString(2, tcno);
			ps.setString(3, psw);
			ps.setInt(4, id);

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

	public boolean addWorker(int user_id, int clinic_id) throws SQLException {
		String query = "INSERT INTO worker" + "(user_id, clinic_id) VALUES" + "(?,?)";
		Connection con = conn.conDb();
		boolean key = false;
		int count = 0;

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM worker WHERE clinic_id=" + clinic_id + "AND user_id=" + user_id);

			while (rs.next()) {
				count++;
			}
			if (count == 0) {
				ps = con.prepareStatement(query);
				ps.setInt(1, user_id);
				ps.setInt(2, clinic_id);
				ps.executeUpdate();
			}

			key = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (key)
			return true;
		else
			return false;
	}

	public ArrayList<Users> getClinicDoctorList(int clinic_id) throws SQLException {
		ArrayList<Users> list = new ArrayList<>();
		Connection con = conn.conDb();
		Users obj;

		try {
			st = con.createStatement();
			rs = st.executeQuery(
					"SELECT u.id, u.tcno, u.psw, u.name, u.type FROM worker w LEFT JOIN users u ON w.user_id = u.id WHERE clinic_id="
							+ clinic_id);
			while (rs.next()) {
				obj = new Users(rs.getInt("id"), rs.getString("tcno"), rs.getString("psw"), rs.getString("name"),
						rs.getString("type"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
