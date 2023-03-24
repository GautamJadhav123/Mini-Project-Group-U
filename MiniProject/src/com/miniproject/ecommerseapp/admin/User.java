
package com.miniproject.ecommerseapp.admin;

import java.sql.Connection;

import java.util.Scanner;
import java.util.Set;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class User {
	
	public User() {
		
	}

				static int id;
				static int quantitySelect;
	
	
				public static int getId() {
					return id;
				}


				public static int getQuantitySelect() {
					return quantitySelect;
				}


				public static void setId(int id) {
					User.id = id;
				}


				public static void setQuantitySelect(int quantitySelect) {
					User.quantitySelect = quantitySelect;
				}

	ArrayList<ProductDetails> listProductDetails=new ArrayList<>();//all shoping items
	
	HashMap <Integer, Integer> hmlist = new HashMap<>();
		
	Scanner sc=new Scanner(System.in);
	
	CalculateBill calculateBill = new CalculateBill();
	
	//"%-15s%03d\n", s1, x
	
  public void getProductDetails()
    {
		try {
				Connection con= CommonConnection.getConnectionObject();
			
				PreparedStatement prps=con.prepareStatement("select * from product_information");
			
				ResultSet resultSet=prps.executeQuery();
			
				while(resultSet.next())
				{			
					int id = resultSet.getInt(1);
					
					String name = resultSet.getString(2);
					
					String description = resultSet.getString(3);
					
					int price = resultSet.getInt(4);
					
					int quantity = resultSet.getInt(5);
				
					System.out.printf("Id = %-5s product Name = %-25s Description = %-30s Price = %-15s Quantity = %-10s \n", id,name,description,price,quantity);				
				}
				System.out.println("__________________________________________________________________________________________________________________________________________");
			} 	
		catch 	(SQLException e) 
				{
					e.printStackTrace();
				}		
					System.out.println();
					System.out.println("Do you want to select product to buy? (yes/no)");
					String buy = sc.next();
					
		if 		(buy.equalsIgnoreCase("yes"))
				{
					
					buyProduct();
				}
		else if (buy.equalsIgnoreCase("no"))
				{
					System.out.println("Thanks for visiting...");
				}		
   }

	
  public void buyProduct()
	{
				System.out.println("How many products do you want to buy");
				int numofprod=sc.nextInt();
				
				for(int i=1; i<=numofprod; i++)
                {
					System.out.println("Enter the productId to Buy ");
					int id=sc.nextInt();				
		
					System.out.println("Enter the quantity");
					int quantitySelect=sc.nextInt();
					
					hmlist.put(id, quantitySelect);
		
				//	updateQuantity(id, quantitySelect);
					
					ResultSet resultSet = getProductDetails(id);
		
					try 
						{
							while(resultSet.next())
								{
									int idSelect = resultSet.getInt(1);
									String prName = resultSet.getString(2);
									String prDes = resultSet.getString(3);
									int prPrice = resultSet.getInt(4);
									int itemAmount = quantitySelect * prPrice;
				
									ProductDetails productSelect = new ProductDetails(idSelect, prName, prDes, quantitySelect, prPrice, itemAmount);
									listProductDetails.add(productSelect);
								}						
						}
					catch 	(SQLException e) 
						{
							e.printStackTrace();
						}
                }
				
				System.out.println();
				System.out.println("Do you want to view cart yes/no");
				String view=sc.next();
				
				if 		(view.equalsIgnoreCase("yes"))
	    			{
	    				viewCart();
	    		
	    			}
				else if (view.equalsIgnoreCase("no"))
	    			{
						System.out.println("Do you want to purchase these items? (yes/no)");
						String purchaseDirect = sc.next();
						if (purchaseDirect.equalsIgnoreCase("yes"))
							{
								getBillCalculate();
							}
						else if (purchaseDirect.equalsIgnoreCase("no"))
							{
								System.out.println("Thanks for visiting...");
							}
	    			}	
	}
	
	
  public ResultSet getProductDetails(int id)
	{
	  		ResultSet resultSet = null;
	  try 
	  	{
		  	Connection con= CommonConnection.getConnectionObject();
		
		  	PreparedStatement prps=con.prepareStatement("select P_Id, P_name, P_description, P_price, P_quantity from product_information where P_Id=?");
		
		  	prps.setInt(1,id);
		
		  	resultSet=prps.executeQuery();		
	  	}
	  catch (SQLException e)
	  	{
		  	e.printStackTrace();
	  	}	
	  return resultSet;
	}


  public  void viewCart() 
	{
	  		System.out.println("Items in your cart are :- ");
		
	  		for(ProductDetails productDetails: listProductDetails)
				{			
					int IdSelect = productDetails.getPrId();
					String nameSelect = productDetails.getPrName();
					String descriptionSelect = productDetails.getPrDescription();
					int quantitySelect = productDetails.getPrQuantity();
					int priceSelect = productDetails.getPrPrice();
					int itemAmount = productDetails.getItemAmount();
		
					System.out.printf("Id = %-5s product Name = %-22s Description = %-30s Price per item = %-12s Quantity = %-5s Item Amount = %-12s\n", IdSelect, nameSelect, descriptionSelect, priceSelect, quantitySelect, itemAmount);		
				}
					System.out.println("__________________________________________________________________________________________________________________________________________");
					System.out.println();
					System.out.println("Do you want to purchase these items? (yes/no)");
					String choice=sc.next();
		
			if			(choice.equalsIgnoreCase("yes"))
				{
						getBillCalculate();
						
				}
			else if 	(choice.equalsIgnoreCase("no"))
				{
						System.out.println("Thanks for using Ecommerce Application.\n" + "Visit again.");
				}	
	}
	
	
  public  void getBillCalculate()
	{		
	  			CalculateBill cbill = new CalculateBill();
				PrintBill pbill = new PrintBill();	
				
				System.out.println();
				System.out.println("Billing Details are ");
				
					double netAmount =0;
					double taxAmount = 0;
					double grossAmount = 0;
					int counter = 0;
					
				Iterator <ProductDetails>	itr = listProductDetails.iterator();
		
			while (itr.hasNext())
				{
					ProductDetails productPurchase = itr.next();
					netAmount = productPurchase.getItemAmount();
					netAmount = netAmount + netAmount;					
				}				
					cbill.getBillCalculateAdmin(netAmount, taxAmount, grossAmount);
					taxAmount = netAmount * 0.1;					
					grossAmount = taxAmount + netAmount;
				
					Set<Integer> s = hmlist.keySet();
					Iterator<Integer> itr1 = s.iterator();
					while (itr1.hasNext())
					{
						int i = itr1.next();
						int j = hmlist.get(i);
						updateQuantity(i, j);
					}
					
					
					
					updateUserHistory();
					
					
					
					//System.out.println("updated history");
					
					System.out.println("Do you want to print final bill ? (yes/no)");
					String finalBill = sc.next();
			
			if 		(finalBill.equalsIgnoreCase("yes")) 
				{
					System.out.println("printing Final Bill please wait...");
					System.out.println("");
					System.out.println("");
						
					System.out.println("__________________________________________________________________________________________________________________________________________");
						
					for (ProductDetails productPurchase : listProductDetails) 
						{
							counter++;
							System.out.printf(" %-2s", counter);
							pbill.printBillToUser1(productPurchase.getPrName(), productPurchase.getPrDescription(), productPurchase.getPrQuantity(), productPurchase.getPrPrice(), productPurchase.getItemAmount());
						}
						
			  System.out.println("__________________________________________________________________________________________________________________________________________");
			  System.out.println("");
						
			  		pbill.printBillToUser2(netAmount, taxAmount, grossAmount);
						
			  		System.out.println("Thanks for Shopping...");
						
				}
			else if (finalBill.equalsIgnoreCase("no"))
				{
					System.out.println("Thanks for Shopping...");
				}
					
	}
	
  
	public void updateUserHistory() {
  		
  		Connection con;
  		UserLogin ul = new UserLogin();
  		CalculateBill  cbill = new CalculateBill ();
  		String purchaseDetailsString="";
  		
  		for(ProductDetails productDetails:listProductDetails)
		{
			if(purchaseDetailsString.isEmpty())
			{
				purchaseDetailsString = productDetails.getPrName() + "-" + productDetails.getPrQuantity();
			}
			else
			{
			purchaseDetailsString=purchaseDetailsString + ", " + productDetails.getPrName() + "-" + productDetails.getPrQuantity();  
			
			}		
		}
  		
  		try {
  			con = CommonConnection.getConnectionObject();
  			PreparedStatement ps = con.prepareStatement("Insert into usershistory (UserName, PurcchaseDetails, Amount) values (?,?,?)");
  			ps.setString(1, ul.getUserName());
  			ps.setString(2, purchaseDetailsString);
  			ps.setDouble(3, cbill.getGrossAmount());
  			ps.execute();
  			
  		}
  		catch (SQLException e)
  		{
  			e.printStackTrace();
  		}
  		
  	}
  
	public static void viewUserHistory() {
		
  		Connection con;
  		Scanner sc = new Scanner(System.in);
  		
  		System.out.println("Enter the UserName to view Purchase History..");
  		String username = sc.next();
  		int counter = 0;

			try {
				con = CommonConnection.getConnectionObject();
				PreparedStatement ps = con.prepareStatement("select PurcchaseDetails, Amount from usershistory where UserName=?");
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					
					
					String PurchaseDetails = rs.getString(1);
					double Amount = rs.getDouble(2);
					
					counter++;
					System.out.printf(" %-5s", counter);
					System.out.printf("PurcchaseDetails = %-40s Amount = %.2f \n",  PurchaseDetails, Amount);	
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	
	public void updateQuantity(int p_id, int quantity)
		{
			Connection con;
			int avlQuantity = 0;
			int finalQuantity = 0;
			
			try {
				con = CommonConnection.getConnectionObject();
				PreparedStatement ps = con.prepareStatement("select P_quantity from Product_Information where P_Id=?");
				ps.setInt(1, p_id);
				ResultSet rs = ps.executeQuery();
				
				while (rs.next())
				{
					avlQuantity = rs.getInt(1);
				}
			if (quantity < avlQuantity)
			{
				finalQuantity = avlQuantity - quantity;
			}
			else System.out.println("Sorry, currently out of Stock.");
			
				PreparedStatement ps2 = con.prepareStatement("Update Product_Information set P_quantity = ? where P_Id=?");
				ps2.setInt(1, finalQuantity);
				ps2.setInt(2, p_id);
				ps2.execute();
				
				System.out.println("quantity updated -" + finalQuantity);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
  
  
 }



	
	/*public void purchase()
	{
		
		
		System.out.println("Billing Details");
		int counter =0;
		int totalAmmount =0;
		
		Connection con;
		
		try {
			con = CommonConnection.getConnectionObject();
		
		for(ProductDetails productDetails: listProductDetails)
		{
			ResultSet resultSet=getProductDetails(productDetails.getPrId());
			int dbQuantity=0;
			try {
				while(resultSet.next())
				{
					
					 dbQuantity=resultSet.getInt(5); 
					
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			int finalQuantity=dbQuantity-productDetails.getPrQuantity();
			//System.out.println("final quantity" +finalQuantity);
			
			PreparedStatement prps=con.prepareStatement("update Products set Quantity=? where id=?");
			prps.setInt(1,finalQuantity);
			prps.setInt(2,productDetails.getPrId());
			prps.execute();
			
			//to calculate total ammount for billing
			totalAmmount=totalAmmount+productDetails.getPrPrice();
		
			//For Billing
			counter=counter+1;	
			
			System.out.println(counter+"  "+productDetails.getPrName()+"  "+productDetails.getPrQuantity());
			
			
			
			
		}
		
		
		
		System.out.println("----------------------------------------------------------------------------------------------");
		System.out.println("Total Ammount = "+ totalAmmount);
		System.out.println("Thank You");
		
		
		
		//inserting data into userHistory
				userHistory();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public void insertUserHistoryDb(String username,String purchaseDetails, int total_Amount) 
	{
		
		
		Connection con;
		try {
			con = CommonConnection.getConnectionObject();
			PreparedStatement prps=con.prepareStatement("insert into userHistory(username,purchaseDetails,total_Amount)values(?,?,?)");
			prps.setString(1, username);
			prps.setString(2, purchaseDetails);
			prps.setInt(3,total_Amount);
			
			prps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 	System.out.println("user history updated succefully");		
	}
	
	
	
	public void userHistory()
	{
			
		String username=getUserName();
		String purchaseDetailsString="";
		int totalAmount=0;
		for(ProductDetails productDetails:listProductDetails)
		{
			if(purchaseDetailsString.isEmpty())
			{
				purchaseDetailsString = productDetails.getPrName() + "-" + productDetails.getPrQuantity();
			}
			else
			{
			purchaseDetailsString=purchaseDetailsString + "," + productDetails.getPrName() + "-" + productDetails.getPrQuantity();  
			
			}
			
			totalAmount=totalAmount+productDetails.getPrPrice();		
		}
		
		insertUserHistoryDb(username,purchaseDetailsString,totalAmount);			
	}
	
	
	public void viewUserHistory()
	{
		System.out.println("Please enter username");
		String username=sc.next();
		
		Connection con;
try {
			con = CommonConnection.getConnectionObject();
			PreparedStatement prps=con.prepareStatement("select purchaseDetails,total_Amount from UserHistory where username=?;");
			
			prps.setString(1,username);
			
			 ResultSet resultSet=prps.executeQuery();
			 
			 while(resultSet.next())
			 {
				 System.out.println("product items and Quantity-->" + resultSet.getString(1));
				 System.out.print("total amount-->" + resultSet.getInt(2));
				 
			 }
	
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		
		
		
		
	}
		
		
	}*/
	
	
	
	
	

		
	


