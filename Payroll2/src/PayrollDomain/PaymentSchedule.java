package PayrollDomain;

import java.util.Date;

/*
 * 接口
 * 支付日期
 * 实现判断是否是工作日 和 计算薪水支付的开始日期
 */

public interface PaymentSchedule {
	boolean isPayDate(Date date);
	
	Date GetPayPeriodStartDate(Date date);
}
