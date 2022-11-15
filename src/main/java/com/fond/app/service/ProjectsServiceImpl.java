package com.fond.app.service;

import com.fond.app.dto.ProjectDto;
import com.fond.app.forms.ChangeStatusProjectForm;
import com.fond.app.forms.CreateProjectForm;
import com.fond.app.models.Project;
import com.fond.app.models.Status;
import com.fond.app.models.User;
import com.fond.app.repositories.CVRepository;
import com.fond.app.repositories.DopDeclarationRepository;
import com.fond.app.repositories.MainDeclarationRepository;
import com.fond.app.repositories.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ProjectsServiceImpl implements ProjectsService{
    @Autowired
    private ProjectsRepository projectsRepository;
    @Autowired
    private MainDeclarationRepository mainDeclarationRepository;
    @Autowired
    private DopDeclarationRepository dopDeclarationRepository;
    @Autowired
    private CVRepository cvRepository;
    @Override
    public void createProject(CreateProjectForm form, User user) {
        Project project=Project.builder()
                .description(form.getDescription())
                .keywords(form.getKeywords())
                .status(Status.WAIT)
                .dopDeclaration(dopDeclarationRepository.getReferenceById(form.getDopDeclaration()))
                .mainDeclaration(mainDeclarationRepository.getReferenceById(form.getMainDeclaration()))
                .user(user)
                .nameProject(form.getNameProject())
                .inn(form.getInn())
                .build();
        projectsRepository.save(project);
    }
    @Override
    public List<ProjectDto> getProjectByStatusInPage(Status status, Pageable pageable){
        Page<Project> projectPage=projectsRepository.findByStatusForPage(status,pageable);
        Stream<Project> projectStream=projectPage.get();
        return ProjectDto.from(projectStream);
    }
    @Override
    public List<ProjectDto> getProjectByUserInPage(long userId, Pageable pageable){
        Page<Project> projectPage=projectsRepository.findByUserForPage(userId,pageable);
        Stream<Project> projectStream=projectPage.get();
        return ProjectDto.from(projectStream);
    }
    @Override
    public int getTotalPage(Status status, Pageable pageable){
        Page<Project> projectPage=projectsRepository.findByStatusForPage(status,pageable);
        return projectPage.getTotalPages();
    }
    @Override
    public int getTotalPage(long userId, Pageable pageable){
        Page<Project> projectPage=projectsRepository.findByUserForPage(userId,pageable);
        return projectPage.getTotalPages();
    }
    @Override
    public void changeStatus(long projectId, ChangeStatusProjectForm form){
        Status status=Status.WAIT;
        if(form.getStatus().equals("CHECK")){
            status=Status.CHECK;
        } else if (form.getStatus().equals("VERIFY")) {
            status=Status.VERIFY;
        }
        Project project=projectsRepository.getReferenceById(projectId);
        project.setStatus(status);
        projectsRepository.save(project);
    }
    @Override
    public ProjectDto getProjectOfUser(long userId, long projectId){
        return ProjectDto.from(projectsRepository.getProjectOfUser(userId,projectId));
    }
    @Override
    public void updateProject(long userId, long projectId, CreateProjectForm form){
        Project project=projectsRepository.getReferenceById(projectId);
        if(project.getUser().getId()==userId){
            project.setNameProject(form.getNameProject());
            project.setDescription(form.getDescription());
            project.setKeywords(form.getKeywords());
            project.setDopDeclaration(dopDeclarationRepository.getReferenceById(form.getDopDeclaration()));
            project.setMainDeclaration(mainDeclarationRepository.getReferenceById(form.getMainDeclaration()));
            project.setStatus(Status.WAIT);
            project.setInn(form.getInn());
            projectsRepository.save(project);
        }
    }
    @Override
    public void deleteProject(long userId, long projectId){
        cvRepository.deleteAllByProject_Id(projectId);
        projectsRepository.deleteProject(userId,projectId);
    }

    @Override
    public List<ProjectDto> selectProjectsByMainDeclaration(Long searchText,Pageable pageable) {
        return projectsRepository.findProjectWithMainDeclaration(searchText,pageable).get().collect(Collectors.toList());
    }

    @Override
    public int totalPageOfProjectsByMainDeclaration(Long searchText,Pageable pageable) {
        return projectsRepository.findProjectWithMainDeclaration(searchText,pageable).get().collect(Collectors.toList()).size();
    }

    @Override
    public List<ProjectDto> selectProjectsByMainAndDopDeclaration(Long searchText, Long searchText2, Pageable pageable) {
        return projectsRepository.findProjectWithMainDeclarationAndDopDeclaration(searchText, searchText2,pageable).get().collect(Collectors.toList());
    }
    @Override
    public int totalPageOfProjectsByMainAndDopDeclaration(Long searchText, Long searchText2, Pageable pageable) {
        return projectsRepository.findProjectWithMainDeclarationAndDopDeclaration(searchText, searchText2,pageable).get().collect(Collectors.toList()).size();
    }
}
