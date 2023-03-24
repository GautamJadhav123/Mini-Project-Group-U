package com.miniproject.ecommerseapp.admin;



public class PrintBill {

	public void printBillToUser1(String p_name, String p_description, int p_quantity, int p_price, int p_amount ) 
	{	

		System.out.printf(" Product Name = %-25s Description = %-30s Quantity = %-5s Price = %-12s Amount = %-12s\n", p_name, p_description, p_quantity, p_price, p_amount  );						
		
	}
		
	public void printBillToUser2(double netAmount, double taxAmount, double grossAmount)
	{			
			System.out.printf("Net Amount   =  %.2f", netAmount);
			System.out.println("");
			
			System.out.printf("Tax Amount   =  %.2f", taxAmount);
			System.out.println("");
			
			System.out.println("==========================================================================================================================================");
			
			System.out.printf("Gross Amount =  %.2f", grossAmount);
			System.out.println("");
			
			System.out.println("==========================================================================================================================================");
		}
	
}
