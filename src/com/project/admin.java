package com.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class admin {
//class for admin to get customer details
	
	public void showAdminData() {
		ConnectionTest con = new ConnectionTest();
		Connection connection=con.getConnectionDetails();
		try {
			PreparedStatement pst =connection.prepareStatement("select* from customerInfo");
			
			
//query to get buyer details
			
			System.out.println("id\tfirstName\tlastName\tmobNo\temail");
			ResultSet rows=pst.executeQuery();
			while(rows.next()) {
		        System.out.println(rows.getInt(1)+"\t"+rows.getString(2)+"\t"+rows.getString(3)+"\t"+rows.getString(4)+"\t"+rows.getString(5));

			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
		
	}

