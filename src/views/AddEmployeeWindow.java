package views;


import java.awt.EventQueue;
import java.awt.BorderLayout;

import javax.swing.*;

import java.awt.Font;

import javax.swing.SwingConstants;

import PayrollApplication.EmployeeDAO;
import Schedules.PaymentSchedule;
import controllers.AddEmployeeController;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AddEmployeeWindow extends JFrame {



	private JButton btnCancel;
	private JButton btnSubmit;

	private JCheckBox chckbxUnionYn;
	private JCheckBox chckbxComRate;
	private JCheckBox chckbxPayTo;
	private JCheckBox addTestEmployee;
	
	private JRadioButton radioButCommissioned;
	private JRadioButton radioButSalary;
	private JRadioButton radioButHourly;

	private JTextField txtFieldFirstName;
	private JTextField txtFieldLastName;
	private JTextField txtFieldHomeAddress;
	private JTextField txtFieldContactNumber;
	private JTextField txtFieldPaymentMethod;
	private ButtonGroup radioButtonGroup;
	
	private EmployeeDAO empDAO;
	private JTextField textFieldHoulryRate;
	private JTextField textFieldSalary;
	private JTextField textFieldCommRate;
	private JTextField textFieldCommSalary;
	private JTextField textFieldUnionFees;
	boolean enableSubmitButton;
	
	private JComboBox comboBox;
	private JCheckBox chckbxInUnion;
	
	boolean isHourlyEmployee;
	boolean isSalaryEmployee;
	boolean isCommissionedEmployee;
	
	private ArrayList<JTextField> listOfTextFields;
	

	public AddEmployeeWindow() {
		initialize();
		
	}
	
	public AddEmployeeWindow(EmployeeDAO empDAO) {
		initialize();		
	}
	
	public void initialize(){
		isHourlyEmployee = false;
		isSalaryEmployee = false;
		isCommissionedEmployee = false;
		enableSubmitButton = true;
		
		this.setBounds(100, 100, 790, 454);
		this.setDefaultCloseOperation(AddEmployeeWindow.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(480, 382, 103, 23);
		btnSubmit.setEnabled(enableSubmitButton);
		this.getContentPane().add(btnSubmit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(224, 382, 103, 23);
		this.getContentPane().add(btnCancel);	
		
		/***** Setup Labels ****/
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(86, 123, 66, 14);
		this.getContentPane().add(lblFirstName);

		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(86, 151, 66, 14);
		this.getContentPane().add(lblLastName);
		
		JLabel lblHomeAddress = new JLabel("Home Address:");
		lblHomeAddress.setBounds(66, 176, 86, 14);
		this.getContentPane().add(lblHomeAddress);
		
		JLabel lblAddEmployee = new JLabel("Add Employee");
		lblAddEmployee.setBounds(21, 89, 131, 23);
		lblAddEmployee.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.getContentPane().add(lblAddEmployee);
				
		JLabel headerLbl = new JLabel("Add Employee");
		headerLbl.setBounds(11, 11, 681, 70);
		headerLbl.setBorder(BorderFactory.createLineBorder(Color.black));
		headerLbl.setForeground(Color.BLACK);
		headerLbl.setBackground(Color.WHITE);
		headerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		headerLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.getContentPane().add(headerLbl);
		
		JLabel lblHomeContactNumber = new JLabel("Home Contact Number:");
		lblHomeContactNumber.setBounds(10, 204, 119, 14);
		this.getContentPane().add(lblHomeContactNumber);		
		
		JLabel lblUnionFees = new JLabel("Fees");
		lblUnionFees.setBounds(263, 305, 36, 23);
		this.getContentPane().add(lblUnionFees);
		
		JLabel lblpaymentMethod = new JLabel("Payment Method");
		lblpaymentMethod.setBounds(25, 232, 119, 14);
		this.getContentPane().add(lblpaymentMethod);			
		
		JLabel lblCommRate = new JLabel("Rate");
		lblCommRate.setBounds(590, 245, 56, 14);
		this.getContentPane().add(lblCommRate);
		
		JLabel lblHourlyRate = new JLabel("Rate");
		lblHourlyRate.setBounds(600, 112, 48, 14);
		this.getContentPane().add(lblHourlyRate);
		
		/***** Setup Checkboxes and Radio Buttons****/		
		radioButHourly = new JRadioButton("Hourly");
		radioButHourly.setBounds(446, 123, 66, 23);
		radioButHourly.setActionCommand("Hourly");
		this.getContentPane().add(radioButHourly);
		
		radioButSalary = new JRadioButton("Salary");
		radioButSalary.setBounds(446, 178, 66, 23);
		radioButSalary.setActionCommand("Salery");
		this.getContentPane().add(radioButSalary);
	
		radioButCommissioned = new JRadioButton("Commissioned");
		radioButCommissioned.setBounds(446, 241, 119, 23);
		radioButCommissioned.setActionCommand("Sales");
		this.getContentPane().add(radioButCommissioned);
		
		/***** Setup radioButtonGroup ****/
		radioButtonGroup = new ButtonGroup();
		radioButtonGroup.add(radioButCommissioned);
		radioButtonGroup.add(radioButSalary);
		radioButtonGroup.add(radioButHourly);		
		
		/***** Setup textfields ****/
		txtFieldFirstName = new JTextField();
		txtFieldFirstName.setBounds(162, 123, 202, 20);
		txtFieldFirstName.setColumns(10);
		this.getContentPane().add(txtFieldFirstName);

		txtFieldLastName = new JTextField();
		txtFieldLastName.setBounds(162, 148, 202, 20);
		txtFieldLastName.setColumns(10);
		this.getContentPane().add(txtFieldLastName);
	
		txtFieldHomeAddress = new JTextField();
		txtFieldHomeAddress.setBounds(162, 171, 202, 20);
		txtFieldHomeAddress.setColumns(10);
		this.getContentPane().add(txtFieldHomeAddress);
	
		txtFieldContactNumber = new JTextField();
		txtFieldContactNumber.setBounds(162, 201, 202, 20);
		txtFieldContactNumber.setColumns(10);
		this.getContentPane().add(txtFieldContactNumber);
		
		comboBox = new JComboBox();
		comboBox.setBounds(162, 232, 202, 20);
		comboBox.addItem("weekly");
		comboBox.addItem("biweekly");
		comboBox.addItem("monthly");
		this.getContentPane().add(comboBox);
		
		/***** Setup add textfields to a list of textfields ****/
		listOfTextFields = new ArrayList<JTextField>();
		listOfTextFields.add(txtFieldFirstName);
		listOfTextFields.add(txtFieldLastName);
		listOfTextFields.add(txtFieldHomeAddress);
		listOfTextFields.add(txtFieldContactNumber);
		
		textFieldHoulryRate = new JTextField();
		textFieldHoulryRate.setBackground(Color.WHITE);
		textFieldHoulryRate.setColumns(10);
		textFieldHoulryRate.setBounds(562, 131, 130, 20);
		textFieldHoulryRate.setEditable(false);
		this.getContentPane().add(textFieldHoulryRate);		
		
		textFieldUnionFees = new JTextField();
		textFieldUnionFees.setEditable(false);
		textFieldUnionFees.setColumns(10);
		textFieldUnionFees.setBackground(Color.WHITE);
		textFieldUnionFees.setBounds(211, 323, 130, 20);
		this.getContentPane().add(textFieldUnionFees);
		
		textFieldSalary = new JTextField();
		textFieldSalary.setColumns(10);
		textFieldSalary.setBackground(Color.WHITE);
		textFieldSalary.setBounds(562, 198, 130, 20);
		textFieldSalary.setEditable(false);
		this.getContentPane().add(textFieldSalary);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setBounds(600, 174, 48, 14);
		this.getContentPane().add(lblSalary);
		
		textFieldCommRate = new JTextField();
		textFieldCommRate.setColumns(10);
		textFieldCommRate.setBackground(Color.WHITE);
		textFieldCommRate.setBounds(562, 262, 130, 20);
		textFieldCommRate.setEditable(false);
		this.getContentPane().add(textFieldCommRate);
		
		textFieldCommSalary = new JTextField();
		textFieldCommSalary.setColumns(10);
		textFieldCommSalary.setBackground(Color.WHITE);
		textFieldCommSalary.setBounds(562, 303, 130, 20);
		textFieldCommSalary.setEditable(false);
		this.getContentPane().add(textFieldCommSalary);
		
		JLabel lblCommSalary = new JLabel("Salary");
		lblCommSalary.setBounds(590, 290, 56, 14);
		this.getContentPane().add(lblCommSalary);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(428, 171, 297, 8);
		this.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(428, 226, 297, 8);
		this.getContentPane().add(separator_1);
		
		chckbxInUnion = new JCheckBox("In Union");
		chckbxInUnion.setBounds(160, 305, 97, 23);
		this.getContentPane().add(chckbxInUnion);
		
		addTestEmployee= new JCheckBox("Check to Add test employee");
		addTestEmployee.setBounds(20, 350, 250, 23);
		this.getContentPane().add(addTestEmployee);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(66, 229, 313, 14);
		this.getContentPane().add(separator_3);	
		
		this.updateComboBoxes();
		
		this.addTestEmployee();

	}

	public JRadioButton getRadioButCommissioned() {
		return radioButCommissioned;
	}

	public void setRadioButCommissioned(JRadioButton radioButCommissioned) {
		this.radioButCommissioned = radioButCommissioned;
	}

	public JRadioButton getRadioButSalary() {
		return radioButSalary;
	}

	public void setRadioButSalary(JRadioButton radioButSalary) {
		this.radioButSalary = radioButSalary;
	}

	public JRadioButton getRadioButHourly() {
		return radioButHourly;
	}

	public void setRadioButHourly(JRadioButton radioButHourly) {
		this.radioButHourly = radioButHourly;
	}

	public ButtonGroup getRadioButtonGroup() {
		return radioButtonGroup;
	}

	public void setRadioButtonGroup(ButtonGroup radioButtonGroup) {
		this.radioButtonGroup = radioButtonGroup;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	public String getPayType(){
		String paytype = radioButtonGroup.getSelection().getActionCommand();		
		return paytype;
	}
	
	public boolean isHourlyEmployee(){
		return this.isHourlyEmployee;
	}
	
	public boolean isSalaryEmployee(){
		return this.isSalaryEmployee;
	}

	public boolean isCommissionedEmployee(){
		return this.isCommissionedEmployee;
	}

	public JTextField getTxtFieldFirstName() {
		return txtFieldFirstName;
	}
	
	public JTextField getTxtFieldLastName() {
		return txtFieldLastName;
	}	

	public JTextField getTxtFieldHomeAddress() {
		return txtFieldHomeAddress;
	}	
	
	public JTextField getTxtFieldPaymentMethod() {
		return txtFieldPaymentMethod;
	}

	public void setTxtFieldPaymentMethod(JTextField txtFieldPaymentMethod) {
		this.txtFieldPaymentMethod = txtFieldPaymentMethod;
	}

	public JTextField getTextFieldSalary() {
		return textFieldSalary;
	}

	public JTextField getTextFieldHoulryRate() {
		return textFieldHoulryRate;
	}

	public JTextField getTextFieldCommRate() {
		return textFieldCommRate;
	}

	public JTextField getTextFieldCommSalary() {
		return textFieldCommSalary;
	}

	public JTextField getTxtFieldContactNumber() {
		return txtFieldContactNumber;
	}	
	
	public void addEmployeeListener(ActionListener listenForCalcButton) {
		
		System.out.println("method addEmployeeListener has been executed");
		btnSubmit.addActionListener(listenForCalcButton);	
	}
	
	public void addCancelEmployeeWindow(ActionListener listenForCancelButton){
		btnCancel.addActionListener(listenForCancelButton);
	}
	
	public void setBtnSubmitEnabled(boolean btnIsEnabled){
		btnSubmit.setEnabled(btnIsEnabled);
	}

	public JCheckBox getChckbxInUnion() {
		return chckbxInUnion;
	}

	public JTextField getTextFieldUnionFees() {
		return textFieldUnionFees;
	}

	
	public void updateComboBoxes(){
		/***** Setup Radio Button ActionListeners****/
		radioButHourly.addActionListener(new ActionListener(){			
			@Override
			public void actionPerformed(ActionEvent arg0){
				System.out.println("CLicked hourly");
				textFieldHoulryRate.setEditable(true);
				textFieldSalary.setEditable(false);
				textFieldCommRate.setEditable(false);
				textFieldCommSalary.setEditable(false);
				
				isHourlyEmployee = true;
				isSalaryEmployee = false;
				isCommissionedEmployee = false;
				
			}
		});
		
		radioButSalary.addActionListener(new ActionListener(){			
			@Override
			public void actionPerformed(ActionEvent arg0){
				System.out.println("CLicked salary");
				textFieldHoulryRate.setEditable(false);
				textFieldSalary.setEditable(true);
				textFieldCommRate.setEditable(false);
				textFieldCommSalary.setEditable(false);				
				isHourlyEmployee = false;
				isSalaryEmployee = true;
				isCommissionedEmployee = false;
			}
		});
		
		radioButCommissioned.addActionListener(new ActionListener(){			
			@Override
			public void actionPerformed(ActionEvent arg0){
				System.out.println("CLicked Sales");
				textFieldHoulryRate.setEditable(false);
				textFieldSalary.setEditable(false);
				textFieldCommRate.setEditable(true);
				textFieldCommSalary.setEditable(true);				
				isHourlyEmployee = false;
				isSalaryEmployee = false;
				isCommissionedEmployee = true;
				
			}
		});		
		
		chckbxInUnion.addActionListener(new ActionListener(){			
			@Override
			public void actionPerformed(ActionEvent arg0){
				System.out.println("Checked in Union");
				if(chckbxInUnion.isSelected()){
					textFieldUnionFees.setEditable(true);				
				} else{
					textFieldUnionFees.setEditable(false);
				}
			}
		});		
		
		
		
/*		comboBox.addActionListener(new ActionListener(){			
			@Override
			public void actionPerformed(ActionEvent arg0){
				System.out.println(comboBox.getSelectedItem().toString());

			}
		});	*/
	}
	
	public void addTestEmployee(){
		addTestEmployee.addActionListener(new ActionListener(){		
			@Override
			public void actionPerformed(ActionEvent arg0){				
				txtFieldFirstName.setText("Bob");
				txtFieldLastName.setText("Smith");
				txtFieldHomeAddress.setText("12 Smith st Annandale");
				txtFieldContactNumber.setText("098746532");		
				
				textFieldUnionFees.setText("100");
				radioButHourly.setSelected(true);
				chckbxInUnion.setSelected(true);		
				
				textFieldHoulryRate.setText("25");	
			}
		});
	}
}