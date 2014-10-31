package controllers;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import models.Employee;
import PayrollApplication.EmployeeDAO;
import PayrollApplication.MainMenuWindow;
import Schedules.DateUtil;

public class RunMonthlyPayrollController extends PayrollController{

	MainMenuWindow mmw;

	public ArrayList<Employee> employeesToRemove;
	
	public RunMonthlyPayrollController(MainMenuWindow mmw){
		this.mmw = mmw;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {

		this.employeesToRemove = new ArrayList();
		
		for(Employee e : mmw.getEmployees()){ 
			if(e.getPayType().equals("commission")){	
				employeesToRemove.add(e);
			}				
		}
		
		System.out.println("Running monthly payroll");			
			
		for(Employee e : employeesToRemove){
			mmw.getEmployees().remove(e);

			try {
				EmployeeDAO.updateEmployeePayroll(e);
				System.out.println(" Updated and removed" + e.toString());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
		}
					
		//mmw.payrollPendingTransactionsTable();			
		mmw.getLblEmployees().setText("payroll run for monthly employees...");
				
		JOptionPane.showMessageDialog(mmw,"payroll run for monthly employees..."); 
			
		
	}
}
