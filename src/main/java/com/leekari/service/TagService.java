package com.leekari.service;

import com.alibaba.fastjson.JSONObject;
import com.leekari.util.Result;

/**
 * @author litao
 * @date 2020/8/28 14:29
 * @description
 */
public interface TagService {
    Result<JSONObject> insertTag(String userId, String tagName);

    Result<JSONObject> getTags(String userId);
}
