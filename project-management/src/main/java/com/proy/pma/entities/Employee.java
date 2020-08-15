package com.proy.pma.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue (strategy=GenerationType.SEQUENCE, generator="employee_seq")
	@SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq", allocationSize = 1)
	private long employeeId;
	
	private String firstName;
	private String lastName;
	private String email;
	
	//@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.LAZY)	// FetchType.LAZY means the contents of the DB are fetched only when you try to access them.
	//@JoinColumn(name="project_id")	// inside employee table will be created a column named "project_id"
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
	fetch = FetchType.LAZY)	
	@JoinTable(name="project_employee",						// by convention we name the table with the names of the tables involved
		joinColumns= @JoinColumn(name="employee_id"), 		// the key from the project will be employee_id
		inverseJoinColumns= @JoinColumn(name="project_id")	// we give the name of the key of project 
	)
	private List<Project> projects;
	
	public Employee() {	
	}
	
	public Employee(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
