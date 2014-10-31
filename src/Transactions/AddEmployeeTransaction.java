package Transactions;

import Classifications.PaymentClassification;

public abstract class AddEmployeeTransaction implements Transaction{
	
	private String fName;
	private String lName;
	private String address;
	private String contactNum;
/*	private String payRate;
	private String payType;*/
	private String unionFees;
	private String employeeID;
	private String union;

	public AddEmployeeTransaction(){}
	
	public AddEmployeeTransaction(String fName, String lName, String address){		
		this.fName = fName;
		this.lName = lName;
		this.address = address;
	}
	
	public void execute(){
		
		/*PaymentClassification pc = new PaymentClassification();
		//PaymentSchedule ps = MakeSchedule();
		PaymentMethod pm = new PaymentMethod();
		*/
		
/*		Employee e = new Employee(empid, name, address);
		e.Classification = pc;
		e.Schedule = ps;
		e.Method = pm;*/
		
	}

	
	
	

}
