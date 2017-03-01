import java.util.Date;

//登记销售凭条
//异常一：所选择的雇员不是支付酬金的
//异常二：描述操作的结构有错误
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
				throw new Exception("添加的不是销售凭条");
			}
		}else {
			throw new Exception("无该销售类雇员");
		}

	}

}
