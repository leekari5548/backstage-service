package com.leekari;

import com.alibaba.fastjson.JSONObject;
import com.leekari.service.BlogService;
import com.leekari.util.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BlogServiceTest {
    @Autowired
    private BlogService blogService;

//    @Test
//    void contextLoads() {
//    }

    @Test
    public void getAllBlog(){
        Result<JSONObject> jsonObjectResult = blogService.blogList(null, null, null, null, null);

        System.err.println(jsonObjectResult.getData());
    }

}