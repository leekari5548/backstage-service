package com.leekari.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.leekari.dao.MenuDao;
import com.leekari.entity.Menu;
import com.leekari.service.MenuService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author litao
 * @date 2020/8/19 14:21
 * @description
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuDao menuDao;

    @Override
    public JSONObject getAll() {
        List<Menu> menus = menuDao.getAllMenu();
        List<Menu> mainMenus= menus.stream().filter(menu -> menu.getMenuLevel().equals(0)).collect(Collectors.toList());
        JSONObject json = new JSONObject();
        json.put("list", mainMenus);
        return json;
    }
}
