package Test;

import PayrollDatabase.PayrollDatabase;
import PayrollDatabaseImpl.InMemoryPayrollDatabase;
import PayrollDomain.Employee;
import PayrollDomain.PaymentClassfication;
import PayrollDomain.PaymentMethod;
import PayrollDomain.PaymentSchedule;
import PayrollImpl.HoldMethod;
import PayrollImpl.SalariedClassification;
import PayrollImpl.ScheduleMonthly;
import TransactionImplemention.AddSalariedEmplyee;

public class TestAddSalariedEmployee {
	public static void main(String[] args){
		PayrollDatabase payrollDatabase = new InMemoryPayrollDatabase();
		int empId = 1;
		AddSalariedEmplyee t = new AddSalariedEmplyee(empId,"Bob","Home",1000.0,payrollDatabase);
		t.execute();
		
		Employee e = payrollDatabase.GetEmployee(empId);
		assert("Bob".equals(e.getName()));
		System.out.println(e.getName());
		
		PaymentClassfication pc = e.getClassfication();
		SalariedClassification sc = (SalariedClassification)pc;
		assert(sc!=null);
		
		assert(sc.getSalary()==1000.0);
		System.out.println(sc.getSalary());
		
		PaymentSchedule ps = e.getSchedule();
		ScheduleMonthly ms = (ScheduleMonthly)ps;
		assert(ms!=null);
		
		PaymentMethod pm = e.getMethod();
		HoldMethod hm = (HoldMethod)pm;
		assert(hm!=null);
		
		System.out.println("Test success!");
	}
}
