package com.proy.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proy.pma.dao.ProjectRepository;
import com.proy.pma.dto.ChartData;
import com.proy.pma.dto.TimeChartData;
import com.proy.pma.entities.Employee;
import com.proy.pma.entities.Project;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository proyRepo;
	
	
	public Project save(Project project) {
		return proyRepo.save(project);
	}
	
	public List<Project> getAll(){
		return proyRepo.findAll();
	}
	
	public List<ChartData> getProjectStatus(){
		return proyRepo.getProjectStatus();
	}
	
	public List<TimeChartData> getTimeData(){
		return proyRepo.getTimeData();
	}
	
	
	public Project findByProjectId(long theId) {
		return proyRepo.findByProjectId(theId);
	}
	
	public void delete(Project theProy) {
		proyRepo.delete(theProy);
	}
}
