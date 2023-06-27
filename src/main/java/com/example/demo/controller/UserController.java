package com.example.demo.controller;
import com.example.demo.entity.accounttb;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private UserMapper mapper;
    @GetMapping("all")
    public List<accounttb> getAllUser(){
        List<accounttb> allUser=service.finduser();
        return  allUser;
    }
    @GetMapping("login")
    public boolean login(@RequestParam("AccountId") Integer AccountId,@RequestParam("Password") String Password){
        System.out.println(AccountId+Password);
        return service.login(AccountId,Password);
    }
    @GetMapping("register")
    public boolean register(@RequestParam("AccountId") Integer AccountId,@RequestParam("Password") String Password){
        System.out.println(AccountId+Password);
        return service.register(AccountId,Password);
    }
    @RequestMapping("query")
    public Integer getStudentByID(){
      return 100;
    };
    @GetMapping("query/{id}")
    public accounttb getUserByID(@PathVariable("id")Integer stuid){
        return service.Userinfo(stuid);
    }
}
