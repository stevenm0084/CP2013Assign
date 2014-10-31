package Schedules;

import java.util.Date;

public class MonthlySchedule implements PaymentSchedule{

	private String schedule;
	
	public MonthlySchedule(String schedule){
		this.schedule = schedule;
	}
	
	@Override
	public boolean isPayDate(Date payDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Date getPayPeriodStartDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString(){
		return "monthly";
	}

}
