package com.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDataInsert {
	// Method for add to cart	
	public void userData(int prodId,int custId,int prodQty) {
		
		ConnectionTest test =new ConnectionTest();
		Connection connection=test.getConnectionDetails();
		
	    try {
	    	
	// Display Product Details
			PreparedStatement pst=connection.prepareStatement("select *from productDetails where prodId =?");
			pst.setInt(1, prodId);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
	// Insert Data into cart by Product id and user id 
				PreparedStatement ps=connection.prepareStatement("select cartId from userCart  where productid=? and custId=?");
				ps.setInt(1, rs.getInt(1));
				ps.setInt(2, custId);
				ResultSet rs1=ps.executeQuery();
				
	// Checking if product is already present or not if present update quantity in cart   			
				boolean productPresent = true;
				while(rs1.next()) {
    				ps=connection.prepareStatement("update userCart set prodQty=prodQty+?  where productId=? and custId=?");
        			ps.setInt(1, prodQty);
    				ps.setInt(2, rs.getInt(1));
        			ps.setInt(3, custId);
        			int i=ps.executeUpdate();
        			
        			if(i>0) {
         				System.out.println("cart updated");
				
     // Updating Product Table Quantities             				
         				productPresent=false;
         				ps=connection.prepareStatement("update productdetails set quantity=quantity-? where productid=?");
             			ps.setInt(2, rs.getInt(1));
             			ps.setInt(1, prodQty);
             			int j=ps.executeUpdate();
        			}
         			}
				
				if(productPresent) {
     				ps=connection.prepareStatement("insert into UserCart(prodId,custId,prodQty) values(?,?,?)");
         			ps.setInt(1, rs.getInt(1));
         			ps.setInt(2, custId);
         			ps.setInt(3, prodQty);
         			int i=ps.executeUpdate();
         			if(i>0) {
         				System.out.println("added data");
         				
             			ps=connection.prepareStatement("update productDetails set prodQty=prodQty-? where prodId=?");
             			ps.setInt(2, rs.getInt(1));
             			ps.setInt(1, prodQty);
             			int j=ps.executeUpdate();
         			}else {
         				System.out.println("not added data");
         			}
         			rs.close();
         			ps.close();
         			connection.close();
			}	}}
		 catch (SQLException e) {
			e.printStackTrace();
		}
	

}}
