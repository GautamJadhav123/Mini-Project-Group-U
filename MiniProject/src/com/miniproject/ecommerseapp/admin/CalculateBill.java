package com.miniproject.ecommerseapp.admin;


public class CalculateBill 
	{
	
			static double grossAmount;
	
		public double getGrossAmount() {
				return grossAmount;
			}

			public void setGrossAmount(double grossAmount) {
				this.grossAmount = grossAmount;
			}

		public  void getBillCalculateAdmin(double netAmount, double taxAmount, double grossAmount) 
		{
			CalculateBill  cbill = new CalculateBill ();
			taxAmount = netAmount * 0.1;
			grossAmount = taxAmount + netAmount;
			cbill.setGrossAmount(grossAmount);
			
			System.out.printf(" Total Net Amount = %-10s Tax Amount = %-10s Total Gross Amount = %-10s\n", netAmount, taxAmount, grossAmount );
			System.out.println();
		}	
	}
