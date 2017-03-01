
public class ChangeHourlyTransaction extends ChangeClassificationTransaction {
	private final double hourlyRate;
	
	public ChangeHourlyTransaction(int empId, double hourlyRate,PayrollDatabase database) {
		super(empId, database);
		this.hourlyRate = hourlyRate;
	}

	@Override
	protected PaymentClassfication getClassification() {
		return new HourlyClassifiaction(hourlyRate);
	}

	@Override
	protected PaymentSchedule getSchedule() {
		return new ScheduleWeekly();
	}

}
