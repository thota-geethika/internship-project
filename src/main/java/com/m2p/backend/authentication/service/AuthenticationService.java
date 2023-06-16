package com.m2p.backend.authentication.service;

import com.m2p.backend.authentication.model.UserDetails;
import com.m2p.backend.authentication.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    long currentUserId = 2;
    @Autowired
    private AuthenticationRepository authenticationRepository;

    public Boolean userIsValid(String user,String password) {
        return (authenticationRepository.validateUserName(user,password) == 1) || (authenticationRepository.validateEmail(user,password) == 1);
    }

    public void createUser(UserDetails userDetails) {
        currentUserId = userDetails.getId();
        authenticationRepository.save(userDetails);
    }

    public boolean checkUserAvailability(String name) {
        return authenticationRepository.checkUserName(name) == 0;
    }

    public boolean checkEmailAvailability(String email) {
        return authenticationRepository.checkEmail(email) == 0;
    }


    public String giveUsername() {
        return authenticationRepository.getUserName(currentUserId);
    }

    public String giveEmail() {
        return authenticationRepository.getEmail(currentUserId);
    }
}
