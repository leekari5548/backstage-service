package com.leekari.service;

import com.alibaba.fastjson.JSONObject;
import com.leekari.util.Result;

/**
 * @author litao
 * @date 2020/9/3 14:42
 * @description
 */
public interface ReviewService {

    Result<JSONObject> addReview(String userId, String toUserId, String content, String targetId);

    Result<JSONObject> getReviews(String targetId);
}
