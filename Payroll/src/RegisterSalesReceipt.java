import java.util.Date;

//�Ǽ�����ƾ��
//�쳣һ����ѡ��Ĺ�Ա����֧������
//�쳣�������������Ľṹ�д���
public class RegisterSalesReceipt extends Transaction {
	private final Date date;
	private final double saleAmount;
	private final int empId;
	
	
	public RegisterSalesReceipt(Date date, double saleAmount, 
			int empId, PayrollDatabase database) {
		super(database);
		this.date = date;
		this.saleAmount = saleAmount;
		this.empId = empId;
	}


	@Override
	public void execute() throws Exception {
		Employee e = database.GetEmployee(empId);
		
		if(e != null){
			CommissionClassification cc = (CommissionClassification)e.getClassification();
			if(cc != null){
				cc.AddSalesReceipt(new SalesReceipt(saleAmount, date));
			}else {
				throw new Exception("��ӵĲ�������ƾ��");
			}
		}else {
			throw new Exception("�޸��������Ա");
		}

	}

}
