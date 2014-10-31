package models;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseSingleton {
	
	/*TABLES
		DailyPayrollDB
			date, hours, salary, employee_id
		Employee
			employee_id, firstName, lastName, address, contactNumber, payType, payRate, union, commissionPercentage
		SalesDB
			receiptNumber, date, employee_id
	*/
	//Access the DB by going Database db = Database.getInstance();
	private static DatabaseSingleton instance = new DatabaseSingleton();
	private static ArrayList<Employee> employees;
	
	private static Connection conn;
	
	public DatabaseSingleton(){
		conn = null;
		employees = new ArrayList<Employee>();
	}

	public static DatabaseSingleton getInstance(){
		return instance;
	}
	
	public java.sql.Connection getConnection(){
		return conn;
	}
	public static void connect() throws Exception {
		if (conn != null)
			return;

		
		try{
			Class.forName("com.mysql.jdbc.Driver"); //.newInstance()
			System.out.println("Connected to jdbc Driver");
			String connectionUrl = "jdbc:mysql://120.146.171.162:3306/together_payroll";
			//String connectionUrl = "jdbc:mysql://localhost:3306/payroll";
	         //String connectionUser = "steven";
			String connectionUser = "steve";
	         String connectionPassword = "7491";
	         conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
			
	         

	         
/*	         PreparedStatement ps = null;
	         ResultSet rs = null;
	         
	         ps = conn.prepareStatement("SELECT * FROM employee"); //'payRate', 'employee_id', 'firstName', 'lastName', 'address', 'contactNumber', 'payType', 'union'
	         rs = ps.executeQuery();
			
			while(rs.next()){
				String firstName = rs.getString("firstName");
				 String lastName = rs.getString("lastName");
				 String homeAddress = rs.getString("address");
				 String homePhone = rs.getString("contactNumber");
				 String payType = rs.getString("payType");
				 //convert the payrate in textfield from String to DOuble  
				 //String payRate = rs.getString("payRate");
				 //String accntName = rs.getString("");
				 //String bsb = rs.getString("");
				 //String accntNum = rs.getString("");
				 String unionDetails = rs.getString("union");	
				 String employeeID = rs.getString("employee_id");			 
				
				employees.add(new Employee(firstName, lastName, homeAddress, homePhone, payType, "", unionDetails, employeeID));	
			}		
			for(Employee ed : employees){
				System.out.println(ed.toString());
			}*/
			
		} catch (ClassNotFoundException e){
			throw new Exception("Driver not found");
		}
		
        
//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=testuser&password=testpassword");
         //String connectionUrl = "jdbc:mysql://120.146.171.162:3306/payroll";

	}
	
	public void disconnect() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				System.out.println("Can't close connection");
			}
		}	
	}
	
}
