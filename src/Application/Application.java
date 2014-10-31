package Application;
import PayrollApplication.EmployeeDAO;
import PayrollApplication.MainMenuWindow;
import PayrollApplication.MainMenuWindowController;
import PayrollApplication.PayrollApplication;
import models.DatabaseSingleton;

public class Application {
	public static void main(String[] args) {	
		PayrollApplication payrollApplication = new PayrollApplication();		
		payrollApplication.run();
	}
}