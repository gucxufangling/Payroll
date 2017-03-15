package PayrollDomain;

import java.util.Date;

import PayrollImpl.NoAffilication;


public class Employee {
	private  final int empid;
	private String name;
	private String address;
	private PaymentClassfication classfication;
	private PaymentSchedule schedule;
	private PaymentMethod method;
	private Affiliation affiliation = new NoAffilication();//默认都没有协会
	
	public Employee(int empid, String name, String address) {
		this.empid = empid;
		this.name = name;
		this.address = address;
	}
	
	public void payday(Paycheck paycheck){
		double grossPay = classfication.calculatePay(paycheck);
		double deductions = affiliation.calculateDeductions(paycheck);
		double netPay = grossPay - deductions;
		paycheck.setGrossPay(grossPay);
		paycheck.setDeductions(deductions);
		paycheck.setNetPay(netPay);
		method.pay(paycheck);
	}
	public  boolean isPayDate(Date date){
		return schedule.isPayDate(date);
	}
	
	public Date GetPayPeriodStartDate(Date date) {
		return schedule.GetPayPeriodStartDate(date);
	}
	
	public int getEmpid() {
		return empid;
	}

	
	public String getName() {
		return name;
	}

	
	public String getAddress() {
		return address;
	}

	
	public PaymentClassfication getClassfication() {
		return classfication;
	}

	
	public PaymentSchedule getSchedule() {
		return schedule;
	}

	
	public PaymentMethod getMethod() {
		return method;
	}

	
	public Affiliation getAffiliation() {
		return affiliation;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
	public void setAddress(String address) {
		this.address = address;
	}

	
	public void setClassfication(PaymentClassfication classfication) {
		this.classfication = classfication;
	}

	
	public void setSchedule(PaymentSchedule schedule) {
		this.schedule = schedule;
	}

	public void setMethod(PaymentMethod method) {
		this.method = method;
	}

	
	public void setAffiliation(Affiliation affiliation) {
		this.affiliation = affiliation;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Emp#: ").append(empid).append("   ");
		builder.append(name).append("   ");
		builder.append(address).append("   ");
		builder.append("Paid ").append(classfication).append(" ");
		builder.append(schedule);
		builder.append(" by ").append(method);
		return builder.toString();
	}
	
}
