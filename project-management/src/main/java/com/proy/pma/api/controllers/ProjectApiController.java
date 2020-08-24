package com.proy.pma.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.proy.pma.dao.ProjectRepository;
import com.proy.pma.entities.Project;
import javax.validation.Valid;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {
	
	@Autowired
	ProjectRepository proyRepo;
	
	@GetMapping
	public Iterable<Project> getProjects(){
		return proyRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Project getProjectById(@PathVariable("id") Long id) {
		return proyRepo.findById(id).get();
	}
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Project create(@RequestBody @Valid Project project) {
		return proyRepo.save(project);
	}
	
	@PutMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Project update(@RequestBody @Valid Project project) {
		return proyRepo.save(project);
	}
	
	
	@PatchMapping(path="/{id}", consumes= "application/json")
	public Project partialUpdate(@PathVariable("id") long id, @RequestBody @Valid Project patchProject) {
		Project proy = proyRepo.findById(id).get();
		
		if(patchProject.getName() != null) {
			proy.setName(patchProject.getName());
		}
		if(patchProject.getStage() != null) {
			proy.setStage(patchProject.getStage());
		}
		if(patchProject.getDescription() != null) {
			proy.setDescription(patchProject.getDescription());
		}
		
		return proyRepo.save(proy);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		try {
			proyRepo.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			
		}
	}
}
