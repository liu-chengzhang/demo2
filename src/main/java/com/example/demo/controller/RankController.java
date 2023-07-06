package com.example.demo.controller;

import com.example.demo.entity.Rank;
import com.example.demo.entity.accounttb;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class RankController {
    @Autowired
    UserService service;
    @RequestMapping("user/rank")
    public List<Rank>getRank(){
        return service.getrank();
    }
}
