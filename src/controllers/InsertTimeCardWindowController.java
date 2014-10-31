package controllers;

import java.awt.event.ActionEvent;

import views.InsertTimeCardWindow;
import PayrollApplication.EmployeeDAO;
import PayrollApplication.MainMenuWindow;

public class InsertTimeCardWindowController extends WeeklyPayrollController{
	
	private InsertTimeCardWindow window;
	private MainMenuWindow mmw;	
	public InsertTimeCardWindowController(MainMenuWindow mmw){ 
		this.mmw = mmw;
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		InsertTimeCardWindow window = new InsertTimeCardWindow(this.mmw);
		window.addCancelInsertTimeCardWindow(new CloseInsertTimeCardWindowController(window));
		window.addInsertTimeCardListener(new SubmitInsertTimeCardWindowController(window, this.mmw));;
		window.setVisible(true);	
	}
}
