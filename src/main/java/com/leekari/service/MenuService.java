package com.leekari.service;

import com.alibaba.fastjson.JSONObject;
import com.leekari.entity.Menu;

import java.util.List;

/**
 * @author litao
 * @date 2020/8/19 14:21
 * @description
 */
public interface MenuService {

    JSONObject getAll(Integer menuType);
}
