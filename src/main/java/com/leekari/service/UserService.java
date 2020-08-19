package com.leekari.service;

/**
 * @author litao
 * @date 2020/7/31 11:31
 * @description
 */
public interface UserService {
    int addUser(String username, String phone, String verifyCode, String picVerify, String password, String loginName);
}
