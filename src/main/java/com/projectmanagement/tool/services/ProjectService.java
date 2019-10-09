package com.projectmanagement.tool.services;

import com.projectmanagement.tool.domain.Project;
import com.projectmanagement.tool.exceptions.ProjectIdException;
import com.projectmanagement.tool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

  @Autowired private ProjectRepository projectRepository;

  public ProjectService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  public void saveOrUpdateProject(Project project) {
    try {
      project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
      projectRepository.save(project);

    } catch (Exception e) {
      throw new ProjectIdException(
          "Project id'" + project.getProjectIdentifier().toUpperCase() + "'already exists");
    }
  }
}
