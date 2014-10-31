package controllers;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import models.Employee;
import PayrollApplication.EmployeeDAO;
import views.EditEmployeeWindow;

public class SubmitEditEmployeeController extends WeeklyPayrollController{
	
	EditEmployeeWindow eew;
	Employee employee;
	
	public SubmitEditEmployeeController(EditEmployeeWindow eew){
		this.eew = eew;
	}	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String empID = eew.getComboBox().getSelectedItem().toString();
		System.out.println("EMP ID:  " + empID);
		
		String firstName = eew.getTxtFieldFirstName();
		 String lastName = eew.getTxtFieldLastName();
		 String homeAddress = eew.getTxtFieldHomeAddress();
		 String contactNum = eew.getTxtFieldContactNumber();
		 String payType = null;
		 String payRate = null;
		 String inUnion;
		 String commissionPercentage = null;
		 String unionFees;
		 String paymentMethod = eew.getComboBoxPaySchedule().getSelectedItem().toString();
		 
		if(eew.getRadioButHourly().isSelected()){
			payType = "hourly";
			payRate = eew.getTextFieldHoulryRate();
			commissionPercentage = "0";				
		}
		
		if(eew.getRadioButSalary().isSelected()){
			payType = "salary";
			payRate = eew.getTextFieldSalary();
			commissionPercentage = "0";
		}
		
		if(eew.getRadioButCommissioned().isSelected()){
			payType = "commission";
			payRate = eew.getTextFieldCommSalary();
			commissionPercentage = eew.getTextFieldCommRate();			
		}
		
		if(eew.getChckbxInUnion().isSelected()){
			inUnion = "yes";
			unionFees = eew.getTextFieldUnionFees().getText();
			 
		 } else{
			 inUnion = "no";
			unionFees = "0"; 
		 }
		
		
		 employee = new Employee(firstName, lastName, homeAddress, contactNum, payType, payRate, inUnion, commissionPercentage, unionFees, empID, paymentMethod);		
		 
		System.out.println(employee);
		  try {
			EmployeeDAO.updateEmployeeToDB(employee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		eew.setVisible(false);
		eew.dispose();
		
	}

}
