import java.util.Calendar;
import java.util.Date;

//每隔一周的周五对其支付
public class ScheduleBiWeekly implements PaymentSchedule {

	public Date GetPayPeriodStartDate(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(Calendar.DATE, -13);
		return ca.getTime();
	}

	public boolean isPayDate(Date date) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		return ca.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY
				&& ca.get(Calendar.WEEK_OF_MONTH)%2 ==0;
	}
	
	public String toString() {
		return "bi-weekly";
	}
}
