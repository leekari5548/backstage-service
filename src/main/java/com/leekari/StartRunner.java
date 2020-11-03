package com.leekari;

import ch.qos.logback.classic.Level;
import com.leekari.define.BasicConst;
import com.leekari.log.LeekariLog;
import com.leekari.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author litao
 * @date 2020/9/3 20:52
 * @description
 */
@Component
public class StartRunner implements CommandLineRunner {
    @Autowired
    private Environment environment;
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        boolean flag = LeekariLog.init(environment);
        if (flag) {
            LeekariLog.info("init success");
        }else {
            LeekariLog.warn("init error");
        }
        LeekariLog.setLevel("sun.rmi.loader", Level.INFO);
        LeekariLog.setLevel("io.lettuce", Level.INFO);
        LeekariLog.setLevel("io.netty", Level.INFO);
        LeekariLog.setLevel("de.codecentric", Level.INFO);
//        LeekariLog.setLevel("com.zaxxer.hikari", Level.INFO);
        LeekariLog.setLevel("sun.rmi", Level.INFO);
        LeekariLog.setLevel("org.apache.tomcat", Level.INFO);
        LeekariLog.setLevel("org.apache.catalina", Level.INFO);
        LeekariLog.setLevel("jdk.event.security", Level.INFO);
        LeekariLog.setLevel("org.apache.coyote", Level.INFO);
        LeekariLog.setLevel("org.thymeleaf", Level.INFO);
        LeekariLog.setLevel("com.leekari.dao", Level.INFO);
        userService.initUserCache();
    }
}
