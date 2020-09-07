package com.leekari.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leekari.dao.ReviewDao;
import com.leekari.dao.UserDao;
import com.leekari.define.BasicConst;
import com.leekari.entity.Review;
import com.leekari.entity.User;
import com.leekari.service.ReviewService;
import com.leekari.util.CommonUtils;
import com.leekari.util.Result;
import com.leekari.util.TimeUtils;
import com.leekari.util.UserCacheUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author litao
 * @date 2020/9/3 14:44
 * @description
 */
@Service
public class ReviewServiceImpl implements ReviewService {
    @Resource
    private ReviewDao reviewDao;
    @Resource
    private UserDao userDao;

    @Override
    public Result<JSONObject> addReview(String userId, String toUserId, String content, String targetId) {
        Review review = new Review();
        review.setId(CommonUtils.uuid());
        review.setContent(content);
        review.setCreateTime(new Date());
        review.setDeleted(0);
        review.setStatus(0);
        review.setFromUserId(userId);
        review.setToUserId(toUserId);
        review.setTargetId(targetId);
        reviewDao.insertReview(review);
        return new Result<>();
    }

    @Override
    public Result<JSONObject> getReviews(String targetId) {
        List<Review> list = reviewDao.getReviewByArticle(targetId);
        JSONObject dataJson = new JSONObject();
        JSONArray ja = new JSONArray();
        for (Review review: list) {
            JSONObject json = JSON.parseObject(JSONObject.toJSONString(review));
            User fromUser = UserCacheUtils.getUserInfo(review.getFromUserId());
            User toUser = UserCacheUtils.getUserInfo(review.getToUserId());
            json.put("fromUserNickname", fromUser.getNickname());
            json.put("fromUserAvatar", fromUser.getPicture());
            json.put("toUserNickname", toUser.getNickname());
            json.put("toUserAvatar", toUser.getPicture());
            json.put("createTime", TimeUtils.parseDateTime(review.getCreateTime()));
            ja.add(json);
        }
        dataJson.put("total", ja.size());
        dataJson.put("list", ja);
        return new Result.Builder<JSONObject>().data(dataJson).builder();
    }
}
