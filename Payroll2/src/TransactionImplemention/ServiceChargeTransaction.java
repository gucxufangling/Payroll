package TransactionImplemention;

import java.util.Date;

import AbstractTransaction.Transaction;
import PayrollDatabase.PayrollDatabase;
import PayrollDomain.Employee;
import PayrollImpl.ServiceCharge;
import PayrollImpl.UnionAffiliation;
//�Ǽ�Э�����
public class ServiceChargeTransaction extends Transaction {
	private final int memberId;//��Ա��
	private final Date time;//����
	private final double charge;//��Ա��
	
	public ServiceChargeTransaction(int id, Date time,double charge,
			PayrollDatabase database) {
		super(database);
		this.memberId = id;
		this.time = time;
		this.charge = charge;
	}

	@Override
	public void execute() throws Exception {
		Employee employee = database.GetUnionMember(memberId);
		if(employee != null){
			UnionAffiliation uAffiliation = null;
			if(employee.getAffiliation() instanceof UnionAffiliation){
				uAffiliation = (UnionAffiliation)employee.getAffiliation();
			}
			if(uAffiliation != null){
				uAffiliation.AddServiceCharge(new ServiceCharge(time,charge));
			}else {
				throw new Exception("Tries to add service charge to union"
						+ "member without a union affiliation");
			}
		}else {
			throw new Exception("�޸û�Ա");
		}
	}

}
