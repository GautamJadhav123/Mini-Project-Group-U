package com.miniproject.ecommerseapp.admin;


import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;

public class CommonConnection
	{

		public static Connection getConnectionObject() throws SQLException
		{	
			Connection con=null;
		
		try 
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
			
				con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerceapp", "root","Gautam@123" );			
			} 
		catch 	(ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		return con;
		
		}
	}