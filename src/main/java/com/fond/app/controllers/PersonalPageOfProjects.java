package com.fond.app.controllers;

import com.fond.app.dto.ProjectDto;
import com.fond.app.dto.UserDto;
import com.fond.app.models.Project;
import com.fond.app.models.Status;
import com.fond.app.service.ProjectsService;
import com.fond.app.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PersonalPageOfProjects {
    @Autowired
    UsersService usersService;
    @Autowired
    ProjectsService projectsService;
    @GetMapping("/myProjects")
    public String getMyProjectsPage(Authentication authentication, Model model,
                                    @RequestParam(name = "page",required = false,defaultValue = "0") int page){
        if(authentication!=null){
            UserDto user=usersService.getCurrentUser();
            Pageable pageable= PageRequest.of(page,5);
            List<ProjectDto> projectDtoList=projectsService.getProjectByUserInPage(user.getId(),pageable);
            model.addAttribute("user",user);
            model.addAttribute("projects",projectDtoList);
            model.addAttribute("totalPage",projectsService.getTotalPage(user.getId(),pageable));
            model.addAttribute("pageSize",pageable.getPageSize());
            model.addAttribute("activePage",page);
            return "personalPageOfProjects";
        }
        return "redirect:/signIn";
    }
}
