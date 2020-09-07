package com.leekari.util;

import com.leekari.define.BasicConst;
import com.leekari.entity.User;

import java.util.Optional;

/**
 * @author litao
 * @date 2020/9/3 21:00
 * @description
 */
public class UserCacheUtils {

    public static User getUserInfo(String id){
        Optional<User> first = BasicConst.userCache.stream().filter(user -> user.getId().equals(id)).findFirst();
        return first.orElseGet(User::new);
    }
}
