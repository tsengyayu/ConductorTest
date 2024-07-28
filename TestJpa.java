package com.mingerzeng.testproject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestJpa {
    @PersistenceContext
    private EntityManager entityManager;

    public AppUser findUserById(Long id) {
        String sql = "SELECT * FROM users WHERE id = :id";
        Query query = entityManager.createNativeQuery(sql, AppUser.class);
        query.setParameter("id", id);
        return (AppUser) query.getSingleResult();
    }

    public List<AppUser> findAllUsers(String sql) {
//        String sql = "SELECT * FROM users";
        Query query = entityManager.createNativeQuery(sql, AppUser.class);
        return query.getResultList();
    }

}
