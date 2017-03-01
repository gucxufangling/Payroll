
public class ChangeSalariedTransaction extends ChangeClassificationTransaction {
	private final double salary;
	
	public ChangeSalariedTransaction(int empId, double salary,
			PayrollDatabase database) {
		super(empId, database);
		this.salary = salary;
	}

	@Override
	protected PaymentClassfication getClassification() {
		return new SalariedClassification(salary);
	}

	@Override
	protected PaymentSchedule getSchedule() {
		return new ScheduleMonthly();
	}

}
