package com.leekari.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.leekari.service.ExcelToolsService;
import com.leekari.util.ExcelUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author litao
 * @date 2020/11/3 11:21
 * @description
 */
@Service
public class ExcelToolsServiceImpl implements ExcelToolsService {

    @Override
    public JSONObject handlerExcel(MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        int fileType = 0;
        if (file.getOriginalFilename().endsWith(".xls")) {
            fileType = 1;
        }
        ExcelUtils.getExcelInputStream(is, fileType);
        return null;
    }
}
