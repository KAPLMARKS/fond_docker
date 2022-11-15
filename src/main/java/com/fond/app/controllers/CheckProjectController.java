package com.fond.app.controllers;

import com.fond.app.dto.UserDto;
import com.fond.app.forms.ChangeStatusProjectForm;
import com.fond.app.models.Status;
import com.fond.app.service.ProjectsService;
import com.fond.app.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Configuration
@RequestMapping("/admin")
@Controller
public class CheckProjectController {
    @Autowired
    ProjectsService projectsService;
    @Autowired
    private UsersService userService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/checkProject/{page}")
    public String getCheckProjectPage(Authentication authentication,
                                      Model model, @PathVariable(name = "page") int page,
                                      @RequestParam(name = "status",required = false,defaultValue = "WAIT") String status){
        if(authentication!=null){
            UserDto user=userService.getCurrentUser();
            Status statusEnum=Status.WAIT;
            if(status.equals("CHECK")){
                statusEnum=Status.CHECK;
            }else if(status.equals("VERIFY")){
                statusEnum=Status.VERIFY;
            }
            Pageable pageable= PageRequest.of(page,5);
            model.addAttribute("projects",projectsService.getProjectByStatusInPage(statusEnum,pageable));
            model.addAttribute("totalPage",projectsService.getTotalPage(statusEnum,pageable));
            model.addAttribute("pageSize",pageable.getPageSize());
            model.addAttribute("activePage",page);
            model.addAttribute("statusFind",statusEnum);
            model.addAttribute("user",user);
            return "CheckProject";
        }
        return "redirect:/signIn";
    }

    @PostMapping("/changeStatus/{projectId:.+}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String changeStatus(@PathVariable(name = "projectId") long projectId, ChangeStatusProjectForm form){
        projectsService.changeStatus(projectId,form);
        return "redirect:/admin/checkProject/0";
    }
}
