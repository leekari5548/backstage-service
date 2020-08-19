package com.leekari.task;

import com.leekari.config.MinioConfiguration;
import com.leekari.dao.FileRecordDao;
import com.leekari.dao.LogRecordDao;
import com.leekari.define.ModuleEnum;
import com.leekari.entity.FileRecord;
import com.leekari.entity.LogRecord;
import com.leekari.util.Result;
import io.minio.MinioClient;
import io.minio.RemoveObjectsArgs;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author litao
 * @date 2020/7/29 20:45
 * @description
 */
@EnableScheduling
@RestController
public class TaskScheduled {
    @Autowired
    private LogRecordDao logRecordDao;
    @Autowired
    private FileRecordDao fileRecordDao;
    @Value("${MinioClient.bucket}")
    private String bucket;

    private static final Logger logger = LoggerFactory.getLogger(TaskScheduled.class);

    @Scheduled(cron = "0 0 0 * * ?")
    public void executeTask1(){
        logger.info("start execute task ....");
        LogRecord logRecord = new LogRecord();
        logRecord.setOperateTime(new Date());
        logRecord.setType(ModuleEnum.FILE_MODULE.code);
        logRecord.setOperator("admin");
        task1(logRecord);
        logRecordDao.insertRecord(logRecord);
    }

    @RequestMapping("execute/task1")
    public Result<String> executeTask(){
        logger.info("start execute task ....");
        LogRecord logRecord = new LogRecord();
        logRecord.setOperateTime(new Date());
        logRecord.setType(ModuleEnum.FILE_MODULE.code);
        logRecord.setOperator("admin");
        task1(logRecord);
        logRecordDao.insertRecord(logRecord);
        return new Result.Builder<String>().code(-1).type(ModuleEnum.FILE_MODULE.name).message("error").builder();
    }

    private void task1(LogRecord logRecord){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        Date date = calendar.getTime();
        List<FileRecord> fileRecords = fileRecordDao.getFileRecords(date);
        if (fileRecords != null && fileRecords.size() != 0) {
            List<DeleteObject> objects = new LinkedList<>();
            for (FileRecord fileRecord: fileRecords) {
                objects.add(new DeleteObject(fileRecord.getStoreFilename()));
                fileRecord.setDelete(1);
            }
            MinioClient minioClient = MinioConfiguration.minioClient();
            Iterable<io.minio.Result<DeleteError>> results = minioClient.removeObjects(RemoveObjectsArgs
                    .builder()
                    .bucket(bucket)
                    .objects(objects)
                    .build());
            try {
                for (io.minio.Result<DeleteError> result : results) {
                    DeleteError error = result.get();
                    logRecordDao.insertRecord(new LogRecord.Builder()
                            .operator("system")
                            .operateTime(new Date())
                            .type(ModuleEnum.FILE_MODULE.code)
                            .operation("Error in deleting object " + error.objectName() + "; " + error.message())
                            .builder());
                }
            }catch (Exception e) {
                logRecordDao.insertRecord(new LogRecord.Builder()
                        .operator("system")
                        .operateTime(new Date())
                        .type(ModuleEnum.FILE_MODULE.code)
                        .operation("Error in deleting object and error, error message [" + e.getMessage() + "]")
                        .builder());
                e.printStackTrace();
            }
            logRecord.setOperation("定时删除7天外的文件,删除"+fileRecords.size()+"个文件成功");
            fileRecordDao.updateRecordBatch(fileRecords);
            logger.info("remove files success...");
        }else {
            logRecord.setOperation("定时删除7天外的文件，不存在7天前的文件");
            logger.info("no file remove");
        }
    }
}
