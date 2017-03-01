import java.util.Date;

//异常情况1：所选择的雇员不是钟点雇员
//异常情况2：描述操作的结构中有错误
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
				throw new Exception("添加的不是时间卡");
			}
		}else {
			throw new Exception("无该雇员");
		}
	}

}
