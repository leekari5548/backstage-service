package com.leekari.exception;

import com.alibaba.fastjson.JSONObject;
import com.leekari.service.ISendMailService;
import com.leekari.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author litao
 * @date 2020/9/24 14:06
 * @description
 */
@RestControllerAdvice
@Component
public class BaseExceptionHandler {
    public static final List<Exception> exceptionList = new CopyOnWriteArrayList<>();

    @Autowired
    private ISendMailService sendMailService;
    @Value("${spring.application.name:default}")
    private String applicationName;

//    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public Result<JSONObject> handleException(Exception e) throws Exception{
//        StackTraceElement[] stackTrace = e.getStackTrace();
//        StringBuilder sb = new StringBuilder();
//        Arrays.stream(stackTrace).forEach(stackTraceElement -> sb.append(stackTraceElement).append("\r\n"));
//
//        String exceptionMessage = e.getMessage();
        exceptionList.add(e);
        throw e;
    }
}
