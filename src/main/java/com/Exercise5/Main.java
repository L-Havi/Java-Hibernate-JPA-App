package com.Exercise5;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.hibernate.cfg.Configuration;

import com.Exercise5.Model.Department;
import com.Exercise5.Model.Employee;
import com.Exercise5.Service.DepartmentService;
import com.Exercise5.Service.EmployeeService;


public class Main {
	
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JPA-PU");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	
	private static DepartmentService depService = new DepartmentService();
	private static EmployeeService empService = new EmployeeService();
	private static Title title = new Title();
	
    public static void main( String[] args ) {
    	
    	boolean run = true;
    	
    	while(run) {
    		Scanner scanner = new Scanner(System.in);
    		title.printTitle();
    		int choice = scanner.nextInt();
    		if(choice > 0 && choice < 11) {
    			if(choice == 1) {
    				listDep();
    			} else if(choice == 2) {
    				getDep();
    			} else if(choice == 3) {
    				addDep();
    			} else if(choice == 4) {
    				updateDep();
    			} else if(choice == 5) {
    				deleteDep();
    			} else if(choice == 6) {
    				listEmployees();
    			} else if(choice == 7) {
    				getEmployee();
    			} else if(choice == 8) {
    				addEmployee();
    			} else if(choice == 9) {
    				updateEmployee();
    			} else if(choice == 10) {
    				deleteEmployee();
    			}
    		} else {
    			System.out.println("Please type a positive integer between 1-10 to choose action!");
    		}
    	}
    }
    
    private static void deleteEmployee() {
		Scanner scanner = new Scanner(System.in);
    	System.out.println("Input employee ID:");
    	Long employeeId = scanner.nextLong();
    	scanner.nextLine();
    	
    	try {
			empService.deleteEmployee(employeeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void updateEmployee() {
		Scanner scanner = new Scanner(System.in);
    	System.out.println("Input employee ID:");
    	Long employeeId = scanner.nextLong();
    	scanner.nextLine();
    	System.out.println("Input first name:");
    	String firstName = scanner.nextLine();
    	System.out.println("Input last name:");
    	String lastName = scanner.nextLine();
    	System.out.println("Input birth date (YYYY-MM-DD format):");
    	String birthDateString = scanner.nextLine();
    	Date birthDate = java.sql.Date.valueOf(birthDateString);
    	System.out.println("Input phone number:");
    	Long phoneNumber = scanner.nextLong();
    	scanner.nextLine();
    	System.out.println("Input salary:");
    	Long salary = scanner.nextLong();
    	scanner.nextLine();
    	System.out.println("Input start date (YYYY-MM-DD format):");
    	String startDateString = scanner.nextLine();
    	Date startDate = java.sql.Date.valueOf(startDateString);
    	System.out.println("Input end date (YYYY-MM-DD format):");
    	String endDateString = scanner.nextLine();
    	Date endDate = java.sql.Date.valueOf(endDateString);
    	System.out.println("Input department ID:");
    	Long departmentId = scanner.nextLong();
    	scanner.nextLine();
    	
    	try {
			empService.updateEmployee(employeeId, firstName, lastName, birthDate, phoneNumber, salary, startDate, endDate, departmentId);
		} catch (Exception e) {
			
		}
    	
	}

	private static void addEmployee() {
		Scanner scanner = new Scanner(System.in);
    	System.out.println("Input first name:");
    	String firstName = scanner.nextLine();
    	System.out.println("Input last name:");
    	String lastName = scanner.nextLine();
    	System.out.println("Input birth date (YYYY-MM-DD format):");
    	String birthDateString = scanner.nextLine();
    	Date birthDate = java.sql.Date.valueOf(birthDateString);
    	System.out.println("Input phone number:");
    	Long phoneNumber = scanner.nextLong();
    	scanner.nextLine();
    	System.out.println("Input salary:");
    	Long salary = scanner.nextLong();
    	scanner.nextLine();
    	System.out.println("Input start date (YYYY-MM-DD format):");
    	String startDateString = scanner.nextLine();
    	Date startDate = java.sql.Date.valueOf(startDateString);
    	System.out.println("Input end date (YYYY-MM-DD format):");
    	String endDateString = scanner.nextLine();
    	Date endDate = java.sql.Date.valueOf(endDateString);
    	System.out.println("Input department ID:");
    	Long departmentId = scanner.nextLong();
    	scanner.nextLine();
    	
    	try {
			empService.addEmployee(firstName, lastName, birthDate, phoneNumber, salary, startDate, endDate, departmentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
	}

	private static void getEmployee() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Input Employee Id:");
		Long empId = scanner.nextLong();
		scanner.nextLine();
		try {
			String employeeToString = empService.getEmployee(empId);
			System.out.println("Employee " + empId + ":");
			System.out.println("----------------------------------------------");
			System.out.println(employeeToString);
		} catch (Exception e) {
			System.out.println();
		}
	}

	private static void listEmployees() {
    	try {
			List<Employee> employees = empService.listEmployees();
			System.out.println("Employees:");
			System.out.println("----------------------------------------------");
			if(employees != null && employees.size() > 0) {
				for(Employee employee : employees) {
					System.out.println(employee);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
    private static void deleteDep() {
		Scanner scanner = new Scanner(System.in);
    	System.out.println("Input department ID:");
    	Long depId = scanner.nextLong();
    	scanner.nextLine();
    	
    	try {
			depService.deleteDepartment(depId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void updateDep() {
		Scanner scanner = new Scanner(System.in);
    	System.out.println("Input department ID:");
    	Long depId = scanner.nextLong();
    	scanner.nextLine();
    	System.out.println("Input department name:");
    	String depName = scanner.nextLine();
    	System.out.println("Input department location:");
    	String depLocation = scanner.nextLine();
		
    	try {
			depService.updateDepartment(depId, depName, depLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
	}

	private static void addDep() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Input department name:");
    	String depName = scanner.nextLine();
    	System.out.println("Input department location:");
    	String depLocation = scanner.nextLine();
		
    	depService.addDepartment(depName, depLocation);
    	
	}

	private static void getDep() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Input Department Id:");
		Long depId = scanner.nextLong();
		scanner.nextLine();
		try {
			String departmentToString = depService.getDepartment(depId);
			System.out.println("Department " + depId + ":");
			System.out.println("----------------------------------------------");
			System.out.println(departmentToString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void listDep() {
    	try {
			List<Department> departments = depService.listDepartments();
			if(departments != null) {
				System.out.println("Departments:");
				System.out.println("----------------------------------------------");
					for(Department department : departments) {
						System.out.println(department);
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
