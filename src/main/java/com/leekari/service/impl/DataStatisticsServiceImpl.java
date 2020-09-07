package com.leekari.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.leekari.define.ButtonTypeEnum;
import com.leekari.define.ContentTypeEnum;
import com.leekari.entity.DataStatistics;
import com.leekari.service.DataStatisticsService;
import com.leekari.util.RedisUtils;
import com.leekari.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author litao
 * @date 2020/9/7 10:26
 * @description
 */
@Service
public class DataStatisticsServiceImpl implements DataStatisticsService {
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Result<JSONObject> clickButton(Integer type, String id, Integer contentType) {
        if (ButtonTypeEnum.getButtonTypeEnum(type) == null) {
            return new Result.Builder<JSONObject>().code(-1).builder();
        }
        String data = redisUtils.get(id);
        if (StringUtils.isEmpty(data)) {
            DataStatistics statistics = new DataStatistics();
            statistics.setId(id);
            statistics.setType(contentType);
            if (type == 0) {
                statistics.setLikes(1);
                statistics.setStars(0);
                statistics.setViews(0);
            }else if (type == 1) {
                statistics.setLikes(0);
                statistics.setStars(1);
                statistics.setViews(0);
            }else {
                statistics.setLikes(0);
                statistics.setStars(0);
                statistics.setViews(1);
            }
            JSONObject json = JSON.parseObject(JSON.toJSONString(statistics));
            redisUtils.set(id, json.toJSONString());
        }else {
            DataStatistics statistics = JSON.toJavaObject(JSON.parseObject(data), DataStatistics.class);
            if (type == 0) {
                statistics.setLikes(statistics.getLikes() + 1);
            }else if (type == 1) {
                statistics.setStars(statistics.getStars() + 1);
            }else {
                statistics.setViews(statistics.getViews() + 1);
            }
            JSONObject json = JSON.parseObject(JSON.toJSONString(statistics));
            redisUtils.set(id, json.toJSONString());
        }
        return new Result<>();
    }
}
