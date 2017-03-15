package PayrollImpl;

import PayrollDomain.Paycheck;
import PayrollDomain.PaymentClassfication;

public class SalariedClassification extends PaymentClassfication {
	private double salary;
	
	public SalariedClassification(double salary) {
		this.salary = salary;
	}

	
	@Override
	public double calculatePay(Paycheck paycheck) {
		return salary;
	}

	public double getSalary() {
		return salary;
	}

	public String toString(){
		return String.format("%f", salary);
	}
	
}
