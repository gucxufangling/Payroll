package TransactionImplemention;

import java.util.Date;

import AbstractTransaction.Transaction;
import PayrollDatabase.PayrollDatabase;
import PayrollDomain.Employee;
import PayrollImpl.CommissionClassification;
import PayrollImpl.SalesReceipt;
//登记销售凭条
//异常一：所选择的雇员不是支付酬金的
//异常二：描述操作的结构有错误
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
				throw new Exception("添加的不是销售凭条");
			}
		}else {
			throw new Exception("无该销售类雇员");
		}
	}

}
