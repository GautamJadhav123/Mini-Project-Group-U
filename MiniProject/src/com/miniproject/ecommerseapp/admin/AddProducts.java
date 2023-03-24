package com.miniproject.ecommerseapp.admin;

import java.util.Scanner; 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddProducts 
	{

			public void addProductDetails()
			{
		
				AddProducts addproduct = new AddProducts();
				ProductDetails productdetailadd = new ProductDetails();
		
				Scanner sc = new Scanner(System.in);
		
				System.out.println("How many Products you want to add? ");
				int n = sc.nextInt();
				
				for (int i =1; i<=n; i++) 
				{
			
					System.out.println("Enter the Product Id :-");
					productdetailadd.setPrId(sc.nextInt());
			
					System.out.println("Enter the Product Name :-");
					productdetailadd.setPrName(sc.next());
			
					System.out.println("Enter the Product Description :-");
					productdetailadd.setPrDescription(sc.next());
			
					System.out.println("Enter the Product Price :-");
					productdetailadd.setPrPrice(sc.nextInt());
			
					System.out.println("Enter the Product Quantity :-");
					productdetailadd.setPrQuantity(sc.nextInt());
				
			
			try 
					{
					addproduct.insertProductDetails(productdetailadd.getPrId(), productdetailadd.getPrName(), productdetailadd.getPrDescription(), productdetailadd.getPrPrice(), productdetailadd.getPrQuantity());
					} 
			catch 	(SQLException e)
					{
					System.out.println(e.getMessage());
					System.out.println("Product Id already Exist");
					--n;				
					}	
				} 	
				
			if 		(n!=0)
					System.out.println( n + " Products added succefully.");
			else 
					System.out.println("No Product added.");
					sc.close();
			}
	
			
			public void insertProductDetails(int p_Id, String p_name, String p_description, int p_price, int p_quantity) throws SQLException 
			{
			
				PreparedStatement ps = null;
				Connection con= CommonConnection.getConnectionObject();
		
				ps = con.prepareStatement("insert into Product_Information(P_Id, P_name, P_description, P_price, P_quantity) values (?,?,?,?,?)");
				
					ps.setInt(1, p_Id);
					ps.setString(2, p_name);
					ps.setString(3, p_description);
					ps.setInt(4, p_price);
					ps.setInt(5, p_quantity);
						
					ps.execute();			
			}	
	}
