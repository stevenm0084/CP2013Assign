package PayrollApplication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import controllers.*;
import models.Employee;
import views.AddEmployeeWindow;
import views.DeleteEmployeeWindow;

public class MainMenuWindowController extends WeeklyPayrollController{

	private MainMenuWindow v;
	private EmployeeDAO empDAO;
	
	public MainMenuWindowController(MainMenuWindow v, EmployeeDAO empDAO) {		
		this.empDAO = empDAO;
		this.v = v;
		v.openAddEmployeeWindow(this);
		v.openDeleteEmployeeWindow(new DeleteEmployeeController(v));
		v.quitProgram(this);
		v.insertTimeCard(new InsertTimeCardWindowController(v));
		v.viewAllEmployees(new ViewAllEmployeesController());
		v.editEmployee(new EditWindowController(v));
		v.runPayrollWeekly(new RunPayrollController(v));
/*		v.runPayrollWeekly(new RunWeeklyPayrollController(v));
 * 		v.runPayrollBiWeekly(new RunBiWeeklyPayrollController(v));
		v.runPayrollMonthly(new RunMonthlyPayrollController(v));*/
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(e.getActionCommand());
		switch (e.getActionCommand()){
		case "Add new Employee":
		
			System.out.println(e.getSource());
			System.out.println("Opening Add Employee Window");
			AddEmployeeWindow employeeWindow = new AddEmployeeWindow(this.empDAO);		
			AddEmployeeController c = new AddEmployeeController(employeeWindow); 
			employeeWindow.setVisible(true);
			break;
		
		case "Exit":
			System.out.println(e.getSource());
			v.setVisible(false);
			v.dispose();
			System.out.println("Exiting");
			break;
			}
	}

	
}
