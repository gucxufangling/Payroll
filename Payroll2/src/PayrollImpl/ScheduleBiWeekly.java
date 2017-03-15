package PayrollImpl;

import java.util.Calendar;
import java.util.Date;

import PayrollDomain.PaymentSchedule;

//每隔一周的周五对其支付

public class ScheduleBiWeekly implements PaymentSchedule {
	
	public Date GetPayPeriodStartDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, -13);
		return calendar.getTime();
	}

	public boolean isPayDate(Date date) {
		Calendar calendar =Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
			&& calendar.get(Calendar.WEEK_OF_MONTH)%2 ==0;
	}
	public String toString() {
		return "bi-weekly";
	}
}
