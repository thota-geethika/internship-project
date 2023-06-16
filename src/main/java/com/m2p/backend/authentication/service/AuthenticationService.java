package com.m2p.backend.authentication.service;

import com.m2p.backend.authentication.model.UserDetails;
import com.m2p.backend.authentication.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationRepository authenticationRepository;

    BCryptPasswordEncoder bCrypt =  new BCryptPasswordEncoder();

    public Boolean userIsValid(String user,String password) {
        return (authenticationRepository.validateUserName(user,password) == 1) || (authenticationRepository.validateEmail(user,password) == 1);
    }

    public String decryptPassword(String password){
        byte[] decodedBytes = Base64.getDecoder().decode(password);
        return new String(decodedBytes);
    }

//    public String bcryptEncoder

    public void createUser(UserDetails userDetails) {
//        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

        String frontendEncryptedPassword = userDetails.getPassword();
        System.out.println(frontendEncryptedPassword);

        String frontendDecryptedPassword = decryptPassword(frontendEncryptedPassword);
        System.out.println(frontendDecryptedPassword);

        String encryptedPwd = bCrypt.encode(frontendDecryptedPassword);
        userDetails.setPassword(encryptedPwd);
        authenticationRepository.save(userDetails);
    }

    public boolean checkUserAvailability(String name) {
        return authenticationRepository.checkUserName(name) == 0;
    }

    public boolean checkEmailAvailability(String email) {
        return authenticationRepository.checkEmail(email) == 0;
    }
}
