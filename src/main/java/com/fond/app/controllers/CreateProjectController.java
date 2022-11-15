package com.fond.app.controllers;

import com.fond.app.dto.MainDeclarationDto;
import com.fond.app.dto.ProjectDto;
import com.fond.app.dto.UserDto;
import com.fond.app.forms.CreateProjectForm;
import com.fond.app.models.User;
import com.fond.app.security.details.UserDetailsImpl;
import com.fond.app.service.DeclarationsService;
import com.fond.app.service.ProjectsService;
import com.fond.app.service.UsersService;
import com.fond.app.service.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CreateProjectController {
    @Autowired
    ProjectsService projectsService;
    @Autowired
    DeclarationsService declarationsService;
    @Autowired
    UsersService usersService;

    @GetMapping("/createProject")
    public String getCreateProjectPage(Authentication authentication, Model model){
        if(authentication != null){
            UserDto user=usersService.getCurrentUser();
            List<MainDeclarationDto> mainDeclarationDtoList=declarationsService.getAllMainDeclaration();
            model.addAttribute("mainDeclarations",mainDeclarationDtoList);
            model.addAttribute("user",user);
            return "createProject";
        }
        return "redirect:/signIn";
    }

    @GetMapping("/editProject/{projectId:.+}")
    public String getEditProjectPage(Authentication authentication, Model model,
                                     @PathVariable(name = "projectId") long projectId){
        if(authentication != null){
            UserDto user=usersService.getCurrentUser();
            ProjectDto projectDto=projectsService.getProjectOfUser(user.getId(),projectId);
            List<MainDeclarationDto> mainDeclarationDtoList=declarationsService.getAllMainDeclaration();
            model.addAttribute("mainDeclarations",mainDeclarationDtoList);
            model.addAttribute("user",user);
            model.addAttribute("project",projectDto);
            return "updateProject";
        }
        return "redirect:/signIn";
    }

    @PostMapping("/createProject")
    public String createProject(@AuthenticationPrincipal UserDetailsImpl userDetails, CreateProjectForm form){
        User user=userDetails.getUser();
        projectsService.createProject(form,user);
        return "redirect:/myProjects";
    }
    @PostMapping("/updateProject/{projectId:.+}")
    public String updateProject(CreateProjectForm form,
                                @PathVariable(name = "projectId") long projectId){
        UserDto userDto=usersService.getCurrentUser();
        projectsService.updateProject(userDto.getId(),projectId,form);
        return "redirect:/myProjects";
    }
    @PostMapping("/deleteProject/{projectId:.+}")
    public String deleteProject(@PathVariable(name = "projectId") long projectId){
        UserDto user=usersService.getCurrentUser();
        projectsService.deleteProject(user.getId(),projectId);
        return "redirect:/myProjects";
    }

}
