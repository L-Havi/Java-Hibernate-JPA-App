package com.Exercise5.DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.Exercise5.Model.Department;

public class DepartmentDAO implements DAO<Department>{

	private List<Department> departments = new ArrayList<Department>();
	
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA-PU");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();
    
    public DepartmentDAO() {
    	
    }
    
    @Override
    public Department get(long id) {
        return entityManager.find(Department.class, id);
    }
    
    @Override
    public List<Department> getAll() {
        Query query = entityManager.createQuery("SELECT e FROM Department e");
        return query.getResultList();
    }
    
    @Override
    public void save(Department department) {
        executeInsideTransaction(entityManager -> entityManager.persist(department));
    }
    
    @Override
    public void update(Department department, String[] params) {
    	if(params[0] != null) {
            department.setDepartmentName(params[0]);
    	}
    	if(params[1] != null) {
            department.setLoc(params[1]);
    	}
        executeInsideTransaction(entityManager -> entityManager.merge(department));
    }
    
    @Override 
    public void delete(Department department) {
        executeInsideTransaction(entityManager -> entityManager.remove(department));
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
	
}
