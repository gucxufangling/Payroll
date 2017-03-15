package TransactionImplemention;

import AbstractTransaction.ChangeClassificationTransaction;
import PayrollDatabase.PayrollDatabase;
import PayrollDomain.PaymentClassfication;
import PayrollDomain.PaymentSchedule;
import PayrollImpl.HourlyClassifiaction;
import PayrollImpl.ScheduleWeekly;

public class ChangeHourlyTransaction extends ChangeClassificationTransaction {
	private final double hourlyRate;
	
	
	public ChangeHourlyTransaction(int empId, double hourlyRate,
			PayrollDatabase database) {
		super(empId, database);
		this.hourlyRate = hourlyRate;
	}

	@Override
	protected PaymentClassfication getClassification() {
		return new HourlyClassifiaction(hourlyRate);
	}

	@Override
	protected PaymentSchedule getSchedule() {
		// TODO Auto-generated method stub
		return new ScheduleWeekly();
	}

}
