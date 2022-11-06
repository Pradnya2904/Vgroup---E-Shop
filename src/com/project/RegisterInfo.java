package com.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//class to get information of customer
public class RegisterInfo {

	//method to get information of customer
	public void registerInfo(String firstName ,String lastName ,long mobNo,String email, String password) {
		ConnectionTest test = new ConnectionTest();
		Connection connection= test.getConnectionDetails();
		
		String sqlQuery="insert into customerInfo (firstName,lastName,mobNo,email,password)values (?,?,?,?,?)";
		try {
			PreparedStatement pst=connection.prepareStatement(sqlQuery);
			pst.setString(1, firstName);
			pst.setString(2, lastName);
			pst.setLong(3, mobNo);
			pst.setString(4, email);
			pst.setString(5, password);
			pst.close();
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
