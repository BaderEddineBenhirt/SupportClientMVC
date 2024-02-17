package com.practice.supportclient.controllers;

import com.practice.supportclient.config.SecurityUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("username")
    public String getCurrentUsername() {
        return SecurityUtil.getSessionUser();
    }
}