package com.leekari.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leekari.dao.UserDao;
import com.leekari.define.BasicConst;
import com.leekari.define.RoleEnum;
import com.leekari.entity.User;
import com.leekari.service.UserService;
import com.leekari.util.CommonUtils;
import com.leekari.util.JwtUtils;
import com.leekari.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author litao
 * @date 2020/7/31 11:32
 * @description
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserDao userDao;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public JSONObject addUser(String avatar, String phone, String email, String password, String nickName) {
        int userCount = userDao.getUsers(phone, email);
        if (userCount > 0) {
            return null;
        }
        JSONObject dataJson = new JSONObject();
        int roleId = RoleEnum.NORMAL_USER.code;
        String userId = "u-" + CommonUtils.uuid();
        User user = new User();
        user.setCreateTime(new Date());
        user.setId(userId);
        user.setEmail(email);
        user.setLastOperationTime(new Date());
        user.setPassword(password);
        user.setPhone(phone);
        user.setPicture(avatar);
        user.setRole(roleId);
        user.setNickname(nickName);
        userDao.insertUser(user);
        String token = JwtUtils.generateJsonWebToken(user);
        dataJson.put("token", token);
        dataJson.put("userId", userId);
        dataJson.put("role", roleId);
        this.initUserCache();
        return dataJson;
    }

    @Override
    public User userInfo(String userId) {
        User user = userDao.getUserInfo(userId, null ,null, null);
        return user;
    }


    @Override
    public JSONObject login(String loginId, String password) {
        User user = userDao.getUserInfo(null, loginId, null, password);
        if (user == null) {
            user = userDao.getUserInfo(null, null, loginId, password);
        }
        if (user == null) {
            return null;
        }
        JSONObject dataJson = new JSONObject();
        String token = JwtUtils.generateJsonWebToken(user);
        dataJson.put("token", token);
        dataJson.put("userId", user.getId());
        dataJson.put("role", user.getRole());
        JSONObject userinfo = JSON.parseObject(JSON.toJSONString(user));
        dataJson.put("userinfo", userinfo);
        return dataJson;
    }

    @Override
    public void initUserCache() {
        logger.info("--------->>>start init user cache<<<---------------");
        BasicConst.userCache.clear();
        BasicConst.userCache = userDao.getAllUsers();
        logger.info("--------->>>finished init user cache<<<---------------");
    }
}
