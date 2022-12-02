package com.Exercise5.Service;

import java.sql.Date;
import java.util.List;

import com.Exercise5.DAO.DepartmentDAO;
import com.Exercise5.DAO.EmployeeDAO;
import com.Exercise5.Model.Department;
import com.Exercise5.Model.Employee;

public class EmployeeService {

	private DepartmentDAO departmentDAO = new DepartmentDAO();
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	
	public List<Employee> listEmployees() {
		List<Employee> employees = employeeDAO.getAll();
		return employees;
	}
	
	public void addEmployee(String firstName, String lastName, Date birthDate,
			Long phoneNumber, Long salary, Date startDate, Date endDate, Long departmentId) {
		
		if(departmentId < 0) {
			System.out.println("Department id must be positive");
		}
		try {
			Department dep = departmentDAO.get(departmentId);
			if(dep == null) {
				throw new Exception("Department " + departmentId + " doesn't exist!");
			} else {
				String[] params = new String[8];
				Employee employee = new Employee();
				
				if(firstName != null) {
					employee.setFirstName(firstName);
				}
				if(lastName != null) {
					employee.setLastName(lastName);
				}
				if(birthDate != null) {
					employee.setBirthDate(birthDate);
				}
				if(phoneNumber != null) {
					employee.setPhoneNumber(phoneNumber);
				}
				if(salary != null) {
					employee.setSalary(salary);
				}
				if(startDate != null) {
					employee.setStartDate(startDate);
				}
				if(endDate != null) {
					employee.setEndDate(endDate);
				}
				
				employee.setDepartment(dep);
				
				employeeDAO.save(employee);
				
				System.out.println("Employee was successfully added.");
				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteEmployee(Long employeeId) {
		if(employeeId < 0) {
			System.out.println("Employee id must be positive");
		}
		try {
			Employee employee = employeeDAO.get(employeeId);
			if(employee == null) {
				throw new Exception("Employee was not found");
			} else {
				employeeDAO.delete(employee);
			}
			
		} catch(Exception e) {
			System.out.println("Deleting employee failed: " + e.getStackTrace());
		}
		System.out.println("Employee " + employeeId + " deleted");
	}
	
	public void updateEmployee(Long employeeId, String firstName, String lastName, 
			Date birthDate, Long phoneNumber, Long salary, Date startDate, Date endDate,
			Long departmentId) {
		String[] params = new String[8];
		
		if(employeeId < 0) {
			System.out.println("Employee id must be positive");
		} 
		
		Employee employee = employeeDAO.get(employeeId);
		if(employee == null) {
			System.out.println("Employee was not found");
		} else {
			if(departmentId < 0) {
				System.out.println("Department id must be positive");
			}
			
			Department dep = departmentDAO.get(departmentId);
			
			if(dep == null) {
				System.out.println("Department was not found");
			} else {
				if(!firstName.isBlank()) {
					params[0] = firstName;
				}
				if(!lastName.isBlank()) {
					params[1] = lastName;
				}
				if(phoneNumber > 0) {
					params[2] = phoneNumber.toString();
				}
				if(birthDate != null) {
					params[3] = birthDate.toString();
				}
				if(salary > 0) {
					params[4] = salary.toString();
				}
				if(startDate != null) {
					params[5] = startDate.toString();
				}
				if(endDate != null) {
					params[6] = endDate.toString();
				}
				
				params[7] = departmentId.toString();
				
				employeeDAO.update(employee, params);
				
				System.out.println("Employee " + employeeId + " successfully updated");
			}
		}
		
	}
	
	public String getEmployee(Long employeeId) {
		if(employeeId < 0) {
			System.out.println("Employee id must be positive");
		}
		
		Employee employee = employeeDAO.get(employeeId);
		if(employee == null) {
			System.out.println("Employee was not found");
			return null;
		} else {
			return employee.toString();
		}
	}
	
}
