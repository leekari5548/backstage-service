package com.leekari.controller;

import com.alibaba.fastjson.JSONObject;
import com.leekari.config.NoAuthVerify;
import com.leekari.entity.User;
import com.leekari.service.UserService;
import com.leekari.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litao
 * @date 2020/7/31 09:37
 * @description
 */
@RestController
@RequestMapping(value = "/user/", method = RequestMethod.POST)
public class UserController {
    @Autowired
    private UserService userService;

    @NoAuthVerify
    @RequestMapping("register")
    public Result<JSONObject> register(String username, String phone, String verifyCode, String picVerify, String password, String loginName){
        return null;
    }
}
