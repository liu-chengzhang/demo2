package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.mapper.AdminLogMapper;
import com.example.demo.mapper.ClockInMapper;
import com.example.demo.mapper.LogMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class AdminController {
    @Autowired
    UserMapper mapper;
    @Autowired
    LogMapper logMapper;
    @Autowired
    FileUploadService fservice;
    @Autowired
    AdminLogMapper adminLogMapper;
    @Autowired
    ClockInMapper clockInMapper;
    @RequestMapping("/admin/login")
    public boolean adminLogin(@RequestParam("username") String AdminId,@RequestParam("password") String AdminPassword)throws IOException {
        return AdminId.equals("admin") && AdminPassword.equals("111111");
    }
    @RequestMapping("/admin/queryuser")
    public accounttb adminQueryUser(@RequestParam("AccountId")String AccountId){
        return mapper.Userinfo(AccountId);
    }
    @RequestMapping("/admin/all")
    public List<accounttb> displayalluser(){
        System.out.println("Adminqueryall");
        return mapper.findalluser();
    }
    @RequestMapping("/admin/all/delete")
    public String deleteuser(String AccountId){
        adminLogMapper.Deleteteam_accountByID(AccountId);
        adminLogMapper.DeleteconcernByID(AccountId);
        adminLogMapper.DeletegoodtbByID(AccountId);
        adminLogMapper.DeletecollectiontbByID(AccountId);
        adminLogMapper.DeletecommentsdtbByID(AccountId);
        adminLogMapper.DeletewishtbByID(AccountId);
        adminLogMapper.DeleteclockintbByID(AccountId);
        adminLogMapper.DeletebelongtbByID(AccountId);
        adminLogMapper.DeleteaccounttbByID(AccountId);
        return "删除成功";
    }
    @RequestMapping("/admin/all/alter")
    public accounttb alteruser(@RequestParam("AccountId") String AccountId,@RequestParam(value = "Password",required = false) String Password,@RequestParam(value = "EMail",required = false)String EMail,@RequestParam(value = "NickName",required = false)String NickName,@RequestParam(value = "photo",required = false)MultipartFile photo,HttpServletRequest request) throws IOException{
        System.out.println(AccountId+Password+EMail+NickName);
        if(photo!=null) fservice.uploadAvator(AccountId,photo,request);
        if(EMail!=null)mapper.updateEmail(AccountId,EMail);
        if(Password!=null)mapper.updatePassword(AccountId,Password);
        if(NickName!=null)mapper.updateNickname(AccountId,NickName);
        return mapper.Userinfo(AccountId);
    }
    @RequestMapping("/admin/log")
    public List<Log> getalllog(){
        return logMapper.getalllog();
    }
    @RequestMapping("/admin/main/all")
    public List<AdminLog> getadminlog(){
        return adminLogMapper.getAdminlog();
    }
    @RequestMapping("/admin/main/count")
    public Count getAllCount(){
        Count count=clockInMapper.getAllClockInCount();
        count.usercount=mapper.getUserCount();
        float totaltime = count.clockintime / 60f;
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        totaltime = Float.parseFloat(decimalFormat.format(totaltime));
        count.clockintime = totaltime;
        return count;
    }
    @RequestMapping("/admin/all/insert")
    public accounttb insertuser(@RequestParam("AccountId") String AccountId,@RequestParam("Password") String Password,@RequestParam("EMail")String EMail,@RequestParam("NickName")String NickName)
    {
        if(mapper.insertuser(AccountId,Password,EMail,NickName))return mapper.Userinfo(AccountId);
        else return null;
    }
    @RequestMapping("/admin/query/log")
    public List<Log> getuserlog(@RequestParam("AccountId")String AccountId){
        return adminLogMapper.getuserlog(AccountId);
    }
}
