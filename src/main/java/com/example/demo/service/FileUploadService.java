package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
@Service
public class FileUploadService implements IFileUploadService{
    @Autowired
    public UserMapper mapper;
    public String uploadAvator(String AccountId, MultipartFile photo, HttpServletRequest request)throws IOException {
        System.out.println(photo.getOriginalFilename());
        System.out.println(photo.getContentType());
        String path ="C:/upload/";
        System.out.println(path+photo.getOriginalFilename());
        String filename = photo.getOriginalFilename();
        String contentType;
        if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
            contentType = "image/jpeg";
        } else if (filename.endsWith(".png")) {
            contentType = "image/png";
        } else if (filename.endsWith(".gif")) {
            contentType = "image/gif";
        } else {
            throw new IllegalArgumentException("不支持上传该类型的文件");
        }
        saveFile(photo,path);
        mapper.updateAvator(path+photo.getOriginalFilename(),AccountId);
        return "上传成功";
    }
//    public String uploadCover(int PID, MultipartFile photo, HttpServletRequest request)throws IOException {
//        System.out.println(photo.getOriginalFilename());
//        System.out.println(photo.getContentType());
//        String path ="C:/upload/";
//        System.out.println(path+photo.getOriginalFilename());
//        String filename = photo.getOriginalFilename();
//        String contentType;
//        if (filename.endsWith(".jpg") || filename.endsWith(".jpeg")) {
//            contentType = "image/jpeg";
//        } else if (filename.endsWith(".png")) {
//            contentType = "image/png";
//        } else if (filename.endsWith(".gif")) {
//            contentType = "image/gif";
//        } else {
//            throw new IllegalArgumentException("不支持上传该类型的文件");
//        }
//        saveFile(photo,path);
//        mapper.updateAvator(path+photo.getOriginalFilename(),PID);
//        return "上传成功";
//    }
    public void saveFile(MultipartFile photo,String path) throws IOException{
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdir();
        }
//        File file =new File(path+photo.getOriginalFilename());
        File file =new File(path);
        photo.transferTo(file);
    }
}
