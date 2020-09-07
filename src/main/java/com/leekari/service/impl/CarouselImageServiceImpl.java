package com.leekari.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leekari.dao.CarouselImageDao;
import com.leekari.define.SourceEnum;
import com.leekari.entity.CarouselImage;
import com.leekari.entity.User;
import com.leekari.service.CarouselImageService;
import com.leekari.service.FileService;
import com.leekari.service.UserService;
import com.leekari.util.CommonUtils;
import com.leekari.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;

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


    @Override
    public Result<JSONObject> deleteCarousel(String id) {
        String[] ids = id.split(",");
        List<String> idList = new ArrayList<>();
        for (String item: ids) {
            if (!StringUtils.isEmpty(item)) {
                idList.add(item);
            }
        }
        carouselImageDao.deleteCarousels(idList);
        return new Result.Builder<JSONObject>().builder();
    }

    @Override
    public Result<JSONObject> uploadCarousel(MultipartFile file, String userId) throws Exception {
        String id = fileService.createFileRecord(file, SourceEnum.VUE_SOURCE.code);
        User user = userService.userInfo(userId);
        CarouselImage carousel = new CarouselImage();
        carousel.setCreateTime(new Date());
        carousel.setCreateUser(user.getNickname());
        carousel.setDeleted(0);
        carousel.setOrder(1);
        carousel.setId(CommonUtils.uuid());
        carousel.setImageId(id);
        carousel.setSource(SourceEnum.VUE_SOURCE.code);
        carouselImageDao.insertCarousel(carousel);
        return new Result.Builder<JSONObject>().builder();
    }
}
