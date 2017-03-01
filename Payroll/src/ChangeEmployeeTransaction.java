
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
			throw new Exception("无该用户");
		}
	}
	
	protected abstract void Change(Employee e);

}
