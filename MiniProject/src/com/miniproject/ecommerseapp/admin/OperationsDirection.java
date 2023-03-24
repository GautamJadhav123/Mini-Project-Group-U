package com.miniproject.ecommerseapp.admin;

import java.util.Scanner;

public class OperationsDirection {

	 public  static void getUserOperations() {
		 
		 String s1 = "User Login";
		 String s2 = "Register New User";
		
		 
		 Scanner sc = new Scanner(System.in);
		 
		 System.out.println("\n Thanks For Using Ecommerce Application \n" + "Choose your operation\n");
		 System.out.printf("1 %-20s 2 %-20s \n", s1, s2);
		 
		 int choice = sc.nextInt();
		 
		 switch (choice) 
		 {
		 
		 case 1 : UserLogin.getUserLogin();
		 	break;
			 
		 case 2 : UserRegistration.getUserRegistrationDetails();
		 	break;
			 
		 case 3 : System.out.println("View Products");
		 	break;
			 
		 }
	 }
	 
	 
	 public static void getAdminOperations() {
		 
		 String s1 = "Add New Product";
		 String s2 = "Check Quantity";
		 String s3 = "Check User Registration Details";
		 String s4 = "Check User History";
		 
		 Scanner sc = new Scanner(System.in);
		 AddProducts addproduct = new AddProducts();
		 
		 System.out.println("choose your operation");
		 System.out.printf("1 %-20s 2 %-20s 3 %-40s 4 %-20s \n", s1, s2, s3, s4);
		 int choice = sc.nextInt();
		 
		 switch (choice)
		 {
		 
		 case 1 : addproduct.addProductDetails();
			 break;
			 
		 case 2 : EcommerceProducts.viewQuantity();
		 	 break;
			 
		 case 3 : UserLogin.checkUserDetails();
		 	 break;
			 
		 case 4 : User.viewUserHistory();;
		 	 break;
			 
		 }
		 
	 }
	
}
