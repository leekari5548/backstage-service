package com.leekari.controller;

import com.alibaba.fastjson.JSONObject;
import com.leekari.util.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litao
 * @date 2020/11/5 10:27
 * @description
 */
@RestController
@RequestMapping("/tools/")
public class CommonToolsController {

    @RequestMapping("conversionNumber")
    public Result<JSONObject> parseNumber(Integer conversionArg, String number) {
        JSONObject data = new JSONObject();
        data.put("bin", number + conversionArg);
        data.put("oct", number + conversionArg);
        data.put("ten", number + conversionArg);
        data.put("oxx", number + conversionArg);
        return new Result.Builder<JSONObject>().data(data).builder();
    }
}
