package com.example.demo.controller;
import com.example.demo.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@RestController
@CrossOrigin
public class FileUploadController {
    @Autowired
    private FileUploadService service;
    @PostMapping("/upload")
    public String uploadAvator(String AccountId, MultipartFile photo, HttpServletRequest request)throws IOException{
        return service.uploadAvator(AccountId,photo,request);
    }

}
