package com.leekari.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leekari.config.NoAuthVerify;
import com.leekari.entity.User;
import com.leekari.service.UserService;
import com.leekari.util.JwtUtils;
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
        if (dataJson == null) {
            return new Result.Builder<JSONObject>().code(-1).message("您使用的手机号/电子邮箱已经注册过，请直接登录").builder();
        }
        return new Result.Builder<JSONObject>().data(dataJson).builder();
    }


    @RequestMapping("info")
    public Result<JSONObject> userinfo(@RequestHeader String token,@RequestHeader String userId){
        Object o = JwtUtils.checkJWT(token);
        if (o == null) {
            return new Result.Builder<JSONObject>().code(-1).message("token校验失败").builder();
        }
        User user = userService.userInfo(userId);
        JSONObject dataJson = JSON.parseObject(JSON.toJSONString(user));
        return new Result.Builder<JSONObject>().data(dataJson).builder();
    }

    @RequestMapping("login")
    public Result<JSONObject> login(String loginId, String password){
        JSONObject jo = userService.login(loginId, password);
        if (jo == null) {
            return new Result.Builder<JSONObject>().code(-1).message("邮箱/手机号或者密码错误").builder();
        }
        return new Result.Builder<JSONObject>().data(jo).builder();
    }
}
