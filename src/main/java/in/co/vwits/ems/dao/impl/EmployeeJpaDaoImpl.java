package in.co.vwits.ems.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import in.co.vwits.ems.dao.EmployeeDao;
import in.co.vwits.ems.model.Employee;

@Repository
public class EmployeeJpaDaoImpl implements EmployeeDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public int save(Employee e) {
		em.persist(e);
		return 1;
	}

	@Override
	public void deleteByEmpId(int empId) {
        Employee e = em.find(Employee.class, empId);
        em.remove(e);//this fires delete query
	}

	@Override
	public Optional<Employee> findByEmpId(int empId) {
		Employee e = em.find(Employee.class, empId);
		return Optional.of(e);
	}

	@Override
	public List<Employee> findAll() {
	    String jpql = "FROM Employee";
	    TypedQuery<Employee> query = em.createQuery(jpql,Employee.class);
	    List<Employee> employeeList = query.getResultList();
		return employeeList;
	}

	@Override
	public void updateByEmpId(Employee e) {
	    em.merge(e);
	}

}
