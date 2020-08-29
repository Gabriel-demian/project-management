package com.proy.pma.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proy.pma.entities.Employee;
import com.proy.pma.services.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired  // this auto create an instance and inject it.
	EmployeeService empService;
	//EmployeeRepository empRepo;
	
	@GetMapping
	public String displayEmployees(Model model) {
		
		//List<Employee> employees = empService.getAll();
		Iterable<Employee> employees = empService.getAll();
		
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
	public String createEmployee(Model model, @Valid Employee employee, Errors errors) {
		if(errors.hasErrors())
			return "employees/new-employee";
		
		//empRepo.save(employee); // one of the methods inside CrudRepository that was extended inside EmployeeRepository
		empService.save(employee);
		// use a redirect to prevent duplicate submissions
		return "redirect:/employees";
	}
	
	@GetMapping("/update")
	public String displayEmployeeUpdateForm(@RequestParam("id") long theId, Model model) {
		
		Employee theEmp = empService.findByEmployeeId(theId);
		
		model.addAttribute("employee", theEmp);
		
		return "employees/new-employee";
	}
	
	@GetMapping("delete")
	public String deleteEmployee(@RequestParam("id") long theId, Model model) {
		Employee theEmp = empService.findByEmployeeId(theId);
		
		empService.delete(theEmp);
		
		return "redirect:/employees";
	}
	
	
	
	
	
	
	
}
