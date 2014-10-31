package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controllers.CloseViewAllEmployeesWindowController;
import models.Employee;
import PayrollApplication.EmployeeDAO;

public class ViewAllEmployeesWindow extends JFrame{
	
	private JTable employeeTable;
	private JScrollPane employeeTableScroller;
	private JTable pendingTransactionsTable;
	private JScrollPane pendingTransactionsTableScroller;
	private JButton btnBack;




	public ViewAllEmployeesWindow() {//pass in model later
		initialize();
		
	}

	
	public void initialize(){

		this.setBounds(100, 100, 1100, 500);
		this.setDefaultCloseOperation(AddEmployeeWindow.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(224, 382, 103, 23);
		this.getContentPane().add(btnBack);
	
		
		/***** Setup Labels ****/
		JLabel lblFirstName = new JLabel("Employees:");
		lblFirstName.setBounds(40, 123, 150, 14);
		this.getContentPane().add(lblFirstName);

		
		/*Load Employees from databse into empty array of employees*/
		try {
			EmployeeDAO.loadEmployees();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<Employee> employees = EmployeeDAO.getEmployeesArray();
		
		/***** Setup Employee Table and display employees****/	
		String[] employeeTableColumnNames = {"EmpID", "FirstName", "LastName", "Address", "ContactNo.", "PayType", "PayRate", "Union", "CommissionPercentage", "UnionFees", "PayMethod"};

		employeeTable = new JTable(new DefaultTableModel(employeeTableColumnNames, employees.size()));
		employeeTable.setPreferredScrollableViewportSize(new Dimension(700, 700));

		int rowNumEt = 0;
		int colNumEt = 0;

		for(Employee e : employees){
			employeeTable.setValueAt(e.getEmployeeID(), rowNumEt, colNumEt);
			employeeTable.setValueAt(e.getFirstName(), rowNumEt, colNumEt+1);
			employeeTable.setValueAt(e.getLastName(), rowNumEt, colNumEt+2);
			employeeTable.setValueAt(e.getHomeAddress(), rowNumEt, colNumEt+3);
			employeeTable.setValueAt(e.getContactNum(), rowNumEt, colNumEt+4);		
			employeeTable.setValueAt(e.getPayType(), rowNumEt, colNumEt+5);
			employeeTable.setValueAt(e.getPayRate(), rowNumEt, colNumEt+6);
			employeeTable.setValueAt(e.getInUnion(), rowNumEt, colNumEt+7);
			employeeTable.setValueAt(e.getCommissionPercentage(), rowNumEt, colNumEt+8);
			employeeTable.setValueAt(e.getUnionFees(), rowNumEt, colNumEt+9);
			employeeTable.setValueAt(e.getPaymentMethod(), rowNumEt, colNumEt+10);
			rowNumEt++;
		}
		
		employeeTable.setFillsViewportHeight(true);
		employeeTable.setBounds(120, 111, 850, 200); 
		
		employeeTableScroller = new JScrollPane(employeeTable);
		employeeTableScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		//tableScroller.setPreferredSize(new Dimension(500, 50));
		employeeTableScroller.setBounds(120, 111, 730, 200); //470, 102
		this.getContentPane().add(employeeTableScroller); //, BorderLayout.SOUTH
	}

	public void addCancelViewAllEmployeesWindow(ActionListener listenForGoButtonClick) {
		btnBack.addActionListener(listenForGoButtonClick);
		
	}

}
