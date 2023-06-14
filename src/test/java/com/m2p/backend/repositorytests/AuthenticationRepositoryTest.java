package com.m2p.backend.repositorytests;

import com.m2p.backend.authentication.repository.AuthenticationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthenticationRepositoryTest {
    @Mock
    private EntityManager entityManager;

    @Mock
    private Query query;

    @InjectMocks
    private AuthenticationRepository authenticationRepository;

    @Test
    public void toTestValidUserReturnsTrue() {
        String user = "admin";
        String password = "admin";
        int count = 1;
        String expectedQuery = "SELECT COUNT(*) FROM UserDetails WHERE username=:user AND password=:password";

        when(entityManager.createQuery(expectedQuery)).thenReturn(query);
        when(query.setParameter("user", user)).thenReturn(query);
        when(query.setParameter("password", password)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(count);

        boolean result = authenticationRepository.validateUserName(user, password);

        assertTrue(result);
        verify(entityManager).createQuery(expectedQuery);
        verify(query).setParameter("user", user);
        verify(query).setParameter("password", password);
        verify(query).getSingleResult();
    }

    @Test
    public void toTestValidEmailReturnsTrue() {
        String email = "admin@gmail.com";
        String password = "admin";
        int count = 1;
        String expectedQuery = "SELECT COUNT(*) FROM UserDetails WHERE email=:user AND password=:password";

        when(entityManager.createQuery(expectedQuery)).thenReturn(query);
        when(query.setParameter("user", email)).thenReturn(query);
        when(query.setParameter("password", password)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(count);

        boolean result = authenticationRepository.validateEmail(email, password);

        assertTrue(result);
        verify(entityManager).createQuery(expectedQuery);
        verify(query).setParameter("user", email);
        verify(query).setParameter("password", password);
        verify(query).getSingleResult();
    }

}
