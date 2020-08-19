package com.leekari.controller;

import com.alibaba.fastjson.JSONObject;
import com.leekari.entity.TypeClass;
import com.leekari.service.TypeClassService;
import com.leekari.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author litao
 * @date 2020/8/19 13:33
 * @description
 */
@RestController
@RequestMapping("/type/")
public class TypeClassController {
    @Autowired
    private TypeClassService typeClassService;

    @RequestMapping("all")
    public Result<JSONObject> getAll(){
        JSONObject json = new JSONObject();
        json.put("list", typeClassService.getAll());
        return new Result.Builder<JSONObject>().data(json).builder();
    }
}
