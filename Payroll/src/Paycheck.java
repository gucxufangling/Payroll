import java.util.Date;
import java.util.Hashtable;
/*
 * ÿ���˵�Ʊ��
 */

public class Paycheck {
	private Date payDate;//֧������
	private final Date payPeriodStartDate;//֧����ʼ��ʱ��
	private double grossPay;//���ʲ�
	private Hashtable<String, String> fields = new Hashtable<String, String>();
	private double deductions;
	private double netPay;
	
	public Paycheck(Date payPeriodStartDate, Date payDate) {
		this.payDate = payDate;
		this.payPeriodStartDate = payPeriodStartDate;
	}
	
	public Date getPayPeriodEndDate() {
		return payDate;
	}
	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public double getGrossPay() {
		return grossPay;
	}
	
	public void setGrossPay(double grossPay) {
		this.grossPay = grossPay;
	}

	public String getField(String fieldName) {
		return fields.get(fieldName);
	}

	public void setField(String fieldName, String value) {
		fields.put(fieldName, value);
	}

	public double getDeductions() {
		return deductions;
	}
	
	public void setDeductions(double deductions) {
		this.deductions = deductions;
	}
	
	public double getNetPay() {
		return netPay;
	}
	
	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}
	
	public Date getPayPeriodStartDate() {
		return payPeriodStartDate;
	}

	
}
