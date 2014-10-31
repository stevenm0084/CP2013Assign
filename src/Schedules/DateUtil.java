package Schedules;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JComboBox;

import models.DatabaseSingleton;

public class DateUtil {

	private static DateUtil instance = null;
	
	
	private GregorianCalendar startOfPayPeriod;
	private SimpleDateFormat df;
	
	private GregorianCalendar currentDate;
	private GregorianCalendar fromDate;
	private GregorianCalendar toDate;
	
	private ArrayList<GregorianCalendar> weeklyPayPeriods;
	private ArrayList<GregorianCalendar> biweeklyPayPeriods;
	
	private ArrayList<GregorianCalendar> monthlyPayPeriods;	
	
	public DateUtil(){
		weeklyPayPeriods = new ArrayList<>();
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yy");
		
		GregorianCalendar startOfPayPeriod = new GregorianCalendar();
		//Create Weekly payperiods
		startOfPayPeriod.set(Calendar.YEAR, 2014);
		startOfPayPeriod.set(Calendar.MONTH, 1);
		startOfPayPeriod.set(Calendar.DATE, 1);
		
		for(int maxdays = 52; maxdays > 0; maxdays--){					
	
			GregorianCalendar d = (GregorianCalendar) startOfPayPeriod.clone();
	
			weeklyPayPeriods.add(d);
			
			startOfPayPeriod.add(startOfPayPeriod.DATE, 7);
		}		
	
		System.out.println("Pay Period Start Date: " + df.format(startOfPayPeriod.getTime()));
	
		for(GregorianCalendar g : weeklyPayPeriods){
			System.out.println("Pay Period Start Date: " + df.format(g.getTime()));
		}
	
		
		/*
		
		
		
		//Create biWeekly payperiods		
		biweeklyPayPeriods = new ArrayList<>();

		startOfPayPeriod.set(Calendar.YEAR, 2014);
		startOfPayPeriod.set(Calendar.MONTH, 1);
		startOfPayPeriod.set(Calendar.DATE, 1);
		
		for(int maxdays = 52; maxdays > 0; maxdays--){					
	
			GregorianCalendar d = (GregorianCalendar) startOfPayPeriod.clone();
	
			weeklyPayPeriods.add(d);
			
			startOfPayPeriod.add(startOfPayPeriod.DATE, 7);
		}		
	
		System.out.println("Pay Period Start Date: " + df.format(startOfPayPeriod.getTime()));
	
		for(GregorianCalendar g : weeklyPayPeriods){
			System.out.println("Pay Period Start Date: " + df.format(g.getTime()));
		}
		
		currentDate = new GregorianCalendar();
		
		
		
		
		
		
		
		//Create monthly payperiods
		monthlyPayPeriods = new ArrayList<>();

		startOfPayPeriod.set(Calendar.YEAR, 2014);
		startOfPayPeriod.set(Calendar.MONTH, 1);
		startOfPayPeriod.set(Calendar.DATE, 1);
		
		for(int maxdays = 12; maxdays > 0; maxdays--){					
	
			GregorianCalendar d = (GregorianCalendar) startOfPayPeriod.clone();
	
			weeklyPayPeriods.add(d);
			
			startOfPayPeriod.add(startOfPayPeriod.DATE, 7);
		}		
	
		System.out.println("Pay Period Start Date: " + df.format(startOfPayPeriod.getTime()));
	
		for(GregorianCalendar g : weeklyPayPeriods){
			System.out.println("Pay Period Start Date: " + df.format(g.getTime()));
		}
		
		currentDate = new GregorianCalendar();*/
		
		
	}
	
	   public static DateUtil getInstance() {
		      if(instance == null) {
		         instance = new DateUtil();
		      }
		      return instance;
		   }

	public GregorianCalendar getStartOfPayPeriod() {
		return startOfPayPeriod;
	}

	public void setStartOfPayPeriod(GregorianCalendar startOfPayPeriod) {
		this.startOfPayPeriod = startOfPayPeriod;
	}

	public SimpleDateFormat getDf() {
		return df;
	}

	public void setDf(SimpleDateFormat df) {
		this.df = df;
	}

	public GregorianCalendar getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(GregorianCalendar currentDate) {
		this.currentDate = currentDate;
	}

	public GregorianCalendar getFromDate() {
		return fromDate;
	}

	public void setFromDate(GregorianCalendar fromDate) {
		this.fromDate = fromDate;
	}

	public GregorianCalendar getToDate() {
		return toDate;
	}

	public void setToDate(GregorianCalendar toDate) {
		this.toDate = toDate;
	}

	public ArrayList<GregorianCalendar> getWeeklyPayPeriods() {
		return weeklyPayPeriods;
	}

	public void setWeeklyPayPeriods(ArrayList<GregorianCalendar> weeklyPayPeriods) {
		this.weeklyPayPeriods = weeklyPayPeriods;
	}

	public ArrayList<GregorianCalendar> getBiweeklyPayPeriods() {
		return biweeklyPayPeriods;
	}

	public void setBiweeklyPayPeriods(
			ArrayList<GregorianCalendar> biweeklyPayPeriods) {
		this.biweeklyPayPeriods = biweeklyPayPeriods;
	}

	public ArrayList<GregorianCalendar> getMonthlyPayPeriods() {
		return monthlyPayPeriods;
	}

	public void setMonthlyPayPeriods(ArrayList<GregorianCalendar> monthlyPayPeriods) {
		this.monthlyPayPeriods = monthlyPayPeriods;
	}
	
	

	
}
