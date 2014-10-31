package controllers;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import models.Employee;
import PayrollApplication.EmployeeDAO;
import PayrollApplication.MainMenuWindow;

public class RunPayrollController extends PayrollController{

	MainMenuWindow mmw;
	String payType;
	
	public ArrayList<Employee> employeesToRemove;
	
	public RunPayrollController(MainMenuWindow mmw){
		this.mmw = mmw;
		payType = null;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.employeesToRemove = new ArrayList();
		
		for(Employee e : mmw.getEmployees()){
			if(mmw.getDates().getSelectedItem().toString().equals("weekly")){
				payType = "hourly";
			} else if(mmw.getDates().getSelectedItem().toString().equals("biweekly")){
				payType = "salary";
			}else if(mmw.getDates().getSelectedItem().toString().equals("monthly")){
				payType = "commission";
			}
				
			if(e.getPayType().equals(payType)){
				employeesToRemove.add(e);
			/*} else if(e.getPayType().equals(mmw.getDates().getSelectedItem().toString())){
				employeesToRemove.add(e);
			} else if(e.getPayType().equals(mmw.getDates().getSelectedItem().toString())){
				employeesToRemove.add(e);
			}*/
			}			
		}				
		
		for(Employee e : employeesToRemove){
			mmw.getEmployees().remove(e);

			try {
				EmployeeDAO.deleteTimeCards(e);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			try {
				EmployeeDAO.updateEmployeePayroll(e);
				System.out.println(" Updated and removed" + e.toString());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
					e1.printStackTrace();
			}			
		}
					
		mmw.payrollPendingTransactionsTable();			
		mmw.getLblEmployees().setText("payroll run for monthly employees...");
				
		JOptionPane.showMessageDialog(mmw,"payroll run for monthly employees..."); 
			
		}
	
	
}
