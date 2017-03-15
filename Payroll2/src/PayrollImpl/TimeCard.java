package PayrollImpl;

import java.util.Date;
/*
 * 工作时间卡
 * 记录了日期以及工作小时数
 */
public class TimeCard {
	private final Date date;
	private final double hours;
	
	public TimeCard(Date date, double hours) {
		this.date = date;
		this.hours = hours;
	}
	
	public Date getDate() {
		return date;
	}
	
	public double getHours() {
		return hours;
	}
	
	
}
