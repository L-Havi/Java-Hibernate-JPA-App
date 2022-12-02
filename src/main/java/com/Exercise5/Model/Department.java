package com.Exercise5.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.List;

import javax.persistence.*;  

@Entity
@Table(name = "department")

public class Department {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id") 
    private long id;
    
    @Column(name = "department_name") 
    private String departmentName;
 
    @Column(name = "loc") 
    private String loc;

    @OneToMany(mappedBy = "department")
    private List<Employee> employee;
    
    public long getId() { 
    	return id; 
    }
    
    public void setId(long id) { 
    	this.id = id; 
    }
 
    public String getDepartmentName() { 
    	return departmentName; 
    }
 
    public void setDepartmentName(String departmentName){
        this.departmentName = departmentName;
    }
 
    public String getLoc() { 
    	return loc; 
    }
 
    public void setLoc(String loc) {
        this.loc = loc;
    }

	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentName=" + departmentName + ", loc=" + loc + "]";
	}
    
}
