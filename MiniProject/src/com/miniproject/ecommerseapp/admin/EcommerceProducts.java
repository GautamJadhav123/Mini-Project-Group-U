package com.miniproject.ecommerseapp.admin;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EcommerceProducts {

	public static void viewProducts() 
		{
			Connection con;
			try 
			{
				con = CommonConnection.getConnectionObject();
				PreparedStatement prps = con.prepareStatement("select * from product_information");
			
				ResultSet resultSet=prps.executeQuery();
		
				while(resultSet.next())
				{			
					int id = resultSet.getInt(1);
					String name = resultSet.getString(2);
					String description = resultSet.getString(3);
					int price = resultSet.getInt(4);
					int quantity = resultSet.getInt(5);
			
					System.out.printf("Id = %-5s product Name = %-20s Description = %-30s Price = %-15s Quantity = %-10s \n", id,name,description,price,quantity);				
				}
				System.out.println("__________________________________________________________________________________________________________________________________________");

			} 
			catch 	(SQLException e)
			{
				e.printStackTrace();
			}
		}
	
	
	public static void viewQuantity() 
		{
			Connection con;
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter the Product Id to view Quantity\n" + "Product Id = ");
			int productId = sc.nextInt();
			
			try
			{
				con = CommonConnection.getConnectionObject();
				PreparedStatement ps = con.prepareStatement("Select P_Id, P_name, P_description, P_quantity From product_information where P_Id =? ");
				ps.setInt(1, productId);
				ResultSet rs = ps.executeQuery();
				
				while (rs.next())
					{
						int id = rs.getInt(1);
						String name = rs.getString(2);
						String description = rs.getString(3);
						int quantity = rs.getInt(4);
						
						System.out.printf("Id = %-8s product Name = %-10s Description = %-20s Quantity available = %-10s \n", id, name, description, quantity);
					}
				
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		
		}

	public static void main(String[] args) {
		viewQuantity();
	}
	
}
