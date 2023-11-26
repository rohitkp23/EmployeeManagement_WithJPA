package in.co.vwits.ems.dao.impl;

import java.util.List;
import java.util.Optional;

import in.co.vwits.ems.dao.EmployeeDao;
import in.co.vwits.ems.model.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class EmployeeJpaDaoImpl implements EmployeeDao {

	private EntityManagerFactory factory;
	
	public EmployeeJpaDaoImpl(){
		factory = Persistence.createEntityManagerFactory("emsapp");
	}
	@Override
	public int save(Employee e) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(e);
		tx.commit();
		em.close();
		return 1;
	}

	@Override
	public void deleteByEmpId(int empId) {
		EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
        Employee e = em.find(Employee.class, empId);
        em.remove(e);//this fires delete query
        tx.commit();
        em.close();
		
	}

	@Override
	public Optional<Employee> findByEmpId(int empId) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Employee e = em.find(Employee.class, empId);
		return Optional.of(e);
	}

	@Override
	public List<Employee> findAll() {
		EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    //call a method which returns all the records from the database.
	    String jpql = "FROM Employee";
	    TypedQuery<Employee> query = em.createQuery(jpql,Employee.class);
	    List<Employee> employeeList = query.getResultList();
	    tx.commit();
	    em.close();
		return employeeList;
	}

	@Override
	public void updateByEmpId(Employee e) {
		EntityManager em = factory.createEntityManager();
	    EntityTransaction tx = em.getTransaction();
	    tx.begin();
	    em.merge(e);
        tx.commit();
        em.close();
	}

}
