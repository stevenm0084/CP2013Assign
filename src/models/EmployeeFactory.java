package models;

import models.TimeCard;

public class EmployeeFactory {
	
	public Employee makeEmployee(){
		return new Employee();
	}
	
	public TimeCard makeTimeCard(){
		return new TimeCard();
	}
	
}
