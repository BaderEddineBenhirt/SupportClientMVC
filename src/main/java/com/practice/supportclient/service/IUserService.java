package com.practice.supportclient.service;

import com.practice.supportclient.models.UserEntity;

public interface IUserService {
    void saveUser(UserEntity user);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}
