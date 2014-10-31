package models;

import java.sql.SQLException;

import javax.swing.text.html.parser.ParserDelegator;

import PayrollApplication.EmployeeDAO;
import Schedules.PaymentSchedule;

public class Employee {
	private String firstName;
	private String lastName;
	private String homeAddress;
	private String contactNum;
	private String payRate;
	private String payType;
	private String unionFees;
	private String employeeID;
	private String paymentSchedule;
	private String inUnion;
	private String paymentMethod;
	private String commissionPercentage;
	private int totalHoursForWeek;
	private double pay;
	private double deductions;
	private double totalCommissionAmount;
	private PaymentSchedule paySchedule;
	private String payPeriodStartDate;
	
	public Employee(){
		
	}
	
	public Employee(String firstName, String lastName, String homeAddress, String contactNum, String payType, String payRate, String inUnion, String commissionPercentage, String unionFees, String paymentMethod){
		 this.firstName = firstName;
		 this.lastName = lastName;
		 this.homeAddress = homeAddress;
		 this.contactNum = contactNum;
		 this.payType = payType;
		 this.payRate = payRate;
		 this.inUnion = inUnion;
		 this.commissionPercentage = commissionPercentage;
		 this.unionFees = unionFees; 
		 this.paymentMethod = paymentMethod;
	}

	public Employee(String firstName, String lastName, String homeAddress, String contactNum, String payType, String payRate, String inUnion, String commissionPercentage, String unionFees, String employeeID, String paymentMethod){
		 this.firstName = firstName;
		 this.lastName = lastName;
		 this.homeAddress = homeAddress;
		 this.contactNum = contactNum;
		 this.payType = payType;
		 this.payRate = payRate;
		 this.inUnion = inUnion;
		 this.commissionPercentage = commissionPercentage;
		 this.unionFees = unionFees;
		 this.employeeID = employeeID;	
		 this.paymentMethod = paymentMethod;
	}
	
	
/*	public Employee(String firstName, String lastName, String homeAddress, String contactNum, String payType, String payRate, String inUnion, String commissionPercentage, String unionFees, String employeeIDString, String paymentMethod){
		 this.firstName = firstName;
		 this.lastName = lastName;
		 this.homeAddress = homeAddress;
		 this.contactNum = contactNum;
		 this.payType = payType;
		 this.payRate = payRate;
		 this.inUnion = inUnion;
		 this.commissionPercentage = commissionPercentage;
		 this.unionFees = unionFees; 
		 this.paymentMethod = paymentMethod;
		 this.employeeID = employeeID;	
	}*/

	
	
	
	public int getHoursWorked(){
		try {
			this.totalHoursForWeek = EmployeeDAO.getHoursWorked(this);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		this.setTotalHoursForWeek(totalHoursForWeek);
		return totalHoursForWeek;
	}

	public int getTimeCardHoursWorkedSoFar(){
		try {
			this.setTotalHoursForWeek(EmployeeDAO.getHoursWorked(this));
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return totalHoursForWeek;
	}
	
	public double calculatePay(){		
		double hoursSoFar = 0.0;
		pay = 0.0;
		
		hoursSoFar = this.getTimeCardHoursWorkedSoFar();
		
		//System.out.println(this.getPayType());		
		
		if(this.getPayType().equals("hourly")){ //this.getPayType().equals("Hourly") || 
			//System.out.println("Calculating hourly pay");
			//System.out.println("Hours so far: " + this.getTotalHoursForWeek());
			pay = Double.parseDouble(this.payRate) * hoursSoFar;
			
		} else if(this.getPayType().equals("Salary") || this.getPayType().equals("salary")){
			//System.out.println("Calculating salery pay");
			pay = Double.parseDouble(this.payRate) * 38;
			
		} else{
			//System.out.println("Calculating commissioned pay"); 
			
			try {
				EmployeeDAO.getCommissionTotal(this);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pay = Double.parseDouble(this.payRate) * 38;
			
			double totalCommission = 0.00;			
			double results = this.getTotalCommissionAmount() * ( (Double.parseDouble(this.commissionPercentage) / 100) );			
			this.pay += results;
			
			//System.out.println("Total Pay: " + this.pay);
		}		
		return this.pay;
	}
	
	public double calculateDeductions(){		
		this.deductions = 0.0;
		if(this.inUnion == "Yes" || this.inUnion == "yes"){
			//System.out.println("deducting union fees from pay");
			this.deductions -= Double.parseDouble(this.getUnionFees());
		} 
		return this.deductions;
	}			
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	
	public String getPaymentSchedule() {
		return paymentSchedule;
	}

	public void setPaymentSchedule(String paymentSchedule) {
		this.paymentSchedule = paymentSchedule;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
   public double getTotalCommissionAmount() {
		return totalCommissionAmount;
	}

	public void setTotalCommissionAmount(double totalCommissionAmount) {
		this.totalCommissionAmount = totalCommissionAmount;
	}

	public String getPayPeriodStartDate() {
			return payPeriodStartDate;
		}

	public void setPayPeriodStartDate(String payPeriodStartDate) {
		this.payPeriodStartDate = payPeriodStartDate;
	}
		
	public double getPay() {
		return pay;
	}

	public void setPay(double pay) {
		this.pay = pay;
	}

	public double getDeductions() {
		return deductions;
	}

	public void setDeductions(double deductions) {
		this.deductions = deductions;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getContactNum() {
		return contactNum;
	}

	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}

	public String getPayRate() {
		return payRate;
	}

	public void setPayRate(String payRate) {
		this.payRate = payRate;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getUnionFees() {
		return unionFees;
	}

	public void setUnionFees(String unionFees) {
		this.unionFees = unionFees;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getInUnion() {
		return inUnion;
	}

	public void setInUnion(String inUnion) {
		this.inUnion = inUnion;
	}

	public String getCommissionPercentage() {
		return commissionPercentage;
	}

	public void setCommissionPercentage(String commissionPercentage) {
		this.commissionPercentage = commissionPercentage;
	}

	public int getTotalHoursForWeek() {
		return totalHoursForWeek;
	}

	public void setTotalHoursForWeek(int totalHoursForWeek) {
		this.totalHoursForWeek = totalHoursForWeek;
	}
	

	public PaymentSchedule getPaySchedule() {
		return paySchedule;
	}

	public void setPaySchedule(PaymentSchedule paySchedule) {
		this.paySchedule = paySchedule;
	}

	

	@Override
	public String toString(){ 
		
		String details = "ID: " + this.employeeID + ", fName: " + this.firstName + ", lName: " + this.lastName + ", Address: " + this.homeAddress + ", ContactNum: " + this.contactNum + ", payType: " + 
		this.payType + ", payRate: " + this.payRate + ", In union: " + this.inUnion + ", commssionPercentage: " + this.commissionPercentage + ", unionFees: " + this.unionFees + "paymentMethod: " + this.paymentMethod;
		return details;		
		

	}
}
