package Classifications;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import models.Paycheck;
import models.SalesReceipt;

public class CommissionClassification extends PaymentClassification{

	
	private double baseRate;
	private double commissionRate;
	private ArrayList<SalesReceipt> salesReceipts;
	
	

	public CommissionClassification(double baseRate, double commissionRate){
		salesReceipts = new ArrayList<SalesReceipt>();
		this.baseRate = baseRate;
		this.commissionRate = commissionRate;
	}
	
	@Override
	public double calculatePay(Paycheck paycheck){
		double salesTotal = 0;
		
/*		for(SalesReceipt receipt : salesReceipts){
			if(Date.IsInPayPeriod(receipt.getDate(), paycheck.PayPeriodStartDate, paycheck.PayPeriodEndDate))
				salesTotal += receipt.getSaleAmount();
		}*/
		return baseRate + (salesTotal * commissionRate * 0.01);
	}

	public double getBaseRate() {
		return baseRate;
	}

	public void setBaseRate(double baseRate) {
		this.baseRate = baseRate;
	}

	public double getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(double commissionRate) {
		this.commissionRate = commissionRate;
	}

	public ArrayList<SalesReceipt> getSalesReceipts() {
		return salesReceipts;
	}

	public void setSalesReceipts(ArrayList<SalesReceipt> salesReceipts) {
		this.salesReceipts = salesReceipts;
	}
	
	

}
