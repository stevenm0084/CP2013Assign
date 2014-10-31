package models;

//import java.util.GregorianCalendar;

public class Sales {

	private String receiptNumber;
	//private GregorianCalendar date; 
	private String date;
	private String employeeID;
	
	public Sales(String receiptNumber, String date, String employeeID){
		this.receiptNumber = receiptNumber;
		this.date = date;
		this.employeeID = employeeID;
	}

	public String getReceiptNumber() {
		return receiptNumber;
	}

	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	
}
