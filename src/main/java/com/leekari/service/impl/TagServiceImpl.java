package com.leekari.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leekari.dao.TagDao;
import com.leekari.entity.Tag;
import com.leekari.service.TagService;
import com.leekari.util.CommonUtils;
import com.leekari.util.Result;
import com.leekari.util.TimeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author litao
 * @date 2020/8/28 14:31
 * @description
 */
@Service
public class TagServiceImpl implements TagService {
    @Resource
    private TagDao tagDao;

    @Override
    public Result<JSONObject> insertTag(String userId, String tagName) {
        List<Tag> list = tagDao.getTagsByUser(userId);
        long count = list.stream().filter(tag -> tag.getTagName().equals(tagName)).count();
        if (count > 0) {
            return new Result.Builder<JSONObject>().builder();
        }
        Tag tag = new Tag();
        tag.setAssociateUser(userId);
        tag.setDeleted(0);
        tag.setId(CommonUtils.uuid());
        tag.setLastOperationTime(new Date());
        tag.setTagName(tagName);
        tagDao.insertTag(tag);
        return new Result.Builder<JSONObject>().builder();
    }

    @Override
    public Result<JSONObject> getTags(String userId) {
        List<Tag> list = tagDao.getTagsByUser(userId);
        JSONArray ja = new JSONArray();
        for (Tag tag: list) {
            JSONObject jo = JSON.parseObject(JSON.toJSONString(tag));
            jo.put("lastOperateTime", TimeUtils.parseDateTime(tag.getLastOperationTime()));
            ja.add(jo);
        }
        JSONObject dataJson = new JSONObject();
        dataJson.put("list", list);
        return new Result.Builder<JSONObject>().data(dataJson).builder();
    }
}
