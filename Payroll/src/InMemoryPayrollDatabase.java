import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


public class InMemoryPayrollDatabase implements PayrollDatabase {
	private static Hashtable<Integer, Employee> employees = new Hashtable<Integer, Employee>();
	private static Hashtable<Integer, Employee> unionMembers = new Hashtable<Integer, Employee>();
	
	public void AddEmployee(Employee employee) {
		employees.put(employee.getEmpid(), employee);
	}

	public void AddUnionMember(int memberId, Employee e) {
		unionMembers.put(memberId, e);
	}

	public void DeleteEmployee(int id) {
		employees.remove(id);
	}

	public ArrayList<Integer> GetAllEmployeeIds() {
		return new ArrayList<Integer>(employees.keySet());
	}

	public List<Employee> GetAllEmployees() {
		return new ArrayList<Employee>(employees.values());
	}

	public Employee GetEmployee(int id) {
		return employees.get(id);
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
