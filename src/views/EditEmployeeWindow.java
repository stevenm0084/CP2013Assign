package views;

import java.awt.Color;
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
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import models.Employee;
import PayrollApplication.EmployeeDAO;

public class EditEmployeeWindow extends JFrame{
	

	private JButton btnCancel;
	private JButton btnSubmit;

	private JTextField txtFieldFirstName;
	private JTextField txtFieldLastName;
	private JTextField txtFieldHomeAddress;
	private JTextField txtFieldContactNumber;

	private JTextField textFieldUnionFees;
	
	private JComboBox comboBox; 
	
	private JCheckBox chckbxUnionYn;
	private JCheckBox chckbxComRate;
	private JCheckBox chckbxPayTo;
	
	private JRadioButton radioButHourly;
	private JRadioButton radioButSalary;
	private JRadioButton radioButCommissioned;
	
	private JComboBox comboBoxPaySchedule;

	
	private ButtonGroup radioButtonGroup;
	
	private EmployeeDAO empDAO;
	private JTextField textFieldHoulryRate;
	private JTextField textFieldSalary;
	private JTextField textFieldCommRate;
	private JTextField textFieldCommSalary;

	boolean enableSubmitButton;
	private JCheckBox chckbxInUnion;

	private ArrayList<JTextField> listOfTextFields;
	private ArrayList<Object> employeeDetails;
	
	
	boolean isHourlyEmployee;
	boolean isSalaryEmployee;
	boolean isCommissionedEmployee;
	
	ArrayList<Employee> employees;
	

	public EditEmployeeWindow() {
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
	
		/***** Setup ComboBox List of Employees ****/
		comboBoxPaySchedule = new JComboBox();
		comboBoxPaySchedule.setBounds(162, 232, 202, 20);
		comboBoxPaySchedule.addItem("bank");
		comboBoxPaySchedule.addItem("cheque");
		this.getContentPane().add(comboBoxPaySchedule);
		
		comboBox = new JComboBox();
		comboBox.setBounds(200, 80, 80, 23);
		
		try {
			EmployeeDAO.loadEmployees();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		employees = EmployeeDAO.getEmployeesArray();

		for(Employee e : employees){
			comboBox.addItem(e.getEmployeeID());
			//System.out.println(e);
		}
		

		this.getContentPane().add(comboBox);
		
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
		
		JLabel lblAddEmployee = new JLabel("Edit Employee");
		lblAddEmployee.setBounds(21, 89, 131, 23);
		lblAddEmployee.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.getContentPane().add(lblAddEmployee);
				
		JLabel headerLbl = new JLabel("Edit Employee");
		headerLbl.setBounds(11, 11, 681, 70);
		headerLbl.setBorder(BorderFactory.createLineBorder(Color.black));
		headerLbl.setForeground(Color.BLACK);
		headerLbl.setBackground(Color.WHITE);
		headerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		headerLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.getContentPane().add(headerLbl);
		
		JLabel lblHomeContactNumber = new JLabel("Home Contact Number:");
		lblHomeContactNumber.setBounds(33, 204, 119, 14);
		this.getContentPane().add(lblHomeContactNumber);
		
/*		JLabel lblAddEmployee = new JLabel("Add Employee");
		lblAddEmployee.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAddEmployee.setBounds(278, 52, 155, 23);
		this.getContentPane().add(lblAddEmployee);	*/		
		
		JLabel lblCommRate = new JLabel("Rate");
		lblCommRate.setBounds(590, 245, 56, 14);
		this.getContentPane().add(lblCommRate);


		
		
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
		
		JLabel lblHourlyRate = new JLabel("Rate");
		lblHourlyRate.setBounds(600, 112, 48, 14);
		this.getContentPane().add(lblHourlyRate);
		
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
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(21, 351, 704, 8);
		this.getContentPane().add(separator_2);
		
		chckbxInUnion = new JCheckBox("In Union");
		chckbxInUnion.setBounds(160, 280, 97, 23);
		this.getContentPane().add(chckbxInUnion);
		
		textFieldUnionFees = new JTextField();
		textFieldUnionFees.setEditable(false);
		textFieldUnionFees.setColumns(10);
		textFieldUnionFees.setBackground(Color.WHITE);
		textFieldUnionFees.setBounds(211, 300, 130, 20);
		this.getContentPane().add(textFieldUnionFees);
		
		JLabel lblUnionFees = new JLabel("Fees");
		lblUnionFees.setBounds(263, 280, 36, 23);
		this.getContentPane().add(lblUnionFees);
		
/*		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(66, 229, 313, 14);
		this.getContentPane().add(separator_3);	*/
		/***** Setup Radio Button ActionListeners****/
		radioButHourly.addActionListener(new ActionListener(){			
			@Override
			public void actionPerformed(ActionEvent arg0){
				System.out.println("CLicked hourly");
				textFieldHoulryRate.setEditable(true);
				textFieldSalary.setEditable(false);
				textFieldCommRate.setEditable(false);
				textFieldCommSalary.setEditable(false);
				
				 textFieldHoulryRate.setText("");
				 textFieldSalary.setText("");
				 textFieldCommSalary.setText("");
				 textFieldUnionFees.setText("");
				
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
				
				 textFieldHoulryRate.setText("");
				 textFieldSalary.setText("");
				 textFieldCommSalary.setText("");
				 textFieldUnionFees.setText("");
				
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
				
				 textFieldHoulryRate.setText("");
				 textFieldSalary.setText("");
				 textFieldCommSalary.setText("");
				 textFieldUnionFees.setText("");
				
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
				

				 textFieldUnionFees.setText("");
			}
		});			
		
		/****** Get the employee's details ***********/
		this.fillInDetails();
		
		comboBox.addActionListener(new ActionListener(){			
			@Override
			public void actionPerformed(ActionEvent arg0){
				fillInDetails();
			}
		});			
	}

	public void fillInDetails(){
		
	
/*		try {
			this.employeeDetails = EmployeeDAO.getEmployee(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 String firstName = (String) employeeDetails.get(0);
		 String lastName = (String) employeeDetails.get(1);
		 String homeAddress = (String) employeeDetails.get(2);
		 String contactNum = (String) employeeDetails.get(3);
		 String payType = (String) employeeDetails.get(4);			 			 
		 String payRate = (String) employeeDetails.get(5);
		 String inUnion = (String) employeeDetails.get(6);
		 String commissionPercentage = (String) employeeDetails.get(7);
		 String unionFees = (String) employeeDetails.get(8);
		 int empID = (int) employeeDetails.get(9);
		 String hourlyTotal = (String) employeeDetails.get(10);*/
		 this.txtFieldFirstName.setText("");
		 this.txtFieldLastName.setText("");
		 this.txtFieldHomeAddress.setText("");
		 this.txtFieldContactNumber.setText("");
		 
		 radioButHourly.setSelected(false);
		 radioButSalary.setSelected(false);
		 radioButCommissioned.setSelected(false);
		 chckbxInUnion.setSelected(false);
		 
		 textFieldHoulryRate.setText("");
		 textFieldSalary.setText("");
		 textFieldCommSalary.setText("");
		 textFieldUnionFees.setText("");
		 
		 for(Employee e : this.employees){					
			 
			 if(getComboBox().getSelectedItem() == e.getEmployeeID()){
				 System.out.println(e);
				 this.txtFieldFirstName.setText(e.getFirstName());
				 this.txtFieldLastName.setText(e.getLastName());
				 this.txtFieldHomeAddress.setText(e.getHomeAddress());
				 this.txtFieldContactNumber.setText(e.getContactNum());
			
				 if(e.getPayType().equals("hourly") || e.getPayType().equals("Hourly")){
					 radioButHourly.setSelected(true);
					 this.textFieldHoulryRate.setText(e.getPayRate());				 
					 
				 }else if(e.getPayType().equals("salary") || e.getPayType().equals("Salary")){
					 radioButCommissioned.setSelected(true);
					 this.textFieldSalary.setText(e.getPayRate());
					 
				 } else{
					 radioButCommissioned.setSelected(true);
					 this.textFieldCommSalary.setText(e.getPayRate());
				 }

				 System.out.println(e.getInUnion().equals("yes"));
				 System.out.println(e.getInUnion().getClass());
				 
				 if(e.getInUnion() != "yes" || e.getInUnion() != "Yes"){
					 //System.out.println("CHeck bock get union did run");
					 chckbxInUnion.setSelected(true);
					 textFieldUnionFees.setText(e.getUnionFees());
				 };				 
			 }
		 }		
	}	
	
	public void addSubmitEditEmployeeListener(ActionListener listenForSubmitButton) {
		
		btnSubmit.addActionListener(listenForSubmitButton);	
	}
	
	public void addCancelEditEmployeeListener(ActionListener listenForCancelButton){
		btnCancel.addActionListener(listenForCancelButton);
	}
	
	public boolean isHourlyEmployee(){
		return this.isHourlyEmployee;
	}
	
	public boolean isSalaryEmployee(){
		return this.isSalaryEmployee;
	}

	public JRadioButton getRadioButHourly() {
		return radioButHourly;
	}


	public JComboBox getComboBoxPaySchedule() {
		return comboBoxPaySchedule;
	}


	public void setComboBoxPaySchedule(JComboBox comboBoxPaySchedule) {
		this.comboBoxPaySchedule = comboBoxPaySchedule;
	}


	public void setRadioButHourly(JRadioButton radioButHourly) {
		this.radioButHourly = radioButHourly;
	}


	public JRadioButton getRadioButSalary() {
		return radioButSalary;
	}


	public void setRadioButSalary(JRadioButton radioButSalary) {
		this.radioButSalary = radioButSalary;
	}


	public JRadioButton getRadioButCommissioned() {
		return radioButCommissioned;
	}


	public void setRadioButCommissioned(JRadioButton radioButCommissioned) {
		this.radioButCommissioned = radioButCommissioned;
	}


	public boolean isCommissionedEmployee(){
		return this.isCommissionedEmployee;
	}

	public String getTxtFieldFirstName() {
		return txtFieldFirstName.getText();
	}
	
	public String getTxtFieldLastName() {
		return txtFieldLastName.getText();
	}	

	public String getTxtFieldHomeAddress() {
		return txtFieldHomeAddress.getText();
	}	
	
	public String getTextFieldSalary() {
		return textFieldSalary.getText();
	}

	public String getTextFieldHoulryRate() {
		return textFieldHoulryRate.getText();
	}

	public String getTextFieldCommRate() {
		return textFieldCommRate.getText();
	}

	public String getTextFieldCommSalary() {
		return textFieldCommSalary.getText();
	}

	public String getTxtFieldContactNumber() {
		return txtFieldContactNumber.getText();
	}	

	public JComboBox getComboBox() {
		return comboBox;
	}


	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
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


}
