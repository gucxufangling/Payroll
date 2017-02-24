
public class HoldMethod implements PaymentMethod {

	public void pay(Paycheck paycheck) {
		paycheck.setField("Diaposition", "Hold");

	}
	public String toString(){
		return "hold";
	}
}
