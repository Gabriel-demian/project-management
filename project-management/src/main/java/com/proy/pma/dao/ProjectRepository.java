package com.proy.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.proy.pma.dato.ChartData;
import com.proy.pma.entities.Project;

public interface ProjectRepository extends CrudRepository<Project, Long>{ // Long is the type of id. 
	
	@Override	// we override the method of the CrudRepository so we can pass a List of Project
	public List<Project> findAll();
	
	@Query(nativeQuery = true, value="SELECT stage as label, COUNT(*) as value " + 
			"FROM project " + 
			"GROUP BY stage")
	public List<ChartData> getProjectStatus();
	
	
}
