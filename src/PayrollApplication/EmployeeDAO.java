package PayrollApplication;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;

import models.DatabaseSingleton;
import models.Employee;

public class EmployeeDAO<E> {

	//private static EmployeeDAO instance = new EmployeeDAO();
	private static ResultSet rs;
	private static  ArrayList<Employee> employees;
	private static Connection conn;
    private static PreparedStatement ps;
    
    private static EmployeeDAO instance = new EmployeeDAO();

	public EmployeeDAO() {
		employees = new ArrayList<Employee>();
		
		try {
			ps = null;
		    ResultSet rs = null;
			
			DatabaseSingleton.getInstance();
			DatabaseSingleton.connect();
			conn = DatabaseSingleton.getInstance().getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public static EmployeeDAO getInstance(){
		return instance;
	}	
	
	public static void deleteTimeCards(Employee emp) throws SQLException{
		 System.out.println("running...");
			ps = conn.prepareStatement("DELETE FROM timecard WHERE employee_id = ?");
			//System.out.println("running...");
			try {
				ps.setInt(1, Integer.parseInt(emp.getEmployeeID()));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("running...");
			ps.execute();
			ps.close();
			//System.out.println("running...");
	}
		
	public static void addEmployeeToDB(Employee employee) throws SQLException{
		
		int comissionPercent= Integer.parseInt(employee.getCommissionPercentage());
		
		//ps = conn.prepareStatement("INSERT INTO test (firstName, lastName, address, contactNumber, payType, payRate, inUnion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		ps = conn.prepareStatement("INSERT INTO employee (firstName, lastName, address, contactNumber, payType, payRate, inUnion, commisionPercentage, unionFees, paymentMethod) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		ps.setString(1, employee.getFirstName());
		ps.setString(2, employee.getLastName());
		ps.setString(3, employee.getHomeAddress());
		ps.setString(4, employee.getContactNum());
		ps.setString(5, employee.getPayType());
		ps.setInt(6, Integer.parseInt(employee.getPayRate()));
		ps.setString(7, employee.getInUnion());
		ps.setInt(8, Integer.parseInt(employee.getCommissionPercentage()));
		ps.setString(9, employee.getUnionFees());
		ps.setString(10, employee.getPaymentMethod());
			
		ps.executeUpdate();
		ps.close();
		System.out.println("Employee has been inserted");		
	}
	
	public static ArrayList<Object> getEmployee(int employeeID) throws Exception{
		 ps = conn.prepareStatement("SELECT * FROM Employee WHERE employee_id = " + employeeID);
		 rs = ps.executeQuery();
		 
		 rs.next();
		 String firstName = rs.getString("firstName");
		 String lastName = rs.getString("lastName");
		 String homeAddress = rs.getString("address");
		 String contactNum = rs.getString("contactNumber");
		 String payType = rs.getString("payType");				 			 
		 String payRate = rs.getString("payRate");
		 String inUnion = rs.getString("inUnion");
		 String commissionPercentage = rs.getString("commisionPercentage");
		 String unionFees = rs.getString("unionFees");
		 String paymentMethod = rs.getString("paymentMethod");
		 int empID = rs.getInt("employee_id");	 
 
		 ArrayList<Object> employeeAttributes = new ArrayList<>();
		 
		 //Map<String, String> employeeAttributes = new HashMap<String, String>();
		 
		 employeeAttributes.add(firstName);
		 employeeAttributes.add(lastName);
		 employeeAttributes.add(homeAddress);
		 employeeAttributes.add(contactNum);
		 employeeAttributes.add(payType);
		 employeeAttributes.add(payRate);
		 employeeAttributes.add(inUnion);
		 employeeAttributes.add(commissionPercentage);
		 employeeAttributes.add(unionFees);
		 employeeAttributes.add(empID);
		 employeeAttributes.add(paymentMethod);

		 ps.close();
		return employeeAttributes;
		
	}
	
	public static void deleteEmployeeFromDB(int empID) throws SQLException{
		 System.out.println("running...");
		ps = conn.prepareStatement("DELETE FROM employee WHERE employee_id = ?");
		//System.out.println("running...");
		ps.setInt(1, empID);
		//System.out.println("running...");
		ps.execute();
		ps.close();
		//System.out.println("running...");
	}
	
	public static void getCommissionTotal(Employee emp) throws SQLException{
		Employee em = emp;		
		
		 try {
			ps = conn.prepareStatement("SELECT * FROM SalesDB WHERE employee_id = " + emp.getEmployeeID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
         
         rs = ps.executeQuery();
         
         //String startPayPer = em.getStartOfPayPeriod();
        
         //System.out.println("running");
         double totalCommissionAmount = 0.0;
         
         
         while(rs.next()){
        	
    		 totalCommissionAmount = Double.parseDouble(rs.getString("totalsales"));
    		 //System.out.println("Sale amount = " + totalCommissionAmount);	 
        	 
         }
         //System.out.println("Sale amount = " + totalCommissionAmount);
         emp.setTotalCommissionAmount(totalCommissionAmount);
         ps.close();
	         
      }
	
	public static void loadEmployees() throws SQLException{ // ArrayList<Employee>
		try {
			employees = new ArrayList<Employee>();      
	         if(conn != null){
	        	 System.out.println("loademployees - not null");
	         }else{
	        	 System.out.println("loademployees - is null");
	         }
         
	         ps = conn.prepareStatement("SELECT * FROM employee");  	         
	         rs = ps.executeQuery();	         
	         employees = new ArrayList<Employee>();
	         Employee emp = null;
	         
			while(rs.next()){
				String firstName = rs.getString("firstName");
				 String lastName = rs.getString("lastName");
				 String homeAddress = rs.getString("address");
				 String contactNum = rs.getString("contactNumber");
				 String payType = rs.getString("payType"); 				 			 
				 String payRate = rs.getString("payRate");
				 String inUnion = rs.getString("inUnion");
				 String commissionPercentage = rs.getString("commisionPercentage");
				 String unionFees = rs.getString("unionFees");
				 System.out.println("Loading unionFees: " + unionFees);
				 String paymentMethod = rs.getString("paymentMethod");
				 String employeeID = rs.getString("employee_id");		
				 
				 String paySchedule;				 
				 
				 if(payType.equals("hourly")){
					 paySchedule = "weekly";
				 }else if(payType.equals("salary")){
					 paySchedule = "biweekly";
				 } else{
					 paySchedule = "monthly";
				 }
				 				 
				 emp = new Employee(firstName, lastName, homeAddress, contactNum, payType, payRate, inUnion, commissionPercentage, unionFees, employeeID, paymentMethod);
				 employees.add(emp);	
				 
				 emp.setPaymentSchedule(paySchedule);
				 System.out.println("PaySchedule: " + emp.getPaymentSchedule());

			}			        
			ps.close();			
			

/*			ps = conn.prepareStatement("SELECT * FROM payschedule");
			ResultSet rs3 = ps.executeQuery();
			while(rs3.next()){

				if(rs3.getString("employee_id")){
					String paymentSchedule = rs3.getString("scheduletype");							
					emp.setPaymentSchedule(paymentSchedule);
					ps.close();	
					System.out.println("PaySchedule: " + emp.getPaymentSchedule());
				}

			}*/
			
					
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void addEmployee(Employee e){
	/*	Connection  conn = Database.getInstance().getConnection();
		
		PreparedStatement ps= conn.prepareStatement(arg0);*/
		employees.add(e);
	}
	
	public static ArrayList<Employee> getEmployeesArray(){
		return employees;
	}
	
	public static int getHoursWorked(Employee emp) throws SQLException{ //, String startOfPayPeriod
		Employee em = emp;		
		ResultSet timeCards;

		 ps = conn.prepareStatement("SELECT * FROM timecard WHERE employee_id = " + emp.getEmployeeID()); 
         
         timeCards = ps.executeQuery();
         
        //String startPayPer = startOfPayPeriod;
        
         //System.out.println("running");
         int totalHoursWorked = 0;
         int count = 0;
         System.out.println("Timecard info for Employee: " + emp.getEmployeeID());
         while(timeCards.next()){        	 
        	 
    	 	 String hours = timeCards.getString("hours");
    	 	
    	 
    		 //totalHoursWorked = Integer.parseInt(hours);
    		 
    		 totalHoursWorked += Integer.parseInt(hours);
    		 System.out.println(" Timecard " + count + ".\n Hours Worked: " + timeCards.getObject("hours") + ".\n Date: " + timeCards.getObject("date") + ".\n Total Hours So far: " + totalHoursWorked);
    		 ++count;
         }
         System.out.println();
         System.out.println();
         
         ps.close();		
		//emp.setTotalHoursForWeek(totalHoursWorked);
         return totalHoursWorked;
		
	}

	public static void InsertTimeCard(int empID, String date, String hoursWorked) throws SQLException {

		ps = conn.prepareStatement("INSERT INTO timecard (date, hours, employee_id) VALUES (?, ?, ?)");
		
		ps.setString(1, date);
		ps.setString(2, hoursWorked);
		ps.setInt(3, empID);
		
		ps.executeUpdate();
		ps.close();
				
	}
	
	public static  ArrayList<Object> getHoursWorked(Employee emp, String payperiod) throws SQLException{
		
		ps = conn.prepareStatement("SELECT * FROM timecard WHERE employee_id = " + emp.getEmployeeID());          
        rs = ps.executeQuery();                
        ArrayList<Object> timecards= new ArrayList();
        double totalHours;
        while(rs.next()){
        	
        	if(emp.getPayType().equals("hourly")){
        		if(rs.getString("startofpayperiod").equals(payperiod)){                	
            		timecards.add(rs.getString("hours")); 
            		
        		}
        	} 
        		
    		if(emp.getPayType().equals("salary")){
        		if(rs.getString("startofpayperiod").equals(payperiod)){                	
            		timecards.add(rs.getString("hours")); 
            		
        		}
            		
        	} 
    		
    		if(emp.getPayType().equals("commissioned")){
        		if(rs.getString("startofpayperiod").equals(payperiod)){                	
            		timecards.add(rs.getString("hours")); 
            		
        		}
    			
        	}
        	
        }	                  
         ps.close();       
 		//System.out.println(payPeriodStart);
		return timecards;
	}

	public static void updateEmployeeToDB(Employee employee) throws Exception {
		
		System.out.println(employee.getEmployeeID());
		//ps = conn.prepareStatement("UPDATE employee (firstName, lastName, address, contactNumber, payType, payRate, inUnion, commisionPercentage, unionFees, paymentMethod) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) WHERE employee_id = " + employee.getEmployeeID());
		ps = conn.prepareStatement("UPDATE employee SET firstName = ?,  lastName = ?, address = ?, contactNumber = ?, payType = ?, payRate = ?, inUnion = ?, commisionPercentage = ?, unionFees = ?, paymentMethod = ? WHERE employee_id = " + employee.getEmployeeID());
		
		ps.setString(1, employee.getFirstName());
		ps.setString(2, employee.getLastName());
		ps.setString(3, employee.getHomeAddress());
		ps.setString(4, employee.getContactNum());
		ps.setString(5, employee.getPayType());
		ps.setInt(6, Integer.parseInt(employee.getPayRate()));
		ps.setString(7, employee.getInUnion());
		ps.setInt(8, Integer.parseInt(employee.getCommissionPercentage()));
		ps.setString(9, employee.getUnionFees());
		ps.setString(10, employee.getPaymentMethod());
		
		ps.executeUpdate();
		ps.close();
		System.out.println("Employee has been updated");		
	}

	public static void updateEmployeePayroll(Employee emp) throws SQLException {
		ps = conn.prepareStatement("INSERT INTO DailyPayrollDB (date, hours, TotalPay, employee_id, paymentMethod) VALUES (?, ?, ?, ?, ?)");
		String totalPay = new Double(emp.getPay()).toString();
		ps.setString(1, "20/10/14");
		ps.setInt(2, emp.getTotalHoursForWeek());
		ps.setString(3, totalPay);
		ps.setInt(4, Integer.parseInt(emp.getEmployeeID()));
		ps.setString(5, emp.getPaymentMethod());

		ps.executeUpdate();
		ps.close();
		//System.out.println("Employee has been inserted");		
		
	}	
}