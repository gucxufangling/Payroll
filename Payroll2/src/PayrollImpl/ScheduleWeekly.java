package PayrollImpl;

import java.util.Calendar;
import java.util.Date;

import PayrollDomain.PaymentSchedule;

public class ScheduleWeekly implements PaymentSchedule {

	public Date GetPayPeriodStartDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -6);
		return calendar.getTime();
	}

	public boolean isPayDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
	}
	
	public String toString(){
		return "weekly";
	}

}
