
public abstract class ChangeClassificationTransaction extends ChangeEmployeeTransaction {
	
	public ChangeClassificationTransaction(int empId, PayrollDatabase database) {
		super(empId, database);
	}

	protected void Change(Employee e) {
		e.setClassification(getClassification());
		e.setSchedule(getSchedule());
	}

	protected abstract PaymentClassfication getClassification();

	protected abstract PaymentSchedule getSchedule();

}
