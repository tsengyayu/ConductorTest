package com.mingerzeng.testproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private TestService testService;

    @GetMapping("/getUser")
    public List<AppUser> getAppUser(){
        String sql = "select * from app_user";
        List<AppUser> allUsers = testService.getAllUsers(sql);
        return allUsers;
    }
}
