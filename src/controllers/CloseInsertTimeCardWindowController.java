package controllers;

import java.awt.event.ActionEvent;

import views.InsertTimeCardWindow;

public class CloseInsertTimeCardWindowController extends WeeklyPayrollController{

	private InsertTimeCardWindow window;
	
	public CloseInsertTimeCardWindowController(InsertTimeCardWindow window){
		this.window = window;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
			window.setVisible(false);
			window.dispose();
					
	
	}
}
