package views;

//import AddEmployeeListener;

import java.awt.EventQueue;
import java.awt.BorderLayout;

import javax.swing.*;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import PayrollApplication.EmployeeDAO;
import models.Employee;
import controllers.AddEmployeeController;
import controllers.DeleteEmployeeCancelButtonController;
import controllers.DeleteEmployeeSubmitButtonController;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class DeleteEmployeeWindow extends JFrame{

	private JButton btnCancel;
	private JButton btnDelete;
	private JComboBox comboBox; 
	private EmployeeDAO empDAO;
	ArrayList<Employee> employees; 
	
	public DeleteEmployeeWindow() {		
		initialize();
	}
	
	public void initialize(){


		//frame = new JFrame();
		this.setBounds(100, 100, 790, 454);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel headerLbl = new JLabel("XYZ Company - Payroll Administration");
		headerLbl.setBorder(BorderFactory.createLineBorder(Color.black));
		headerLbl.setForeground(Color.BLACK);
		headerLbl.setBackground(Color.WHITE);
		headerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		headerLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		headerLbl.setBounds(11, 11, 681, 70);
		this.getContentPane().add(headerLbl);		
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(314, 244, 103, 23);
		btnDelete.setEnabled(true);
		this.getContentPane().add(btnDelete);
		
		btnCancel = new JButton("Back");
		btnCancel.setBounds(40, 362, 103, 23);
		this.getContentPane().add(btnCancel);	
	
		
		
		
		showEmployeesList();
			
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	public void deleteEmployee(ActionListener listenForGoButtonClick){
		btnDelete.addActionListener(listenForGoButtonClick);
	}
	
	public void closeWindow(ActionListener e){
		btnCancel.addActionListener(e);
	}
	
	public void displayEmployees(){
		ArrayList<Employee> employees = this.empDAO.getEmployeesArray();
		
		try {
			this.empDAO.loadEmployees();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(Employee e : employees){
			//this.textAreaDisplayEmployees.append(e.toString() + "\n");
			//this.textAreaDisplayEmployees.append();
			System.out.println(e.toString());
		}
	}

	public void showEmployeesList(){
		
		
		try {
			EmployeeDAO.loadEmployees();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		

		displayEmployees();
		if(comboBox != null){
			this.getContentPane().remove(comboBox);
		}
		comboBox = new JComboBox();
		comboBox.setBounds(325, 180, 80, 23);
		
		employees = EmployeeDAO.getEmployeesArray();	

		for(Employee e : employees){
			comboBox.addItem(e.getEmployeeID());
		}
		
		this.getContentPane().add(comboBox);
		
		JLabel lblSelectA = new JLabel("Select employee by id");
		lblSelectA.setBounds(314, 146, 125, 23);
		this.getContentPane().add(lblSelectA);
	}

	public void setupDeleteFunction(ActionListener listenForGoButtonClick){
		btnDelete.addActionListener(listenForGoButtonClick);
	
	}

	public void setupCancelFunction(ActionListener listenForGoButtonClick){
		btnCancel.addActionListener(listenForGoButtonClick);
	}
		
	
	
}