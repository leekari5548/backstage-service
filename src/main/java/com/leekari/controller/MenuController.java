package com.leekari.controller;

import com.alibaba.fastjson.JSONObject;
import com.leekari.service.MenuService;
import com.leekari.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litao
 * @date 2020/8/19 14:34
 * @description
 */
@RestController
@RequestMapping("/menu/")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("list")
    public Result<JSONObject> getMenu(){
        return new Result.Builder<JSONObject>().data(menuService.getAll()).builder();
    }
}
