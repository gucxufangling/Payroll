import java.util.Date;

/*
 * ����ƾ���������������ں���������
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
