package com.proy.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proy.pma.dao.EmployeeRepository;
import com.proy.pma.entities.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired  // this auto create an instance and inject it.
	EmployeeRepository empRepo;
	
	@GetMapping
	public String displayEmployees(Model model) {
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("employees", employees);
		return "employees/list-employees";
	}
	
	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {
		
		Employee anEmployee = new Employee();
		
		model.addAttribute("employee", anEmployee);
		
		return "employees/new-employee";
	}
	
	@PostMapping("/save")
	public String createEmployee(Employee employee, Model model) {
		empRepo.save(employee); // one of the methods inside CrudRepository that was extended inside EmployeeRepository
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/employees";
	}
}
