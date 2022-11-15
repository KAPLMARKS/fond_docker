package com.fond.app.controllers;

import com.fond.app.dto.MainDeclarationDto;
import com.fond.app.dto.ProjectDto;
import com.fond.app.dto.UserDto;
import com.fond.app.models.Project;
import com.fond.app.models.Status;
import com.fond.app.service.DeclarationsService;
import com.fond.app.service.ProjectsService;
import com.fond.app.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.PermitAll;
import java.util.List;

@Configuration
@RequestMapping("/")
@Controller
public class RootController {
    @Autowired
    private UsersService userService;
    @Autowired
    private ProjectsService projectsService;
    @Autowired
    private DeclarationsService declarationsService;

    @PermitAll
    @GetMapping
    public String getRoot(Authentication authentication, Model model, @RequestParam(defaultValue = "0",required = false,name = "page") int page, @RequestParam(value="mainDeclaration" ,defaultValue = "0") Long mainDeclaration, @RequestParam(value = "dopDeclaration",defaultValue = "0") Long dopDeclaration) {
        UserDto user=null;
        if(authentication !=null){
            user=userService.getCurrentUser();
        }
        List<ProjectDto> projects = null;
        int totalPage=0;
        Pageable pageable= PageRequest.of(page,5);
        List<MainDeclarationDto> mainDeclarationDtoList=declarationsService.getAllMainDeclaration();
        model.addAttribute("mainDeclarations",mainDeclarationDtoList);
        if (mainDeclaration == 0) {
            projects = projectsService.getProjectByStatusInPage(Status.VERIFY,pageable);
            totalPage=projectsService.getTotalPage(Status.VERIFY,pageable);
        } else {
            if(dopDeclaration == 0) {
                projects = projectsService.selectProjectsByMainDeclaration(mainDeclaration,pageable);
                totalPage = projectsService.totalPageOfProjectsByMainDeclaration(mainDeclaration,pageable);
            } else {
                projects = projectsService.selectProjectsByMainAndDopDeclaration(mainDeclaration, dopDeclaration,pageable);
                totalPage = projectsService.totalPageOfProjectsByMainAndDopDeclaration(mainDeclaration, dopDeclaration,pageable);
            }
        }
        model.addAttribute("user",user);
        model.addAttribute("projects",projects);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("pageSize",pageable.getPageSize());
        model.addAttribute("activePage",page);
        model.addAttribute("mainDeclarationSel",mainDeclaration!=0 ? declarationsService.getMainDeclaration(mainDeclaration):null);
        model.addAttribute("dopDeclarationSel",dopDeclaration!=0 ? declarationsService.getDopDeclaration(dopDeclaration):null);
        return "home";
    }
}