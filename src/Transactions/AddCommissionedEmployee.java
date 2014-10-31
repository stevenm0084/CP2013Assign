package Transactions;

public class AddCommissionedEmployee extends AddEmployeeTransaction{

	private String salary;
	private double commissionedRate;
	private String fName;
	private String lName;
	private String address;
	
	public AddCommissionedEmployee(String fName, String lName, String address, String salary){
		
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.salary = salary;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public double getCommissionedRate() {
		return commissionedRate;
	}

	public void setCommissionedRate(double commissionedRate) {
		this.commissionedRate = commissionedRate;
	}

}
