package PayrollApplication;

//import AddEmployeeListener;

import java.awt.EventQueue;
import java.awt.BorderLayout;

import javax.swing.*;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import models.Employee;
import controllers.AddEmployeeController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

public class MainMenuWindow extends JFrame{
	private JButton btnDeleteEmployee;
	private JButton btnAddEmployee;
	private JButton btnEditAnEmployee; 
	private JButton btnViewAllEmployees;
	private JButton btnInsertTimeCard;
	private JButton exit;
	private JButton btnGo;
	private JButton btnRunPayrollWeekly;
	private JButton btnRunPayrollBiWeekly; 
	private JButton btnRunPayrollMonthly; 
	private EmployeeDAO empDAO;
	private JTextArea textAreaDisplayEmployees;
	private JTextArea textAreaPendingTransactions;
	private JScrollPane areaScrollPane; 
	
	private JComboBox dates;
	
	//create references to tables
	private JTable employeeTable;
	private JScrollPane employeeTableScroller;
	private JTable pendingTransactionsTable;
	private JScrollPane pendingTransactionsTableScroller;
	public ArrayList<Employee> employees;
	public DefaultTableModel dtm;
	JLabel lblEmployees;
	
	private GregorianCalendar currentDate;
	private GregorianCalendar fromDate;
	private GregorianCalendar toDate;
	
	public MainMenuWindow(EmployeeDAO empDAO) {
		this.empDAO = empDAO;
				
		this.displayEmployees();
		try {
			EmployeeDAO.loadEmployees();
			employees = EmployeeDAO.getEmployeesArray();
		} catch (SQLException e1) {
			 
			e1.printStackTrace();
		}
		initialize();	
	}
	
	public void initialize(){
		
		
		GregorianCalendar startOfPayPeriod = new GregorianCalendar();	
		
		/***** Setup the window Frame ****/
		this.setBounds(100, 100, 900, 480);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);		
				
		/*******Setup Label************/		
		JLabel headerLbl = new JLabel("XYZ Company - Payroll Administration");
		headerLbl.setBorder(BorderFactory.createLineBorder(Color.black));
		headerLbl.setForeground(Color.BLACK);
		headerLbl.setBackground(Color.WHITE);
		headerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		headerLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		headerLbl.setBounds(11, 11, 681, 70);
		this.getContentPane().add(headerLbl);
		
		JLabel lblPendingTransactions = new JLabel("Employees Pending Payments Table");
		lblPendingTransactions.setBounds(352, 96, 250, 14);
		getContentPane().add(lblPendingTransactions);
		
		lblEmployees = new JLabel("");
		lblEmployees.setBounds(332, 280, 200, 14);
		getContentPane().add(lblEmployees);

		/***** Setup buttons ****/
		btnAddEmployee = new JButton("Add new Employee");
		btnAddEmployee.setBounds(10, 112, 147, 23);
		this.getContentPane().add(btnAddEmployee);
		
		btnDeleteEmployee = new JButton("Delete an Employee");
		btnDeleteEmployee.setBounds(10, 142, 147, 23);
		this.getContentPane().add(btnDeleteEmployee);
				
		btnEditAnEmployee = new JButton("Edit an Employee");
		btnEditAnEmployee.setBounds(10, 172, 147, 23);
		this.getContentPane().add(btnEditAnEmployee);
		
		btnViewAllEmployees = new JButton("View all Employees");
		btnViewAllEmployees.setBounds(10, 202, 147, 23);
		this.getContentPane().add(btnViewAllEmployees);
		
		btnInsertTimeCard = new JButton("Post a Timecard");
		btnInsertTimeCard.setBounds(10, 232, 147, 23);
		this.getContentPane().add(btnInsertTimeCard);
		
		btnRunPayrollWeekly = new JButton("Run Payroll");
		btnRunPayrollWeekly.setBounds(550, 235, 280, 23);
		getContentPane().add(btnRunPayrollWeekly);

		/*btnRunPayrollBiWeekly = new JButton("Run BiWeekly Employees Payroll");
		btnRunPayrollBiWeekly.setBounds(550, 265, 280, 23);
		getContentPane().add(btnRunPayrollBiWeekly);
		
		btnRunPayrollMonthly = new JButton("Run Monthly Employees Payroll");
		btnRunPayrollMonthly.setBounds(550, 305, 280, 23);
		getContentPane().add(btnRunPayrollMonthly);		*/
		
		exit = new JButton("Exit");			
		exit.setBounds(20, 340, 103, 23);
		this.getContentPane().add(exit);					
			
		
		setupPayPeriod();
		displayTable();
		
		//this.updatePendingTransactionsTable();
	}	
	
	public JLabel getLblEmployees() {
		return lblEmployees;
	}

	public void setLblEmployees(JLabel lblEmployees) {
		this.lblEmployees = lblEmployees;
	}

	public void editEmployee(ActionListener listenForEditEmployeeButtonClick){
		btnEditAnEmployee.addActionListener(listenForEditEmployeeButtonClick);
	}	
	
	public void viewAllEmployees(ActionListener listenForviewAllEmployeesButtonClick){
		btnViewAllEmployees.addActionListener(listenForviewAllEmployeesButtonClick);
	}
	
	public void insertTimeCard(ActionListener listenForInsertTimeCardButtonClick){
		btnInsertTimeCard.addActionListener(listenForInsertTimeCardButtonClick);
	}
	
	public void runPayrollWeekly(ActionListener listenForRunPayrollButtonClick){
		btnRunPayrollWeekly.addActionListener(listenForRunPayrollButtonClick);
	}	
	
	public void runPayrollBiWeekly(ActionListener listenForRunPayrollButtonClick){
		btnRunPayrollBiWeekly.addActionListener(listenForRunPayrollButtonClick);
	}		
	
	public void runPayrollMonthly(ActionListener listenForRunPayrollButtonClick){
		btnRunPayrollMonthly.addActionListener(listenForRunPayrollButtonClick);
	}	
	
	public void openAddEmployeeWindow(ActionListener listenForGoButtonClick){
		btnAddEmployee.addActionListener(listenForGoButtonClick);
	}

	public void openDeleteEmployeeWindow(ActionListener listenForGoButtonClick){
		btnDeleteEmployee.addActionListener(listenForGoButtonClick);
	}
	
	public void quitProgram(ActionListener e){
		exit.addActionListener(e);
	}
	
	public void displayTable(){
	
		String[] pendingTransactionsTableColumnNames = {"EmpID", "Name", "Pay from", "Pay to", "Gross", "Deductions", "Net"};
		dtm = new DefaultTableModel(pendingTransactionsTableColumnNames, employees.size());
		
		pendingTransactionsTable = new JTable(dtm);
		pendingTransactionsTable.setPreferredScrollableViewportSize(new Dimension(500, 50));
		
		int rowNumPt = 0;
		int colNumPt = 0;
		
		pendingTransactionsTable.setFillsViewportHeight(true);
		pendingTransactionsTable.setBounds(181, 111, 700, 102);
		
		pendingTransactionsTableScroller = new JScrollPane(pendingTransactionsTable);
		pendingTransactionsTableScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		pendingTransactionsTableScroller.setBounds(181, 111, 700, 102);
		
		this.getContentPane().remove(pendingTransactionsTableScroller); 
		this.getContentPane().add(pendingTransactionsTableScroller); //, BorderLayout.SOUTH	
		
	}
	
	public void displayEmployees(){
		employees = EmployeeDAO.getEmployeesArray();
		
		try {
			EmployeeDAO.loadEmployees();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void updatePendingTransactionsTable(){

			payrollPendingTransactionsTable();
	}

	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}

	public ArrayList<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return employees;
	}
	
	public void displayWeekly(){
		/***** Setup Employee Table and display employees****/
		String[] pendingTransactionsTableColumnNames = {"EmpID", "Name", "Pay from", "Pay to", "Gross", "Deductions", "Net"};
		dtm = new DefaultTableModel(pendingTransactionsTableColumnNames, employees.size());
		
		pendingTransactionsTable = new JTable(dtm);
		pendingTransactionsTable.setPreferredScrollableViewportSize(new Dimension(500, 50));
		
		int rowNumPt = 0;
		int colNumPt = 0;

		for(Employee e : employees){
			String[] startOfPer; 
			
			if(e.getPayType().equals("hourly")){
					
				pendingTransactionsTable.setValueAt(e.getEmployeeID(), rowNumPt, colNumPt);
				pendingTransactionsTable.setValueAt(e.getFirstName(), rowNumPt, colNumPt+1);	
				
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
				
				currentDate =  new GregorianCalendar();
	
				currentDate.add(Calendar.DATE, -7);		
				pendingTransactionsTable.setValueAt(df.format(currentDate.getTime()), rowNumPt, colNumPt+2);
				currentDate.add(Calendar.DATE, 7);
				//pendingTransactionsTable.setValueAt(sdf.format(c.getTime()), rowNumPt, colNumPt+3);
				pendingTransactionsTable.setValueAt(df.format(currentDate.getTime()), rowNumPt, colNumPt+3);
				
				pendingTransactionsTable.setValueAt(e.calculatePay(), rowNumPt, colNumPt+4);
			
				pendingTransactionsTable.setValueAt(e.getUnionFees(), rowNumPt, colNumPt+5);
				//System.out.println("Pay: " + e.getPay() + "Deductions: " + e.getUnionFees());
				pendingTransactionsTable.setValueAt(e.getPay() - Double.parseDouble((e.getUnionFees())), rowNumPt, colNumPt+6);				
	
				rowNumPt++;
					
			}
		if(pendingTransactionsTableScroller != null){
			this.getContentPane().remove(pendingTransactionsTableScroller);
		}
		
		pendingTransactionsTable.setFillsViewportHeight(true);
		pendingTransactionsTable.setBounds(181, 111, 700, 102);
		
		pendingTransactionsTableScroller = new JScrollPane(pendingTransactionsTable);
		pendingTransactionsTableScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		pendingTransactionsTableScroller.setBounds(181, 111, 700, 102);
		
		//this.getContentPane().remove(pendingTransactionsTableScroller); 
		this.getContentPane().add(pendingTransactionsTableScroller); //, BorderLayout.SOUTH	
		}
	}

	public void displayBiWeekly(){
		/***** Setup Employee Table and display employees****/
		String[] pendingTransactionsTableColumnNames = {"EmpID", "Name", "Pay from", "Pay to", "Gross", "Deductions", "Net"};
		dtm = new DefaultTableModel(pendingTransactionsTableColumnNames, employees.size());
		
		pendingTransactionsTable = new JTable(dtm);
		pendingTransactionsTable.setPreferredScrollableViewportSize(new Dimension(500, 50));
		
		int rowNumPt = 0;
		int colNumPt = 0;

		for(Employee e : employees){
		//System.out.println("Employee Num: " + e.getEmployeeID());
			
		
			String[] startOfPer; 
			
			if(e.getPayType().equals("salary")){
				
				
				pendingTransactionsTable.setValueAt(e.getEmployeeID(), rowNumPt, colNumPt);
				pendingTransactionsTable.setValueAt(e.getFirstName(), rowNumPt, colNumPt+1);	
				
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
				
				currentDate =  new GregorianCalendar();
	
				currentDate.add(Calendar.DATE, -14);		
				pendingTransactionsTable.setValueAt(df.format(currentDate.getTime()), rowNumPt, colNumPt+2);
				currentDate.add(Calendar.DATE, 14);
				//pendingTransactionsTable.setValueAt(sdf.format(c.getTime()), rowNumPt, colNumPt+3);
				pendingTransactionsTable.setValueAt(df.format(currentDate.getTime()), rowNumPt, colNumPt+3);
				
				pendingTransactionsTable.setValueAt(e.calculatePay(), rowNumPt, colNumPt+4);
			
				pendingTransactionsTable.setValueAt(e.getUnionFees(), rowNumPt, colNumPt+5);
				//System.out.println("Pay: " + e.getPay() + "Deductions: " + e.getUnionFees());
				pendingTransactionsTable.setValueAt(e.getPay() - Double.parseDouble((e.getUnionFees())), rowNumPt, colNumPt+6);				
	
				rowNumPt++;
					
			}
		if(pendingTransactionsTableScroller != null){
			this.getContentPane().remove(pendingTransactionsTableScroller);
		}
		
		pendingTransactionsTable.setFillsViewportHeight(true);
		pendingTransactionsTable.setBounds(181, 111, 700, 102);
		
		pendingTransactionsTableScroller = new JScrollPane(pendingTransactionsTable);
		pendingTransactionsTableScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		pendingTransactionsTableScroller.setBounds(181, 111, 700, 102);
		
		this.getContentPane().remove(pendingTransactionsTableScroller); 
		this.getContentPane().add(pendingTransactionsTableScroller); //, BorderLayout.SOUTH	
		}
	}
	
	public void refreshEmployees(){
		this.displayEmployees();
		try {
			EmployeeDAO.loadEmployees();
			employees = EmployeeDAO.getEmployeesArray();
		} catch (SQLException e1) {
			 
			e1.printStackTrace();
		}
	}

	public void displayMonthly(){
		/***** Setup Employee Table and display employees****/
		String[] pendingTransactionsTableColumnNames = {"EmpID", "Name", "Pay from", "Pay to", "Gross", "Deductions", "Net"};
		dtm = new DefaultTableModel(pendingTransactionsTableColumnNames, employees.size());
		
		pendingTransactionsTable = new JTable(dtm);
		pendingTransactionsTable.setPreferredScrollableViewportSize(new Dimension(500, 50));
		
		int rowNumPt = 0;
		int colNumPt = 0;

		for(Employee e : employees){
		//System.out.println("Employee Num: " + e.getEmployeeID());
			
		
			String[] startOfPer; 
			
			if(e.getPayType().equals("commission")){
				
				
				pendingTransactionsTable.setValueAt(e.getEmployeeID(), rowNumPt, colNumPt);
				pendingTransactionsTable.setValueAt(e.getFirstName(), rowNumPt, colNumPt+1);	
				
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
				
				currentDate =  new GregorianCalendar();
	
				currentDate.add(Calendar.DATE, -30);		
				pendingTransactionsTable.setValueAt(df.format(currentDate.getTime()), rowNumPt, colNumPt+2);
				currentDate.add(Calendar.DATE, 30);
				//pendingTransactionsTable.setValueAt(sdf.format(c.getTime()), rowNumPt, colNumPt+3);
				pendingTransactionsTable.setValueAt(df.format(currentDate.getTime()), rowNumPt, colNumPt+3);
				
				pendingTransactionsTable.setValueAt(e.calculatePay(), rowNumPt, colNumPt+4);
			
				pendingTransactionsTable.setValueAt(e.getUnionFees(), rowNumPt, colNumPt+5);
				//System.out.println("Pay: " + e.getPay() + "Deductions: " + e.getUnionFees());
				pendingTransactionsTable.setValueAt(e.getPay() - Double.parseDouble((e.getUnionFees())), rowNumPt, colNumPt+6);				
	
				rowNumPt++;
					
			}
		if(pendingTransactionsTableScroller != null){
			this.getContentPane().remove(pendingTransactionsTableScroller);
		}
		
		pendingTransactionsTable.setFillsViewportHeight(true);
		pendingTransactionsTable.setBounds(181, 111, 700, 102);
		
		pendingTransactionsTableScroller = new JScrollPane(pendingTransactionsTable);
		pendingTransactionsTableScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		pendingTransactionsTableScroller.setBounds(181, 111, 700, 102);
		
		this.getContentPane().remove(pendingTransactionsTableScroller); 
		this.getContentPane().add(pendingTransactionsTableScroller); //, BorderLayout.SOUTH	
		}
	}
	
	public void payrollPendingTransactionsTable() {	
		/*for(Employee e : employees){
			System.out.println(" Employee still in: " + e.toString());			
		}*/
				
		/***** Setup Employee Table and display employees****/
		String[] pendingTransactionsTableColumnNames = {"EmpID", "Name", "Pay from", "Pay to", "Gross", "Deductions", "Net"};
		dtm = new DefaultTableModel(pendingTransactionsTableColumnNames, employees.size());
		
		pendingTransactionsTable = new JTable(dtm);
		pendingTransactionsTable.setPreferredScrollableViewportSize(new Dimension(500, 50));
		
		int rowNumPt = 0;
		int colNumPt = 0;
		System.out.println("Employees size = " + employees.size());
		if(employees.size() <= 0){
			this.getContentPane().remove(pendingTransactionsTableScroller);
			
			pendingTransactionsTable.setFillsViewportHeight(true);
			pendingTransactionsTable.setBounds(181, 111, 700, 102);
			
			pendingTransactionsTableScroller = new JScrollPane(pendingTransactionsTable);
			pendingTransactionsTableScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			
			pendingTransactionsTableScroller.setBounds(181, 111, 700, 102);
			
			this.getContentPane().remove(pendingTransactionsTableScroller); 
			this.getContentPane().add(pendingTransactionsTableScroller); //, BorderLayout.SOUTH	
			
		}
		
		
		for(Employee e : employees){
		//System.out.println("Employee Num: " + e.getEmployeeID());
		pendingTransactionsTable.setValueAt(e.getEmployeeID(), rowNumPt, colNumPt);
		pendingTransactionsTable.setValueAt(e.getFirstName(), rowNumPt, colNumPt+1);				
		
				String[] startOfPer; 
			
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
					
					currentDate =  new GregorianCalendar();
						
					if(e.getPayType().equals("hourly")){
						
						pendingTransactionsTable.setValueAt(df.format(currentDate.getTime()), rowNumPt, colNumPt+3);
						currentDate.add(Calendar.DATE, -7);	
						pendingTransactionsTable.setValueAt(df.format(currentDate.getTime()), rowNumPt, colNumPt+2);

					} else if(e.getPayType().equals("salary")){
						pendingTransactionsTable.setValueAt(df.format(currentDate.getTime()), rowNumPt, colNumPt+3);
						currentDate.add(Calendar.DATE, -14);	
						pendingTransactionsTable.setValueAt(df.format(currentDate.getTime()), rowNumPt, colNumPt+2);

					} else if(e.getPayType().equals("commission")){
						pendingTransactionsTable.setValueAt(df.format(currentDate.getTime()), rowNumPt, colNumPt+3);
						currentDate.add(Calendar.DATE, -30);	
						pendingTransactionsTable.setValueAt(df.format(currentDate.getTime()), rowNumPt, colNumPt+2);

					}

					pendingTransactionsTable.setValueAt(e.calculatePay(), rowNumPt, colNumPt+4);
				
				pendingTransactionsTable.setValueAt(e.getUnionFees(), rowNumPt, colNumPt+5);

				pendingTransactionsTable.setValueAt(e.getPay() - Double.parseDouble((e.getUnionFees())), rowNumPt, colNumPt+6);				
		
				rowNumPt++;
		
		if(pendingTransactionsTableScroller != null){
			this.getContentPane().remove(pendingTransactionsTableScroller);
		}
		
		pendingTransactionsTable.setFillsViewportHeight(true);
		pendingTransactionsTable.setBounds(181, 111, 700, 102);
		
		pendingTransactionsTableScroller = new JScrollPane(pendingTransactionsTable);
		pendingTransactionsTableScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		pendingTransactionsTableScroller.setBounds(181, 111, 700, 102);
		
		this.getContentPane().remove(pendingTransactionsTableScroller); 
		this.getContentPane().add(pendingTransactionsTableScroller); //, BorderLayout.SOUTH	
		
	}
	}
	
	public void setupPayPeriod(){
		/***** Setup ComboBox ****/	
		JLabel lblPendingTransactions = new JLabel("Select PayPeriod: ");
		lblPendingTransactions.setBounds(250, 302, 250, 14);
		getContentPane().add(lblPendingTransactions);
		
		dates = new JComboBox();
		dates.setBounds(355, 300, 150, 20);
		
		
/*		dates.addItem("weekly");
		dates.addItem("biweekly");
		dates.addItem("monthly");*/
		
		dates.addItem("weekly");
		dates.addItem("biweekly");
		dates.addItem("monthly");
		
		/*SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
				
		GregorianCalendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, 2014);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 1);
		
		SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yy");
		int counter = 0;
		for(int maxdays = 11; maxdays > 0; maxdays--){					
			dates.addItem(df1.format(cal.getTime()));			
			cal.add(cal.DATE, 30);		
					
			System.out.println("Pay Period Start Date: " + df1.format(cal.getTime())); 
		}*/
		this.getContentPane().add(dates);
		
		dates.addActionListener(new ActionListener(){			
			@Override
			public void actionPerformed(ActionEvent e){
				
				if(dates.getSelectedItem().toString().equals("weekly")){
					System.out.println("Payperiod: weekly");
					displayWeekly();
					/*fromDate = currentDate;
					currentDate.add(Calendar.DATE, 7);*/
				} 
				
				if(dates.getSelectedItem().toString().equals("biweekly")){
					System.out.println("Payperiod: biweekly");
					displayBiWeekly();
				}
				
				if(dates.getSelectedItem().toString().equals("monthly")){
					System.out.println("Payperiod: monthly");
					
					displayMonthly();
/*					fromDate = currentDate;
					currentDate.add(Calendar.DATE, 30);*/
				}
			}
		});		
	}

	public JComboBox getDates() {
		return dates;
	}

	public void setDates(JComboBox dates) {
		this.dates = dates;
	}


}