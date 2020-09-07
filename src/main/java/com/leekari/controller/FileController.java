package com.leekari.controller;

import com.alibaba.fastjson.JSONObject;
import com.leekari.config.NoAuthVerify;
import com.leekari.define.ModuleEnum;
import com.leekari.define.SourceEnum;
import com.leekari.service.FileService;
import com.leekari.util.Result;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;

/**
 * @author litao
 * @date 2020/7/29 10:37
 * @description
 */
@RestController
@RequestMapping("/file/")
public class FileController {
    @Autowired
    private FileService fileService;

    private final static Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Result<JSONObject> upload(MultipartFile file, Integer sourceCode){
        try {
            String id = fileService.createFileRecord(file, sourceCode);
            JSONObject dataJson = new JSONObject();
            dataJson.put("fileId", "/file/download/"+id);
            return new Result.Builder<JSONObject>().code(0).type(ModuleEnum.FILE_MODULE.name).message("success").data(dataJson).builder();
        }catch (Exception e){
            e.printStackTrace();
            return new Result.Builder<JSONObject>().code(-1).type(ModuleEnum.FILE_MODULE.name).message("error").builder();
        }
    }

    @RequestMapping(value = "upload/avatar")
    public Result<JSONObject> uploadAvatar(HttpServletRequest request){
        try {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
//            Iterator<String> it = multipartHttpServletRequest.getFileNames();
//            if (it.hasNext()) {
//                System.err.println(it.next());
//            }
            MultipartFile file = multipartHttpServletRequest.getFile("file");
//            System.err.println(file.getOriginalFilename());
            String fileId = fileService.createFileRecord(file, SourceEnum.AVATAR_SOURCE.code);
            JSONObject dataJson = new JSONObject();
            dataJson.put("avatarUrl", "/file/download/"+fileId);
            return new Result.Builder<JSONObject>().code(0).type(ModuleEnum.FILE_MODULE.name).message("success").data(dataJson).builder();
        }catch (Exception e){
            e.printStackTrace();
            return new Result.Builder<JSONObject>().code(-1).type(ModuleEnum.FILE_MODULE.name).message("error").builder();
        }
    }

    public static boolean base64ToImageFile(String base64, String path) throws IOException {// 对字节数组字符串进行Base64解码并生成图片
        // 生成jpeg图片
        try {
            OutputStream out = new FileOutputStream(path);
            return base64ToImageOutput(base64, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 处理Base64解码并输出流
     *
     * @param base64
     * @param out
     * @return
     */
    public static boolean base64ToImageOutput(String base64, OutputStream out) throws IOException {
        if (base64 == null) { // 图像数据为空
            return false;
        }
        try {
            // Base64解码
            byte[] bytes = Base64.decodeBase64(base64);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }
            // 生成jpeg图片
            out.write(bytes);
            out.flush();
            return true;
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @NoAuthVerify
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
