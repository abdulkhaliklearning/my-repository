package com.telus.model;

import javax.validation.constraints.NotBlank;

public class Employee {

    private Long id;

    @NotBlank(message = "emp name is required")
    private String emp_name;

    @NotBlank(message = "email is required")
    private String email;

    @NotBlank(message = "project is required")
    private String project;

    @NotBlank(message = "designation is required")
    private String designation;

    Employee() {
    }

    public Employee(Long id, String emp_name, String email, String project, String designation) {
        this.id = id;
        this.emp_name = emp_name;
        this.email = email;
        this.project = project;
        this.designation = designation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
