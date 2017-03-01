import java.util.Date;

//�Ǽ�Э�����
public class RegisterServiceCharge extends Transaction {
	private final int memberId;//��Ա��
	private final Date time;//����
	private final double charge;//��Ա��
	
	
	public RegisterServiceCharge(int id, Date time,double charge,
			PayrollDatabase database) {
		super(database);
		this.memberId = id;
		this.time = time;
		this.charge = charge;
	}


	@Override
	public void execute() throws Exception {
		Employee e = database.GetUnionMember(memberId);
		if(e != null){
			UnionAffiliation ua = null;
			if(e.getAffiliation() instanceof UnionAffiliation) {
				ua = (UnionAffiliation)e.getAffiliation();
			}
			if(ua!=null){
				ua.AddServiceCharge(new ServiceCharge(time,charge));
			}else{
				throw new Exception("Tries to add service charge to union"
						+ "member without a union affiliation");
			}
		}else{
			throw new Exception("�޸û�Ա");
		}
	}

}
