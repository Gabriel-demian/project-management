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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proy.pma.validators.UniqueValue;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue (strategy=GenerationType.SEQUENCE, generator="employee_seq")
	@SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq", allocationSize = 1)
	private long employeeId;
	
	@NotBlank(message="*Must give a first name")
	@Size(min=2, max=50, message = "First Name must be between 2 and 50 characters")
	private String firstName;
	
	@NotBlank(message="*Must give a last name")
	@Size(min=2, max=50, message = "Last Name must be between 2 and 50 characters")
	private String lastName;
	
	@NotBlank
	@Email(message="*Must be a valid email address")
	//@Column(unique = true)
	@UniqueValue
	private String email;
	
	//@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.LAZY)	// FetchType.LAZY means the contents of the DB are fetched only when you try to access them.
	//@JoinColumn(name="project_id")	// inside employee table will be created a column named "project_id"
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
	fetch = FetchType.LAZY)	
	@JoinTable(name="project_employee",						// by convention we name the table with the names of the tables involved
		joinColumns= @JoinColumn(name="employee_id"), 		// the key from the project will be employee_id
		inverseJoinColumns= @JoinColumn(name="project_id")	// we give the name of the key of project 
	)
	
	@JsonIgnore
	private List<Project> projects;
	
	//******************************
	//**Constructor
	//******************************
	public Employee() {	
	}
	
	public Employee(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	//******************************
	//**GETTERS AND SETTERS
	//******************************
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
