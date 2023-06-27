package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClockInController {
    @RequestMapping("user/clockin")
    public boolean clockin(Integer AccountId,Integer ClockInTime,String content){
        return true;
    }

}
