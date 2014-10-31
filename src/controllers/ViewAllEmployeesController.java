package controllers;

import java.awt.event.ActionEvent;

import PayrollApplication.EmployeeDAO;
import views.ViewAllEmployeesWindow;

public class ViewAllEmployeesController extends WeeklyPayrollController{
	
	ViewAllEmployeesWindow waew;
	
	public ViewAllEmployeesController() {
		//ViewAllEmployeesWindow waew
		//this.waew = waew;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		waew = new ViewAllEmployeesWindow();
		waew.setVisible(true);
		
		waew.addCancelViewAllEmployeesWindow(new CloseViewAllEmployeesWindowController(waew));
		//waew.addInsertTimeCardListener(new SubmitInsertTimeCardWindowController(waew));;
				

	}
	
}
