
public class AddSalariedEmplyee extends AddEmployeeTransaction {
	private final double salary;
	
	public AddSalariedEmplyee(int empid, String name, String address,
			double salary, PayrollDatabase database) {
		super(empid, name, address, database);
		this.salary = salary;
	}

	@Override
	protected PaymentClassfication makeClassfication() {
		return new SalariedClassification(salary);
	}

	@Override
	protected PaymentSchedule makeSchedule() {
		// TODO Auto-generated method stub
		return new ScheduleMonthly();
	}

}
