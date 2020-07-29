package com.leekari.wechat.service.impl;

import com.leekari.wechat.config.MinioConfiguration;
import com.leekari.wechat.dao.FileRecordDao;
import com.leekari.wechat.dao.LogRecordDao;
import com.leekari.wechat.define.ModuleEnum;
import com.leekari.wechat.entity.FileRecord;
import com.leekari.wechat.entity.LogRecord;
import com.leekari.wechat.service.FileService;
import com.leekari.wechat.util.CommonUtils;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * @author litao
 * @date 2020/7/29 10:38
 * @description
 */
@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRecordDao fileRecordDao;
    @Autowired
    private LogRecordDao logRecordDao;

    @Value("${MinioClient.bucket}")
    private String bucket;
    private final static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public void createFileRecord(MultipartFile file) throws Exception{
        MinioClient minioClient = MinioConfiguration.minioClient();
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        ObjectWriteResponse response = minioClient.putObject(PutObjectArgs
                .builder()
                .bucket(bucket)
                .object(filename)
                .stream(file.getInputStream(), file.getSize(), -1)
                .build());
        logger.info("version = {}, tag = {}", response.versionId(), response.etag());
        FileRecord fileRecord = new FileRecord();
        fileRecord.setId(CommonUtils.uuid());
        fileRecord.setBucket(bucket);
        fileRecord.setFilename(file.getOriginalFilename());
        fileRecord.setStoreFilename(filename);
        fileRecord.setCreateTime(new Date());
        fileRecord.setFileType(file.getContentType());
        fileRecord.setCreateUser("admin");
        fileRecord.setMd5(CommonUtils.md5(new String(file.getBytes())));
        fileRecordDao.insertRecord(fileRecord);
        LogRecord logRecord = new LogRecord();
        logRecord.setOperateTime(new Date());
        logRecord.setOperation("上传图片");
        logRecord.setType(ModuleEnum.FILE_MODULE.code);
        logRecord.setOperator("admin");
        logRecordDao.insertRecord(logRecord);
    }

    private byte[] getBytesByInputStream(InputStream is) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = is.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            is.close();
            byte[] data = bos.toByteArray();
            bos.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void downloadFile(String fileId, HttpServletResponse response) throws Exception{
        FileRecord fileRecord = fileRecordDao.getFileRecordById(fileId);
        String realFilename = fileRecord.getStoreFilename();
        MinioClient minioClient = MinioConfiguration.minioClient();
        InputStream stream =
                minioClient.getObject(
                        GetObjectArgs.builder().bucket(bucket).object(realFilename).build());
        byte[] data = getBytesByInputStream(stream);
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        outputStream.write(data);
        outputStream.flush();
        outputStream.close();
        stream.close();
    }
}
