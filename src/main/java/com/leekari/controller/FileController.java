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
    public Result<JSONObject> upload(MultipartFile file, Integer sourceCode) throws Exception {
        String id = fileService.createFileRecord(file, sourceCode);
        JSONObject dataJson = new JSONObject();
        dataJson.put("fileId", "/file/download/"+id);
        return new Result.Builder<JSONObject>().code(0).type(ModuleEnum.FILE_MODULE.name).message("success").data(dataJson).builder();
    }

    @RequestMapping(value = "upload/avatar")
    public Result<JSONObject> uploadAvatar(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartHttpServletRequest.getFile("file");
        String fileId = fileService.createFileRecord(file, SourceEnum.AVATAR_SOURCE.code);
        JSONObject dataJson = new JSONObject();
        dataJson.put("avatarUrl", "/file/download/"+fileId);
        return new Result.Builder<JSONObject>().code(0).type(ModuleEnum.FILE_MODULE.name).message("success").data(dataJson).builder();

    }


    @NoAuthVerify
    @RequestMapping("download/{fileId}")
    public Result<String> download(@PathVariable String fileId, final HttpServletResponse response) throws Exception {
        fileService.downloadFile(fileId, response);
        return new Result.Builder<String>().code(0).type(ModuleEnum.FILE_MODULE.name).message("success").builder();
    }
}
