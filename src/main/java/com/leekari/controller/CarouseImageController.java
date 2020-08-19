package com.leekari.controller;

import com.alibaba.fastjson.JSONObject;
import com.leekari.service.CarouselImageService;
import com.leekari.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litao
 * @date 2020/8/19 21:01
 * @description
 */
@RestController
@RequestMapping("/carousel/")
public class CarouseImageController {
    @Autowired
    private CarouselImageService carouselImageService;

    @RequestMapping("list")
    public Result<JSONObject> carouselList(Integer type){
        JSONObject jo = carouselImageService.getAllCarouseImages(type);
        return new Result.Builder<JSONObject>().data(jo).builder();
    }
}
