package in.co.vwits.ems.service;

import java.util.List;
import java.util.Optional;

import in.co.vwits.ems.model.Employee;
import in.co.vwits.model.exception.EmployeeNotFoundException;

public interface EmployeeService {
	
	List<Employee> findAllEmployeeSortedBySalary();
	
	List<Employee> findAllEmployeeSortedByName();

	List<Employee> findAll();

	void save(Employee e);

	Optional<Employee> findByEmpId(int empId) throws EmployeeNotFoundException;

	void deleteByEmpId(int empId);

	void updateByEmpId(Employee e);
	
}
