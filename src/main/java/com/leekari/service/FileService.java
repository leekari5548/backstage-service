package com.leekari.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author litao
 * @date 2020/7/29 10:37
 * @description
 */
public interface FileService {
    String createFileRecord(MultipartFile file, Integer sourceCode) throws Exception;

    void downloadFile(String fileId, HttpServletResponse response) throws Exception;
}
