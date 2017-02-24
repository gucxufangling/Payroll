import java.util.Date;
import java.util.Hashtable;
/*
 * 钟点工的分类
 * 
 */
public class HourlyClassifiaction extends PaymentClassfication {
	private double hourlyRate;
	private Hashtable<Date, TimeCard> timeCards = new Hashtable<Date, TimeCard>();
	
	@Override	
	//计算总的工资卡
	public double calculatePay(Paycheck paycheck) {
		double totalPay = 0.0;
		for(TimeCard timeCard : timeCards.values()){
			if(DateUtil.IsInPayPeriod(timeCard.getDate(),
					paycheck.getPayPeriodStartDate(),
					paycheck.getPayPeriodEndDate()))
				totalPay += CalculatePayForTimeCard(timeCard);
		}
		return totalPay;
	}
	
	//计算时间卡的工资
	private double CalculatePayForTimeCard(TimeCard card) {
		double overtimeHours  = Math.max(0.0, card.getHours()-8);
		double normalHours = card.getHours() - overtimeHours;
		return hourlyRate*normalHours + hourlyRate*1.5*overtimeHours;
	}

	public HourlyClassifiaction(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public double getHourlyRate() {
		return hourlyRate;
	}

	public TimeCard GetTimeCard(Date date) {
		return timeCards.get(date);
	}

	public void AddTimeCard(TimeCard card) {
		timeCards.put(card.getDate(), card);
	}
	
	public String toString(){
		return String.format("%f/hr", hourlyRate);
	}

}
