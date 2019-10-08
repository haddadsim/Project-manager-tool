package com.projectmanagement.tool.web;

import com.projectmanagement.tool.domain.Project;
import com.projectmanagement.tool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

  @Autowired private ProjectService projectService;

  @PostMapping("")
  public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
    // response entity has more control options over json responses
      if(result.hasErrors()){
          return new ResponseEntity<String>("Invalid Project", HttpStatus.BAD_REQUEST);

      }
    projectService.saveOrUpdateProject(project);
    return new ResponseEntity<>(project, HttpStatus.CREATED);
  }
}
