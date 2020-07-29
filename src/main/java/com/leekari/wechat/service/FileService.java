package com.leekari.wechat.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author litao
 * @date 2020/7/29 10:37
 * @description
 */
public interface FileService {
    void createFileRecord(MultipartFile file) throws Exception;
}
