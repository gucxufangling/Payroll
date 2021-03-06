import java.util.Date;
import java.util.Hashtable;


public class CommissionClassification extends PaymentClassfication {
	private final double baseRate;
	private final double commissionRate;
	private Hashtable<Date, SalesReceipt> salesReceipts = new Hashtable<Date, SalesReceipt>();
	
	public CommissionClassification(double baseRate, double commissionRate) {
		this.baseRate = baseRate;
		this.commissionRate = commissionRate;
	}

	@Override
	public double calculatePay(Paycheck paycheck) {
		double salesTotal = 0.0;
		for(SalesReceipt receipt : salesReceipts.values()){
			if(DateUtil.IsInPayPeriod(receipt.getDate(),
					paycheck.getPayPeriodStartDate(), 
					paycheck.getPayPeriodEndDate()))
				salesTotal += receipt.getSaleAmount();
		}
		return baseRate+(salesTotal*commissionRate);
	}

	public double getBaseRate() {
		return baseRate;
	}

	public double getCommissionRate() {
		return commissionRate;
	}

	public SalesReceipt getSalesReceipt(Date time) {
		return salesReceipts.get(time);
	}

	public void AddSalesReceipt(SalesReceipt receipt) {
		salesReceipts.put(receipt.getDate(), receipt);
	}

	public String toString(){
		return String.format("%f + %f 基本资金 酬金", baseRate, commissionRate);
	}
}
