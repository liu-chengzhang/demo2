package com.example.demo.entity;

import lombok.*;
import org.springframework.http.ResponseEntity;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfo {
    String EMail;
    String NickName;
    ResponseEntity<byte[]> photo;
}
