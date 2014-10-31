package controllers;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import PayrollApplication.EmployeeDAO;
import PayrollApplication.MainMenuWindow;
import views.InsertTimeCardWindow;

public class SubmitInsertTimeCardWindowController extends WeeklyPayrollController{


	private InsertTimeCardWindow window;
	private MainMenuWindow mmw;
	
	public SubmitInsertTimeCardWindowController(InsertTimeCardWindow window, MainMenuWindow mmw){
		this.window = window;
		this.mmw = mmw;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
			
		System.out.println("Submitting timecard");
		String employID;
		String date;
		String startOfPayPeriod;
		String hoursWorked;
		employID = window.getComboBox().getSelectedItem().toString();

		date = window.getDates().getSelectedItem().toString();

		hoursWorked = window.getTxtFieldHoursWorked().getText();
		
		try {
			EmployeeDAO.InsertTimeCard(Integer.parseInt(employID), date, hoursWorked);			
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		JOptionPane.showMessageDialog(window,"Timecard Submitted.");  
		
		this.mmw.refreshEmployees();
		this.mmw.updatePendingTransactionsTable();
		window.setVisible(false);
		window.dispose();
		
	}


}
