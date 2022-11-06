package com.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.project.ConnectionTest;

public class ShowCart {
	
	public void showCart(int custId) {
		
		ConnectionTest test = new ConnectionTest();
		Connection connection=test.getConnectionDetails();
		PreparedStatement ps;
		try {
			ps = connection.prepareStatement("select p.prodId,p.prodDescription,p.prodName,u.prodQty,p.prodPrize,u.prodQty*p.prodPrize as total"
					+ " from userCart u ,productDetails p   where userId=? and u.productid=p.productid");
			ps.setInt(1,custId);
			ResultSet rs=ps.executeQuery();
			System.out.println("productid\tprodDescreption\tprodName\tprodQty\tprodPrize\t\ttotal");
			long carttotal=0;
//Printing cart details			
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getInt(4)+"\t\t"+rs.getInt(5)+
						"\t\t"+rs.getInt(6));

				carttotal=carttotal+rs.getInt(6);

			}

			System.out.println("carttotal="+carttotal);
					
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
	}

}
