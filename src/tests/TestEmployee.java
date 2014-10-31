package tests;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import models.Employee;


public class TestEmployee {

	public static void main(String[] args){
		ArrayList<Employee> employees;
		employees = new ArrayList<Employee>();
		
		/*Create a non union hourly Employee*/
		String firstName = "Bob";
		String lastName = "smith";
		String homeAddress = "12 furby st";
		String contactNum = "123456";
		String payType = "Hourly";
		String payRate = "20.55";
		String union = "no";
		String commissionPercentage = "0.00";
		String unionFees = "0.00";

		//System.out.println(e1.toString());	
		//employees.add(e1);
	
		 /*Create a non union salaried Employee*/
		firstName = "Bob";
		lastName = "Bob";
		homeAddress = "Bob";
		contactNum = "55443322";
		payType = "Salary";
		payRate = "50,000";
		union = "no";
		commissionPercentage = "0.00";
		unionFees = "0.00";
		
		//Employee e2 = new Employee(firstName, lastName, homeAddress, contactNum, payType, payRate, union, commissionPercentage, unionFees);	
		
		//System.out.println(e2.toString());
		//employees.add(e2);
		
		/*Create a non union commissioned Employee*/
		firstName = "Sally";
		lastName = "Jane";
		homeAddress = "88 Love lane";
		contactNum = "44887766";
		payType = "Commissioned";
		payRate = "20.55";
		union = "no";
		commissionPercentage = "0.00";
		unionFees = "0.00";
		
		//Employee e3 = new Employee(firstName, lastName, homeAddress, contactNum, payType, payRate, union, commissionPercentage, unionFees);	
		
		//System.out.println(e3.toString());
		//employees.add(e3);
	
		/*Create a Union Member Employee - make them hourly*/
		firstName = "Rick";
		lastName = "Fenwick";
		homeAddress = "44 sand st";
		contactNum = "55998877";
		payType = "hourly";
		payRate = "20.55";
		union = "yes";
		commissionPercentage = "0.00";
		unionFees = "5.00";
		
		//Employee e4 = new Employee(firstName, lastName, homeAddress, contactNum, payType, payRate, union, commissionPercentage, unionFees);	
		
		//System.out.println(e4.toString());
		//employees.add(e4);
	}
}
