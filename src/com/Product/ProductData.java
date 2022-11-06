package com.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.ConnectionTest;

//method to see product into ascending order

public class ProductData {
	public void productDetails() {
		
		ConnectionTest con = new ConnectionTest();
		Connection connection=con.getConnectionDetails();
		
		//ascending order query
		try {
			PreparedStatement pst=connection.prepareStatement("select*from productDetails order by name asc");
			ResultSet rs=pst.executeQuery();
			System.out.println("prodId \t prodDescription \t prodName \t prodQty \t prodPrize");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getInt(4)+"\t"+rs.getInt(5));
			}
			connection.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
}
