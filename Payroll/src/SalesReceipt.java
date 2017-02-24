import java.util.Date;

/*
 * 销售凭条，关于销售日期和销售数量
 */
public class SalesReceipt {
	private final double saleAmount;
	private final Date date;
	
	public SalesReceipt(double saleAmount, Date date) {
		this.saleAmount = saleAmount;
		this.date = date;
	}

	public double getSaleAmount() {
		return saleAmount;
	}

	public Date getDate() {
		return date;
	}
	
	
	
}
