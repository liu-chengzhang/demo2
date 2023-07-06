package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    int PID;
    String AccountId;
    String Title;
    String Content;
    String Brief;
    String Type;
    int CommentNum;
    int CollectNum;
    int GoodNum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime RelTime;
    MultipartFile Cover;
    String CoverURL;
    String NickName;

}
