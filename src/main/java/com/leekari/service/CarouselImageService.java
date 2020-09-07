package com.leekari.service;

import com.alibaba.fastjson.JSONObject;
import com.leekari.util.Result;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author litao
 * @date 2020/8/19 20:54
 * @description
 */
public interface CarouselImageService {

    JSONObject getAllCarouseImages(Integer type);

    Result<JSONObject> deleteCarousel(String id);

    Result<JSONObject> uploadCarousel(MultipartFile file, String userId) throws Exception;
 }
