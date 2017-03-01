
public class AddHourlyEmplyee extends AddEmployeeTransaction {
	private final double hourlyRate;
	
	public AddHourlyEmplyee(int empid, String name, String address,
			double hourlyRate, PayrollDatabase database) {
		super(empid, name, address, database);
		this.hourlyRate = hourlyRate;
	}

	@Override
	protected PaymentClassfication makeClassfication() {
		return new HourlyClassifiaction(hourlyRate);
	}

	@Override
	protected PaymentSchedule makeSchedule() {
		return new ScheduleWeekly();
	}

}
