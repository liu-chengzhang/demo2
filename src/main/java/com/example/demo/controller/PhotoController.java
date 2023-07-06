package com.example.demo.controller;

import com.example.demo.mapper.UserMapper;
import com.example.demo.service.PhotoService;
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
    PhotoService service;
    @RequestMapping("user/avatar")
    public ResponseEntity<byte[]> getImg(String AccountId) throws  IOException{
       return service.getImg(AccountId);
    }
}

