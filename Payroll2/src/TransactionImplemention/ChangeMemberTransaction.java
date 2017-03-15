package TransactionImplemention;

import AbstractTransaction.ChangeAffilationTransaction;
import PayrollDatabase.PayrollDatabase;
import PayrollDomain.Affiliation;
import PayrollDomain.Employee;
import PayrollImpl.UnionAffiliation;

public class ChangeMemberTransaction extends ChangeAffilationTransaction {
	
	private final int memberId;
	private final double dues;
	
	public ChangeMemberTransaction(int empId, int memberId,
			double dues, PayrollDatabase database) {
		super(empId, database);
		this.memberId = memberId;
		this.dues = dues;
	}

	@Override
	protected void RecordMembership(Employee e) {
		database.AddUnionMember(memberId, e);
	}

	@Override
	protected Affiliation getAffiliation() {
		return new UnionAffiliation(memberId, dues);
	}

}
