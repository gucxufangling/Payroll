
public abstract class ChangeEmployeeTransaction extends Transaction {
	private final int empId;
	
	
	public ChangeEmployeeTransaction(int empId, PayrollDatabase database) {
		super(database);
		this.empId = empId;
	}

	@Override
	public void execute() throws Exception {
		Employee e = database.GetEmployee(empId);
		if(e!=null)
			Change(e);
		else {
			throw new Exception("�޸��û�");
		}
	}
	
	protected abstract void Change(Employee e);

}
