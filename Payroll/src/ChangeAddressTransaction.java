
public class ChangeAddressTransaction extends ChangeEmployeeTransaction {
	
	private final String newAddress;
	
	public ChangeAddressTransaction(int empId, String newAddress,
			PayrollDatabase database) {
		super(empId, database);
		this.newAddress = newAddress;
	}

	@Override
	protected void Change(Employee e) {
		e.setAddress(newAddress);
	}

}
