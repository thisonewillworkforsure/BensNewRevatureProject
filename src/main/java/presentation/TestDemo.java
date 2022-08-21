package presentation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String urlString = "jdbc:postgresql://localhost:5432/book";
		final String user = "postgres";
		final String password = "dataword622";

		// TODO Auto-generated method stub
		// Steps for jdbc
		// Step 1 - Load the driver
		try {
			// DrIVER is a class present in a package org.postgress
			// so we specifiy the fully qualified name of the class to be loaded
			Class.forName("org.postgresql.Driver");
			System.out.println("Driver loaded successfully");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Step 2 - establish connection to the database
		// here we provide 3 strings:
		// 1st - url, 2nd - user name 3rd - password
		// for the url it is connection url (protocol, ipaddress, port, credential)
		// <protocol>://<ipaddress>:<port>/<db name>
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(urlString, user, password);

			if (connection != null) {
				System.out.println("yay we are connected");
			}

			// Step 3 - create a statement and execute it
			Statement statement = connection.createStatement();
			// get the query ready and execute it
			String queryString = "SELECT * FROM book_details";
			ResultSet resultSet = statement.executeQuery(queryString);

			while(resultSet.next()) {
				System.out.println(resultSet.getString("book_title"));
			}

			// Step 4 - handle the exception
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
		
		
		
		
		
		
	}

}