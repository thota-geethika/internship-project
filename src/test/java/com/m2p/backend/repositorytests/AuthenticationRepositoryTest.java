package com.m2p.backend.repositorytests;

import com.m2p.backend.authentication.model.UserDetails;
import com.m2p.backend.authentication.repository.AuthenticationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringJUnitConfig
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthenticationRepositoryTest {
    @MockBean
    private AuthenticationRepository authenticationRepository;

    UserDetails details;
    @BeforeEach
    void setUp(){
        details = new UserDetails(1,"Geethika","geethika@gmail.com","geeths02");
    }

//    @Test
//    public void toTestValidUserReturnsTrue() {
//        String user = "admin";
//        String password = "admin";
//        int count = 1;
//        String expectedQuery = "SELECT COUNT(*) FROM UserDetails WHERE username=:user AND password=:password";
//
//        when(entityManager.createQuery(expectedQuery)).thenReturn(query);
//        when(query.setParameter("user", user)).thenReturn(query);
//        when(query.setParameter("password", password)).thenReturn(query);
//        when(query.getSingleResult()).thenReturn(count);
//
//        int result = authenticationRepository.validateUserName(user, password);
//
//        verify(entityManager).createQuery(expectedQuery);
//        verify(query).setParameter("user", user);
//        verify(query).setParameter("password", password);
//        verify(query).getSingleResult();
//        assertEquals(result,count);
//    }
//
//    @Test
//    public void toTestValidEmailReturnsTrue() {
//        String email = "admin@gmail.com";
//        String password = "admin";
//        int count = 1;
//        String expectedQuery = "SELECT COUNT(*) FROM UserDetails WHERE email=:user AND password=:password";
//
//        when(entityManager.createQuery(expectedQuery)).thenReturn(query);
//        when(query.setParameter("user", email)).thenReturn(query);
//        when(query.setParameter("password", password)).thenReturn(query);
//        when(query.getSingleResult()).thenReturn(count);
//
//        int result = authenticationRepository.validateEmail(email, password);
//        verify(entityManager).createQuery(expectedQuery);
//        verify(query).setParameter("user", email);
//        verify(query).setParameter("password", password);
//        verify(query).getSingleResult();
//        assertEquals(result,count);
//    }


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


    @Nested
    class ProfileDetails{

        @Test
        void shouldReturnUsernameForTheGivenIdFromDatabase(){

            Mockito.when(authenticationRepository.getUserName(1)).thenReturn(details.getUsername());
            String userName = authenticationRepository.getUserName(1);
            assertThat(userName).isEqualTo("Geethika");
        }

        @Test
        void shouldReturnEmailForTheGivenIdFromDatabase(){
            Mockito.when(authenticationRepository.getEmail(1)).thenReturn(details.getEmail());
            String userName = authenticationRepository.getEmail(1);
            assertThat(userName).isEqualTo("geethika@gmail.com");
        }
    }
}
