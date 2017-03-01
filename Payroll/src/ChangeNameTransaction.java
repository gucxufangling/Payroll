
public class ChangeNameTransaction extends ChangeEmployeeTransaction {
	private final String newName;
	
	
	public ChangeNameTransaction(int empId, String newName,PayrollDatabase database) {
		super(empId, database);
		this.newName = newName;
	}


	@Override
	protected void Change(Employee e) {
		e.setName(newName);	}

}
