package com.fond.app.controllers;

import com.fond.app.forms.SignUpForm;
import com.fond.app.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "sign_up";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid SignUpForm form) {
        if(signUpService.signUp(form)){
            return "redirect:/signIn";
        }
        return "redirect:/signUp";
    }
}


