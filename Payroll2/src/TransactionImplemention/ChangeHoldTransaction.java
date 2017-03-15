package TransactionImplemention;

import AbstractTransaction.ChangeMethodTransaction;
import PayrollDatabase.PayrollDatabase;
import PayrollDomain.PaymentMethod;
import PayrollImpl.HoldMethod;

public class ChangeHoldTransaction extends ChangeMethodTransaction {
	
	public ChangeHoldTransaction(int empId, PayrollDatabase database) {
		super(empId, database);
	}

	@Override
	protected PaymentMethod getMethod() {
		return new HoldMethod();
	}

}
