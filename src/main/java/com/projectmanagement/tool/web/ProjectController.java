package com.projectmanagement.tool.web;

import com.projectmanagement.tool.domain.Project;
import com.projectmanagement.tool.services.MapValidationErrorService;
import com.projectmanagement.tool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

  @Autowired private ProjectService projectService;

  @Autowired private MapValidationErrorService mapValidationErrorService;

  @PostMapping("")
  public ResponseEntity<?> createNewProject(
      @Valid @RequestBody Project project, BindingResult result) {
    ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
    if (errorMap != null) return errorMap;
    ResponseEntity<?> res;

    projectService.saveOrUpdateProject(project);
    res = new ResponseEntity<>(project, HttpStatus.CREATED);

    return res;
  }

  @GetMapping("/{projectId}")
  public ResponseEntity<?> getProjectById(@PathVariable String projectId) {
    Project project = projectService.findProjectByIdentifier(projectId);
    return new ResponseEntity<>(project, HttpStatus.OK);
  }

  @GetMapping("/all")
  public Iterable<Project> getAllProjects() {
    return projectService.findAllProjects();
  }

  @DeleteMapping("/{projectId}")
  public ResponseEntity<?> deleteProject(@PathVariable String projectId) {
    projectService.deleteProjectByIdentifier(projectId);
    return new ResponseEntity<String>(
        "Project with Id: '" + projectId + "'was deleted", HttpStatus.OK);
  }
}
