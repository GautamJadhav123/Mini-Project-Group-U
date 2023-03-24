package com.miniproject.ecommerseapp.admin;



import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UserRegistration {
	
	public static void getInsertRecord(String firstname,String lastname,String username,String password,String city,String emailid,long mobilenumber)
		{	
			try
			{
				Connection connection=CommonConnection.getConnectionObject();
				PreparedStatement preparestatement=connection.prepareStatement("insert into User(firstname,lastname,username,password,city,emailid,mobilenumber) values(?,?,?,?,?,?,?)");
			
				preparestatement.setString(1, firstname);
				preparestatement.setString(2, lastname);
				preparestatement.setString(3, username);
				preparestatement.setString(4, password);
				preparestatement.setString(5, city);
				preparestatement.setString(6, emailid);
				preparestatement.setLong(7, mobilenumber);
			
				if 	(preparestatement.execute())
				{
					System.out.println("registration is successful...");
				}	
			} 
			catch 	(SQLException e) 
			{			
					System.out.println(e.getMessage());
					System.out.println("Username already exists\n" + "Registration Unsuccessful.");
			}
		}
	
	
	public static void getUserRegistrationDetails()
		{

			User user1 = new User();
			Scanner sc=new Scanner(System.in);
			UserDetails user=new UserDetails();
	
				System.out.println("-------------------------------------------------------------------------------------------------");
				System.out.println("Please Enter Registration Details:-");
				System.out.println("-------------------------------------------------------------------------------------------------");
	
				System.out.println("Enter the first name:-");
				String firstname=sc.next();
				user.setFirstname(firstname);
	
				System.out.println("Enter the last name:-");
				String lastname=sc.next();
				user.setLastname(lastname);
	
				System.out.println("Enter the username:-");
				String username=sc.next();
				user.setUsername(username);
	
				System.out.println("Enter the password:-");
				String password=sc.next();
				user.setPassword(password);
	
				System.out.println("Enter the city:-");
				String city=sc.next();
				user.setCity(city);
	
				System.out.println("Enter the emailid:-");
				String emailid=sc.next();
				user.setEmailid(emailid);
	
				System.out.println("Enter the mobileno:-");
				long mobilenumber=sc.nextLong();
				user.setMobilenumber(mobilenumber);
	
				getInsertRecord(firstname,lastname,username,password,city,emailid,mobilenumber);
				
				System.out.println("Welcome, You have Registered Succesfully...\n");
				
				System.out.println("Do you want to view products ? (yes/no)");
				String ans = sc.next();
				if 		(ans.equalsIgnoreCase("yes")) 
					{
						user1.getProductDetails();
					}
				else if	(ans.equalsIgnoreCase("no"))
					{
						System.out.println("thanks for visiting...");
					}
				
		}
	
}
