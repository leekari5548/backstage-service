package com.leekari.dao;

import com.leekari.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author litao
 * @date 2020/8/19 14:20
 * @description
 */
@Mapper
public interface MenuDao {

    List<Menu> getAllMenu();
}
