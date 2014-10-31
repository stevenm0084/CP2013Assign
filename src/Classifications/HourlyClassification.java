package Classifications;


import java.util.Date;
import java.util.HashMap;

import models.Paycheck;

public class HourlyClassification extends PaymentClassification{

	private String hourlyRate;
	private HashMap timeCards;
	private String timeCard;
	
	public HourlyClassification(String hourlyRate){
		this.hourlyRate = hourlyRate;
		timeCards = new HashMap();
	}

	@Override
	public double calculatePay(Paycheck pc) {
		double totalPay = 0.0;
		
/*		for(TimeCard timeCard : timeCards.values())
		{
			if(Date.IsInPayPeriod(timeCard.getDate(), paycheck.PayPeriodStartDate, paycheck.PayPeriodEndDate))
				totalPay += CalculatePayForTimeCard(timeCard);
		}*/
		return totalPay;
	}

	public String getHourlyRate() {
		return hourlyRate;
	}

	public void setHourlyRate(String hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public String getTimeCard() {
		return timeCard;
	}

	public void setTimeCard(String timeCard) {
		this.timeCard = timeCard;
	}
	
	
	
}
