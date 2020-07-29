package com.leekari.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class WeChatApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(WeChatApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WeChatApplication.class);
    }
}

@RestController
class IndexController{
    @RequestMapping("/")
    public String index(){
        return "this is a service";
    }
}
