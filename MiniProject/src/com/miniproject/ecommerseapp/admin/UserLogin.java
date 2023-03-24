package com.miniproject.ecommerseapp.admin;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

	public class UserLogin
		{
			static String UserName ;
			static String Password ;
		
			public String getUserName() {
				return UserName;
			}
			public String getPassword() {
				return Password;
			}

			public void setUserName(String userName) {
				UserName = userName;
			}

			public void setPassword(String password) {
				Password = password;
			}

			
			public static void getUserLogin()
				{
					Scanner scanner=new Scanner(System.in);
					User user = new User();
					UserLogin ul = new UserLogin();
					
					System.out.println("Please Enter the following Details \n");
					
					try 
					 {
						System.out.println("Enter your Username:");
						String UserName = scanner.next();
						String dbUserName = "";
						ul.setUserName(UserName);
						
						System.out.println("Enter your Password:");
						String Password = scanner.next();
						String dbPassword ="";
						ul.setPassword(Password);
	
							PreparedStatement ps1 = null;
							PreparedStatement ps2 = null;
							Connection con= CommonConnection.getConnectionObject();
					
							ps1 = con.prepareStatement("select Username from user where UserName = ?;");
							ps1.setString(1,UserName);
							ResultSet rs1=ps1.executeQuery();
							while (rs1.next()) 
								{
									dbUserName = rs1.getString(1);
								}
							
							ps2	=	con.prepareStatement("select Password from user where Password =?  AND Username =?;" );
							ps2.setString(1,Password);
							ps2.setString(2, UserName);
							ResultSet rs2 = ps2.executeQuery();
							while (rs2.next())
								{
									dbPassword = rs2.getString(1);
								}
							
					if		((UserName.equals(dbUserName)) && (Password.equals(dbPassword)))
								{
									System.out.println("login successfull\n");
									
									System.out.println("Do you want to view products ? (yes/no)");
									String ans = scanner.next();
									if 		(ans.equalsIgnoreCase("yes")) 
										{
											user.getProductDetails();
										}
									else if	(ans.equalsIgnoreCase("no"))
										{
											System.out.println("thanks for visiting...");
										}
								}
				   else 			System.out.println("Username or Password is incorrect\n" + "login Unsuccessfull");
					 	}					
				   catch 	(SQLException e)
						{
					   				e.printStackTrace();
						}
					
				}
			
			
	public static void checkUserDetails() {
		
		Scanner sc = new Scanner(System.in);
		Connection con;
		
		System.out.println("Enter the Username :- ");
		String username = sc.next();
		
		
		try 
		 {
			con = CommonConnection.getConnectionObject();
			PreparedStatement ps = con.prepareStatement("Select Userid, UserName, City, Emailid, MobileNumber from user where UserName = ?");
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) 
			{
			int userid = rs.getInt(1);
			String Username = rs.getString(2);
			String city = rs.getString(3);
			String emailid = rs.getString(4);
			long mobileno = rs.getLong(5);
			
			String s = String.format("User Id = %-5s UserName = %-10s City = %-10s Email Id = %-30s MobileNo. = %-15s \n", userid, Username, city, emailid, mobileno);

			if (s.equalsIgnoreCase(""))
			{
				System.out.println("UserName not found");
			}
			else
				System.out.println(s);
			}
		 }
		catch 	(SQLException e)
		 {
				e.printStackTrace();
		 }
	}
}
