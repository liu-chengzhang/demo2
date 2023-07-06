package com.example.demo.controller;

import com.example.demo.entity.Post;
import com.example.demo.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
public class PostController {
    @Autowired
    PostMapper postMapper;
    @RequestMapping("post/upload")
    public String uploadpost(Post post)throws IOException{
        post.setRelTime(LocalDateTime.now());
        MultipartFile photo =post.getCover();
        System.out.println(photo.getOriginalFilename());
        System.out.println(photo.getContentType());
        String path ="C:/upload/cover/";
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
        post.setCoverURL(path+photo.getOriginalFilename());
        int result=postMapper.insertPost(post);
        if (result > 0) {
            return "success";
        } else {
            return "fail";
        }
    }
    public void saveFile(MultipartFile photo,String path) throws IOException{
        File dir = new File(path);
        if(!dir.exists()){
            dir.mkdir();
        }
        File file =new File(path+photo.getOriginalFilename());
        photo.transferTo(file);
    }
    @RequestMapping("post/getall")
    public List<Post> getallpost(){
        System.out.println("queryallpost");
        return postMapper.getallpost();
    }
    @RequestMapping("post/getpost")
    public Post getpost(@RequestParam("PID") int PID){
        return postMapper.getpostbyId(PID);
    }
    @RequestMapping("post/deletebyID")
    public int deletepost(@RequestParam("PID")int PID){
        return postMapper.deletepostbyId(PID);
    }
    @RequestMapping("post/getcover")
    public ResponseEntity<byte[]> getcoverbyId(@RequestParam("PID")int PID)throws IOException{
        String filePath = postMapper.getCoverURLbyId(PID);
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
        byte[] bytesByStream = multipartFile.getBytes();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(multipartFile.getContentType()));
        return new ResponseEntity<>(bytesByStream, headers, HttpStatus.OK);
    }
    @RequestMapping("/post/getpostbyAccountId")
    public List<Post> getpostbyAccountId(@RequestParam("AccountId")String AccountId){
        return postMapper.getpostbyAccountId(AccountId);
    }
    @RequestMapping("/post/querytitle")
    public List<Post> querypostbytitle(@RequestParam("querystring")String querystring){
        String[] chars = querystring.split("");
        String likePattern = "%" + String.join("%", chars) + "%";
        System.out.println(likePattern);
        return postMapper.QueryPost(likePattern);
    }
}
