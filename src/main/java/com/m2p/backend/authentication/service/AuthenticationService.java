package com.m2p.backend.authentication.service;

import com.m2p.backend.authentication.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    public Boolean userIsValid(String user,String password) {
        System.out.println(authenticationRepository.validateUserName(user,password));
        System.out.println(authenticationRepository.validateEmail(user,password));
        return authenticationRepository.validateUserName(user,password) || authenticationRepository.validateEmail(user,password);
    }
}