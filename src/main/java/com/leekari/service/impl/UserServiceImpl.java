package com.leekari.service.impl;

import com.leekari.dao.UserDao;
import com.leekari.define.BasicConst;
import com.leekari.entity.User;
import com.leekari.service.UserService;
import com.leekari.util.RedisUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author litao
 * @date 2020/7/31 11:32
 * @description
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public int addUser(String username, String phone, String verifyCode, String picVerify, String password, String loginName) {
        String code = redisUtils.get(username + BasicConst.PIC_VERIFY_SUFFIX);
        User user = new User();
        user.setCreateTime(new Date());
        return 0;
    }
}
