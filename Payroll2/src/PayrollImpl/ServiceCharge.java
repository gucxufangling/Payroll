package PayrollImpl;

import java.util.Date;

//Э�����ѣ����ںͷ���
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
