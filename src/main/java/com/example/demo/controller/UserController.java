package com.example.demo.controller;
import com.example.demo.entity.ClockIn;
import com.example.demo.entity.accounttb;
import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.FileUploadService;
import com.example.demo.service.MailService;
import com.example.demo.service.PhotoService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private FileUploadService fservice;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private UserMapper mapper;
    @Autowired
    private MailService mailService;
    @RequestMapping("all")
    public List<accounttb> getAllUser(){
        List<accounttb> allUser=service.findalluser();
        return  allUser;
    }
    @RequestMapping ("login")
    public boolean login(@RequestParam("AccountId") String AccountId, @RequestParam("Password") String Password, HttpServletRequest request){
        System.out.println(AccountId);
        System.out.println(Password);
        return service.login(AccountId,Password,request);
    }

    @RequestMapping("register")
    public boolean register(@RequestParam("AccountId") String AccountId,@RequestParam("Password") String Password,@RequestParam("EMail")String EMail,@RequestParam("Code")String Code){
        System.out.println(AccountId);
        System.out.println(Password);
        System.out.println(EMail);
        System.out.println(Code);
        if(!mailService.verify(EMail,Code)) return false;
        return service.register(AccountId,Password,EMail);
    }
    @RequestMapping("info")
    public accounttb getuserinfo(@RequestParam("AccountId")String AccountId){
        System.out.println(AccountId);
        return service.Userinfo(AccountId);
    }
    @RequestMapping("info/test")
    public UserInfo getuserinfotest(@RequestParam("AccountId")String AccountId) throws IOException{
        UserInfo userInfo=new UserInfo();
        userInfo.setPhoto(photoService.getImg(AccountId));
        accounttb accounttb=service.Userinfo(AccountId);
        userInfo.setNickName(accounttb.getNickName());
        userInfo.setEMail(accounttb.getEmail());
        return userInfo;
    }
    @RequestMapping("update/password")
    public accounttb updateUserpassword(@RequestParam("AccountId") String AccountId,@RequestParam("Password") String Password) throws IOException {
        if(Password!=null)mapper.updatePassword(AccountId,Password);
        return service.Userinfo(AccountId);
    }
    @RequestMapping("update/email")
    public accounttb updateUserEMail(@RequestParam("AccountId") String AccountId,@RequestParam("EMail")String EMail) throws IOException {
        if(EMail!=null)mapper.updateEmail(AccountId,EMail);
        return service.Userinfo(AccountId);
    }
    @RequestMapping("update/nickname")
    public accounttb updateUserNickName(@RequestParam("AccountId") String AccountId,@RequestParam("NickName")String NickName) throws IOException {
        if(NickName!=null)mapper.updateNickname(AccountId,NickName);
        return service.Userinfo(AccountId);
    }
    @RequestMapping("update/avatar")
    public accounttb updateUserAvatar(@RequestParam("AccountId") String AccountId,@RequestParam("photo") MultipartFile photo,HttpServletRequest request) throws IOException {
        if(photo!=null) fservice.uploadAvator(AccountId,photo,request);
        return service.Userinfo(AccountId);
    }
//    @GetMapping("query/{id}")
//    public accounttb getUserByID(@PathVariable("id")String stuid){
//        return service.Userinfo(stuid);
//    }
    @RequestMapping("totaltime")
    public float gettotaltime(@RequestParam("AccountId")String AccountId) {
        float totaltime = (float)mapper.gettotaltime(AccountId) / 60f;
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        totaltime = Float.parseFloat(decimalFormat.format(totaltime));
        return totaltime;
    }
    @RequestMapping("clockinlog")
    public List<ClockIn> getclockinlog(@RequestParam("AccountId")String AccountId){
        return mapper.getclockinlog(AccountId);
    }
    @RequestMapping("forget")
    public boolean forgetpassword(@RequestParam("Password") String Password,@RequestParam("AccountId")String AccountId){
        System.out.println(Password);
        System.out.println(AccountId);
        return service.forgetpassword(AccountId,Password);
    }
    @RequestMapping("send")
    public String forgetpassword(@RequestParam("AccountId")String AccountId){
        String EMail=service.Userinfo(AccountId).getEmail();
        mailService.send(EMail);
        return "我们已经向您绑定的邮箱"+EMail+"发送了验证码";
    }
    @RequestMapping("verify")
    public boolean verify(@RequestParam("AccountId")String AccountId,@RequestParam("Code")String Code){
        String EMail=service.Userinfo(AccountId).getEmail();
        return mailService.verify(EMail,Code);
    }
}
