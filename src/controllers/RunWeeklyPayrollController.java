package controllers;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import models.Employee;
import PayrollApplication.EmployeeDAO;
import PayrollApplication.MainMenuWindow;
import Schedules.DateUtil;

public class RunWeeklyPayrollController extends WeeklyPayrollController{

	MainMenuWindow mmw;

	public ArrayList<Employee> employeesToRemove;
	
	public RunWeeklyPayrollController(MainMenuWindow mmw){
	this.mmw = mmw;	
	 
	}

	@Override
	public void actionPerformed(ActionEvent evnt) {			
		this.employeesToRemove = new ArrayList();
		for(Employee e : mmw.getEmployees()){ 
			//System.out.println(e);
			if(e.getPayType().equals("hourly")){	
				employeesToRemove.add(e);
			}				
		}
		
		System.out.println("Running weekly payroll");				
		for(Employee e : employeesToRemove){
			mmw.getEmployees().remove(e);

			try {
				EmployeeDAO.updateEmployeePayroll(e);
				System.out.println(" Updated and removed" + e.toString());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		
		//mmw.payrollPendingTransactionsTable();			
		mmw.getLblEmployees().setText("payroll run for weekly employees...");
				
		JOptionPane.showMessageDialog(mmw,"payroll run for weekly employees..."); 
	
	
		}
	}	
}
