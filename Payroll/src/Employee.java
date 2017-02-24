import java.util.Date;


public class Employee {
	private final int empid;
	private String name;
	private String address;
	private PaymentClassfication classification; //雇员的分类
	private PaymentSchedule schedule;//支付薪水的日期
	private PaymentMethod method;//支付方式
	private Affiliation affiliation = new NoAffiliation();//有无协会组织，默认无
	
	public Employee(int empid, String name, String address) {
		this.empid = empid;
		this.name = name;
		this.address = address;
	}
	
	public void payday(Paycheck paycheck) {
		double grossPay = classification.calculatePay(paycheck);//不同部门的职员算得总工资不一样
		double deductions = affiliation.calculateDeductions(paycheck);//协会交的资金
		double netPay = grossPay - deductions;//雇员实际到手的资金
		paycheck.setGrossPay(grossPay);
		paycheck.setDeductions(deductions);
		paycheck.setNetPay(netPay);
		method.pay(paycheck);//支付资金
	}
	/* 
	 * 计算是否是薪水支付日
	 */
	public boolean isPayDate(Date date){
		return schedule.isPayDate(date);
	}
	/*
	 * 
	 */
	public Date GetPayPeriodStartDate(Date date) {
		return schedule.GetPayPeriodStartDate(date);
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public PaymentClassfication getClassification() {
		return classification;
	}


	public void setClassification(PaymentClassfication classification) {
		this.classification = classification;
	}


	public PaymentSchedule getSchedule() {
		return schedule;
	}


	public void setSchedule(PaymentSchedule schedule) {
		this.schedule = schedule;
	}


	public PaymentMethod getMethod() {
		return method;
	}


	public void setMethod(PaymentMethod method) {
		this.method = method;
	}


	public Affiliation getAffiliation() {
		return affiliation;
	}

	
	public void setAffiliation(Affiliation affiliation) {
		this.affiliation = affiliation;
	}

	
	public int getEmpid() {
		return empid;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Emp#: ").append(empid).append("   ");
		builder.append(name).append("   ");
		builder.append(address).append("   ");
		builder.append("Paid ").append(classification).append(" ");
		builder.append(schedule);
		builder.append(" by ").append(method);
		return builder.toString();
	}
	
	
}
