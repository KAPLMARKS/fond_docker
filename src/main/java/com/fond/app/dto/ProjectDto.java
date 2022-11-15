package com.fond.app.dto;

import com.fond.app.models.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProjectDto {
    private Long id;
    private String nameProject;
    private String description;
    private String inn;
    private Status status;
    private MainDeclaration mainDeclaration;
    private DopDeclaration dopDeclaration;
    private String keywords;
    private User user;
    private Set<CV> cvs;

    public static ProjectDto from(Project project){
        return ProjectDto.builder()
                .id(project.getId())
                .nameProject(project.getNameProject())
                .description(project.getDescription())
                .status(project.getStatus())
                .mainDeclaration(project.getMainDeclaration())
                .dopDeclaration(project.getDopDeclaration())
                .keywords(project.getKeywords())
                .user(project.getUser())
                .cvs(project.getCvs())
                .inn(project.getInn())
                .build();
    }
    public static List<ProjectDto> from(List<Project> projects) {
        return projects.stream()
                .map(ProjectDto::from)
                .collect(Collectors.toList());
    }
    public static List<ProjectDto> from(Stream<Project> projects) {
        return projects
                .map(ProjectDto::from)
                .collect(Collectors.toList());
    }
}
