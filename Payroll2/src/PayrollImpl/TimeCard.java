package PayrollImpl;

import java.util.Date;
/*
 * ����ʱ�俨
 * ��¼�������Լ�����Сʱ��
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
