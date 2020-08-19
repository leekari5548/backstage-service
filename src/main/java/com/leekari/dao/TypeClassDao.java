package com.leekari.dao;

import com.leekari.entity.TypeClass;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author litao
 * @date 2020/8/19 13:28
 * @description
 */
@Mapper
public interface TypeClassDao {

    List<TypeClass> getAllTypeClass();
}
