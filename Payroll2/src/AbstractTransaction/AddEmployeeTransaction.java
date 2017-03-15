package AbstractTransaction;

import PayrollDatabase.PayrollDatabase;
import PayrollDomain.Employee;
import PayrollDomain.PaymentClassfication;
import PayrollDomain.PaymentMethod;
import PayrollDomain.PaymentSchedule;
import PayrollImpl.HoldMethod;

/*
 * ����û�
 * ���û����з��ࡢȷ��֧����ʽ��֧�����ڣ�����Ӧ�����ݵ����û�������
 * ���û�����������ݿ���
 */
public abstract class AddEmployeeTransaction extends Transaction{
	private final int empid;
	private final String name;
	private final String address;

	public AddEmployeeTransaction(int empid, String name, String address,
			PayrollDatabase database) {
		super(database);
		this.empid = empid;
		this.name = name;
		this.address = address;
	}
	
	protected abstract PaymentClassfication makeClassfication();
	
	protected abstract PaymentSchedule makeSchedule();
	
	@Override
	public void execute() {
		PaymentClassfication pc = makeClassfication();
		PaymentSchedule ps = makeSchedule();
		PaymentMethod pm = new HoldMethod();
		Employee e = new Employee(empid,name,address);
		e.setClassfication(pc);
		e.setSchedule(ps);
		e.setMethod(pm);
		database.AddEmployee(e);
	}
	
	public String toString(){
		return String.format("%s  id:%d   name:%s   address:%s", getClass()
				.getName(), empid, name, address);
	}
}
