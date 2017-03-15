package PayrollImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import PayrollDomain.Affiliation;
import PayrollDomain.Paycheck;

public class UnionAffiliation implements Affiliation {
	private final int memberId;
	private final double dues;
	private Hashtable<Date, ServiceCharge> charges = new Hashtable<Date, ServiceCharge>();
	
	public UnionAffiliation(int memberId, double dues) {
		this.memberId = memberId;
		this.dues = dues;
	}

	public UnionAffiliation() {
		this(-1, 0.0);
	}

	public ServiceCharge GetServiceCharge(Date time) {
		return charges.get(time);
	}

	public void AddServiceCharge(ServiceCharge sc) {
		charges.put(sc.getDate(), sc);
	}
	
	//费用分两部分，每周固定的费用与charge中的费用
	public double calculateDeductions(Paycheck paycheck) {
		double totalDues =0;
		int fridays = NumberOfFridaysInPayPeriod(
				paycheck.getPayPeriodStartDate(),
				paycheck.getPayPeriodEndDate());
		totalDues = dues*fridays;//每周固定的费用
		
		//其他费用
		for(ServiceCharge charge : charges.values()){
			if(isInPayPeriod(charge.getDate(),
					paycheck.getPayPeriodStartDate(), 
					paycheck.getPayPeriodEndDate()))
				totalDues += charge.getAmount();
		}
		
		return totalDues;
	}
	
	public boolean isInPayPeriod(Date theDate, Date startDate,
			Date endDate){
		return (theDate.after(startDate)) && (theDate.before(endDate));
	}
	
	private int NumberOfFridaysInPayPeriod(Date payPeriodStart,
			Date payPeriodEnd) {
		Calendar dayStart = Calendar.getInstance();
		dayStart.setTime(payPeriodStart);
		
		Calendar dayEnd = Calendar.getInstance();
		dayEnd.setTime(payPeriodEnd);
		
		int fridays = 0;
		while(dayStart.before(dayEnd)){
			if(dayStart.get(Calendar.DAY_OF_WEEK) == Calendar.FEBRUARY){
				fridays++;
			}
			dayStart.add(Calendar.DATE, 1);
		}
		return fridays;
	}
	public double getDues() {
		return dues;
	}

	public int getMemberId() {
		return memberId;
	}

}
