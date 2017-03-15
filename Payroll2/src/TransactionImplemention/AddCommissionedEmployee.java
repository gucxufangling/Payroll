package TransactionImplemention;

import AbstractTransaction.AddEmployeeTransaction;
import PayrollDatabase.PayrollDatabase;
import PayrollDomain.PaymentClassfication;
import PayrollDomain.PaymentSchedule;
import PayrollImpl.CommissionClassification;
import PayrollImpl.ScheduleBiWeekly;

public class AddCommissionedEmployee extends AddEmployeeTransaction {
	private final double commissionRate;
	private final double baseRate;
	
	public AddCommissionedEmployee(int empid, String name, String address,
			double baseRate, double commissionRate,PayrollDatabase database) {
		super(empid, name, address, database);
		this.baseRate = baseRate;
		this.commissionRate = commissionRate;
	}
	@Override
	protected PaymentClassfication makeClassfication() {
		return new CommissionClassification(baseRate,commissionRate);
	}

	@Override
	protected PaymentSchedule makeSchedule() {
		// TODO Auto-generated method stub
		return new ScheduleBiWeekly();
	}

}
