package com.proy.pma;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;


import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

import com.proy.pma.ProjectManagementApplication;
import com.proy.pma.dao.ProjectRepository;
import com.proy.pma.entities.Project;

@SpringBootTest
//@ContextConfiguration(classes=ProjectManagementApplication.class)
@RunWith(SpringRunner.class)
//@DataJpaTest
@SqlGroup({	@Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath:data.sql"}),
			@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD , scripts = "classpath:drop.sql")})
public class ProjectRespositoryIntegrationTest {
	
	@Autowired
	ProjectRepository proRepo;
	
	@Test
	public void ifNewProjectSaved_thenSuccess() {
		Project newProject = new Project("New Test Project", "COMPLETE", "Test Description");
		proRepo.save(newProject);
		
		assertEquals(5, proRepo.findAll().size());
	}
	
}
