package com.m2p.backend.authentication.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class AuthenticationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public int validateUserName(String user, String password) {

        String queryForUserNameCheck = "SELECT COUNT(*) FROM UserDetails WHERE username=:user AND password=:password";
        Query jpaQueryForUserNameCheck = entityManager.createQuery(queryForUserNameCheck);
        jpaQueryForUserNameCheck.setParameter("user", user);
        jpaQueryForUserNameCheck.setParameter("password", password);
        int countForUserNameCheck = ((Number) jpaQueryForUserNameCheck.getSingleResult()).intValue();

        return countForUserNameCheck;

    }

    public int validateEmail(String user,String password){

        String queryForEmailCheck = "SELECT COUNT(*) FROM UserDetails WHERE email=:user AND password=:password";
        Query jpaQueryForEmailCheck = entityManager.createQuery(queryForEmailCheck);
        jpaQueryForEmailCheck.setParameter("user", user);
        jpaQueryForEmailCheck.setParameter("password", password);
        int countForEmailCheck = ((Number) jpaQueryForEmailCheck.getSingleResult()).intValue();

        return countForEmailCheck;

    }
}
