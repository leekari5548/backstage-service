package com.leekari.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leekari.dao.CarouselImageDao;
import com.leekari.define.SourceEnum;
import com.leekari.entity.CarouselImage;
import com.leekari.service.CarouselImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author litao
 * @date 2020/8/19 20:54
 * @description
 */
@Service
public class CarouselImageServiceImpl implements CarouselImageService {
    @Resource
    private CarouselImageDao carouselImageDao;

    @Override
    public JSONObject getAllCarouseImages(Integer type) {
        SourceEnum sourceEnum = SourceEnum.getSourceEnum(type);
        if (sourceEnum == null) {
            return null;
        }
        List<CarouselImage> list = carouselImageDao.getCarouselImage(type);
        JSONObject dataJson = new JSONObject();
        JSONArray ja = new JSONArray();
        for (CarouselImage c: list) {
            JSONObject jo = JSON.parseObject(JSON.toJSONString(c));
            jo.remove("imageId");
            jo.put("imageUrl", "/file/download/"+c.getImageId());
            ja.add(jo);
        }
        dataJson.put("list", ja);
        return dataJson;
    }
}
