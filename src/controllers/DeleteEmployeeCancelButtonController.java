package controllers;

import java.awt.event.ActionEvent;

import views.DeleteEmployeeWindow;

public class DeleteEmployeeCancelButtonController extends PayrollController{

	private DeleteEmployeeWindow dew;
	
	public DeleteEmployeeCancelButtonController(DeleteEmployeeWindow dew){
		this.dew = dew;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		dew.setVisible(false);
		dew.dispose();
	}
}
