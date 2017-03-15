package TransactionImplemention;

import AbstractTransaction.ChangeClassificationTransaction;
import PayrollDatabase.PayrollDatabase;
import PayrollDomain.PaymentClassfication;
import PayrollDomain.PaymentSchedule;
import PayrollImpl.CommissionClassification;
import PayrollImpl.ScheduleBiWeekly;

public class ChangeCommissionedTransaction extends
		ChangeClassificationTransaction {
	private final double baseSalary;
	private final double commissionRate;
	
	
	public ChangeCommissionedTransaction(int empId, double baseSalary,
			double commissionRate, PayrollDatabase database) {
		super(empId, database);
		this.baseSalary = baseSalary;
		this.commissionRate = commissionRate;
	}

	@Override
	protected PaymentClassfication getClassification() {
		return new CommissionClassification(baseSalary,commissionRate);
	}

	@Override
	protected PaymentSchedule getSchedule() {
		// TODO Auto-generated method stub
		return new ScheduleBiWeekly();
	}

}
