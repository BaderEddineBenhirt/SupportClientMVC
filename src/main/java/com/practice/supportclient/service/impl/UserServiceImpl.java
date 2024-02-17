package com.practice.supportclient.service.impl;

import com.practice.supportclient.models.Role;
import com.practice.supportclient.models.UserEntity;
import com.practice.supportclient.repositories.IRoleRepository;
import com.practice.supportclient.repositories.IUserRepository;
import com.practice.supportclient.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public void saveUser(UserEntity user) {
        Role r= roleRepository.findByName("USER");
        if (r==null)
            r=new Role("USER");
            user.setRoles(Arrays.asList(r));

        user.setPassword(passwordEncoder.encode(user.getPassword()));


        userRepository.save(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
       return userRepository.findByEmail(email);

    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
