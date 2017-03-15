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
	//根据会员号得到对应的雇员
	Employee GetUnionMember(int memberId);


}
