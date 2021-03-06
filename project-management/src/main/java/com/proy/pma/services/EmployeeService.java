package com.proy.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proy.pma.dao.EmployeeRepository;
import com.proy.pma.dto.EmployeeProject;
import com.proy.pma.entities.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository empRepo;
	
	public Employee save(Employee employee) {
		return empRepo.save(employee);
	}
	
	public Iterable<Employee> getAll(){
		return empRepo.findAll();
	}
	
	public List<EmployeeProject> employeeProjects(){
		return empRepo.employeeProjects();
	}

	public Employee findByEmployeeId(long theId) {
		return empRepo.findByEmployeeId(theId);
	}
	
	public void delete(Employee theEmp) {
		empRepo.delete(theEmp);
	}
}
