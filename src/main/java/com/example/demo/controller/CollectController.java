package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.mapper.CollectMapper;
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
public class CollectController {
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    PostMapper postMapper;
    @RequestMapping("/collect")
    public int collect(@RequestParam("AccountId")String AccountId,@RequestParam("PID") int PID){
        postMapper.Collect(PID);
        return collectMapper.collect(AccountId,PID,LocalDateTime.now());
    }
    @RequestMapping("/collect/delete")
    public int deletecollect(@RequestParam("AccountId")String AccountId,@RequestParam("PID")int PID){
        postMapper.CancelCollect(PID);
        return collectMapper.deleteCollection(AccountId,PID);
    }
    @RequestMapping("/my/query/collect")
    public List<Post> querycollect(@RequestParam("AccountId")String AccountId){
        return collectMapper.querycollect(AccountId);
    }
}
