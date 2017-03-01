
public abstract class ChangeAffiliationTransaction extends
		ChangeEmployeeTransaction {
	
	public ChangeAffiliationTransaction(int empId, PayrollDatabase database) {
		super(empId, database);
	}

	@Override
	protected void Change(Employee e) {
		RecordMembership(e);
		Affiliation affiliation = getAffiliation();
		e.setAffiliation(affiliation);
	}
	protected abstract Affiliation getAffiliation();

	protected abstract void RecordMembership(Employee e);
}
