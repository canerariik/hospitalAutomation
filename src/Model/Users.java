package Model;

import Helper.DBConnection;

public class Users {

	private int id;
	private String tcno;
	private String psw;
	private String name;
	private String type;

	DBConnection conn = new DBConnection();

	public Users() {

	}

	public Users(int id, String tcno, String psw, String name, String type) {
		super();
		this.id = id;
		this.tcno = tcno;
		this.psw = psw;
		this.name = name;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTcno() {
		return tcno;
	}

	public void setTcno(String tcno) {
		this.tcno = tcno;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
