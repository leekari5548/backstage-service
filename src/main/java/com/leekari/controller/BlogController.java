package com.leekari.controller;

import com.alibaba.fastjson.JSONObject;
import com.leekari.service.BlogService;
import com.leekari.util.HtmlUtils;
import com.leekari.util.JwtUtils;
import com.leekari.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litao
 * @date 2020/8/25 19:43
 * @description
 */
@RestController
@RequestMapping("/blog/")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @RequestMapping("type/list")
    Result<JSONObject> typeList(){
        return blogService.listBlogType();
    }

    @RequestMapping("list")
    Result<JSONObject> list(String title, String startTime, String endTime, Integer pageIndex, Integer pageSize){
        return blogService.blogList(title, startTime, endTime, pageIndex, pageSize);
    }

    @RequestMapping("most/popular")
    Result<JSONObject> mostPopular(){
        return blogService.mostPopular();
    }

    @RequestMapping("detail")
    public Result<JSONObject> detail(String id){
        return blogService.detail(id);
    }

    @RequestMapping("add")
    Result<JSONObject> addBlog(String tags, String articleTitle,String htmlContent, String mdContent, @RequestHeader String userId,@RequestHeader String token){
        System.err.println(tags);
        Object o = JwtUtils.checkJWT(token);
        if(StringUtils.isEmpty(articleTitle)) {
            return new Result.Builder<JSONObject>().code(-1).message("文章标题不能为空").builder();
        }
        if(StringUtils.isEmpty(htmlContent) || StringUtils.isEmpty(mdContent)) {
            return new Result.Builder<JSONObject>().code(-1).message("文章正文不能为空").builder();
        }
        if (HtmlUtils.Html2Text(htmlContent).isEmpty()) {
            return new Result.Builder<JSONObject>().code(-1).message("文章正文不能为空").builder();
        }
        if (o == null) {
            return new Result.Builder<JSONObject>().code(-1).message("token校验失败").builder();
        }
        return blogService.addNewBlog(articleTitle, htmlContent, mdContent, userId, tags);
    }

    @RequestMapping("edit")
    Result<JSONObject> editBlog(String id, String tags, String articleTitle,String htmlContent, String mdContent, @RequestHeader String userId,@RequestHeader String token){
//        System.err.println(tags);
        Object o = JwtUtils.checkJWT(token);
        if(StringUtils.isEmpty(articleTitle)) {
            return new Result.Builder<JSONObject>().code(-1).message("文章标题不能为空").builder();
        }
        if(StringUtils.isEmpty(htmlContent) || StringUtils.isEmpty(mdContent)) {
            return new Result.Builder<JSONObject>().code(-1).message("文章正文不能为空").builder();
        }
        if (HtmlUtils.Html2Text(htmlContent).isEmpty()) {
            return new Result.Builder<JSONObject>().code(-1).message("文章正文不能为空").builder();
        }
        if (o == null) {
            return new Result.Builder<JSONObject>().code(-1).message("token校验失败").builder();
        }
        return blogService.editBlog(id, articleTitle, htmlContent, mdContent, userId, tags);
    }
}
