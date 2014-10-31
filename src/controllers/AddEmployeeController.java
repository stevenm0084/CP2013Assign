package controllers;

//import AddEmployeeListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;








import javax.swing.JOptionPane;

import PayrollApplication.EmployeeDAO;
import models.*;
import views.AddEmployeeWindow;


public class AddEmployeeController extends WeeklyPayrollController{// implements ActionListener
	
	private AddEmployeeWindow empView;
	private EmployeeDAO employeeDAO;
	private ArrayList<Employee> employees;	
	private Employee employee;

	public AddEmployeeController(AddEmployeeWindow empView){ //pass in Model later , PayrollDB empModel
		System.out.println("AddEmployeeController object has been instantiated");
		this.empView = empView;		
		this.empView.addEmployeeListener(this);
		this.empView.addCancelEmployeeWindow(this);
		//this.empView.checkRadioButtons(this);
		
		this.employeeDAO = new EmployeeDAO();
		employees = new ArrayList<Employee>();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()){
		case "Submit":
			System.out.println("Adding Employee");
			 
			 String firstName = empView.getTxtFieldFirstName().getText();
			 String lastName = empView.getTxtFieldLastName().getText();
			 String homeAddress = empView.getTxtFieldHomeAddress().getText();
			 String contactNum = empView.getTxtFieldContactNumber().getText();
			 String payType = null;
			 String payRate = null;
			 String inUnion;
			 String commissionPercentage = null;
			 String unionFees;
			 String paymentMethod = empView.getComboBox().getSelectedItem().toString();
			 
			if(empView.getRadioButHourly().isSelected()){
				payType = "hourly";
				payRate = empView.getTextFieldHoulryRate().getText();
				commissionPercentage = "0";				
			}
			
			if(empView.getRadioButSalary().isSelected()){
				payType = "salary";
				payRate = empView.getTextFieldSalary().getText();
				commissionPercentage = "0";
			}
			
			if(empView.getRadioButCommissioned().isSelected()){
				payType = "commission";
				payRate = empView.getTextFieldCommSalary().getText();
				commissionPercentage = empView.getTextFieldCommRate().getText();			
			}
			
			if(empView.getChckbxInUnion().isSelected()){
				inUnion = "yes";
				unionFees = empView.getTextFieldUnionFees().getText();
				 
			 } else{
				inUnion = "no";
				unionFees = "0"; 
			 }

			employee = new Employee(firstName, lastName, homeAddress, contactNum, payType, payRate, inUnion, commissionPercentage, unionFees, paymentMethod);		
			 
			System.out.println(employee);
			  try {
				EmployeeDAO.addEmployeeToDB(employee);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			JOptionPane.showMessageDialog(empView,"Employee Added.");  
			empView.setVisible(false);
			empView.dispose();
			
			break;
			
		case "Cancel":
			System.out.println("Close Adding Employee window");
			empView.setVisible(false);
			empView.dispose();
			break;
		}
					
			//} else{
			
	}

	
	public void updateView(){
		if(allInformationIsCollected()){
			this.empView.setBtnSubmitEnabled(true);
		} else {
			empView.setBtnSubmitEnabled(false);
		}
	}
	
	public boolean allInformationIsCollected(){
		return true;
	}


}
