package com.m2p.backend.authentication.service;

import com.m2p.backend.authentication.model.UserDetails;
import com.m2p.backend.authentication.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;


import java.util.Base64;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    public String decrypt(String encryptedPassword) {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedPassword);
        return new String(decodedBytes);
    }

    public Boolean userIsValid(String user, String password) {
        String decryptPassword = decrypt(password);
        String passwordFromDb = authenticationRepository.validUserAsPassword(user);
        if(passwordFromDb == null){
            return false;
        }
        return BCrypt.checkpw(decryptPassword,passwordFromDb);
    }

    public void createUser(UserDetails userDetails) {
        authenticationRepository.save(userDetails);
    }

    public boolean checkUserAvailability(String name) {
        return authenticationRepository.checkUserName(name) == 0;
    }

    public boolean checkEmailAvailability(String email) {
        return authenticationRepository.checkEmail(email) == 0;
    }
}
