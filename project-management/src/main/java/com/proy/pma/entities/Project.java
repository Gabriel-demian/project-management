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

// Entity means that this class must be stored in a database, if we dont have a table in the database this will create it. 
@Entity
public class Project {
	
	@Id
	@GeneratedValue (strategy=GenerationType.SEQUENCE, generator="project_seq")
	@SequenceGenerator(name = "project_seq", sequenceName = "project_seq", allocationSize = 1)
	private long projectId;
	private String name;
	
	private String stage;	// NOTSTARTED, COMPLETED, INPROGRESS
	
	private String description;
	
	//@OneToMany(mappedBy="project")
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JoinTable(name="project_employee",								// by convention we name the table with the names of the tables involved
				joinColumns= @JoinColumn(name="project_id"), 		// the key from the project will be project_id
				inverseJoinColumns= @JoinColumn(name="employee_id")	// we give the name of the key of employee 
	) 
	private List<Employee> employees;

	
	//******************************
	//**Constructor
	//******************************
	public Project() {
		
	}
	
	public Project(String name, String stage, String description) {
		super();
		this.name = name;
		this.stage = stage;
		this.description = description;
	}
	
	//******************************
	//**GETTERS AND SETTERS
	//******************************
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// Convenience method:
//	public void addEmployee(Employee emp) {
//		if(employees==null) {
//			employees = new ArrayList<>();
//		}
//		employees.add(emp);
//	}

	
	

}
