package com.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.project.ConnectionTest;

//Class for inserting data from Admin into Database

public class InsertProduct {

	// Method to insert data into Database

	public void insertProduct( String prodDescription ,String prodName,int prodQty ,int prodPrize ) {
		ConnectionTest test = new ConnectionTest();
		Connection connection=test.getConnectionDetails();
		
		try {
			PreparedStatement pst=connection.prepareStatement("insert into productDetails (prodDescription,prodName,prodQty,prodPrize)values (?,?,?,?)");
			pst.setString(1, prodDescription);
			pst.setString(2, prodName);
			pst.setLong(3, prodQty);
			pst.setInt(4, prodPrize);
			int r=pst.executeUpdate();
			System.out.println("insert data>>"+r);
			
			connection.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
