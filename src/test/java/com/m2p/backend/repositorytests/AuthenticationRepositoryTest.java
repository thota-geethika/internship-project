package com.m2p.backend.repositorytests;

import com.m2p.backend.authentication.repository.AuthenticationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthenticationRepositoryTest {
    @MockBean
    private AuthenticationRepository authenticationRepository;
    @Test
    void shouldReturn1IfUserNameAlreadyExists()
    {
        String userName = "Vicky7";
        int expectedCount = 1;
        int count = authenticationRepository.checkUserName(userName);
        assertEquals(count,expectedCount);
    }

    @Test
    void shouldReturn0IfUserNameIsAvailable()
    {
        String userName = "sruthi";
        int expectedCount = 0;
        int count = authenticationRepository.checkUserName(userName);
        assertEquals(count,expectedCount);
    }

    @Test
    void shouldReturn1IfEmailAlreadyExists()
    {
        String email = "vigneshmanikam2001@gmail.com";
        int expectedCount = 1;
        int count = authenticationRepository.checkEmail(email);
        assertEquals(count,expectedCount);
    }

    @Test
    void shouldReturn0IfAEmailIsAvailable()
    {
        String email = "sruthijith05@gmail";
        int expectedCount = 0;
        int count = authenticationRepository.checkEmail(email);
        assertEquals(count,expectedCount);
    }

    @Test
    void shouldReturnHashedPasswordWhenAUserIsPassed(){
        String user = "admin";
        String expectedHashedPassword = "$2a$10$hM97P9pUgKrOCv0kBp7REuBWuduZ.5gZMkBz9uk/v.E2UAN1du1Yi";
        when(authenticationRepository.validUserAsPassword(user)).thenReturn(expectedHashedPassword);
        String actualPassword = authenticationRepository.validUserAsPassword(user);
        assertEquals(expectedHashedPassword, actualPassword);
    }

}
