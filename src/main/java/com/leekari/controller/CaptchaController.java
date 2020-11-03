package com.leekari.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.alibaba.fastjson.JSONObject;
import com.leekari.config.NoAuthVerify;
import com.leekari.define.BasicConst;
import com.leekari.util.CommonUtils;
import com.leekari.util.RedisUtils;
import com.leekari.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author litao
 * @date 2020/7/31 13:45
 * @description
 */
@RestController
public class CaptchaController {

    @Autowired
    private RedisUtils redisUtils;

    @NoAuthVerify
    @RequestMapping("/captcha")
    public Result<JSONObject> test1(HttpServletResponse response, String param){
        LineCaptcha circleCaptcha = CaptchaUtil.createLineCaptcha(200, 100, 4,5);
        String code = circleCaptcha.getCode();
        redisUtils.set(param + BasicConst.PIC_VERIFY_SUFFIX, code, 6000);
        String image = circleCaptcha.getImageBase64();
        JSONObject dataJson = new JSONObject();
        dataJson.put("img", "data:image/png;base64," + image);
        dataJson.put("verifyId", CommonUtils.uuid());
        dataJson.put("verifyCode", circleCaptcha.getCode());
        return new Result.Builder<JSONObject>().data(dataJson).builder();
    }
}
