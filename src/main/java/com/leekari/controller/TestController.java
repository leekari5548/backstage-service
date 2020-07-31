package com.leekari.controller;

import com.leekari.config.AuthVerify;
import com.leekari.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litao
 * @date 2020/7/30 17:12
 * @description
 */
@RestController
public class TestController {
    @Autowired
    private RedisUtils redisUtils;

    @AuthVerify
    @RequestMapping("/test")
    public String test(){
        redisUtils.set("1","3");
        return "s";
    }
}
