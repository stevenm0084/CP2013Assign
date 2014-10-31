package Schedules;

import java.util.Date;

public interface PaymentSchedule {

	public boolean isPayDate(Date payDate);	
	Date getPayPeriodStartDate(Date date);
	
	
}
