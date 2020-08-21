package com.leekari.dao;

import com.leekari.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author litao
 * @date 2020/7/31 11:28
 * @description
 */
@Mapper
public interface UserDao {

    int insertUser(User user);

    User getUserInfo(@Param("id") String id, @Param("phone") String phone, @Param("email") String email, @Param("password") String password);
}
