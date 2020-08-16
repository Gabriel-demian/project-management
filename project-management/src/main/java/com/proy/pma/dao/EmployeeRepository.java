package com.proy.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.proy.pma.dto.EmployeeProject;
import com.proy.pma.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{

	@Override	// we override the method of the CrudRepository so we can pass a List of Employee
	public List<Employee> findAll();
	
	@Query(nativeQuery = true, value="SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount " + 
			"FROM employee e left join project_employee pe ON pe.employee_id = e.employee_id " + 
			"GROUP BY e.first_name, e.last_name ORDER BY 3 DESC")
	public List<EmployeeProject> employeeProjects();
	
}
