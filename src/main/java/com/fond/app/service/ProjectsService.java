package com.fond.app.service;

import com.fond.app.dto.ProjectDto;
import com.fond.app.forms.ChangeStatusProjectForm;
import com.fond.app.forms.CreateProjectForm;
import com.fond.app.models.Project;
import com.fond.app.models.Status;
import com.fond.app.models.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectsService {
    void createProject(CreateProjectForm form, User user);
    List<ProjectDto> getProjectByStatusInPage(Status status, Pageable pageable);

    List<ProjectDto> getProjectByUserInPage(long userId, Pageable pageable);

    int getTotalPage(Status status, Pageable pageable);

    int getTotalPage(long userId, Pageable pageable);

    void changeStatus(long projectId, ChangeStatusProjectForm form);

    ProjectDto getProjectOfUser(long userId, long projectId);

    void updateProject(long userId, long projectId, CreateProjectForm form);

    void deleteProject(long userId, long projectId);

    List<ProjectDto> selectProjectsByMainDeclaration(Long searchText, Pageable pageable);

    int totalPageOfProjectsByMainDeclaration(Long searchText, Pageable pageable);

    List<ProjectDto> selectProjectsByMainAndDopDeclaration(Long searchText, Long searchText2, Pageable pageable);

    int totalPageOfProjectsByMainAndDopDeclaration(Long searchText, Long searchText2, Pageable pageable);
}
