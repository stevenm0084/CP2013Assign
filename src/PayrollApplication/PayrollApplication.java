package PayrollApplication;


public class PayrollApplication extends RunApplication{
	
	private EmployeeDAO empDAO;
	private MainMenuWindow main;
	private MainMenuWindowController c;
		
	
	public PayrollApplication(){

	}

	@Override
	public void run() {
		empDAO = new EmployeeDAO();
		main = new MainMenuWindow(empDAO);
		c = new MainMenuWindowController(main, empDAO);
		main.setVisible(true);	
		
	}

}
