package com.leekari.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author litao
 * @date 2020/7/31 11:31
 * @description
 */
public interface UserService {
    JSONObject addUser(String avatar, String phone, String email, String password, String nickName);

    JSONObject userInfo(String userId);

    JSONObject login(String loginId, String password);
}
