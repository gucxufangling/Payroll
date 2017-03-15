package AbstractTransaction;

import PayrollDatabase.PayrollDatabase;
import PayrollDomain.Affiliation;
import PayrollDomain.Employee;

public abstract class ChangeAffilationTransaction extends
		ChangeEmployeeTransaction{
	
	public ChangeAffilationTransaction(int empId, PayrollDatabase database) {
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
