package com.leekari.service;

import com.alibaba.fastjson.JSONObject;
import com.leekari.util.Result;

/**
 * @author litao
 * @date 2020/8/25 19:44
 * @description
 */
public interface BlogService {

    Result<JSONObject> addNewBlog(String articleTitle, String htmlContent, String mdContent, String userId, String tags);

    Result<JSONObject> editBlog(String id, String articleTitle, String htmlContent, String mdContent, String userId, String tags);

    Result<JSONObject> blogList(String title, String startTime, String endTime,Integer pageIndex,Integer pageSize);

    Result<JSONObject> detail(String id);

    Result<JSONObject> listBlogType();

    Result<JSONObject> mostPopular();

    Result<JSONObject> deleteBlog(String id);
}
