package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {
	Connection connection = null;
	
	public Connection getConnectionDetails() {   
		
		//connecting database with java using JDBC;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url="jdbc:mysql://localhost:3306/projectdb?characterEncoding=utf8";
			
			 connection=DriverManager.getConnection(url, "root", "root");
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
		
	}
	

}
