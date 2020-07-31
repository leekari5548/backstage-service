package com.leekari.dao;

import com.leekari.entity.FileRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author litao
 * @date 2020/7/29 10:38
 * @description
 */
@Mapper
public interface FileRecordDao {

    int insertRecord(FileRecord fileRecord);

    FileRecord getFileRecordById(String id);

    List<FileRecord> getFileRecords(Date date);

    int updateRecord(FileRecord fileRecord);

    int updateRecordBatch(@Param("list") List<FileRecord> fileRecords);
}
