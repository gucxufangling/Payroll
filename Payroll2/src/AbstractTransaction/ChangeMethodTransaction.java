package AbstractTransaction;

import PayrollDatabase.PayrollDatabase;
import PayrollDomain.Employee;
import PayrollDomain.PaymentMethod;

public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction{

	public ChangeMethodTransaction(int empId, PayrollDatabase database) {
		super(empId, database);
	}

	@Override
	protected void Change(Employee e) {
		e.setMethod(getMethod());
	}
	
	protected abstract PaymentMethod getMethod();
	
}
