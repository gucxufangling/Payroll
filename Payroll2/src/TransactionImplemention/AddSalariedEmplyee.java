package TransactionImplemention;

import AbstractTransaction.AddEmployeeTransaction;
import PayrollDatabase.PayrollDatabase;
import PayrollDomain.PaymentClassfication;
import PayrollDomain.PaymentSchedule;
import PayrollImpl.SalariedClassification;
import PayrollImpl.ScheduleMonthly;

public class AddSalariedEmplyee extends AddEmployeeTransaction {
	private final double salary;
	
	public AddSalariedEmplyee(int empid, String name, String address,
			double salary, PayrollDatabase database) {
		super(empid, name, address, database);
		this.salary = salary;
	}
	
	@Override
	protected PaymentClassfication makeClassfication() {
		return new SalariedClassification(salary);
	}

	@Override
	protected PaymentSchedule makeSchedule() {
		return new ScheduleMonthly();
	}

}
