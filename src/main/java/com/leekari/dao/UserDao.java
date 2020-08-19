package com.leekari.dao;

import com.leekari.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author litao
 * @date 2020/7/31 11:28
 * @description
 */
@Mapper
public interface UserDao {

    int insertUser(User user);
}
