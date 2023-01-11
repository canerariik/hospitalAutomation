package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Clinic {

	private int id;
	private String name;

	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	DBConnection conn = new DBConnection();

	public Clinic() {

	}

	public Clinic(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Clinic> getClinicList() throws SQLException {
		ArrayList<Clinic> list = new ArrayList<>();
		Connection con = conn.conDb();
		Clinic obj;

		try {
			st = con.createStatement();
			rs = st.executeQuery("Select * From clinic");
			while (rs.next()) {
				obj = new Clinic(rs.getInt("id"), rs.getString("name"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}
		return list;
	}

	public Clinic getFetch(int id) {
		Connection con = conn.conDb();
		Clinic obj = new Clinic();

		try {
			st = con.createStatement();
			rs = st.executeQuery("Select * From clinic WHERE id =" + id);
			while (rs.next()) {
				obj = new Clinic(rs.getInt("id"), rs.getString("name"));
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return obj;
	}

	public boolean addClinic(String name) throws SQLException {
		String query = "INSERT INTO clinic" + "(name) VALUES" + "(?)";
		Connection con = conn.conDb();
		boolean key = false;

		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setString(1, name);

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

	public boolean deleteClinic(int id) throws SQLException {
		String query = "delete from clinic WHERE id = ?";
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

	public boolean updateClinic(int id, String name) throws SQLException {
		String query = "UPDATE clinic SET name = ? WHERE id = ?";
		Connection con = conn.conDb();
		boolean key = false;

		try {
			st = con.createStatement();
			ps = con.prepareStatement(query);
			ps.setString(1, name);
			ps.setInt(2, id);

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
