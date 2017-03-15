package PayrollDatabase;

import java.util.ArrayList;

import PayrollDomain.Employee;

public interface PayrollDatabase {

	void AddEmployee(Employee e);

	Employee GetEmployee(int empId);

	void DeleteEmployee(int empid);

	void AddUnionMember(int memberId, Employee e);

	void RemoveUnionMember(int memberId);

	ArrayList<Integer> GetAllEmployeeIds();
	//���ݻ�Ա�ŵõ���Ӧ�Ĺ�Ա
	Employee GetUnionMember(int memberId);


}
