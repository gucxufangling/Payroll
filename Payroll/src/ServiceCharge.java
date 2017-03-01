import java.util.Date;

//协会服务费
public class ServiceCharge {
	private final Date date;
	private final double amount;
	
	public ServiceCharge(Date date, double amount) {
		this.date = date;
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	
	public double getAmount() {
		return amount;
	}
	
	
	
	
}
