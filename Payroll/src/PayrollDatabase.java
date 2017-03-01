import java.util.ArrayList;
import java.util.List;


public interface PayrollDatabase {
	void AddEmployee(Employee employee);

	Employee GetEmployee(int id);

	void DeleteEmployee(int id);

	Employee GetUnionMember(int memberId);

	ArrayList<Integer> GetAllEmployeeIds();

	List<Employee> GetAllEmployees();

	void RemoveUnionMember(int memberId);

	void AddUnionMember(int memberId, Employee e);




}
