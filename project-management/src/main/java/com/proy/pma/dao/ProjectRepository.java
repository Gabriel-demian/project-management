package com.proy.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.proy.pma.dto.ChartData;
import com.proy.pma.dto.TimeChartData;
import com.proy.pma.entities.Project;

@RepositoryRestResource(collectionResourceRel = "apiprojects", path ="apiprojects")
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>{ // Long is the type of id. 
	
	@Override	// we override the method of the CrudRepository so we can pass a List of Project
	public List<Project> findAll();
	
	@Query(nativeQuery = true, value="SELECT stage as label, COUNT(*) as value " + 
			"FROM project " + 
			"GROUP BY stage")
	public List<ChartData> getProjectStatus();
	
	@Query(nativeQuery=true, value="SELECT name as projectName, start_date as startDate, end_date as endDate"
			+ " FROM project WHERE start_date is not null")
	public List<TimeChartData> getTimeData();

	public Project findByProjectId(long theId);
}
