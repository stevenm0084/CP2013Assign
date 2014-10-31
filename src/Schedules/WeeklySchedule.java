package Schedules;

import java.util.Date;

public class WeeklySchedule  implements PaymentSchedule{

	private String schedule;
	
	public WeeklySchedule(String schedule){
		this.schedule = schedule;
	}
	
	@Override
	public boolean isPayDate(Date payDate) {
		
		/*if(payDate == ){
			
		}*/
		return false;
	}

	@Override
	public Date getPayPeriodStartDate(Date date) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString(){
		return "weekly";
	}

}
