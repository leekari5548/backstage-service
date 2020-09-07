package com.leekari.dao;

import com.leekari.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author litao
 * @date 2020/8/28 11:37
 * @description
 */
@Mapper
public interface TagDao {
    List<Tag> getTagsByUser(String userId);

    int insertTag(Tag tag);

    int batchInsertTag(@Param("list") List<Tag> list);

    List<Tag> getTagsByIds(@Param("list") List<String> list);

}
