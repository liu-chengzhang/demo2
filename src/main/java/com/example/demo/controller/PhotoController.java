package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class PhotoController {
    @Autowired
    UserMapper mapper;
    @RequestMapping("user/avator")
    public ResponseEntity<byte[]> getImg(Integer AccountId) throws  IOException{//通过自己写的http工具类获取到图片输入流
       MultipartFile file=this.getMulipartFiles2(AccountId);
         byte[] bytesByStream = file.getBytes();
         final HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.parseMediaType(file.getContentType()));
         return new ResponseEntity<>(bytesByStream,headers, HttpStatus.OK);}
    public MultipartFile getMulipartFiles2(Integer AccountId) throws IOException {
        String filePath = mapper.Userinfo(AccountId).getAvatar();
        System.out.println(filePath);
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile =  new MockMultipartFile(file.getName(), file.getName(),
                "image/png", fileInputStream);
        long size = multipartFile.getSize();
        return multipartFile;
    }
}

