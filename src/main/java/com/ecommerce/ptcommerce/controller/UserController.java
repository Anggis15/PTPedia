package com.ecommerce.ptcommerce.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ptcommerce.repository.UserInterface;
import com.ecommerce.ptcommerce.model.UserRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    public UserInterface userInterface;


    @GetMapping("/v1/user")
    public List<UserRegistration> getAllUser() {
        List<UserRegistration> allUser = userInterface.findAll();
        return allUser;
    }
    
    
    

}
