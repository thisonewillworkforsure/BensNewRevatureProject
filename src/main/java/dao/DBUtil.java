package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	static Connection connection;
		
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static Connection makeConnection() {
		String urlString = "jdbc:postgresql://localhost:5432/bank";
		String userString = "postgres";
		String passwordString = "dataword622";
		
		try {
			if(connection == null) {
			connection = DriverManager.getConnection(urlString,userString,passwordString);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}