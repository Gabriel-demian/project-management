package com.proy.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proy.pma.dto.TimeChartData;
import com.proy.pma.entities.Employee;
import com.proy.pma.entities.Project;
import com.proy.pma.services.EmployeeService;
import com.proy.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	
	@Autowired  // this auto create an instance and inject it.
	ProjectService proService;
	//ProjectRepository proRepo;
	
	@Autowired
	EmployeeService empService;
	//EmployeeRepository empRepo;
	
	@GetMapping
	public String displayProjects(Model model) {
		List<Project> projects = proService.getAll();
		model.addAttribute("projects", projects);
		return "projects/list-projects";
	}
	
	@GetMapping("/new")
	public String displayPorjectForm(Model model) {
		
		// Send the model of a Project
		Project aProject = new Project();
		model.addAttribute("project", aProject); // this will bind "project" with th:object="${project}" in the HTML
		
		// Send the list of employees
		Iterable<Employee> employees = empService.getAll();
		model.addAttribute("allEmployees", employees);
		
		return "projects/new-project";
	}
	
	@PostMapping("/save")
	public String createProject(Project project,  Model model) { //@RequestParam List<Long> employees,
		
		proService.save(project); // one of the methods inside CrudRepository that was extended inside ProjectRepository
		
		/*  Deprecated by @ManyToMany
		Iterable<Employee> chosenEmployees = empRepo.findAllById(employees);
		
		for(Employee emp : chosenEmployees) {
			emp.setProject(project);	// will set the projectId inside the employee
			empRepo.save(emp);			// and save it
		}*/
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/projects";
	}
	
	@GetMapping("/timelines")
	public String displayProjectTimelines(Model model) throws JsonProcessingException {
		
		List<TimeChartData> timelineData = proService.getTimeData();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonTimelineString = objectMapper.writeValueAsString(timelineData);

		System.out.println("---------- project timelines ----------");
		System.out.println(jsonTimelineString);
		
		model.addAttribute("projectTimeList", jsonTimelineString);
		
		return "projects/project-timelines";
	}
}
