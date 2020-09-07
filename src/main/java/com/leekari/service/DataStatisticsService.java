package com.leekari.service;

import com.alibaba.fastjson.JSONObject;
import com.leekari.util.Result;

/**
 * @author litao
 * @date 2020/9/7 10:24
 * @description
 */
public interface DataStatisticsService {

    Result<JSONObject> clickButton(Integer type, String id, Integer contentType);
}
