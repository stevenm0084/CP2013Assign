package models;

import Classifications.PaymentClassification;
import Schedules.PaymentSchedule;

public class Employee2 {
	
	private String empid;
	private String name;
	private String address;
	private PaymentClassification classification;
	private PaymentSchedule schedule;	
	private PaymentMethod method;
	private Affiliation affiliation;
	
	
	public Employee2(String empid, String name, String address, String union) {
		
		this.empid = empid;
		this.name = name;
		this.address = address;
/*		this.classification = classification;
		this.schedule = schedule;
		this.method = method;*/
		if(union != "no"){
			this.affiliation = new Affiliation();
		} 
		
	}


	public String getEmpid() {
		return empid;
	}


	public void setEmpid(String empid) {
		this.empid = empid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public PaymentClassification getClassification() {
		return classification;
	}


	public void setClassification(PaymentClassification classification) {
		this.classification = classification;
	}


	public PaymentSchedule getSchedule() {
		return schedule;
	}


	public void setSchedule(PaymentSchedule schedule) {
		this.schedule = schedule;
	}


	public PaymentMethod getMethod() {
		return method;
	}


	public void setMethod(PaymentMethod method) {
		this.method = method;
	}


	public Affiliation getAffiliation() {
		return affiliation;
	}


	public void setAffiliation(Affiliation affiliation) {
		this.affiliation = affiliation;
	}
	
	

	
}
