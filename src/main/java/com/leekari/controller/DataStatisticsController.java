package com.leekari.controller;

import com.alibaba.fastjson.JSONObject;
import com.leekari.service.DataStatisticsService;
import com.leekari.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litao
 * @date 2020/9/7 10:23
 * @description
 */
@RestController
@RequestMapping("/statistics")
public class DataStatisticsController {
    @Autowired
    private DataStatisticsService dataStatisticsService;

    @RequestMapping("/click")
    public Result<JSONObject> click(Integer type, String id, Integer contentType){
        return dataStatisticsService.clickButton(type, id, contentType);
    }
}
