package com.proy.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proy.pma.dao.EmployeeRepository;
import com.proy.pma.dao.ProjectRepository;
import com.proy.pma.entities.Employee;
import com.proy.pma.entities.Project;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired  // this auto create an instance and inject it.
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projects", projects);
		return "projects/list-projects";
	}
	
	@GetMapping("/new")
	public String displayPorjectForm(Model model) {
		
		// Send the model of a Project
		Project aProject = new Project();
		model.addAttribute("project", aProject); // this will bind "project" with th:object="${project}" in the HTML
		
		// Send the list of employees
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("allEmployees", employees);
		
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project,  Model model) { //@RequestParam List<Long> employees,
		
		proRepo.save(project); // one of the methods inside CrudRepository that was extended inside ProjectRepository
		
		/*  Deprecated by @ManyToMany
		Iterable<Employee> chosenEmployees = empRepo.findAllById(employees);
		
		for(Employee emp : chosenEmployees) {
			emp.setProject(project);	// will set the projectId inside the employee
			empRepo.save(emp);			// and save it
		}*/
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/projects";
	}
	
}
