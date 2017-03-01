
public abstract class ChangeMethodTransaction extends ChangeEmployeeTransaction {
	
	public ChangeMethodTransaction(int empId, PayrollDatabase database) {
		super(empId, database);
	}

	@Override
	protected void Change(Employee e) {
		PaymentMethod method = getMethod();
		e.setMethod(method);
	}
	
	protected abstract PaymentMethod getMethod();
}
