package com.lwr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lwr.dao.UserDao;

@RestController
public class TestController {
    @Autowired
    UserDao userDao;
    @RequestMapping("/jpa")
    public String test(@RequestParam String id) {
        return userDao.getUserById(id).toString();
    }

}
