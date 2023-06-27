package com.example.demo.service;


import com.example.demo.entity.accounttb;
import org.springframework.stereotype.Service;

import java.util.List;
public interface UserService {
    boolean login(Integer AccountId,String Password);
    boolean register(Integer AccountId,String Password);
    public List<accounttb> finduser();
    accounttb Userinfo(Integer id);
}
