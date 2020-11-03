package com.leekari.controller;

import com.alibaba.fastjson.JSONObject;
import com.leekari.service.CarouselImageService;
import com.leekari.util.JwtUtils;
import com.leekari.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping("list/manage")
    public Result<JSONObject> carouselManageList(Integer type){
        JSONObject jo = carouselImageService.getAllCarouseImagesManage(type);
        return new Result.Builder<JSONObject>().data(jo).builder();
    }

    @RequestMapping("delete")
    public Result<JSONObject> deleteCarousel(String ids){
        return carouselImageService.deleteCarousel(ids);
    }

    @RequestMapping("/upload")
    public Result<JSONObject> carouselUpload(@RequestHeader String token, @RequestHeader String userId, MultipartFile file) throws Exception {
        String role = JwtUtils.getUserRole(token);
        if (file == null || file.isEmpty()) {
            return new Result.Builder<JSONObject>().code(-1).message("文件不存在").builder();
        }
        if (StringUtils.isEmpty(file.getContentType()) || !file.getContentType().contains("image")){
            return new Result.Builder<JSONObject>().code(-1).message("文件类型错误，只能上传图片").builder();
        }
        if (file.getSize() > 2 * 1024 * 1024) {
            return new Result.Builder<JSONObject>().code(-1).message("图片大小不大于2MB").builder();
        }
        if (StringUtils.isEmpty(role)) {
            return new Result.Builder<JSONObject>().code(-1).message("token校验失败").builder();
        }
        if (!role.equals("509") && !role.equals("250")) {
            return new Result.Builder<JSONObject>().code(-1).message("权限不足").builder();
        }
        return carouselImageService.uploadCarousel(file, userId);
    }
}
