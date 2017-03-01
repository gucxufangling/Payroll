
public class ChangeMemberTransaction extends ChangeAffiliationTransaction {
	
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
		// TODO Auto-generated method stub
		return new UnionAffiliation(memberId, dues);
	}

}
