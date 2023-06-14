package com.m2p.backend.authentication.service;

import com.m2p.backend.authentication.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    public Boolean userIsValid(String user,String password) {
        return authenticationRepository.validateUserName(user,password) > 0|| authenticationRepository.validateEmail(user,password) > 0;
    }
}
