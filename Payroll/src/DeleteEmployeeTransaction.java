
public class DeleteEmployeeTransaction extends Transaction {
	private final int empid;
	
	public DeleteEmployeeTransaction(int empid, PayrollDatabase database) {
		super(database);
		this.empid = empid;
	}

	@Override
	public void execute() throws Exception {
		database.DeleteEmployee(empid);

	}

}
