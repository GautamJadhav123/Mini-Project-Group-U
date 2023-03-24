package com.miniproject.ecommerseapp.admin;

import java.util.Scanner;

public class EcommerceApplication {
	
	

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nWelcome to Ecommerce Application...\n");
		
		String s1 = "User";
		String s2 = "Admin";
		String s3 = "Guest";
		
		System.out.println("Choose the following operation");
		System.out.printf("1 %-10s 2 %-10s 3 %-10s\n", s1, s2, s3);
		
		int choice = sc.nextInt();
		
		switch (choice)
		{
		
		case 1 : OperationsDirection.getUserOperations();
			break;
		
		case 2 : AdminLogin.defualtAdmin();
			break;
			
		case 3 : {
				   System.out.println("Products in Store :-\n");
				   EcommerceProducts.viewProducts();
				   System.out.println("do you want to buy ? (yes/no)");
				   String ans = sc.next();
				   if (ans.equalsIgnoreCase("yes"))
				   OperationsDirection.getUserOperations();
				   else if (ans.equalsIgnoreCase("no"))
				   System.out.println("thanks for visiting...");
				 
				 }
			break;
			
		}
		
		
		

	}

}
