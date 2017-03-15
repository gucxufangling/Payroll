package PayrollImpl;

import PayrollDomain.Affiliation;
import PayrollDomain.Paycheck;

public class NoAffilication implements Affiliation {

	public double calculateDeductions(Paycheck paycheck) {
		return 0;
	}
	
}
