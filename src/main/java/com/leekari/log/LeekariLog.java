package com.leekari.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.jul.LevelChangePropagator;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.SizeAndTimeBasedFNATP;
import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import ch.qos.logback.core.rolling.TimeBasedFileNamingAndTriggeringPolicy;
import ch.qos.logback.core.util.FileSize;
import ch.qos.logback.core.util.OptionHelper;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

/**
 * @author litao
 * @date 2020/8/3 22:42
 * @description
 */
@SuppressWarnings("rawtypes")
public final class LeekariLog {

    private static Environment environment;

    public static final String DEFAULT_PATTERN = "%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger - %msg%n";

    private static String NAME = "leekari";

    private static final LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

    private static Logger logger;

    private LeekariLog(){
    }

    static{
        if(logger == null){
            logger = genLogger(NAME);
        }
    }

    public static boolean init(Environment environment){
        LeekariLog.environment = environment;
        String name = environment.getProperty("spring.application.name");
        if(StringUtils.isEmpty(name)){
            return false;
        }
        logger = genLogger(name);
        System.err.println(name);
        return logger != null;
    }

    private static Logger genLogger(String name){

        LevelChangePropagator levelChangePropagator = new LevelChangePropagator();
        levelChangePropagator.setContext(context);
        levelChangePropagator.setResetJUL(true);
        context.addListener(levelChangePropagator);
        context.putObject("org.springframework.boot.logging.LoggingSystem", new Object());
        logger = context.getLogger(name);
        logger.setLevel(Level.DEBUG);

//        <logger name="org.springframework.data.mybatis" level="INFO"/>
        Logger mybatisLogger = context.getLogger("org.springframework.data.mybatis");
        mybatisLogger.setLevel(Level.INFO);
//        <logger name="org.springframework.aop.aspectj" level="ERROR"/>
        Logger aspectjLogger = context.getLogger("org.springframework.aop.aspectj");
        aspectjLogger.setLevel(Level.ERROR);
//        <logger name="org.springframework.web" level="INFO"/>
        Logger webLogger = context.getLogger("org.springframework.web");
        webLogger.setLevel(Level.INFO);
//        <logger name="org.springframework.security" level="WARN"/>
        Logger securityLogger = context.getLogger("org.springframework.security");
        securityLogger.setLevel(Level.WARN);
//        <logger name="org.springframework.cache" level="WARN"/>
        Logger cacheLogger = context.getLogger("org.springframework.cache");
        cacheLogger.setLevel(Level.WARN);
//        <logger name="org.springframework.core" level="WARN"/>
        Logger springCore = context.getLogger("org.springframework.core");
        springCore.setLevel(Level.INFO);
//        <logger name="org.springframework.boot" level="INFO"/>
        Logger springBoot = context.getLogger("org.springframework.boot");
        springBoot.setLevel(Level.INFO);
//        <logger name="org.springframework.beans" level="INFO"/>
        Logger springBeans = context.getLogger("org.springframework.beans");
        springBeans.setLevel(Level.INFO);
//        <logger name="org.springframework" level="INFO"/>
        Logger spring = context.getLogger("org.springframework");
        spring.setLevel(Level.INFO);
//        <logger name="org.apache.kafka" level="INFO"/>
        Logger kafkaSpringLogger = context.getLogger("org.apache.kafka");
        kafkaSpringLogger.setLevel(Level.INFO);
//        <logger name="org.mybatis.spring" level="INFO"/>
        Logger mybatisSpringLogger = context.getLogger("org.mybatis.spring");
        mybatisSpringLogger.setLevel(Level.INFO);
//        <logger name="org.mongodb.driver.cluster" level="WARN" />
        Logger mongoLogger = context.getLogger("org.mongodb.driver.cluster");
        mongoLogger.setLevel(Level.WARN);

//    <logger name="javax.activation" level="WARN"/>
        Logger activationLogger = context.getLogger("javax.activation");
        activationLogger.setLevel(Level.WARN);
//    <logger name="javax.mail" level="WARN"/>
        Logger mailLogger = context.getLogger("javax.mail");
        mailLogger.setLevel(Level.WARN);
//    <logger name="javax.xml.bind" level="WARN"/>
        Logger xmlBindLogger = context.getLogger("javax.xml.bind");
        xmlBindLogger.setLevel(Level.WARN);
//    <logger name="ch.qos.logback" level="WARN"/>
        Logger logback = context.getLogger("ch.qos.logback");
        logback.setLevel(Level.WARN);
//    <logger name="com.sun" level="WARN"/>
        Logger sunLogger = context.getLogger("com.sun");
        sunLogger.setLevel(Level.WARN);
//    <logger name="org.apache" level="WARN"/>
        Logger apacheLogger = context.getLogger("org.apache");
        apacheLogger.setLevel(Level.WARN);
//    <logger name="org.apache.catalina.startup.DigesterFactory" level="OFF"/>
        Logger digesterFactoryLogger = context.getLogger("org.apache.catalina.startup.DigesterFactory");
        digesterFactoryLogger.setLevel(Level.OFF);
//    <logger name="org.hibernate.validator" level="WARN"/>
        Logger hibernateValidatorLogger = context.getLogger("org.hibernate.validator");
        hibernateValidatorLogger.setLevel(Level.WARN);
//    <logger name="org.hibernate" level="WARN"/>
        Logger hibernateLogger = context.getLogger("org.hibernate");
        hibernateLogger.setLevel(Level.WARN);
//    <logger name="org.hibernate.ejb.HibernatePersistence" level="OFF"/>
        Logger hibernatePersistenceLogger = context.getLogger("org.hibernate.ejb.HibernatePersistence");
        hibernatePersistenceLogger.setLevel(Level.OFF);
//    <logger name="sun.rmi.transport" level="WARN"/>
        Logger rmiTransportLogger = context.getLogger("sun.rmi.transport");
        rmiTransportLogger.setLevel(Level.WARN);

        Appender<ch.qos.logback.classic.spi.ILoggingEvent> rollingFileAppender = rollingFileAppender(context);
        Appender<ch.qos.logback.classic.spi.ILoggingEvent> consoleAppender = consoleAppender(context);
        context.getLogger("ROOT").detachAndStopAllAppenders();
        context.getLogger("ROOT").setAdditive(true);
        context.getLogger("ROOT").setLevel(Level.DEBUG);
        context.getLogger("ROOT").addAppender(rollingFileAppender);
        context.getLogger("ROOT").addAppender(consoleAppender);
        context.getLogger("ROOT").setAdditive(false);
        return logger;
    }

    public static Logger getLogger(){
        return logger;
    }

    public static void setLevel(String name, Level level){
        Logger logger = context.getLogger(name);
        logger.setLevel(level);
    }

    public static void info(String content, Throwable thrown){
        logger.info(content, thrown);
    }

    public static void info(String content){
        logger.info(content);
    }

    public static void debug(String content){
        logger.debug(content);
    }

    public static void debug(String content, Throwable thrown){
        logger.debug(content, thrown);
    }

    public static void error(String content, Throwable thrown){
        logger.error(content, thrown);
    }

    public static void error(String content){
        logger.error(content);
    }

    public static void trace(String content, Throwable thrown){
        logger.trace(content, thrown);
    }

    public static void trace(String content){
        logger.trace(content);
    }

    public static void warn(String content, Throwable thrown){
        logger.warn(content, thrown);
    }

    public static void warn(String content){
        logger.warn(content);
    }

    public static String getName(){
        return logger.getName();
    }


    private static RollingFileAppender<ch.qos.logback.classic.spi.ILoggingEvent> rollingFileAppender(final LoggerContext context){
        RollingFileAppender<ch.qos.logback.classic.spi.ILoggingEvent> appender = new RollingFileAppender<>();
        appender.setName("leekari-file");
        appender.setContext(context);
        appender.setFile(OptionHelper.substVars("hilogs"+File.separator+logger.getName()+".log", context));
        appender.setAppend(true);
        appender.setPrudent(false);



        SizeAndTimeBasedRollingPolicy policy = new SizeAndTimeBasedRollingPolicy();
        String pattern = OptionHelper.substVars("hilogs"+File.separator+logger.getName()+"-%d{yyyyMMdd}[%i].log", context);
        policy.setMaxFileSize(FileSize.valueOf("10MB"));
        policy.setFileNamePattern(pattern);
        policy.setParent(appender);
        policy.setContext(context);

        TimeBasedFileNamingAndTriggeringPolicy timeBasedFileNamingAndTriggeringPolicy = new SizeAndTimeBasedFNATP();
        timeBasedFileNamingAndTriggeringPolicy.setContext(context);
        ((SizeAndTimeBasedFNATP) timeBasedFileNamingAndTriggeringPolicy).setMaxFileSize(FileSize.valueOf("10MB"));
        timeBasedFileNamingAndTriggeringPolicy.setTimeBasedRollingPolicy(policy);


        policy.setTimeBasedFileNamingAndTriggeringPolicy(timeBasedFileNamingAndTriggeringPolicy);

        policy.start();

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern(DEFAULT_PATTERN);
        encoder.setCharset(StandardCharsets.UTF_8);
        encoder.start();

        appender.setRollingPolicy(policy);
        appender.setEncoder(encoder);
        appender.start();

        return appender;
    }


    private static ConsoleAppender<ch.qos.logback.classic.spi.ILoggingEvent> consoleAppender(final LoggerContext context){
        ConsoleAppender<ch.qos.logback.classic.spi.ILoggingEvent> appender = new ConsoleAppender<>();
        appender.setName("leekari-console");
        appender.setContext(context);

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern(DEFAULT_PATTERN);
        encoder.start();

        appender.setEncoder(encoder);
        appender.start();

        return appender;
    }

}

