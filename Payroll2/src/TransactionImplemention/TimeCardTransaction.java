package TransactionImplemention;

import java.util.Date;

import AbstractTransaction.Transaction;
import PayrollDatabase.PayrollDatabase;
import PayrollDomain.Employee;
import PayrollImpl.HourlyClassifiaction;
import PayrollImpl.TimeCard;

public class TimeCardTransaction extends Transaction {
	private final Date date;
	private final double hours;
	private final int empId;
	
	public TimeCardTransaction(Date date, double hours, int empId,
			PayrollDatabase database) {
		super(database);
		this.date = date;
		this.hours = hours;
		this.empId = empId;
	}

	@Override
	public void execute() throws Exception {
		Employee employee = database.GetEmployee(empId);
		if(employee != null){
			HourlyClassifiaction hc = (HourlyClassifiaction)(employee.getClassfication());
			if(hc != null){
				hc.addTimecards(new TimeCard(date,hours));
			}else {
				throw new Exception("��ӵĲ���ʱ�俨");
			}
		}else {
			throw new Exception("�޸ù�Ա");
		}
	}

}
