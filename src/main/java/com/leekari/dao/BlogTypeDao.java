package com.leekari.dao;

import com.leekari.entity.BlogType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author litao
 * @date 2020/8/31 09:30
 * @description
 */
@Mapper
public interface BlogTypeDao {

    List<BlogType> getAllBlogType();

}
