package com.example.demo.service;


import com.example.demo.entity.Rank;
import com.example.demo.entity.accounttb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
public interface UserService {
    boolean login(String AccountId, String Password, HttpServletRequest request);
    boolean register(String AccountId,String Password,String EMail);
    public List<accounttb> findalluser();
     List<Rank> getrank();
    accounttb Userinfo(String id);
    boolean forgetpassword(String AccountId,String Password);
}
