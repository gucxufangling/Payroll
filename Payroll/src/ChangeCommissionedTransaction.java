
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
		// TODO Auto-generated method stub
		return new CommissionClassification(baseSalary,commissionRate);
	}

	@Override
	protected PaymentSchedule getSchedule() {
		// TODO Auto-generated method stub
		return new ScheduleBiWeekly();
	}

}
