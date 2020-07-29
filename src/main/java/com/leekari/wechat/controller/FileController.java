package com.leekari.wechat.controller;

import com.leekari.wechat.define.ModuleEnum;
import com.leekari.wechat.service.FileService;
import com.leekari.wechat.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @author litao
 * @date 2020/7/29 10:37
 * @description
 */
@RestController
@RequestMapping("/file/")
public class FileController {
    @Value("${MinioClient.bucket}")
    private String bucket;
    @Autowired
    private FileService fileService;

    private final static Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<String> upload(MultipartFile file){
        try {
            fileService.createFileRecord(file);
            return new Result.Builder<String>().code(0).type(ModuleEnum.FILE_MODULE.name).message("success").builder();
        }catch (Exception e){
            e.printStackTrace();
            return new Result.Builder<String>().code(-1).type(ModuleEnum.FILE_MODULE.name).message("error").builder();
        }

    }

    @RequestMapping("download/{fileId}")
    public Result<String> download(@PathVariable String fileId, final HttpServletResponse response){
        try {
            fileService.downloadFile(fileId, response);
        }catch (Exception e){
            e.printStackTrace();
            return new Result.Builder<String>().code(-100).type(ModuleEnum.FILE_MODULE.name).message("file isn't exist").builder();
        }
        return new Result.Builder<String>().code(0).type(ModuleEnum.FILE_MODULE.name).message("success").builder();
    }

    public static byte[] getBytesByInputStream(InputStream is) {
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
}
