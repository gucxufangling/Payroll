package Test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import PayrollDatabase.PayrollDatabase;
import PayrollDatabaseImpl.InMemoryPayrollDatabase;
import PayrollDomain.Affiliation;
import PayrollDomain.Employee;
import PayrollDomain.Paycheck;
import PayrollDomain.PaymentClassfication;
import PayrollDomain.PaymentMethod;
import PayrollDomain.PaymentSchedule;
import PayrollImpl.CommissionClassification;
import PayrollImpl.HoldMethod;
import PayrollImpl.HourlyClassifiaction;
import PayrollImpl.NoAffilication;
import PayrollImpl.SalariedClassification;
import PayrollImpl.SalesReceipt;
import PayrollImpl.ScheduleBiWeekly;
import PayrollImpl.ScheduleMonthly;
import PayrollImpl.ScheduleWeekly;
import PayrollImpl.ServiceCharge;
import PayrollImpl.TimeCard;
import PayrollImpl.UnionAffiliation;
import TransactionImplemention.AddCommissionedEmployee;
import TransactionImplemention.AddHourlyEmplyee;
import TransactionImplemention.AddSalariedEmplyee;
import TransactionImplemention.ChangeCommissionedTransaction;
import TransactionImplemention.ChangeHourlyTransaction;
import TransactionImplemention.ChangeMemberTransaction;
import TransactionImplemention.ChangeNameTransaction;
import TransactionImplemention.ChangeSalariedTransaction;
import TransactionImplemention.ChangeUnaffiliatedTransaction;
import TransactionImplemention.DeleteEmployeeTransaction;
import TransactionImplemention.PaydayTransaction;
import TransactionImplemention.SalesReceiptTransaction;
import TransactionImplemention.ServiceChargeTransaction;
import TransactionImplemention.TimeCardTransaction;


import org.junit.Before;
import org.junit.Test;

public class PayrolTest {
	private PayrollDatabase database;
	
	@Before
	public void before() {
		database = new InMemoryPayrollDatabase();
	}
	
	@Test
	public void testAddSalariedEmployee() {
		int empId = 1;
		AddSalariedEmplyee t = new AddSalariedEmplyee(empId, "Bob", "Home",
				1000.00, database);
		t.execute();

		Employee e = database.GetEmployee(empId);
		assertEquals("Bob", e.getName());

		PaymentClassfication pc = e.getClassfication();
		assertTrue(pc instanceof SalariedClassification);
		SalariedClassification sc = (SalariedClassification) pc;

		assertEquals(1000.00, sc.getSalary(), .001);
		PaymentSchedule ps = e.getSchedule();
		assertTrue(ps instanceof ScheduleMonthly);

		PaymentMethod pm = e.getMethod();
		assertTrue(pm instanceof HoldMethod);
	}
	
	@Test
	public void TestAddHourlyEmployee() {
		int empId = 2;
		AddHourlyEmplyee t = new AddHourlyEmplyee(empId,"Miach","home",
				200.00, database);
		t.execute();
		
		Employee e = database.GetEmployee(empId);
		assertEquals("Micah", e.getName());
		
		PaymentClassfication pc = e.getClassfication();
		assertTrue(pc instanceof HourlyClassifiaction);
		
		HourlyClassifiaction hc = (HourlyClassifiaction) pc;
		assertEquals(200.00, hc.getHourlyRate(), .001);
		
		PaymentSchedule ps = e.getSchedule();
		assertTrue(ps instanceof ScheduleWeekly);

		PaymentMethod pm = e.getMethod();
		assertTrue(pm instanceof HoldMethod);
	}
	
	@Test
	public void TestAddCommissionedEmployee() {
		int empId = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId,
				"Justin", "Home", 2500, 9.5, database);
		t.execute();

		Employee e = database.GetEmployee(empId);
		assertEquals("Justin", e.getName());

		PaymentClassfication pc = e.getClassfication();
		assertTrue(pc instanceof CommissionClassification);
		CommissionClassification cc = (CommissionClassification) pc;

		assertEquals(2500, cc.getBaseRate(), .001);
		assertEquals(9.5, cc.getCommissionRate(), .001);
		PaymentSchedule ps = e.getSchedule();
		assertTrue(ps instanceof ScheduleBiWeekly);

		PaymentMethod pm = e.getMethod();
		assertTrue(pm instanceof HoldMethod);
	}
	
	@Test
	public void DeleteEmplyee() throws Exception {
		int empId = 4;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Bill",
				"Home", 2500, 3.2, database);
		t.execute();

		Employee e = database.GetEmployee(empId);
		assertNotNull(e);
		DeleteEmployeeTransaction dt = new DeleteEmployeeTransaction(empId,
				database);
		dt.execute();

		e = database.GetEmployee(empId);
		assertNull(e);
	}
	
	@Test
	public void TestTimeCardTransaction() throws Exception {
		int empId = 5;
		AddHourlyEmplyee t = new AddHourlyEmplyee(empId, "Bill", "Home",
				15.25, database);
		t.execute();
		TimeCardTransaction tct = new TimeCardTransaction(
				new Date(2005, 7, 31), 8.0, empId, database);
		tct.execute();

		Employee e = database.GetEmployee(empId);
		assertNotNull(e);

		PaymentClassfication pc = e.getClassfication();
		assertTrue(pc instanceof HourlyClassifiaction);
		HourlyClassifiaction hc = (HourlyClassifiaction) pc;

		TimeCard tc = hc.getTimecards(new Date(2005, 7, 31));
		assertNotNull(tc);
		assertEquals(8.0, tc.getHours(), .0);
	}
	
	@Test
	public void TestSalesReceiptTransaction() throws Exception {
		int empId = 5;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Bill",
				"Home", 2000, 15.25, database);
		t.execute();
		SalesReceiptTransaction tct = new SalesReceiptTransaction(new Date(
				2005, 7, 31), 250.00, empId, database);
		tct.execute();

		Employee e = database.GetEmployee(empId);
		assertNotNull(e);

		PaymentClassfication pc = e.getClassfication();
		assertTrue(pc instanceof CommissionClassification);
		CommissionClassification cc = (CommissionClassification) pc;

		SalesReceipt sr = cc.getSalesReceipt(new Date(2005, 7, 31));
		assertNotNull(sr);
		assertEquals(250.00, sr.getSaleAmount(), .001);
	}
	
	@Test
	public void AddServiceCharge() throws Exception {
		int empId = 2;
		AddHourlyEmplyee t = new AddHourlyEmplyee(empId, "Bill", "Home",
				15.25, database);
		t.execute();
		Employee e = database.GetEmployee(empId);
		assertNotNull(e);
		UnionAffiliation af = new UnionAffiliation();
		e.setAffiliation(af);
		int memberId = 86; // Maxwell Smart
		database.AddUnionMember(memberId, e);
		ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId,
				new Date(2005, 8, 8), 12.95, database);
		sct.execute();
		ServiceCharge sc = af.GetServiceCharge(new Date(2005, 8, 8));
		assertNotNull(sc);
		assertEquals(12.95, sc.getAmount(), .001);
	}
	
	@Test
	public void TestChangeNameTransaction() throws Exception {
		int empId = 2;
		AddHourlyEmplyee t = new AddHourlyEmplyee(empId, "Bill", "Home",
				15.25, database);
		t.execute();
		ChangeNameTransaction cnt = new ChangeNameTransaction(empId, "Bob",
				database);
		cnt.execute();
		Employee e = database.GetEmployee(empId);
		assertNotNull(e);
		assertEquals("Bob", e.getName());
	}
	
	@Test
	public void TestChangeHourlyTransaction() throws Exception {
		int empId = 3;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance",
				"Home", 2500, 3.2, database);
		t.execute();
		ChangeHourlyTransaction cht = new ChangeHourlyTransaction(empId, 27.52,
				database);
		cht.execute();
		Employee e = database.GetEmployee(empId);
		assertNotNull(e);
		PaymentClassfication pc = e.getClassfication();
		assertNotNull(pc);
		assertTrue(pc instanceof HourlyClassifiaction);
		HourlyClassifiaction hc = (HourlyClassifiaction) pc;
		assertEquals(27.52, hc.getHourlyRate(), .001);
		PaymentSchedule ps = e.getSchedule();
		assertTrue(ps instanceof ScheduleWeekly);
	}
	
	@Test
	public void TestChangeSalaryTransaction() throws Exception {
		int empId = 4;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Lance",
				"Home", 2500, 3.2, database);
		t.execute();
		ChangeSalariedTransaction cst = new ChangeSalariedTransaction(empId,
				3000.00, database);
		cst.execute();
		Employee e = database.GetEmployee(empId);
		assertNotNull(e);
		PaymentClassfication pc = e.getClassfication();
		assertNotNull(pc);
		assertTrue(pc instanceof SalariedClassification);
		SalariedClassification sc = (SalariedClassification) pc;
		assertEquals(3000.00, sc.getSalary(), .001);
		PaymentSchedule ps = e.getSchedule();
		assertTrue(ps instanceof ScheduleMonthly);
	}

	@Test
	public void TestChangeCommisionTransaction() throws Exception {
		int empId = 5;
		AddSalariedEmplyee t = new AddSalariedEmplyee(empId, "Bob", "Home",
				2500.00, database);
		t.execute();
		ChangeCommissionedTransaction cht = new ChangeCommissionedTransaction(
				empId, 1250.00, 5.6, database);
		cht.execute();
		Employee e = database.GetEmployee(empId);
		assertNotNull(e);
		PaymentClassfication pc = e.getClassfication();
		assertNotNull(pc);
		assertTrue(pc instanceof CommissionClassification);
		CommissionClassification cc = (CommissionClassification) pc;
		assertEquals(1250.00, cc.getBaseRate(), .001);
		assertEquals(5.6, cc.getCommissionRate(), .001);
		PaymentSchedule ps = e.getSchedule();
		assertTrue(ps instanceof ScheduleBiWeekly);
	}
	
	@Test
	public void ChangeUnionMember() throws Exception {
		int empId = 9;
		AddHourlyEmplyee t = new AddHourlyEmplyee(empId, "Bill", "Home",
				15.25, database);
		t.execute();
		int memberId = 7743;
		ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId,
				memberId, 99.42, database);
		cmt.execute();
		Employee e = database.GetEmployee(empId);
		assertNotNull(e);
		Affiliation affiliation = e.getAffiliation();
		assertNotNull(affiliation);
		assertTrue(affiliation instanceof UnionAffiliation);
		UnionAffiliation uf = (UnionAffiliation) affiliation;
		assertEquals(99.42, uf.getDues(), .001);
		Employee member = database.GetUnionMember(memberId);
		assertNotNull(member);
		assertEquals(e, member);
	}
	
	@Test
	public void ChangeUnaffiliatedMember() throws Exception {
		int empId = 10;
		AddHourlyEmplyee t = new AddHourlyEmplyee(empId, "Bill", "Home",
				15.25, database);
		t.execute();
		int memberId = 7743;
		new ChangeMemberTransaction(empId, memberId, 99.42, database).execute();
		ChangeUnaffiliatedTransaction cut = new ChangeUnaffiliatedTransaction(
				empId, database);
		cut.execute();
		Employee e = database.GetEmployee(empId);
		assertNotNull(e);
		Affiliation affiliation = e.getAffiliation();
		assertNotNull(affiliation);
		assertTrue(affiliation instanceof NoAffilication);
		Employee member = database.GetUnionMember(memberId);
		assertNull(member);
	}
	
	@Test
	public void PaySingleSalariedEmployee() throws Exception {
		int empId = 1;
		AddSalariedEmplyee t = new AddSalariedEmplyee(empId, "Bob", "Home",
				1000.00, database);
		t.execute();
		Date payDate = new Date(2001, 11, 30);
		PaydayTransaction pt = new PaydayTransaction(payDate, database);
		pt.execute();
		Paycheck pc = pt.GetPaycheck(empId);
		System.out.println(pc);
		assertNotNull(pc);
		assertEquals(payDate, pc.getPayDate());
		assertEquals(1000.00, pc.getGrossPay(), .001);
		assertEquals("Hold", pc.getField("Disposition"));
		assertEquals(0.0, pc.getDeductions(), .001);
		assertEquals(1000.00, pc.getNetPay(), .001);
	}
	
	@Test
	public void PaySingleSalariedEmployeeOnWrongDate() throws Exception {
		int empId = 1;
		AddSalariedEmplyee t = new AddSalariedEmplyee(empId, "Bob", "Home",
				1000.00, database);
		t.execute();
		Date payDate = new Date(2001, 11, 29);
		PaydayTransaction pt = new PaydayTransaction(payDate, database);
		pt.execute();
		Paycheck pc = pt.GetPaycheck(empId);
		assertNull(pc);
	}
	
	private void ValidatePaycheck(PaydayTransaction pt, int empid,
			Date payDate, double pay) {
		Paycheck pc = pt.GetPaycheck(empid);
		assertNotNull(pc);
		assertEquals(payDate, pc.getPayDate());
		assertEquals(pay, pc.getGrossPay(), .001);
		assertEquals("Hold", pc.getField("Disposition"));
		assertEquals(0.0, pc.getDeductions(), .001);
		assertEquals(pay, pc.getNetPay(), .001);
	}
	
	@Test
	public void PaySingleHourlyEmployeeOneTimeCard() throws Exception {
		int empId = 2;
		AddHourlyEmplyee t = new AddHourlyEmplyee(empId, "Bill", "Home",
				15.25, database);
		t.execute();
		Date payDate = new Date(2001, 11, 9); // Friday

		TimeCardTransaction tc = new TimeCardTransaction(payDate, 2.0, empId,
				database);
		tc.execute();
		PaydayTransaction pt = new PaydayTransaction(payDate, database);
		pt.execute();
		ValidatePaycheck(pt, empId, payDate, 30.5);
	}

	@Test
	public void PaySingleHourlyEmployeeOvertimeOneTimeCard() throws Exception {
		int empId = 2;
		AddHourlyEmplyee t = new AddHourlyEmplyee(empId, "Bill", "Home",
				15.25, database);
		t.execute();
		Date payDate = new Date(2001, 11, 9); // Friday
		
		TimeCardTransaction tc = new TimeCardTransaction(payDate, 9.0, empId,
				database);
		tc.execute();
		PaydayTransaction pt = new PaydayTransaction(payDate, database);
		pt.execute();
		ValidatePaycheck(pt, empId, payDate, (8 + 1.5) * 15.25);
	}
	
	@Test
	public void PaySingleHourlyEmployeeOnWrongDate() throws Exception {
		int empId = 2;
		AddHourlyEmplyee t = new AddHourlyEmplyee(empId, "Bill", "Home",
				15.25, database);
		t.execute();
		Date payDate = new Date(2001, 11, 8); // Thursday

		TimeCardTransaction tc = new TimeCardTransaction(payDate, 9.0, empId,
				database);
		tc.execute();
		PaydayTransaction pt = new PaydayTransaction(payDate, database);
		pt.execute();

		Paycheck pc = pt.GetPaycheck(empId);
		assertNull(pc);
	}

	@Test
	public void PaySingleHourlyEmployeeTwoTimeCards() throws Exception {
		int empId = 2;
		AddHourlyEmplyee t = new AddHourlyEmplyee(empId, "Bill", "Home",
				15.25, database);
		t.execute();
		Date payDate = new Date(2001, 11, 9); // Friday

		TimeCardTransaction tc = new TimeCardTransaction(payDate, 2.0, empId,
				database);
		tc.execute();
		Calendar cal = Calendar.getInstance();
		cal.setTime(payDate);
		cal.add(Calendar.DATE, -1);
		TimeCardTransaction tc2 = new TimeCardTransaction(cal.getTime(), 5.0,
				empId, database);
		tc2.execute();
		PaydayTransaction pt = new PaydayTransaction(payDate, database);
		pt.execute();
		ValidatePaycheck(pt, empId, payDate, 7 * 15.25);
	}
	
	@Test
	public void TestPaySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods()
			throws Exception {
		int empId = 2;
		AddHourlyEmplyee t = new AddHourlyEmplyee(empId, "Bill", "Home",
				15.25, database);
		t.execute();
		Date payDate = new Date(2001, 11, 9); // Friday
		Date dateInPreviousPayPeriod = new Date(2001, 10, 30);

		TimeCardTransaction tc = new TimeCardTransaction(payDate, 2.0, empId,
				database);
		tc.execute();
		TimeCardTransaction tc2 = new TimeCardTransaction(
				dateInPreviousPayPeriod, 5.0, empId, database);
		tc2.execute();
		PaydayTransaction pt = new PaydayTransaction(payDate, database);
		pt.execute();
		ValidatePaycheck(pt, empId, payDate, 2 * 15.25);
	}
	
	@Test
	public void PayingSingleCommissionedEmployeeNoReceipts() throws Exception {
		int empId = 2;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Bill",
				"Home", 1500, 10, database);
		t.execute();
		Date payDate = new Date(2001, 11, 16); // Payday
		PaydayTransaction pt = new PaydayTransaction(payDate, database);
		pt.execute();
		ValidatePaycheck(pt, empId, payDate, 1500.0);
	}
	
	@Test
	public void PaySingleCommissionedEmployeeOneReceipt() throws Exception {
		int empId = 2;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Bill",
				"Home", 1500, 10, database);
		t.execute();
		Date payDate = new Date(2001, 11, 16); // Payday

		SalesReceiptTransaction sr = new SalesReceiptTransaction(payDate,
				5000.00, empId, database);
		sr.execute();
		PaydayTransaction pt = new PaydayTransaction(payDate, database);
		pt.execute();
		ValidatePaycheck(pt, empId, payDate, 2000.00);
	}
	
	@Test
	public void PaySingleCommissionedEmployeeOnWrongDate() throws Exception {
		int empId = 2;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Bill",
				"Home", 1500, 10, database);
		t.execute();
		Date payDate = new Date(2001, 11, 9); // wrong friday

		SalesReceiptTransaction sr = new SalesReceiptTransaction(payDate,
				5000.00, empId, database);
		sr.execute();
		PaydayTransaction pt = new PaydayTransaction(payDate, database);
		pt.execute();

		Paycheck pc = pt.GetPaycheck(empId);
		assertNull(pc);
	}

	@Test
	public void PaySingleCommissionedEmployeeTwoReceipts() throws Exception {
		int empId = 2;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Bill",
				"Home", 1500, 10, database);
		t.execute();
		Date payDate = new Date(2001, 11, 16); // Payday

		SalesReceiptTransaction sr = new SalesReceiptTransaction(payDate,
				5000.00, empId, database);
		sr.execute();

		Calendar cal = Calendar.getInstance();
		cal.setTime(payDate);
		cal.add(Calendar.DATE, -1);
		SalesReceiptTransaction sr2 = new SalesReceiptTransaction(
				cal.getTime(), 3500.00, empId, database);
		sr2.execute();
		PaydayTransaction pt = new PaydayTransaction(payDate, database);
		pt.execute();
		ValidatePaycheck(pt, empId, payDate, 2350.00);
	}
	
	@Test
	public void TestPaySingleCommissionedEmployeeWithReceiptsSpanningTwoPayPeriods()
			throws Exception {
		int empId = 2;
		AddCommissionedEmployee t = new AddCommissionedEmployee(empId, "Bill",
				"Home", 1500, 10, database);
		t.execute();
		Date payDate = new Date(2001, 11, 16); // Payday

		SalesReceiptTransaction sr = new SalesReceiptTransaction(payDate,
				5000.00, empId, database);
		sr.execute();
		Calendar cal = Calendar.getInstance();
		cal.setTime(payDate);
		cal.add(Calendar.DATE, -15);
		SalesReceiptTransaction sr2 = new SalesReceiptTransaction(
				cal.getTime(), 3500.00, empId, database);
		sr2.execute();
		PaydayTransaction pt = new PaydayTransaction(payDate, database);
		pt.execute();
		ValidatePaycheck(pt, empId, payDate, 2000.00);
	}
	
	@Test
	public void SalariedUnionMemberDues() throws Exception {
		int empId = 1;
		AddSalariedEmplyee t = new AddSalariedEmplyee(empId, "Bob", "Home",
				1000.00, database);
		t.execute();
		int memberId = 7734;
		ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId,
				memberId, 9.42, database);
		cmt.execute();
		Date payDate = new Date(2001, 11, 30);
		PaydayTransaction pt = new PaydayTransaction(payDate, database);
		pt.execute();
		Paycheck pc = pt.GetPaycheck(empId);
		assertNotNull(pc);
		assertEquals(payDate, pc.getPayDate());
		assertEquals(1000.0, pc.getGrossPay(), .001);
		assertEquals("Hold", pc.getField("Disposition"));
		assertEquals(47.1, pc.getDeductions(), .001);
		assertEquals(1000.0 - 47.1, pc.getNetPay(), .001);
	}

	@Test
	public void HourlyUnionMemberServiceCharge() throws Exception {
		int empId = 1;
		AddHourlyEmplyee t = new AddHourlyEmplyee(empId, "Bill", "Home",
				15.24, database);
		t.execute();
		int memberId = 7734;
		ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId,
				memberId, 9.42, database);
		cmt.execute();
		Date payDate = new Date(2001, 11, 9);
		ServiceChargeTransaction sct = new ServiceChargeTransaction(memberId,
				payDate, 19.42, database);
		sct.execute();
		TimeCardTransaction tct = new TimeCardTransaction(payDate, 8.0, empId,
				database);
		tct.execute();
		PaydayTransaction pt = new PaydayTransaction(payDate, database);
		pt.execute();
		Paycheck pc = pt.GetPaycheck(empId);
		assertNotNull(pc);
		assertEquals(payDate, pc.getPayPeriodEndDate());
		assertEquals(8 * 15.24, pc.getGrossPay(), .001);
		assertEquals("Hold", pc.getField("Disposition"));
		assertEquals(9.42 + 19.42, pc.getDeductions(), .001);
		assertEquals((8 * 15.24) - (9.42 + 19.42), pc.getNetPay(), .001);
	}

	@Test
	public void ServiceChargesSpanningMultiplePayPeriods() throws Exception {
		int empId = 1;
		AddHourlyEmplyee t = new AddHourlyEmplyee(empId, "Bill", "Home",
				15.24, database);
		t.execute();
		int memberId = 7734;
		ChangeMemberTransaction cmt = new ChangeMemberTransaction(empId,
				memberId, 9.42, database);
		cmt.execute();
		Date payDate = new Date(2001, 11, 9);
		Date earlyDate = new Date(2001, 11, 2); // previous Friday
		Date lateDate = new Date(2001, 11, 16); // next Friday
		ServiceChargeTransaction  sct = new ServiceChargeTransaction(memberId,
				payDate, 19.42, database);
		sct.execute();
		ServiceChargeTransaction sctEarly = new ServiceChargeTransaction(
				memberId, earlyDate, 100.00, database);
		sctEarly.execute();
		ServiceChargeTransaction sctLate = new ServiceChargeTransaction(
				memberId, lateDate, 200.00, database);
		sctLate.execute();
		TimeCardTransaction tct = new TimeCardTransaction(payDate, 8.0, empId,
				database);
		tct.execute();
		PaydayTransaction pt = new PaydayTransaction(payDate, database);
		pt.execute();
		Paycheck pc = pt.GetPaycheck(empId);
		assertNotNull(pc);
		assertEquals(payDate, pc.getPayPeriodEndDate());
		assertEquals(8 * 15.24, pc.getGrossPay(), .001);
		assertEquals("Hold", pc.getField("Disposition"));
		assertEquals(9.42 + 19.42, pc.getDeductions(), .001);
		assertEquals((8 * 15.24) - (9.42 + 19.42), pc.getNetPay(), .001);
	}
}
