package com.leekari.wechat.task;

import com.leekari.wechat.dao.LogRecordDao;
import com.leekari.wechat.define.ModuleEnum;
import com.leekari.wechat.entity.LogRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author litao
 * @date 2020/7/29 20:45
 * @description
 */
@Component
@EnableScheduling
public class TaskScheduled {
    @Autowired
    private LogRecordDao logRecordDao;

    private static final Logger logger = LoggerFactory.getLogger(TaskScheduled.class);

    @Scheduled(cron = "0 0 0 * * ?")
    public void executeTask1(){
        logger.info("start execute task ....");
        LogRecord logRecord = new LogRecord();
        logRecord.setOperateTime(new Date());
        logRecord.setOperation("定时删除7天外的文件");
        logRecord.setType(ModuleEnum.FILE_MODULE.code);
        logRecord.setOperator("admin");
        logRecordDao.insertRecord(logRecord);
    }
}
