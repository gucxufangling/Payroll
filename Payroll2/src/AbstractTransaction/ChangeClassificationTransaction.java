package AbstractTransaction;

import PayrollDatabase.PayrollDatabase;
import PayrollDomain.Employee;
import PayrollDomain.PaymentClassfication;
import PayrollDomain.PaymentSchedule;

public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction{

	public ChangeClassificationTransaction(int empId, PayrollDatabase database) {
		super(empId, database);
	}

	@Override
	protected void Change(Employee e) {
		e.setClassfication(getClassification());
		e.setSchedule(getSchedule());
		
	}
	
	protected abstract PaymentClassfication getClassification();

	protected abstract PaymentSchedule getSchedule();
	
}
