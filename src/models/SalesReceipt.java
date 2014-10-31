package models;

import java.util.Date;

public class SalesReceipt {

	private Date date;
	private double saleAmount;

	public SalesReceipt(Date date, double amount)
	{
		this.date = date;
		this.saleAmount = amount;
	}
	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getSaleAmount() {
		return saleAmount;
	}

	public void setSaleAmount(double saleAmount) {
		this.saleAmount = saleAmount;
	}

	
}
