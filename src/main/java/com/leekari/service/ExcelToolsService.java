package com.leekari.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author litao
 * @date 2020/11/3 11:20
 * @description
 */
public interface ExcelToolsService {
    JSONObject handlerExcel(MultipartFile file) throws IOException;
}
