package com.Exercise5.Model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;  

@Entity
@Table(name = "employee")

public class Employee {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "EMPLOYEE_ID") 
	 private long id;

	 @Column(name = "FIRST_NAME") 
	 private String firstName;
	 
	 @Column(name = "LAST_NAME") 
	 private String lastName;
	 
	 @Basic
	 @Column(name = "BIRTH_DATE")
	 private java.sql.Date birthDate;
	 
	 @Column(name = "PHONE_NUMBER")
	 private long phoneNumber;
	 
	 @Column(name = "SALARY")
	 private long salary;
	 
	 @Basic
	 @Column(name = "START_DATE")
	 private java.sql.Date startDate;
	 
	 @Basic
	 @Column(name = "END_DATE")
	 private java.sql.Date endDate;
	 
	 @ManyToOne
	 @JoinColumn(name = "department_id", nullable = false)
	 private Department department;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public java.sql.Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(java.sql.Date birthDate) {
		this.birthDate = birthDate;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public java.sql.Date getStartDate() {
		return startDate;
	}

	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}

	public java.sql.Date getEndDate() {
		return endDate;
	}

	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", phoneNumber=" + phoneNumber + ", salary=" + salary + ", startDate=" + startDate + ", endDate="
				+ endDate + ", department=" + department + "]";
	}
	
}
