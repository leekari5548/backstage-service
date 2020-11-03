package com.leekari;

import ch.qos.logback.classic.Level;
import com.leekari.log.LeekariLog;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class WeChatApplication extends SpringBootServletInitializer{
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(WeChatApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WeChatApplication.class);
    }
}
