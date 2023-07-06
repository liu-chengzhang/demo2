package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
@Service
public class PhotoService {
    @Autowired
    UserMapper mapper;

    public ResponseEntity<byte[]> getImg(String AccountId) throws IOException {//通过自己写的http工具类获取到图片输入流
        MultipartFile file = this.getMulipartFiles2(AccountId);
        byte[] bytesByStream = file.getBytes();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(file.getContentType()));
        return new ResponseEntity<>(bytesByStream, headers, HttpStatus.OK);
    }

    public MultipartFile getMulipartFiles2(String AccountId) throws IOException {
        String filePath = mapper.Userinfo(AccountId).getAvatar();
        System.out.println(filePath);
        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);
        String filename = file.getName();
        String extension = filename.substring(filename.lastIndexOf(".") + 1);
        String contentType;
        switch (extension.toLowerCase()) {
            case "jpg":
            case "jpeg":
                contentType = "image/jpeg";
                break;
            case "png":
                contentType = "image/png";
                break;
            case "gif":
                contentType = "image/gif";
                break;
            default:
                contentType = "application/octet-stream";
        }
        MultipartFile multipartFile = new MockMultipartFile(filename, filename, contentType, fileInputStream);
        return multipartFile;
    }
}


