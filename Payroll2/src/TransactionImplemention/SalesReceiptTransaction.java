package TransactionImplemention;

import java.util.Date;

import AbstractTransaction.Transaction;
import PayrollDatabase.PayrollDatabase;
import PayrollDomain.Employee;
import PayrollImpl.CommissionClassification;
import PayrollImpl.SalesReceipt;
//�Ǽ�����ƾ��
//�쳣һ����ѡ��Ĺ�Ա����֧������
//�쳣�������������Ľṹ�д���
public class SalesReceiptTransaction extends Transaction {
	private final Date date;
	private final double saleAmount;
	private final int empId;
	
	public SalesReceiptTransaction(Date date, double saleAmount, 
			int empId, PayrollDatabase database) {
		super(database);
		this.date = date;
		this.saleAmount = saleAmount;
		this.empId = empId;
	}

	@Override
	public void execute() throws Exception {
		Employee employee = database.GetEmployee(empId);
		if(employee  != null){
			CommissionClassification cc = (CommissionClassification)employee.getClassfication();
			if(cc != null){
				cc.AddSalesReceipt(new SalesReceipt(saleAmount,date));
			}else{
				throw new Exception("��ӵĲ�������ƾ��");
			}
		}else {
			throw new Exception("�޸��������Ա");
		}
	}

}
