package com.practice.supportclient.controllers;


import com.practice.supportclient.models.UserEntity;
import com.practice.supportclient.repositories.IUserRepository;
import com.practice.supportclient.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class UserController {
    private final IUserService userService;
    private final IUserRepository iUserRepository;


    @GetMapping("login")
    public String loginPage(){
        return "connexion";
    }
    @GetMapping("index")
    public String indexPage(){
        return "index";
    }

    @GetMapping("/inscription")
    public String getRegisterForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "inscription";
    }


    @PostMapping("/inscription")
    public String register(@Valid @ModelAttribute("user") UserEntity user,
                           BindingResult result, Model model) {/*
        UserEntity existingUserEmail = userService.findByEmail(user.getEmail());
        if(existingUserEmail != null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()) {
            return "redirect:/register?fail";
        }
        UserEntity existingUserUsername = userService.findByUsername(user.getUsername());
        if(existingUserUsername != null && existingUserUsername.getUsername() != null && !existingUserUsername.getUsername().isEmpty()) {
            return "redirect:/register?fail";
        }*/

        if(result.hasErrors()) {
            model.addAttribute("user", new UserEntity());
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/login?success";
    }
}
