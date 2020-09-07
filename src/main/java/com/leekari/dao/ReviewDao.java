package com.leekari.dao;

import com.leekari.entity.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author litao
 * @date 2020/9/3 14:33
 * @description
 */
@Mapper
public interface ReviewDao {
    List<Review> getReviewByArticle(String id);

    int insertReview(Review review);
}
