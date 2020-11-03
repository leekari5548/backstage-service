package com.leekari.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leekari.dao.BlogDao;
import com.leekari.dao.BlogTypeDao;
import com.leekari.dao.TagDao;
import com.leekari.define.BasicConst;
import com.leekari.entity.*;
import com.leekari.entity.view.BlogList;
import com.leekari.service.BlogService;
import com.leekari.service.UserService;
import com.leekari.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author litao
 * @date 2020/8/25 19:44
 * @description
 */
@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserService userService;
    @Resource
    private BlogDao blogDao;
    @Resource
    private BlogTypeDao blogTypeDao;
    @Resource
    private TagDao tagDao;

    @Override
    public Result<JSONObject> mostPopular() {
        return blogList(null,null,null,null,null);
    }

    @Override
    public Result<JSONObject> listBlogType() {
        List<BlogType> list = blogTypeDao.getAllBlogType();
        JSONObject dataJson = new JSONObject();
        JSONArray ja = new JSONArray();
        for (BlogType blogType: list) {
            JSONObject object = JSON.parseObject(JSON.toJSONString(blogType));
            object.put("lastOperationTime", TimeUtils.parseDateTime(blogType.getLastOperationTime()));
            ja.add(object);
        }
        dataJson.put("list", ja);
        return new Result.Builder<JSONObject>().data(dataJson).builder();
    }

    @Override
    public Result<JSONObject> detail(String id) {
        BlogList blog = blogDao.getBlogDetail(id);
        String tagIds = blog.getTags();
        List<Tag> tagList = new ArrayList<>();
        if (!StringUtils.isEmpty(tagIds)) {
            String[] tagId = tagIds.split(",");
            List<String> tagIdList = new ArrayList<>();
            for (String tag: tagId) {
                if (!StringUtils.isEmpty(tag)) {
                    tagIdList.add(tag);
                }
            }
            tagList = tagDao.getTagsByIds(tagIdList);
        }
        JSONObject dataJson = JSONObject.parseObject(JSON.toJSONString(blog));
        dataJson.put("createTime", TimeUtils.parseDateTime(blog.getCreateTime()));
        if (tagList.size() > 0) {
            dataJson.put("tags", tagList);
        }else {
            dataJson.put("tags", JSONArray.parse("[]"));
        }
        return new Result.Builder<JSONObject>().data(dataJson).builder();
    }

    @Override
    public Result<JSONObject> addNewBlog(String articleTitle, String htmlContent, String mdContent, String userId, String tags) {
        StringBuilder tagIds = new StringBuilder();
        List<String> saveTag = new ArrayList<>();
        if (!StringUtils.isEmpty(tags)) {
            String[] tagNames = tags.split(",");
            List<Tag> tagList = tagDao.getTagsByUser(userId);
            for (String tagName: tagNames) {
                String tagId = "";
                for (Tag tag: tagList) {
                    if (tag.getTagName().equals(tagName)) {
                        tagId = tag.getId();
                    }
                }
                if (StringUtils.isEmpty(tagId)) {
                    saveTag.add(tagName);
                }else {
                    tagIds.append(tagId).append(",");
                }
            }
        }

        if (saveTag.size() > 0) {
            List<Tag> saveTags = new ArrayList<>();
            for (String tagName: saveTag) {
                Tag tag = new Tag();
                String id = CommonUtils.uuid();
                tagIds.append(id).append(",");
                tag.setId(id);
                tag.setTagName(tagName);
                tag.setDeleted(0);
                tag.setLastOperationTime(new Date());
                tag.setAssociateUser(userId);
                saveTags.add(tag);
            }
            tagDao.batchInsertTag(saveTags);
        }
        System.err.println(tagIds.toString());
        String articleId = CommonUtils.uuid();
        User user = userService.userInfo(userId);
        BlogContent blogContent = new BlogContent();
        BlogArticle blogArticle = new BlogArticle();
        blogArticle.setId(articleId);
        blogArticle.setArticleContentHtml(htmlContent);
        blogArticle.setArticleContentMd(mdContent);
        blogArticle.setCreateUser(user.getNickname());
        blogArticle.setCreateTime(new Date());
        blogArticle.setDeleted(0);
        blogArticle.setArticleTitle(articleTitle);
        blogDao.insertBlogArticle(blogArticle);
        blogContent.setId(CommonUtils.uuid());
        blogContent.setArticleId(articleId);
        blogContent.setComments(0);
        blogContent.setCreateTime(new Date());
        blogContent.setCreateUser(user.getNickname());
        blogContent.setDeleted(0);
        blogContent.setStatus(0);
        blogContent.setStars(0);
        blogContent.setViews(0);
        blogContent.setLikes(0);
        blogContent.setTags(tagIds.toString());
        blogDao.insertBlogContent(blogContent);
        return new Result.Builder<JSONObject>().builder();
    }

    @Override
    public Result<JSONObject> editBlog(String id, String articleTitle, String htmlContent, String mdContent, String userId, String tags) {
        StringBuilder tagIds = new StringBuilder();
        List<String> saveTag = new ArrayList<>();
        if (!StringUtils.isEmpty(tags)) {
            String[] tagNames = tags.split(",");
            List<Tag> tagList = tagDao.getTagsByUser(userId);
            for (String tagName: tagNames) {
                String tagId = "";
                for (Tag tag: tagList) {
                    if (tag.getTagName().equals(tagName)) {
                        tagId = tag.getId();
                    }
                }
                if (StringUtils.isEmpty(tagId)) {
                    saveTag.add(tagName);
                }else {
                    tagIds.append(tagId).append(",");
                }
            }
        }

        if (saveTag.size() > 0) {
            List<Tag> saveTags = new ArrayList<>();
            for (String tagName: saveTag) {
                Tag tag = new Tag();
                String tagId = CommonUtils.uuid();
                tagIds.append(tagId).append(",");
                tag.setId(tagId);
                tag.setTagName(tagName);
                tag.setDeleted(0);
                tag.setLastOperationTime(new Date());
                tag.setAssociateUser(userId);
                saveTags.add(tag);
            }
            tagDao.batchInsertTag(saveTags);
        }
        System.err.println(tagIds.toString());
        BlogArticle blogArticle = blogDao.getBlogArticle(id);
        BlogContent blogContent = blogDao.getBlogContent(id);
        blogArticle.setArticleContentHtml(htmlContent);
        blogArticle.setArticleContentMd(mdContent);
        blogArticle.setArticleTitle(articleTitle);
        blogDao.updateBlogArticle(blogArticle);
        blogContent.setTags(tagIds.toString());
        System.err.println(blogContent);
        blogDao.updateBlogContent(blogContent);
        return new Result<>();
    }

    @Override
    public Result<JSONObject> blogList(String title, String startTime, String endTime, Integer pageIndex, Integer pageSize) {
        List<BlogList> list = new ArrayList<>();
        long total = 0L;
        if (pageIndex == null) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<BlogList> blogList = blogDao.getBlogList(title, startTime, endTime);
        PageInfo<BlogList> pageInfo = new PageInfo<>(blogList);
        total = pageInfo.getTotal();
        list = pageInfo.getList();

        JSONArray ja = new JSONArray();
        for (BlogList blog: list) {
            String des = HtmlUtils.Html2Text(blog.getDescription());
            if (des.length() > 100) {
                des = des.substring(0, 100) + "...";
            }
            blog.setDescription(des);
            JSONObject jo = JSON.parseObject(JSON.toJSONString(blog));
            jo.put("createTime", TimeUtils.parseDateTime(blog.getCreateTime()));
            User user = BasicConst.userCache.stream()
                    .filter(user1 -> !StringUtils.isEmpty(user1.getNickname()) &&
                            user1.getNickname().equals(blog.getCreateUser()))
                    .findFirst().orElse(new User());
            jo.put("createUserId", user.getId());
            String jsonStr = redisUtils.get(blog.getId());
            if (!StringUtils.isEmpty(jsonStr)) {
                DataStatistics statistics = JSONObject.parseObject(jsonStr,DataStatistics.class);
                jo.put("likes", statistics.getLikes());
                jo.put("stars", statistics.getStars());
                jo.put("views", statistics.getViews());
            }
            ja.add(jo);
        }
        JSONObject dataJson = new JSONObject();
        dataJson.put("total", total);
        dataJson.put("list", ja);
        return new Result.Builder<JSONObject>().data(dataJson).builder();
    }

//    public void test(int... s){
//
//    }

    @Override
    public Result<JSONObject> deleteBlog(String id) {
        List<String> ids = new ArrayList<>();
        ids.add(id);
        blogDao.logicDeleteBlog(ids);
        return new Result<>();
    }
}
