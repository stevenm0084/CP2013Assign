package models;

import java.util.Date;
import java.util.HashMap;

public class Paycheck {
	
	private Date payDate;
	private Date payPeriodStartDate;
	private double grossPay;
	private HashMap fields;
	private double deductions;
	private double netPay;

	public Paycheck(Date payPeriodStartDate, Date payDate)
	{
		 fields = new HashMap();
		this.payDate = payDate;
		this.payPeriodStartDate = payPeriodStartDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Date getPayPeriodStartDate() {
		return payPeriodStartDate;
	}

	public void setPayPeriodStartDate(Date payPeriodStartDate) {
		this.payPeriodStartDate = payPeriodStartDate;
	}

	public double getGrossPay() {
		return grossPay;
	}

	public void setGrossPay(double grossPay) {
		this.grossPay = grossPay;
	}

	public HashMap getFields() {
		return fields;
	}

	public void setFields(HashMap fields) {
		this.fields = fields;
	}

	public double getDeductions() {
		return deductions;
	}

	public void setDeductions(double deductions) {
		this.deductions = deductions;
	}

	public double getNetPay() {
		return netPay;
	}

	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}

}
