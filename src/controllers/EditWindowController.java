package controllers;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import views.EditEmployeeWindow;
import PayrollApplication.MainMenuWindow;

public class EditWindowController extends WeeklyPayrollController{
	
	MainMenuWindow mmw;
	
	public EditWindowController(MainMenuWindow mmw){
		this.mmw = mmw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		EditEmployeeWindow eew = new EditEmployeeWindow();
		eew.addCancelEditEmployeeListener(new CloseEditWindowController(eew));
		eew.addSubmitEditEmployeeListener(new SubmitEditEmployeeController(eew));;
		eew.setVisible(true);
		
		JOptionPane.showMessageDialog(mmw,"Employee Updated!"); 
	}
}
