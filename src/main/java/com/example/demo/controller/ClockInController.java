package com.example.demo.controller;

import com.example.demo.service.ClockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ClockInController {
@Autowired
    ClockInService service;
    @RequestMapping("user/clockin")
    public boolean clockin(String AccountId,Integer ClockInhour,Integer ClockInmin,String Content){
        Integer ClockInTime =ClockInhour*60+ClockInmin;
        System.out.println(ClockInhour.toString()+ClockInmin.toString()+ClockInTime.toString());
        service.clockin(AccountId,ClockInTime,Content);
        return true;
    }

}
