
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
		// TODO Auto-generated method stub
		return new CommissionClassification(baseRate,commissionRate);
	}

	@Override
	protected PaymentSchedule makeSchedule() {
		// TODO Auto-generated method stub
		return new ScheduleBiWeekly();
	}

}
