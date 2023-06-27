package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.WxUserInfo;
import com.example.demo.mapper.WxUserMapper;
import com.example.demo.utils.HttpClientUtils;
import com.example.demo.utils.WxUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;
import java.util.Base64;

@Service
public class WxService implements IWxService {
    public WxUserMapper wxUserMapper;
    @Override
    public WxUserInfo getLoginCertificate(String code) throws Exception {
        //请求地址
        String requestUrl = WxUtil.getWxServerUrl(code);
        // 发送请求
        String response = HttpClientUtils.getRequest(requestUrl);
        //格式化JSON数据
        WxUserInfo wxUserInfo = JSONObject.parseObject(response, WxUserInfo.class);
        //检查数据库中是否存在 OPENID
        WxUserInfo wxUserInfo_ = this.wxUserMapper.selectById(wxUserInfo.getOpenId());
        if (wxUserInfo_ == null) {
            //数据库中没有用户的 OPENID，添加到数据库中
            this.wxUserMapper.insert(wxUserInfo);
        } else {
            if (!wxUserInfo.getSessionKey().equals(wxUserInfo_.getSessionKey())) {
                //如果数据库保存的session_key和最新的session_key 不相同，则更新
                wxUserInfo_.setSessionKey(wxUserInfo.getSessionKey());
                this.wxUserMapper.updateById(wxUserInfo_);
            }
        }
        return wxUserInfo_;
    }
    public String decrypt(String session_key, String iv, String encryptData) {

        String decryptString = "";
        //解码经过 base64 编码的字符串
        byte[] sessionKeyByte = Base64.getDecoder().decode(session_key);
        byte[] ivByte = Base64.getDecoder().decode(iv);
        byte[] encryptDataByte = Base64.getDecoder().decode(encryptData);

        try {
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            //得到密钥
            Key key = new SecretKeySpec(sessionKeyByte, "AES");
            //AES 加密算法
            AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance("AES");
            algorithmParameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, key, algorithmParameters);
            byte[] bytes = cipher.doFinal(encryptDataByte);
            decryptString = new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptString;
    }
    @Override
    public WxUserInfo getWxUserInfo(@NotNull String code, @NotNull String rawData, @NotNull String encryptedData, @NotNull String iv, @NotNull String signature) throws Exception {
        //会话密钥
        WxUserInfo wxUserInfo = this.getLoginCertificate(code);
        String signature2 = DigestUtils.sha1Hex(rawData + wxUserInfo.getSessionKey());
        if (!signature.equals(signature2)) {
            throw new Exception("数字签名验证失败");
        }
        //数字签名验证成功,解密
        String infos = this.decrypt(wxUserInfo.getSessionKey(), iv, encryptedData);
        //反序列化 JSON 数据
        WxUserInfo wxUserInfo_ = JSONObject.parseObject(infos, WxUserInfo.class);
        wxUserInfo_.setSessionKey(wxUserInfo.getSessionKey());
        wxUserInfo_.setOpenId(wxUserInfo.getOpenId());
        //更新数据库
        this.wxUserMapper.updateById(wxUserInfo_);
        return wxUserInfo_;
    }
}