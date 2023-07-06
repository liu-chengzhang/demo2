package com.example.demo.controller;

import com.example.demo.entity.ConcernCard;
import com.example.demo.entity.Post;
import com.example.demo.mapper.ConcernMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ConcernController {
    @Autowired
    ConcernMapper concernMapper;
    @RequestMapping("/concern")
    public int concern(@RequestParam("Account1Id") String Account1Id,@RequestParam("Account2Id") String Account2Id){
        return concernMapper.concern(Account1Id,Account2Id, LocalDateTime.now());
    }
    @RequestMapping("/concern/cancel")
    public int cancelConcern(@RequestParam("Account1Id") String Account1Id,@RequestParam("Account2Id") String Account2Id){
        return concernMapper.deleteconcern(Account1Id,Account2Id);
    }
    @RequestMapping("/my/concern/query")
    public List<ConcernCard> myConcern(@RequestParam("AccountId") String AccountId){
        return concernMapper.querymyconcern(AccountId);
    }
    @RequestMapping("/concern/query")
    public List<Post> ConcernPost(@RequestParam("AccountId") String AccountId){
        return concernMapper.queryconcernpost(AccountId);
    }
}
