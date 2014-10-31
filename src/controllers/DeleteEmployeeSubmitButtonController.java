package controllers;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import views.DeleteEmployeeWindow;
import PayrollApplication.EmployeeDAO;


public class DeleteEmployeeSubmitButtonController extends PayrollController{

	private DeleteEmployeeWindow dew;
	
	public DeleteEmployeeSubmitButtonController(DeleteEmployeeWindow dew){
		this.dew = dew;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String employID;
		employID = dew.getComboBox().getSelectedItem().toString();
		System.out.println("Deleteing employee ID: " + employID);
		dew.showEmployeesList(); 
		
		try {
			EmployeeDAO.deleteEmployeeFromDB(Integer.parseInt(employID));
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(dew,"Employee deleted."); 
	}
	
	
	

}
