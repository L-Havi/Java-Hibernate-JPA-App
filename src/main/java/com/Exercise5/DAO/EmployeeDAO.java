package com.Exercise5.DAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.SessionFactory;

import com.Exercise5.Model.Department;
import com.Exercise5.Model.Employee;

public class EmployeeDAO implements DAO<Employee> {

	private List<Employee> employees = new ArrayList<Employee>();
	
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA-PU");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    private DepartmentDAO departmentDAO = new DepartmentDAO();
    
    public EmployeeDAO() {
    	
    }
    
    @Override
    public Employee get(long id) {
        return entityManager.find(Employee.class, id);
    }
    
    @Override
    public List<Employee> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Employee e");
        return query.getResultList();
    }
    
    @Override
    public void save(Employee employee) {
        executeInsideTransaction(entityManager -> entityManager.persist(employee));
    }
    
    @Override
    public void update(Employee employee, String[] params) {
    	if(params[0] != null) {
            employee.setFirstName(params[0]);
    	}
    	if(params[1] != null) {
    		employee.setLastName(params[1]);
    	}
    	if(params[2] != null) {
    		Date birthDate = java.sql.Date.valueOf(params[2]);
            employee.setBirthDate(birthDate);
    	}
    	if(params[3] != null) {
    		Long phone = Long.parseLong(params[3]);
            employee.setPhoneNumber(phone);
    	}
    	if(params[4] != null) {
    		Long salary = Long.parseLong(params[4]);
            employee.setSalary(salary);
    	}
    	if(params[5] != null) {
    		Date startDate = java.sql.Date.valueOf(params[5]);
            employee.setStartDate(startDate);
    	}
    	if(params[6] != null) {
    		Date endDate = java.sql.Date.valueOf(params[6]);
            employee.setEndDate(endDate);
    	}
    	if(params[7] != null) {
    		Long depId = Long.parseLong(params[7]);
    		Department dep = departmentDAO.get(depId);
            employee.setDepartment(dep);
    	}
        executeInsideTransaction(entityManager -> entityManager.merge(employee));
    }
    
    @Override 
    public void delete(Employee employee) {
        executeInsideTransaction(entityManager -> entityManager.remove(employee));
    }
    
    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit(); 
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
	
	public List<Employee> getEmployeesByDepartmentId(Long department) {
		
        Query query = entityManager.createNativeQuery("SELECT * FROM Employee WHERE department_id = :department");
        query.setParameter("department", department);
        return query.getResultList();
	}


}
