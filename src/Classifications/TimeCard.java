package Classifications;

import java.sql.Date;

public class TimeCard {

	private Date date;
	private Double hours;
	
	public TimeCard(Date date, double hours){
		this.date = date;
		this.hours = hours;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getHours() {
		return hours;
	}

	public void setHours(Double hours) {
		this.hours = hours;
	}
}
