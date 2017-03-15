package TransactionImplemention;

import AbstractTransaction.ChangeClassificationTransaction;
import PayrollDatabase.PayrollDatabase;
import PayrollDomain.PaymentClassfication;
import PayrollDomain.PaymentSchedule;
import PayrollImpl.SalariedClassification;
import PayrollImpl.ScheduleMonthly;

public class ChangeSalariedTransaction extends ChangeClassificationTransaction {
	private final double salary;
	
	
	public ChangeSalariedTransaction(int empId, double salary, PayrollDatabase database) {
		super(empId, database);
		this.salary = salary;
	}


	@Override
	protected PaymentClassfication getClassification() {
		// TODO Auto-generated method stub
		return new SalariedClassification(salary);
	}

	@Override
	protected PaymentSchedule getSchedule() {
		// TODO Auto-generated method stub
		return new ScheduleMonthly();
	}

}
