package PayrollImpl;

import java.util.Date;
import java.util.Hashtable;

import PayrollDomain.Paycheck;
import PayrollDomain.PaymentClassfication;

public class HourlyClassifiaction extends PaymentClassfication {
	private double hourlyRate;
	private Hashtable<Date, TimeCard> timecards = new Hashtable<Date, TimeCard>();
	
	
	public HourlyClassifiaction(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}


	@Override
	public double calculatePay(Paycheck paycheck) {
		double totalPay = 0.0;
		for(TimeCard timeCard :timecards.values()){
			if(isInPayPeriod(timeCard.getDate(),
					paycheck.getPayPeriodStartDate(), 
					paycheck.getPayPeriodEndDate())){
				totalPay += CalculatePayForTimeCard(timeCard);
			}
		}
		return totalPay;	
	}
	
	//计算时间卡的工资
	private double CalculatePayForTimeCard(TimeCard card) {
		double overtimeHours  = Math.max(0.0, card.getHours()-8);
		double normalHours = card.getHours() - overtimeHours;
		return hourlyRate*normalHours + hourlyRate*1.5*overtimeHours;
	}
	
	public boolean isInPayPeriod(Date theDate, Date startDate,
			Date endDate){
		return (theDate.after(startDate)) && (theDate.before(endDate));
	}
	
	public double getHourlyRate() {
		return hourlyRate;
	}


	public TimeCard getTimecards(Date date) {
		return timecards.get(date);
	}


	public void addTimecards(TimeCard card) {
		timecards.put(card.getDate(), card);
	}

	public String toString(){
		return String.format("%f/hr", hourlyRate);
	}
	
}
