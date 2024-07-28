package com.mingerzeng.testproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    private TestJpa testJpa;

    public List<AppUser> getAllUsers(String sql) {
        return testJpa.findAllUsers(sql);
    }
}
