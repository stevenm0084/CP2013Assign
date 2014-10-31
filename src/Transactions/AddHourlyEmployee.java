package Transactions;

public class AddHourlyEmployee  extends AddEmployeeTransaction{

	private String hourlyRate;  
	private String fName;
	private String lName;
	private String address;
	
	public AddHourlyEmployee(String fName, String lName, String address, String hourlyRate){
		
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.hourlyRate = hourlyRate;
	}

	public String getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(String hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	
/*	public AddHourlyEmployee(int id, String name, String address, double hourlyRate){
		this.
		
	}	*/
	
}
