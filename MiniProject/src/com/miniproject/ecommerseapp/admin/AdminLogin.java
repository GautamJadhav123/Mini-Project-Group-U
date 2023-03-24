package com.miniproject.ecommerseapp.admin;

import java.util.Scanner;	

public class AdminLogin {
	
	public static void defualtAdmin() {
		
			final String Username = "Admin";
			final String Password = "Admin" ;
			Scanner sc = new Scanner (System.in);
		
			System.out.println("Enter the Admin details");
			System.out.println("Enter Admin Username");
			String UsernameAdmin = sc.next();
			
			System.out.println("Enter the Admin Passowrd");
			String PasswordAdmin = sc.next();
		
			if 	(UsernameAdmin.equals(Username) && PasswordAdmin.equals(Password))
				{
				OperationsDirection.getAdminOperations();
				}
			else
				{
				System.out.println("Incorrect details \n" + "Access denied");
				}	
			}
	
		}
	