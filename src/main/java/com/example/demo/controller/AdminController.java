package com.example.demo.controller;

import com.example.demo.entity.accounttb;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class AdminController {
    @Autowired
    UserMapper mapper;
    @Autowired
    FileUploadService fservice;
    @PostMapping("/admin/login")
    public boolean adminLogin(@RequestParam("username") String AdminId,@RequestParam("password") String AdminPassword)throws IOException {
        return AdminId.equals("dmin") && AdminPassword.equals("111111");
    }
    @RequestMapping("/admin/all")
    public List<accounttb> displayalluser(){
        return mapper.finduser();
    }
    @RequestMapping("/admin/all/delete")
    public boolean deleteuser(Integer AccountId){
        return mapper.DeleteByID(AccountId);
    }
    @RequestMapping("/admin/all/alter")
    public boolean alteruser(@RequestParam("AccountId") Integer AccountId,@RequestParam(value = "Password",required = false) String Password,@RequestParam(value = "EMail",required = false)String EMail,@RequestParam(value = "Avatar",required = false)String Avatar,@RequestParam(value = "NickName",required = false)String NickName,@RequestParam(value = "photo",required = false)MultipartFile photo,HttpServletRequest request) throws IOException{
        if(Avatar!=null) fservice.uploadAvator(AccountId,photo,request);
        if(EMail!=null&&NickName!=null)mapper.updateinfo(AccountId,NickName,EMail);
        return true;
    }
}
