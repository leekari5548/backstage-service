package com.leekari.dao;

import com.leekari.wechat.entity.LogRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author litao
 * @date 2020/7/29 20:56
 * @description
 */
@Mapper
public interface LogRecordDao {

    int insertRecord(LogRecord logRecord);
}
