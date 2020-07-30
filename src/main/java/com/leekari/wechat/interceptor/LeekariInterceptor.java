package com.leekari.wechat.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.leekari.wechat.dao.LogRecordDao;
import com.leekari.wechat.define.ErrorCode;
import com.leekari.wechat.define.ModuleEnum;
import com.leekari.wechat.entity.LogRecord;
import com.leekari.wechat.util.JwtUtils;
import com.leekari.wechat.util.Result;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author litao
 * @date 2020/7/30 21:38
 * @description
 */
@Component
public class LeekariInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private LogRecordDao logRecordDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        String username = request.getHeader("username");
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
            username = request.getParameter("username");
        }
        Claims claims = JwtUtils.checkJWT(token);
        if (claims == null) {
            response.setStatus(HttpServletResponse.SC_OK);
            Result<String> result = new Result.Builder<String>()
                    .code(ErrorCode.AUTH_FAILED_ERROR)
                    .message("token verify failed")
                    .type(ModuleEnum.LOGIN_MODULE.name)
                    .builder();
            response.getOutputStream().write(JSONObject.toJSONString(result).getBytes());
            return false;
        }
        String usernameArgs = JwtUtils.getUsername(token);
        if (!username.equals(usernameArgs)) {
            response.setStatus(HttpServletResponse.SC_OK);
            Result<String> result = new Result.Builder<String>()
                    .code(ErrorCode.TOKEN_NOT_MATCH_USERNAME)
                    .message("username is not match token")
                    .type(ModuleEnum.LOGIN_MODULE.name)
                    .builder();
            response.getOutputStream().write(JSONObject.toJSONString(result).getBytes());
            return false;
        }
        return true;
    }
}
