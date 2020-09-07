package com.leekari.controller;

import com.alibaba.fastjson.JSONObject;
import com.leekari.service.TagService;
import com.leekari.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litao
 * @date 2020/8/28 14:39
 * @description
 */
@RestController
@RequestMapping("/tag/")
public class TagController {
    @Autowired
    private TagService tagService;

    @RequestMapping("list")
    public Result<JSONObject> getTags(String userId, String token){
        return tagService.getTags(userId);
    }


    @RequestMapping("add")
    public Result<JSONObject> addTag(String userId, String token, String tagName){
        return tagService.insertTag(userId, tagName);
    }
}
