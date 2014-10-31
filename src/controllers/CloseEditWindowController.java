package controllers;

import java.awt.event.ActionEvent;

import views.EditEmployeeWindow;

public class CloseEditWindowController extends WeeklyPayrollController{
	
	EditEmployeeWindow eew;
	public CloseEditWindowController(EditEmployeeWindow eew){
		this.eew = eew;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		eew.setVisible(false);
		eew.dispose();
	}
	
	

}
