package com.leekari.controller;

import com.alibaba.fastjson.JSONObject;
import com.leekari.service.ExcelToolsService;
import com.leekari.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author litao
 * @date 2020/11/3 11:18
 * @description
 */
@RestController
@RequestMapping("/tools/excel/")
public class ExcelToolsController {
    @Autowired
    private ExcelToolsService excelToolsService;

    public Result<JSONObject> uploadExcel(MultipartFile file){
        try {
            JSONObject jsonObject = excelToolsService.handlerExcel(file);
            return new Result.Builder<JSONObject>().data(jsonObject).builder();
        } catch (IOException e) {
            e.printStackTrace();
            return new Result<>();
        }
    }
}
