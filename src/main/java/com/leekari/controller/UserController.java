package com.leekari.controller;

import com.alibaba.fastjson.JSONObject;
import com.leekari.config.NoAuthVerify;
import com.leekari.service.UserService;
import com.leekari.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     *
     * @param avatar
     * @param phone
     * @param password
     * @param nickname
     * @return
     */
    @NoAuthVerify
    @RequestMapping(value = "register",method = RequestMethod.POST)
    public Result<JSONObject> register(String avatar, String phone, String email, String password, String nickname){
        JSONObject dataJson = userService.addUser(avatar, phone, email, password, nickname);
        return new Result.Builder<JSONObject>().data(dataJson).builder();
    }


    @RequestMapping("info")
    public Result<JSONObject> userinfo(@RequestHeader String token,@RequestAttribute String userId){
        return new Result.Builder<JSONObject>().data(userService.userInfo(userId)).builder();
    }
}
