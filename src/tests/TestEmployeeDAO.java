package tests;

import static org.junit.Assert.*;
import models.Employee;

import org.junit.Test;

import PayrollApplication.EmployeeDAO;

public class TestEmployeeDAO {

	@Test
	//create an employee and add it to the list of employees
	public void TestAddEmployee() {
		
		EmployeeDAO empDAO = new EmployeeDAO();

/*		 String firstName = "Bob";
		 String lastName = "Cobb";
		 String homeAddress = "123 Melvill Street";
		 String homePhone = "11223344";
		 String payType = empView.getPayType();
		 //convert the payrate in textfield from String to DOuble  
		 Double payRate = Double.parseDouble(empView.getTxtFieldPayRate());
		 String accntName = empView.getTxtFieldAccountName();
		 String bsb = empView.getTxtFieldBsb();
		 String accntNum = empView.getTxtFieldAccountNum();
		 String unionDetails = empView.getTxtFieldUnionBranch();*/			
		
		 empDAO.addEmployee(new Employee());	
		
		assertEquals(1, empDAO.getEmployeesArray().size());
	}
	
	public void TestloadEmployee() {
		
		EmployeeDAO empDAO = new EmployeeDAO();

		
		
		assertEquals(1, empDAO.getEmployeesArray().size());
	}

}
