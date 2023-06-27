package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("gk_wx_user")
public class WxUserInfo {
    //OPEN_id
    @TableId(type = IdType.ASSIGN_ID, value = "open_id")
    private String openId;
    //会话密钥
    @TableField(value = "session_key")
    private String sessionKey;
    //头像路径
    @TableField("avatar_url")
    private String avatarUrl;
    //城市
    private String city;
    //国家
    private String country;
    //性别
    private String gender;
    //语言
    private String language;
    //昵称

    private String nickName;


    private String realName;
    //省份
    private String province;
    //学生ID
    @TableField("stu_id")
    private Integer stuId;
}
