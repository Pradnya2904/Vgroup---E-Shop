package com.project;

import java.util.Scanner;

public class User {
	
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		
		RegisterInfo reg = new RegisterInfo();
		System.out.println("Enter first name");
		String firstName=sc.next();
		
		System.out.println("Enter last name");
		String lastName= sc.next();
		
		System.out.println("Enter mobile number");
		long mobNo=sc.nextLong();
		
		System.out.println("Enter email address");
		String email = sc.next();
		
		System.out.println("Enter password");
		String password= sc.next();
		
		reg.registerInfo(firstName, lastName, mobNo, email, password);
		
	}
	

}
