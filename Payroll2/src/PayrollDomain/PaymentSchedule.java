package PayrollDomain;

import java.util.Date;

/*
 * �ӿ�
 * ֧������
 * ʵ���ж��Ƿ��ǹ����� �� ����нˮ֧���Ŀ�ʼ����
 */

public interface PaymentSchedule {
	boolean isPayDate(Date date);
	
	Date GetPayPeriodStartDate(Date date);
}
