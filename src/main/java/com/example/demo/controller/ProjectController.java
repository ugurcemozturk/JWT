package com.example.demo.controller;

import com.example.demo.domain.Project;
import org.springframework.util.Assert;

import com.example.demo.repository.ProjectRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @PostMapping
    public void addProject(@RequestBody Project project) {
        projectRepository.save(project);
    }

    @GetMapping
    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    @PutMapping("/{id}")
    public void editProject(@PathVariable long id, @RequestBody Project project) {
        Project existingProject = projectRepository.findOne(id);
        Assert.notNull(existingProject, "Project not found");
        existingProject.setDescription(project.getDescription());
        projectRepository.save(existingProject);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable long id) {
        projectRepository.delete(id);
    }
}