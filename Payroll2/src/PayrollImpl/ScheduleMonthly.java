package PayrollImpl;

import java.util.Calendar;
import java.util.Date;

import PayrollDomain.PaymentSchedule;
//ÿ���µ����һ��������
public class ScheduleMonthly implements PaymentSchedule {

	public Date GetPayPeriodStartDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH);
		while(calendar.get(Calendar.MONTH)== month){
			calendar.add(Calendar.DATE, -1);
		}
		return calendar.getTime();
	}

	public boolean isPayDate(Date date) {
		return isLastDayOfMonth(date);
	}

	private boolean isLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH);
		calendar.add(Calendar.DATE, 3);//�����3����һ
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY 
			&& calendar.get(Calendar.MONTH) == month+1;
			
	}
	public String toString() {
		return "monthly";
	}
}
