package com.example.demo.controller;

import com.example.demo.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    @Autowired
    public MailService mailService;
    @Value("${spring.mail.from}")
    private String emailFrom;// 从资源文件中进行读取发送方的邮件地址

    @RequestMapping("/email")
    public String send(String EMail) {
       mailService.send(EMail);
       return "发送成功";
    }
}