package PayrollDatabaseImpl;

import java.util.ArrayList;
import java.util.Hashtable;

import PayrollDatabase.PayrollDatabase;
import PayrollDomain.Employee;

public class InMemoryPayrollDatabase implements PayrollDatabase {
	private static Hashtable<Integer, Employee> employees = new Hashtable<Integer, Employee>();
	private static Hashtable<Integer, Employee> unionMembers = new Hashtable<Integer, Employee>();
	//会员号与雇员
	public void AddEmployee(Employee employee) {
		employees.put(employee.getEmpid(), employee);
	}
	
	public Employee GetEmployee(int id) {
		return employees.get(id);
	}

	public void AddUnionMember(int memberId, Employee e) {
		unionMembers.put(memberId, e);
	}

	public void DeleteEmployee(int empid) {
		employees.remove(empid);
	}

	public ArrayList<Integer> GetAllEmployeeIds() {
		return new ArrayList<Integer>(employees.keySet());
	}

	public Employee GetUnionMember(int memberId) {
		return unionMembers.get(memberId);
	}

	public void RemoveUnionMember(int memberId) {
		unionMembers.remove(memberId);		
	}
	public void clear() {
		employees.clear();
		unionMembers.clear();
	}
	
}
