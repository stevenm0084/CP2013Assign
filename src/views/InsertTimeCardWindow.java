package views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import models.Employee;
import PayrollApplication.EmployeeDAO;
import PayrollApplication.MainMenuWindow;

public class InsertTimeCardWindow extends JFrame{

	private JButton btnCancel;
	private JButton btnSubmit;

	private JTextField txtFieldDate;
	private JTextField txtFieldHoursWorked;
	private JTextField txtFieldStartOfPayPeriod;
	private JButton addTestTimeCard;

	
	private JComboBox comboBox;
	private JComboBox dates;
	private MainMenuWindow mmw;
	private ArrayList<Employee> employees;

	//private ArrayList<Employee> employees;
	

	public InsertTimeCardWindow(MainMenuWindow mmw) {//pass in model later
		initialize();
		this.mmw = mmw;
		
	}
	
	public void initialize(){

		this.setBounds(100, 100, 790, 454);
		this.setDefaultCloseOperation(AddEmployeeWindow.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JLabel headerLbl = new JLabel("XYZ Company - Post Time Card");
		headerLbl.setBorder(BorderFactory.createLineBorder(Color.black));
		headerLbl.setForeground(Color.BLACK);
		headerLbl.setBackground(Color.WHITE);
		headerLbl.setHorizontalAlignment(SwingConstants.CENTER);
		headerLbl.setFont(new Font("Tahoma", Font.BOLD, 16));
		headerLbl.setBounds(11, 11, 681, 70);
		this.getContentPane().add(headerLbl);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(480, 382, 103, 23);
		this.getContentPane().add(btnSubmit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(224, 382, 103, 23);
		this.getContentPane().add(btnCancel);
	
		/***** Setup Checkbox ****/
		addTestTimeCard= new JButton("Click to Add test timecards");
		addTestTimeCard.setBounds(50, 300, 300, 23);
		this.getContentPane().add(addTestTimeCard);
			
		/***** Setup Labels ****/
		JLabel lblFirstName = new JLabel("Select Employee Number:");
		lblFirstName.setBounds(40, 123, 150, 14);
		this.getContentPane().add(lblFirstName);

/*		JLabel lblStartOfWorkPeriod = new JLabel("Start of Work Period: ");
		lblStartOfWorkPeriod.setBounds(35, 151, 120, 14);
		this.getContentPane().add(lblStartOfWorkPeriod);*/
		
		JLabel lblLastName = new JLabel("Date: (dd/mm/yy)");
		lblLastName.setBounds(86, 148, 150, 14);
		this.getContentPane().add(lblLastName);
		
		JLabel lblHoursWorkedomeAddress = new JLabel("Hours: ");
		lblHoursWorkedomeAddress.setBounds(70, 195, 120, 14);
		this.getContentPane().add(lblHoursWorkedomeAddress);
		
		/***** Setup textfields ****/
		/*txtFieldDate = new JTextField();
		txtFieldDate.setBounds(180, 148, 202, 20);
		txtFieldDate.setColumns(10);
		this.getContentPane().add(txtFieldDate);*/
		
		txtFieldHoursWorked = new JTextField();
		txtFieldHoursWorked.setBounds(180, 194, 202, 20);
		txtFieldHoursWorked.setColumns(10);
		this.getContentPane().add(txtFieldHoursWorked);

		/***** Setup ComboBox ****/	
		dates = new JComboBox();
		dates.setBounds(180, 148, 202, 20);
		this.getContentPane().add(dates);
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
				
		GregorianCalendar cal = new GregorianCalendar();
		SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yy");
		int counter = -1;
		for(int maxdays = 30; maxdays > 0; maxdays--){					
			dates.addItem(df1.format(cal.getTime()));			
			cal.add(Calendar.DATE, -1);		
			counter -= 1;		
		}
		
		comboBox = new JComboBox();
		comboBox.setBounds(200, 120, 80, 23);
		
		try {
			EmployeeDAO.loadEmployees();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ArrayList<Employee> employeesList = EmployeeDAO.getEmployeesArray();

		for(Employee e : employeesList){
			comboBox.addItem(e.getEmployeeID());
			//System.out.println(e);
		}
		
		this.getContentPane().add(comboBox);
	
		JSeparator separator = new JSeparator();
		separator.setBounds(428, 171, 297, 8);
		this.getContentPane().add(separator);
	
		addTestTimeCard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				/*dates.setSelectedIndex(5);
				txtFieldHoursWorked.setText("8");*/
				try {
					EmployeeDAO.loadEmployees();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				employees = EmployeeDAO.getEmployeesArray();

				for(Employee e : employees){
					GregorianCalendar cal = new GregorianCalendar();
					SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yy");
					
					if(e.getPayType().equals("hourly")){
						System.out.println("running for hourly");
						cal.add(Calendar.DATE, -5);
						
						cal.getTime();
					/*	int day = Integer.parseInt(splitDate[0]);
						int month = Integer.parseInt(splitDate[1]);
						int year = Integer.parseInt(splitDate[2]);*/

						for(int dayCounter = 0; dayCounter < 4; ++dayCounter){
							try {
								cal.add(Calendar.DATE, 1);
								EmployeeDAO.InsertTimeCard(Integer.parseInt(e.getEmployeeID()), df1.format(cal.getTime()), Integer.toString(new Random().nextInt(10 - 7) + 8));		
								System.out.println(e.calculatePay());
							} catch (NumberFormatException e1) {
								e1.printStackTrace();
								
							} catch (SQLException e1) {
								e1.printStackTrace();
							}	// try block
						}// for loop


					}//if statement
					
					if(e.getPayType().equals("salary")){
						
						cal.add(Calendar.DATE, -12);
						
						String[] splitDate = df1.format(cal.getTime()).split("/");
						int day = Integer.parseInt(splitDate[0]);
						int month = Integer.parseInt(splitDate[1]);
						int year = Integer.parseInt(splitDate[2]);
						
						for(int dayCounter = 0; dayCounter < 12; ++dayCounter){
							try {
								cal.add(Calendar.DATE, 1);
								EmployeeDAO.InsertTimeCard(Integer.parseInt(e.getEmployeeID()), df1.format(cal.getTime()), Integer.toString(new Random().nextInt(10 - 7) + 8)); //day+dayCounter+"/"+"9"+"/"+"14"		
								
							} catch (NumberFormatException e1) {
								e1.printStackTrace();
								
							} catch (SQLException e1) {
								e1.printStackTrace();
							}	// try block
						}// for loop


					}//if statement
					
					if(e.getPayType().equals("commission")){
						cal.add(Calendar.DATE, -22);
						
						String[] splitDate = df1.format(cal.getTime()).split("/");
						int day = Integer.parseInt(splitDate[0]);
						int month = Integer.parseInt(splitDate[1]);
						int year = Integer.parseInt(splitDate[2]);
						
						for(int dayCounter = 0; dayCounter < 22; ++dayCounter){
							try {
								cal.add(Calendar.DATE, 1);
								EmployeeDAO.InsertTimeCard(Integer.parseInt(e.getEmployeeID()), df1.format(cal.getTime()), Integer.toString(new Random().nextInt(10 - 7) + 8)); //day+dayCounter+"/"+"9"+"/"+"14"		
								
							} catch (NumberFormatException e1) {
								e1.printStackTrace();
								
							} catch (SQLException e1) {
								e1.printStackTrace();
							}	// try block
						}// for loop


					}//if statement
				} // for loop
				
				JOptionPane.showMessageDialog(mmw, "Test Timecards have been submitted.");  
				
				mmw.refreshEmployees();
				mmw.updatePendingTransactionsTable();
				setVisible(false);
				dispose();
			}
		});
	}
	
	public JTextField getTxtFieldStartOfWorkPeriod() {
		return txtFieldStartOfPayPeriod;
	}


	public void setTxtFieldStartOfWorkPeriod(JTextField txtFieldStartOfWorkPeriod) {
		this.txtFieldStartOfPayPeriod = txtFieldStartOfWorkPeriod;
	}


	public JTextField getTxtFieldDate() {
		return txtFieldDate;
	}


	public JComboBox getDates() {
		return dates;
	}

	public void setDates(JComboBox dates) {
		this.dates = dates;
	}

	public void setTxtFieldDate(JTextField txtFieldDate) {
		this.txtFieldDate = txtFieldDate;
	}


	public JTextField getTxtFieldHoursWorked() {
		return txtFieldHoursWorked;
	}


	public void setTxtFieldHoursWorked(JTextField txtFieldHoursWorked) {
		this.txtFieldHoursWorked = txtFieldHoursWorked;
	}


	public JComboBox getComboBox() {
		return comboBox;
	}


	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}


	public void addInsertTimeCardListener(ActionListener listenForSubmitButton) {
		
		btnSubmit.addActionListener(listenForSubmitButton);	
	}
	
	public void addCancelInsertTimeCardWindow(ActionListener listenForCancelButton){
		btnCancel.addActionListener(listenForCancelButton);
	}



}