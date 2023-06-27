package com.example.demo.controller;

import com.example.demo.entity.accounttb;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RankController {
    @Autowired
    UserService service;
    @RequestMapping("user/rank")
    public List<accounttb>getAllUser(){
        List<accounttb> allUser=service.finduser();
        return  allUser;
    }
}
