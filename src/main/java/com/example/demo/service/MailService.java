package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

@Service
public class MailService {
    @Autowired
    public JavaMailSender javaMailSender;// 从容器中拿到 邮件发送对象
    @Resource
    private RedisTemplate redisTemplate;
    @Autowired
    private UserMapper mapper;

    @Value("${spring.mail.from}")
    private String emailFrom;// 从资源文件中进行读取发送方的邮件地址
    public String send(String EMail) {
        // new一个 简单邮件对象
        SimpleMailMessage mail = new SimpleMailMessage();
        // 设置邮件对象的各个属性，构造成一个较为完整的邮件对象
        mail.setFrom(emailFrom);// 发邮件的邮箱地址，从资源可直接提取
        mail.setTo(EMail);// 给谁发，通过参数进行传递
        Random random = new Random();
        String code=String.valueOf(random.nextInt(10));
        for(int i=0;i<5;i++){
            code += String.valueOf(random.nextInt(10));
        }
        mail.setText("您的验证码是：" + code);// 设置邮件的文本内容
        mail.setSubject("注册验证码");// 设置邮件的主题
        ValueOperations<String, Object>ops=redisTemplate.opsForValue();
        ops.set(EMail,code,5, TimeUnit.MINUTES);
        Object Value=ops.get(EMail);
        System.out.println(Value);


        javaMailSender.send(mail);// 进行发送

        return "邮件发送成功";
    }
    public boolean verify(String EMail,String Code){
        ValueOperations<String, Object>ops=redisTemplate.opsForValue();
        Object Value=ops.get(EMail);
        if(Value.toString().equals(Code)) return true;
        else return false;
    }
}
