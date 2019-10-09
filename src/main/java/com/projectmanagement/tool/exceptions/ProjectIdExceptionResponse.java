package com.projectmanagement.tool.exceptions;

public class ProjectIdExceptionResponse {

  public ProjectIdExceptionResponse(String projectIdentifier) {
    this.projectIdentifier = projectIdentifier;
  }

  public String getProjectIdentifier() {
    return projectIdentifier;
  }

  public void setProjectIdentifier(String projectIdentifier) {
    this.projectIdentifier = projectIdentifier;
  }

  private String projectIdentifier;
}
