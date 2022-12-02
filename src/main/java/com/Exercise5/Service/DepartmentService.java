package com.Exercise5.Service;

import java.util.List;
import java.util.Optional;

import com.Exercise5.DAO.DepartmentDAO;
import com.Exercise5.DAO.EmployeeDAO;
import com.Exercise5.Model.Department;
import com.Exercise5.Model.Employee;

public class DepartmentService {

	private DepartmentDAO departmentDAO = new DepartmentDAO();
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	
	public List<Department> listDepartments() {
		List<Department> departments = departmentDAO.getAll();
		return departments;
	}
	
	public void addDepartment(String departmentName, String location) {
		
		String[] params = new String[2];
		Department dep = new Department();
		
		if(!departmentName.isBlank()) {
			dep.setDepartmentName(departmentName);
		}
		if(!location.isBlank()) {
			dep.setLoc(location);
		}

		departmentDAO.save(dep);
		
		System.out.println("Department was successfully added.");
		
	}
	
	public void deleteDepartment(Long departmentId) {
		if(departmentId < 0) {
			System.out.println("Department id must be positive");
		}
		try {
			Department dep = departmentDAO.get(departmentId);
			if(dep == null) {
				System.out.println("Department was not found");
			} else {
				List<Employee> employees = employeeDAO.getEmployeesByDepartmentId(departmentId);
				
				if(employees != null || employees.size() > 0) {
					for(Employee employee : employees) {
						employeeDAO.delete(employee);
					}
				}
				
				departmentDAO.delete(dep);
				System.out.println("Department " + departmentId + " deleted");
			}
		} catch(Exception e) {
			System.out.println("Deleting department failed.");
		}
	}
	
	public void updateDepartment(Long departmentId, String departmentName, String location) {
		String[] params = new String[2];
		
		if(departmentId < 0) {
			System.out.println("Department id must be positive");
		}
		
		Department dep = departmentDAO.get(departmentId);
		if(dep == null) {
			System.out.println("Department was not found");
		} else {
			if(!departmentName.isBlank()) {
				params[0] = departmentName;
			}
			if(!location.isBlank()) {
				params[1] = location;
			}
			
			departmentDAO.update(dep, params);
			
			System.out.println("Department " + departmentId + " was updated.");
		}
		
	}
	
	public String getDepartment(Long departmentId) {
		if(departmentId < 0) {
			System.out.println("Department id must be positive");
		}
		
		Department dep = departmentDAO.get(departmentId);
		if(dep == null) {
			System.out.println("Department was not found");
			return null;
		} else {
			return dep.toString();
		}

	}
	
}
