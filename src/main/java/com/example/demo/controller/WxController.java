package com.example.demo.controller;

import com.example.demo.entity.WxUserInfo;
import com.example.demo.service.IWxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx")
public class WxController {
    @Autowired
    private IWxService wxService;

    /***
     * 获取到微信用户的 OPENID
     */
    @GetMapping("/getLoginCertificate")
    public String getLoginCertificate(@RequestParam("code") String code) throws Exception {
        WxUserInfo wxInfo = this.wxService.getLoginCertificate(code);
        //用户不存在，或者用户的信息不全
        return wxInfo == null || wxInfo.getNickName() == null ? "0" : "1";
    }
        /***
         *
         * @param code
         * @param rawData
         * @param encryptedData
         * @param iv
         * @param signature
         * @return
         * @throws Exception
         */
        @GetMapping("/wxLogin")
        public WxUserInfo wxLogin(@RequestParam("code") String code, @RequestParam("rawData") String rawData,
                                  @RequestParam("encryptedData") String encryptedData, @RequestParam("iv") String iv,
                                  @RequestParam("signature") String signature) throws Exception {
            WxUserInfo wxInfo = this.wxService.getWxUserInfo(code, rawData, encryptedData, iv, signature);
            return wxInfo;
        }
    }
