package AbstractTransaction;

import PayrollDatabase.PayrollDatabase;
import PayrollDomain.Employee;

public abstract class ChangeEmployeeTransaction extends Transaction{
	
	private final int empId;
	
	public ChangeEmployeeTransaction(int empId,PayrollDatabase database) {
		super(database);
		this.empId = empId;
	}
	
	@Override
	public void execute() throws Exception {
		Employee employee = database.GetEmployee(empId);
		if(employee!=null)
			Change(employee);
		else {
			throw new Exception("无该用户");
		}
	}
	
	protected abstract void Change(Employee e);
}
