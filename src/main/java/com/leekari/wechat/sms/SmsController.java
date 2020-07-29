///*
// * Copyright 2013-2018 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//
//package com.leekari.wechat.sms;
//
//import com.alibaba.cloud.spring.boot.sms.ISmsService;
//import com.aliyun.mns.model.Message;
//import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
//import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
//import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsRequest;
//import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsResponse;
//import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
//import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
//import com.aliyuncs.exceptions.ClientException;
//import com.aliyuncs.http.MethodType;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.env.Environment;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//public class SmsController {
//
//    @Autowired
//    private Environment environment;
//
//    @Autowired
//    private ISmsService smsService;
//
////    @Autowired
////    private SmsReportMessageListener smsReportMessageListener;
//
//    @GetMapping("/report-queue.do")
//    public String getSmsReportQueuename() {
//
//        return environment.getProperty("spring.cloud.alicloud.sms.up-queue-name");
//    }
//
//
//    @RequestMapping("/sms/send")
//    public SendSmsResponse sendCheckCode(@RequestParam(name = "code", defaultValue = "234567") String code) {
//        // 组装请求对象-具体描述见控制台-文档部分内容
//        SendSmsRequest request = new SendSmsRequest();
//        // 必填:待发送手机号
//        request.setPhoneNumbers("18234009399");
//        // 必填:短信签名-可在短信控制台中找到
//        request.setSignName("leekari的个人服务");
//        // 必填:短信模板-可在短信控制台中找到
//        request.setTemplateCode("SMS_198365373");
//        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//        request.setTemplateParam("{\"code\":\"" + code + "\"}");
//
//        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
//        // request.setSmsUpExtendCode("90997");
//
//        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//        request.setOutId(UUID.randomUUID().toString());
//        try {
//            SendSmsResponse sendSmsResponse = smsService.sendSmsRequest(request);
//            return sendSmsResponse;
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//        return new SendSmsResponse();
//    }
//
//    @RequestMapping("/query.do")
//    public QuerySendDetailsResponse querySendDetailsResponse(
//            @RequestParam(name = "tel") String telephone) {
//        // 组装请求对象
//        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
//        // 必填-号码
//        request.setPhoneNumber(telephone);
//        // 必填-短信发送的日期 支持30天内记录查询（可查其中一天的发送数据），格式yyyyMMdd
//        request.setSendDate("20190103");
//        // 必填-页大小
//        request.setPageSize(10L);
//        // 必填-当前页码从1开始计数
//        request.setCurrentPage(1L);
//        try {
//            QuerySendDetailsResponse response = smsService.querySendDetails(request);
//            return response;
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//
//        return new QuerySendDetailsResponse();
//    }
//
////    @RequestMapping("/sms-report.do")
////    public List<Message> smsReport() {
////
////        return smsReportMessageListener.getSmsReportMessageSet();
////    }
//
//}
