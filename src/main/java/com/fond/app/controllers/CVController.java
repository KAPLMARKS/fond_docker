package com.fond.app.controllers;

import com.fond.app.dto.CVDto;
import com.fond.app.dto.MainDeclarationDto;
import com.fond.app.dto.ProjectDto;
import com.fond.app.dto.UserDto;
import com.fond.app.forms.CVForm;
import com.fond.app.forms.CreateProjectForm;
import com.fond.app.models.Project;
import com.fond.app.models.User;
import com.fond.app.repositories.CVRepository;
import com.fond.app.repositories.ProjectsRepository;
import com.fond.app.security.details.UserDetailsImpl;
import com.fond.app.service.CVService;
import com.fond.app.service.ProjectsService;
import com.fond.app.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CVController {
    @Autowired
    private CVService cvService;
    @Autowired
    private ProjectsService projectsService;
    @Autowired
    private ProjectsRepository projectsRepository;

    @Autowired
    private UsersService userService;
    @GetMapping("/cv/{id:.+}")
    private String getCVPage(@PathVariable("id") Long id, Model model, Authentication authentication) {
        UserDto userDto=null;
        if(authentication!=null){
            userDto=userService.getCurrentUser();
        }
        Project project = projectsRepository.getReferenceById(id);
        model.addAttribute("project", project);
        model.addAttribute("user", userDto);
        return "form";
    }
    @GetMapping("/cvsOfProject/{projectId:.+}")
    public String getCvsOfProjectPage(Authentication authentication, Model model,
                                     @PathVariable(name = "projectId") long projectId,
                                     @RequestParam(name = "page",required = false,defaultValue = "0") int page){
        if(authentication != null){
            UserDto user=userService.getCurrentUser();
            Pageable pageable= PageRequest.of(page,5);
            List<CVDto> cvs=cvService.getAllCVByProject(projectId,user.getId(),pageable);
            model.addAttribute("user",user);
            model.addAttribute("cvs",cvs);
            model.addAttribute("totalPage",cvService.getTotalPageCVsByProject(projectId,user.getId(),pageable));
            model.addAttribute("pageSize",pageable.getPageSize());
            model.addAttribute("activePage",page);
            model.addAttribute("projectId",projectId);
            return "cvsOfProject";
        }
        return "redirect:/signIn";
    }
    @PostMapping("/cv/{id:.+}")
    public String sendCVForm(@PathVariable("id") Long id, CVForm form, Model model){
        Project project = projectsRepository.getReferenceById(id);
        model.addAttribute("project", project);
        cvService.createCV(form,(Project)model.getAttribute("project"));
        System.out.println(model.getAttribute("project"));
        return "redirect:/";
    }

}
