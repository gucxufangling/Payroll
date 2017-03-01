import java.util.Date;

//�쳣���1����ѡ��Ĺ�Ա�����ӵ��Ա
//�쳣���2�����������Ľṹ���д���
public class RegisterTimeCard extends Transaction {
	private final Date date;
	private final double hours;
	private final int empId;
	
	
	public RegisterTimeCard(Date date, double hours, int empId,
			PayrollDatabase database) {
		super(database);
		this.date = date;
		this.hours = hours;
		this.empId = empId;
	}

	@Override
	public void execute() throws Exception {
		Employee e = database.GetEmployee(empId);
		if(e != null){
			HourlyClassifiaction hc = (HourlyClassifiaction)(e.getClassification());
			if(hc != null){
				hc.AddTimeCard(new TimeCard(date, hours));
			}else {
				throw new Exception("��ӵĲ���ʱ�俨");
			}
		}else {
			throw new Exception("�޸ù�Ա");
		}
	}

}
