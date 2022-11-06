package com.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.Product.InsertProduct;
import com.Product.ProductData;
import com.Product.ShowCart;

public class Main {

	public static void main(String[] args) {
		int j=111;
		String ch;
		String prodDescription,prodName;
		int prodPrize, prodQty;
		int userId,cartId=0;
		
		UserDataInsert u = new UserDataInsert();
		admin ad = new admin();
		ConnectionTest test = new ConnectionTest();
		InsertProduct ip =new InsertProduct();
		ProductData pd = new ProductData();
		RegisterInfo re = new RegisterInfo();
		ShowCart show= new ShowCart();
		
		Connection connection=test.getConnectionDetails();
		Scanner scanner =new Scanner (System.in);
		do {
			System.out.println("Welcome !");
			System.out.println("\nEnter 1 for Admin \nEnter 2 for Buyer");
			int i = scanner.nextInt();
			
//admin entry
			if(i==1) {
				System.out.println("Enter password>>");
				int k=scanner.nextInt();
				
				
// Checking Admin password
				if(j==k) {
					System.out.println("\nEnter 1 to Insert product details \nEnter 2 to check product quantity \nEnter 3 to check user details");
					int n=scanner.nextInt();
					switch(n) {
					case 1:
						System.out.println("\nEnter number of products you wish to enter");
						int num = scanner.nextInt();
						for( i=0;i<num;i++) {
							System.out.println("Enter Product Descreption");
							prodDescription=scanner.next();

							System.out.println("Enter Product Name");
							prodName=scanner.next();

							System.out.println("Enter Prize");
							prodPrize=scanner.nextInt();

							System.out.println("Enter Quantity");
							prodQty=scanner.nextInt(); 
							ip.insertProduct(prodDescription, prodName, prodQty, prodPrize);
				}
						break;
// Checking Product Quantities						
                  	case 2:
                  		PreparedStatement ps;
						try {
							ps = connection.prepareStatement("select * from productDetails  order by prodName asc");
							ResultSet rs=ps.executeQuery();
							System.out.println("prodId\t prodDescription\t prodName\t prodPrize\t prodQty");
							while(rs.next()) {
								System.out.println(rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t"+rs.getInt(4)+"\t\t"+rs.getInt(5));
	}

						} catch (SQLException e) {
							e.printStackTrace();
						}
						
						break;
//to check customer data
                    case 3:
                    	    ad.showAdminData();
							System.out.println("Enter custId");
							userId=scanner.nextInt();
							show.showCart(userId);
							break;
					}
					}
					else {
						System.out.println("Invalid Admin");
					}
				}
			// customer Entry 			
						else if(i == 2) {
							System.out.println("\nEnter 1 for Register \nEnter 2 for Login");
							int choice = scanner.nextInt();
							switch(choice) {
			// For New customer			
							case 1: 
								System.out.println("Enter Firstname");
								String firstName=scanner.next();

								System.out.println("Enter Lastname");
								String lastName=scanner.next();

								System.out.println("Enter Emailaddress");
								String email=scanner.next();
								
								System.out.println("Enter mobile number");
								long mobNo=scanner.nextLong();
								
								System.out.println("Enter Password");
								String password=scanner.next();
								
                                 re.registerInfo(firstName, lastName, mobNo, email, password);
								PreparedStatement ps;
								try {
									ps = connection.prepareStatement("select userid from register where emailaddress=? and password=?");
									ps.setString(1 , email);
									ps.setString(2 , password);
									ResultSet rs;
									rs = ps.executeQuery();
									while(rs.next()) {
										userId = rs.getInt(1);
									}
							} catch (SQLException e) {
								
								}

								pd.productDetails();
								System.out.println("\n 1.add to cart\n 2.showcart");
								int n=scanner.nextInt();

// Giving Add to cart and Show cart options
								if(n==1 || n==2) {
									switch(n) {
									case 1:do {
										pd.productDetails();
										System.out.println("Enter above product id that you wish to buy");
										int prodId=scanner.nextInt();
										System.out.println("Enter Quantity");
										prodQty=scanner.nextInt();
										u.userData(prodId, cartId, prodQty);

										System.out.println("Do You Wish To Continue:enter Y for Yes or N for No");
										 ch=scanner.next();
									}
									while(ch.equalsIgnoreCase("Y"));
									show.showCart(cartId);
									break;
									
									case 2:
										show=new ShowCart();
										show.showCart(cartId);

										break;	
									}
								

							}else {
								System.out.println("Invalid User");
							}
							break;
						}
						System.out.println("\nThank You So Much For Shopping With Us !");
					}

					System.out.println("\nDo You Wish To Continue:enter Y for Yes or N for No");
					ch=scanner.next();
					
		}
		while(ch.equalsIgnoreCase("Y"));
scanner.close();
try {
	connection.close();
} catch (SQLException e) {
	e.printStackTrace();
}
	}
	

}
