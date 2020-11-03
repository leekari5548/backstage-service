package com.leekari.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leekari.config.MinioConfiguration;
import com.leekari.dao.CarouselImageDao;
import com.leekari.dao.FileRecordDao;
import com.leekari.define.SourceEnum;
import com.leekari.entity.CarouselImage;
import com.leekari.entity.FileRecord;
import com.leekari.entity.User;
import com.leekari.service.CarouselImageService;
import com.leekari.service.FileService;
import com.leekari.service.UserService;
import com.leekari.util.CommonUtils;
import com.leekari.util.Result;
import com.leekari.util.TimeUtils;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    @Resource
    private FileRecordDao fileRecordDao;

    @Override
    public JSONObject getAllCarouseImagesManage(Integer type) {
        SourceEnum sourceEnum = SourceEnum.getSourceEnum(type);
        if (sourceEnum == null) {
            return null;
        }
        List<CarouselImage> list = carouselImageDao.getCarouselImage(type);
        JSONObject dataJson = new JSONObject();
        JSONArray ja = new JSONArray();
        for (CarouselImage c: list) {
            JSONObject jo = JSON.parseObject(JSON.toJSONString(c));
            FileRecord record = fileRecordDao.getFileRecordById(c.getImageId());
            jo.put("imageUrl", "/file/download/"+c.getImageId());
            if (record.getSize() == null) {
                jo.put("size", "0 KB");
            }else {
                long size = record.getSize();
                double kb = size / 1024D;
                DecimalFormat df=new DecimalFormat("0.00");
                jo.put("size", df.format(kb) + " KB");
            }
            jo.put("createTime", TimeUtils.parseDateTime(c.getCreateTime()));
            ja.add(jo);
        }
        dataJson.put("list", ja);
        return dataJson;
    }

    private String getBase64FromInputStream(InputStream in) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {
            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[100];
            int rc = 0;
            while ((rc = in.read(buff, 0, 100)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            data = swapStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new String(Objects.requireNonNull(Base64.encodeBase64(data)));
    }

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
    public Result<JSONObject> uploadCarousel(MultipartFile file, @RequestHeader String userId) throws Exception {
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
