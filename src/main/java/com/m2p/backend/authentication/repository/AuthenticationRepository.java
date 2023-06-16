package com.m2p.backend.authentication.repository;

import com.m2p.backend.authentication.model.UserDetails;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.hibernate.annotations.NamedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationRepository extends JpaRepository<UserDetails, Long> {
    @Query("SELECT password FROM UserDetails WHERE username=:User OR email=:User")
    public String validUserAsPassword(String User);

    @Query("SELECT COUNT(*) FROM UserDetails WHERE username=:name")
    public int checkUserName(String name);
    @Query("SELECT COUNT(*) FROM UserDetails WHERE email=:email")
    public int checkEmail(String email);

}

