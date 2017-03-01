import java.util.Calendar;
import java.util.Date;


public class ScheduleMonthly implements PaymentSchedule {

	public Date GetPayPeriodStartDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH);
		while (calendar.get(Calendar.MONTH) == month) {
			calendar.add(Calendar.DATE, -1);
		}
		return calendar.getTime();
	}

	public boolean isPayDate(Date date) {
		return isLastDayOfMonth(date);
	}
	//每个月的最后一天进行支付
	private boolean isLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE)+1));
		if(calendar.get(Calendar.DAY_OF_MONTH) == 1){
			return true;
		}
		return false;
	}
	public String toString() {
		return "monthly";
	}

}
