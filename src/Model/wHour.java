package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class wHour {

	private int id;
	private int doctor_id;
	private String doctor_name;
	private String wDate;
	private String status;

	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	DBConnection conn = new DBConnection();

	public wHour() {

	}

	public wHour(int id, int doctor_id, String doctor_name, String wDate, String status) {
		this.id = id;
		this.doctor_id = doctor_id;
		this.doctor_name = doctor_name;
		this.wDate = wDate;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	public String getDoctor_name() {
		return doctor_name;
	}

	public void setDoctor_name(String doctor_name) {
		this.doctor_name = doctor_name;
	}

	public String getwDate() {
		return wDate;
	}

	public void setwDate(String wDate) {
		this.wDate = wDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

}
