package com.fond.app.controllers;

import com.fond.app.dto.MainDeclarationDto;
import com.fond.app.dto.UserDto;
import com.fond.app.forms.*;
import com.fond.app.service.DeclarationsService;
import com.fond.app.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Configuration
@RequestMapping("/admin")
@Controller
public class CreateDeclarationController {
    @Autowired
    DeclarationsService declarationsService;
    @Autowired
    UsersService usersService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/createDeclaration")
    public String getCreateDeclarationPage(Authentication authentication, Model model){
        List<MainDeclarationDto> mainDeclarationDto=declarationsService.getAllMainDeclaration();
        UserDto user=usersService.getCurrentUser();
        if(authentication!=null){
            model.addAttribute("mainDeclarations",mainDeclarationDto);
            model.addAttribute("user",user);
            return "createDeclaration";
        }
        return "redirect:/signIn";
    }
    @PostMapping("/createMainDeclaration")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createMainDeclaration(CreateMainDeclarationForm form){
        declarationsService.CreateMainDeclaration(form);
        return "redirect:/admin/createDeclaration";
    }
    @PostMapping("/createDopDeclaration")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createDopDeclaration(CreateDopDeclarationForm form){
        MainDeclarationDto mainDeclarationDto=declarationsService.getMainDeclaration(form.getMainDeclaration());
        declarationsService.CreateDopDeclaration(form,mainDeclarationDto.getMainDeclaration());
        return "redirect:/admin/createDeclaration";
    }
    @PostMapping("/deleteMainDeclaration")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteMainDeclaration(DeleteMainDeclarationForm form){
        declarationsService.deleteMainDeclaration(form.getMainDeclaration());
        return "redirect:/admin/createDeclaration";
    }
    @PostMapping("/deleteDopDeclaration")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteDopDeclaration(DeleteDopDeclarationForm form){
        declarationsService.deleteDopDeclaration(form);
        return "redirect:/admin/createDeclaration";
    }
    @PostMapping("/updateMainDeclaration")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateMainDeclaration(UpdateMainDeclarationForm form){
        declarationsService.updateMainDeclaration(form);
        return "redirect:/admin/createDeclaration";
    }
    @PostMapping("/updateDopDopDeclaration")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateDopDeclaration(UpdateDopDeclarationForm form){
        declarationsService.updateDopDeclaration(form);
        return "redirect:/admin/createDeclaration";
    }
}
