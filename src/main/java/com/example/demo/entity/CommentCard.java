package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentCard {
    int CMId;
    int PID;
    String AccountId;
    String CMContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime CMTime;
    String NickName;
}