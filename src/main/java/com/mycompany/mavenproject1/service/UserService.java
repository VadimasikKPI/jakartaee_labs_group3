package com.mycompany.mavenproject1.service;

import com.mycompany.mavenproject1.repository.UserRepository;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Stateless
@Transactional
public class UserService {
    @Inject
    private UserRepository userRepository;

//    public UserService() {
//        this.userRepository = new UserRepository();
//    }

    public boolean login(String username, String password) {
        return userRepository.validateUser(username, password);
    }
}
