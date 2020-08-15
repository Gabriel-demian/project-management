package com.proy.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proy.pma.dao.EmployeeRepository;
import com.proy.pma.dao.ProjectRepository;
import com.proy.pma.dato.ChartData;
import com.proy.pma.dato.EmployeeProject;
import com.proy.pma.entities.Project;

@Controller
public class HomeController {
	
	@Value("${version}")
	private String ver;
	
	//	@Autowired	-->		we tell spring to create an instance of ProjectRepository and to inject all the dependencies, in this case the CrudRepository dependencies. 
	//			public interface ProjectRepository extends CrudRepository<Project, Long>   we inject the CrudRepository dependencies into ProjectRepository
	@Autowired		
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		model.addAttribute("versionNumber", ver);
		
		Map<String, Object> map = new HashMap<>();
		
		// we are querying the database for projects
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projects", projects);
		
		// we are querying the database for chartData
		List<ChartData> projectData = proRepo.getProjectStatus();
		
		// lest´s convert projectData object into a json structure for use in JavaScript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		// Example values from jsonString  =  [["NOTSTARTED", 1], ["INPROGRESS", 2], ["COMPLETED", 1]]  
		model.addAttribute("projectStatus", jsonString);
		
		
		// we are querying the database for employees
		List<EmployeeProject> employeesProjectCnt = empRepo.employeeProjects();
		model.addAttribute("employeesListProjectCnt", employeesProjectCnt);
		
		return "main/home";
	}

}
