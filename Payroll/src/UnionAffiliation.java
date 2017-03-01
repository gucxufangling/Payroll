import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;



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
	
	public double getDues() {
		return dues;
	}

	public int getMemberId() {
		return memberId;
	}

	public double calculateDeductions(Paycheck paycheck) {
		double totalDues = 0;

		int fridays = NumberOfFridaysInPayPeriod(
				paycheck.getPayPeriodStartDate(),
				paycheck.getPayPeriodEndDate());
		totalDues = dues * fridays;//每周固定的费用
		
		//其他费用
		for (ServiceCharge charge : charges.values()) {
			if (DateUtil.IsInPayPeriod(charge.getDate(),
					paycheck.getPayPeriodStartDate(),
					paycheck.getPayPeriodEndDate()))
				totalDues += charge.getAmount();
		}

		return totalDues;
	}
	
	//计算支付周期中星期五所占的天数
	private int NumberOfFridaysInPayPeriod(Date payPeriodStart,
			Date payPeriodEnd) {
		Calendar dayCa = Calendar.getInstance();
		dayCa.setTime(payPeriodStart);
		Calendar payPeriodEndCa = Calendar.getInstance();
		dayCa.setTime(payPeriodEnd);

		int fridays = 0;
		while (dayCa.before(payPeriodEndCa)) {
			if (dayCa.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
				fridays++;
			}
			dayCa.add(Calendar.DATE, 1);
		}

		return fridays;
	}
	
	
}
