package com.leekari.controller;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.alibaba.fastjson.JSONObject;
import com.leekari.util.HttpClientUtils;
import com.leekari.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author litao
 * @date 2020/9/7 15:42
 * @description
 */
@RestController
@RequestMapping("/wechat/")
public class WechatConnectionController {
    @Value("${wechat.token}")
    private String token;
    @Value("${wechat.appId}")
    private String appId;
    @Value("${wechat.secret}")
    private String secret;

    static Logger logger = LoggerFactory.getLogger(WechatConnectionController.class);

    @RequestMapping("register")
    public String wechatConnection(String signature, String timestamp, String nonce, String echostr){
        logger.info("signature ==> {}", signature);
        logger.info("timestamp ==> {}", timestamp);
        logger.info("nonce ==> {}", nonce);
        logger.info("echostr ==> {}", echostr);
        List<String> list = Arrays.asList(token, timestamp, nonce);
        list = list.stream().sorted().collect(Collectors.toList());
        StringBuilder resultStr = new StringBuilder();
        for (String str: list) {
            resultStr.append(str);
        }
        Digester digester = new Digester(DigestAlgorithm.SHA1);
        String digestHex = digester.digestHex(resultStr.toString());
        if (digestHex.equals(signature)) {
            return echostr;
        }
        return "";
    }


    @RequestMapping("code2Session")
    public Result<JSONObject> code2Session(String code){
//        GET https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code

        JSONObject json = HttpClientUtils.httpGet("https://api.weixin.qq.com/sns/jscode2session" +
                "?appid=" + appId + "&secret=" + secret + "&js_code=" + code + "&grant_type=authorization_code");
        return new Result.Builder<JSONObject>().data(json).builder();
    }
}
