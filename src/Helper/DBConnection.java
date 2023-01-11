package Helper;

import java.sql.*;

public class DBConnection {
	Connection c = null;

	public DBConnection() {

	}

	public Connection conDb() {
		try {
			this.c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospitalDb", "postgres", "12345");
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
}
