import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;


public class PaydayTransaction extends Transaction {
	private final Date payDate;
	private Hashtable<Integer, Paycheck> paychecks = new Hashtable<Integer, Paycheck>();
	
	
	public PaydayTransaction(Date payDate,PayrollDatabase database) {
		super(database);
		this.payDate = payDate;
	}

	@Override
	public void execute() throws Exception {
		ArrayList<Integer> empIds = database.GetAllEmployeeIds();
		
		for(int empId : empIds){
			Employee employee = database.GetEmployee(empId);
			if(employee.isPayDate(payDate)){
				Date startDate = employee.GetPayPeriodStartDate(payDate);
				Paycheck pc = new Paycheck(startDate,payDate);
				paychecks .put(empId, pc);
				employee.payday(pc);
			}
		}
	}
	
	public Paycheck GetPaycheck(int empId) {
		return paychecks.get(empId);
	}

}
