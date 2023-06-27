package com.example.demo.utils;

import java.io.IOException;
import java.text.MessageFormat;

public class WxUtil {
    private final static String APP_ID = "微信小程序开发者申请的 appid";
    private final static String APP_SECRET = "微信小程序开发者申请的 APP_SECRET";
    //
    private final static String WX_LOGIN_SERVER_URL = "https://api.weixin.qq.com/sns/jscode2session?appid={0}&secret={1}&js_code={2}&grant_type=authorization_code";
    public static String getWxServerUrl(String code) throws IOException {
        String url = MessageFormat.format(WX_LOGIN_SERVER_URL, new String[]{APP_ID, APP_SECRET, code});
        return url;
    }
}
