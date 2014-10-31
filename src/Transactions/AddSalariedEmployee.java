package Transactions;

public class AddSalariedEmployee  extends AddEmployeeTransaction{

	private String salary;
	private String fName;
	private String lName;
	private String address;
	
	public AddSalariedEmployee(String fName, String lName, String address, String salary){		
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.salary = salary;
		
	}
}
