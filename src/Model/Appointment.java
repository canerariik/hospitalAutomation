package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Appointment {

	private int id;
	private int doctorId;
	private int hastaId;
	private String doctorName;
	private String hastaName;
	private String appDate;

	Statement st = null;
	ResultSet rs = null;
	PreparedStatement ps = null;
	DBConnection conn = new DBConnection();

	public Appointment() {

	}

	public Appointment(int id, int doctorId, int hastaId, String doctorName, String hastaName, String appDate) {
		super();
		this.id = id;
		this.doctorId = doctorId;
		this.hastaId = hastaId;
		this.doctorName = doctorName;
		this.hastaName = hastaName;
		this.appDate = appDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getHastaId() {
		return hastaId;
	}

	public void setHastaId(int hastaId) {
		this.hastaId = hastaId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getHastaName() {
		return hastaName;
	}

	public void setHastaName(String hastaName) {
		this.hastaName = hastaName;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public ArrayList<Appointment> getHastaList(int hasta_id) throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Connection con = conn.conDb();
		Appointment obj;

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment WHERE hasta_id = " + hasta_id);
			while (rs.next()) {
				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctorId(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setHastaId(rs.getInt("hasta_id"));
				obj.setHastaName(rs.getString("hasta_name"));
				obj.setAppDate(rs.getString("app_date"));
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

	public ArrayList<Appointment> getDoctorList(int doctor_id) throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Connection con = conn.conDb();
		Appointment obj;

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment WHERE doctor_id = " + doctor_id);
			while (rs.next()) {
				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setDoctorId(rs.getInt("doctor_id"));
				obj.setDoctorName(rs.getString("doctor_name"));
				obj.setHastaId(rs.getInt("hasta_id"));
				obj.setHastaName(rs.getString("hasta_name"));
				obj.setAppDate(rs.getString("app_date"));
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

}
