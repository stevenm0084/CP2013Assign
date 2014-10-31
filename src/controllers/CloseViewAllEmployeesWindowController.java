package controllers;

import java.awt.event.ActionEvent;

import views.ViewAllEmployeesWindow;

public class CloseViewAllEmployeesWindowController extends WeeklyPayrollController{
	
	ViewAllEmployeesWindow vaew;
	public CloseViewAllEmployeesWindowController(ViewAllEmployeesWindow vaew){
		this.vaew = vaew;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		vaew.setVisible(false);
		vaew.dispose();		
	}
}
