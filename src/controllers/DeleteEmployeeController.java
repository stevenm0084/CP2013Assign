package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import PayrollApplication.EmployeeDAO;
import PayrollApplication.MainMenuWindow;
import models.Employee;
import views.AddEmployeeWindow;
import views.DeleteEmployeeWindow;

public class DeleteEmployeeController extends WeeklyPayrollController{

	private MainMenuWindow v;
	private DeleteEmployeeWindow dew;
	private EmployeeDAO empDAO;
	//private Employee emp;
	
	public DeleteEmployeeController(MainMenuWindow v) {

		
	}


	public void actionPerformed(ActionEvent e) {
		dew = new DeleteEmployeeWindow();
		dew.setVisible(true);
		dew.setupDeleteFunction(new DeleteEmployeeSubmitButtonController(dew));
		dew.setupCancelFunction(new DeleteEmployeeCancelButtonController(dew));
			
	}

	
}