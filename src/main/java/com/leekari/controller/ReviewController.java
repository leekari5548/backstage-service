package com.leekari.controller;

import com.alibaba.fastjson.JSONObject;
import com.leekari.service.ReviewService;
import com.leekari.util.Result;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litao
 * @date 2020/9/3 14:48
 * @description
 */
@RestController
@RequestMapping("/review/")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @RequestMapping("add")
    public Result<JSONObject> addReview(@RequestHeader String userId,@RequestHeader String token, String toUserId, String content, String targetId){
        return reviewService.addReview(userId, toUserId, content, targetId);
    }

    @RequestMapping("list")
    Result<JSONObject> listReview(String targetId){
        return reviewService.getReviews(targetId);
    }
}
