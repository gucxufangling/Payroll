package TransactionImplemention;

import AbstractTransaction.ChangeAffilationTransaction;
import PayrollDatabase.PayrollDatabase;
import PayrollDomain.Affiliation;
import PayrollDomain.Employee;
import PayrollImpl.NoAffilication;
import PayrollImpl.UnionAffiliation;

public class ChangeUnaffiliatedTransaction extends ChangeAffilationTransaction {

	public ChangeUnaffiliatedTransaction(int empId, PayrollDatabase database) {
		super(empId, database);
	}

	@Override
	protected void RecordMembership(Employee e) {
		Affiliation affiliation = e.getAffiliation();
		if(affiliation instanceof UnionAffiliation){
			UnionAffiliation unionAffiliation = (UnionAffiliation)affiliation;
			int memberId = unionAffiliation.getMemberId();
			database.RemoveUnionMember(memberId);
		}
	}

	@Override
	protected Affiliation getAffiliation() {
		// TODO Auto-generated method stub
		return new NoAffilication();
	}

}
