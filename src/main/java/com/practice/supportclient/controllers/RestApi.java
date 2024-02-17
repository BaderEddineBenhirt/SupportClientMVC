package com.practice.supportclient.controllers;

import com.practice.supportclient.models.Role;
import com.practice.supportclient.repositories.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class RestApi {
    private final IRoleRepository iRoleRepository;

    @GetMapping("/{rolename}")
    public Role role( @PathVariable String rolename){

        return iRoleRepository.findByName(rolename);
    }
}
