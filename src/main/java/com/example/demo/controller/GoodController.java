package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.mapper.CollectMapper;
import com.example.demo.mapper.GoodMapper;
import com.example.demo.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@CrossOrigin
public class GoodController {
    @Autowired
    GoodMapper goodMapper;
    @Autowired
    PostMapper postMapper;
    @RequestMapping("/good")
    public int collect(@RequestParam("AccountId")String AccountId, @RequestParam("PID") int PID){
        postMapper.Good(PID);
        return goodMapper.good(AccountId,PID, LocalDateTime.now());
    }
    @RequestMapping("/good/delete")
    public int deletecollect(@RequestParam("AccountId")String AccountId,@RequestParam("PID")int PID){
        postMapper.CancelGood(PID);
        return goodMapper.deletegood(AccountId,PID);
    }
    @RequestMapping("/my/query/good")
    public List<Post> querycollect(@RequestParam("AccountId")String AccountId){
        return goodMapper.querygood(AccountId);
    }
}
