//按月支付的雇员
public class SalariedClassification extends PaymentClassfication {
	private final double salary;
	
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
