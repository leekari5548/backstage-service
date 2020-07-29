package com.leekari.wechat.dao;

import com.leekari.wechat.entity.FileRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author litao
 * @date 2020/7/29 10:38
 * @description
 */
@Mapper
public interface FileRecordDao {

    int insertRecord(FileRecord fileRecord);
}
