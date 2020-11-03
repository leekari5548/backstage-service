package com.leekari.dao;

import com.leekari.entity.BlogArticle;
import com.leekari.entity.BlogContent;
import com.leekari.entity.view.BlogList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author litao
 * @date 2020/8/25 17:02
 * @description
 */
@Mapper
public interface BlogDao {
    int insertBlogArticle(BlogArticle blogArticle);

    int insertBlogContent(BlogContent blogContent);

    List<BlogList> getBlogList(@Param("title") String title, @Param("startTime") String startTime,
                               @Param("endTime") String endTime);

    BlogList getBlogDetail(String id);

    BlogContent getBlogContent(String id);

    BlogArticle getBlogArticle(String id);

    int updateBlogContent(BlogContent blogContent);

    int updateBlogArticle(BlogArticle blogArticle);

    List<BlogList> getBlogListByIdList(@Param("list") List<String> ids);

    int logicDeleteBlog(List<String> ids);
}
