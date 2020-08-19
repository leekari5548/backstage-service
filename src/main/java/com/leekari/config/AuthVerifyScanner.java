//package com.leekari.config;
//
//import org.glassfish.jersey.server.internal.scanning.PackageNamesScanner;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.List;
//
//
///**
// * @author litao
// * @date 2020/7/2 17:18
// * @description
// */
//@SuppressWarnings({"rawtypes"})
//public class AuthVerifyScanner implements CommandLineRunner {
//
//    private static final Logger logger = LoggerFactory.getLogger(AuthVerifyScanner.class);
//
//    private final static String PACKAGE_NAME = "com.leekari";
//
//    public static String[] pattern = null;
//
//
//    public static String[] executeInit(){
//        return executeInit(PACKAGE_NAME);
//    }
//
//    /**
//     *
//     * @return
//     */
//    private static String[] executeInit(String packageName) {
//        String[] packageNames = new String[]{packageName};
//        final PackageNamesScanner scanner = new PackageNamesScanner(packageNames, false);
//        List<String> urlPattern = new ArrayList<>();
//        while (scanner.hasNext()) {
//            String name = scanner.next();
//            if (name.contains(".class")) {
//                name = name.split("\\.")[0];
//            }
//            String clazzName = packageName + "." + name;
//            String clazzMappingName = "";
//            String methodMappingName = "";
//            Class clazz = null;
//            try{
//                clazz = Class.forName(clazzName);
//                logger.info("[" + name + "] has been scanned");
//            }catch (ClassNotFoundException e){
//                logger.warn("ClassNotFoundException : ----> " + clazzName);
//            }
//            if (!clazzMappingName.startsWith("/")){
//                clazzMappingName = "/" + clazzMappingName;
//            }
//            assert clazz != null;
//            Annotation annotation = clazz.getAnnotation(RequestMapping.class);
//            if (annotation != null) {
//                RequestMapping clazzMapping = (RequestMapping) annotation ;
//                String[] clazzMappingValue = clazzMapping.value();
//                if (clazzMappingValue.length > 0) {
//                    clazzMappingName = clazzMappingValue[0];
//                }
//            }
//            Method[] methods = clazz.getMethods();
//            for (Method m: methods) {
//                Annotation a = m.getAnnotation(AuthVerify.class);
//
//                if (a != null) {
//                    RequestMapping mapping = m.getAnnotation(RequestMapping.class);
//                    if (mapping != null) {
//                        String[] methodMappingValue = mapping.value();
//                        if (methodMappingValue.length > 0) {
//                            methodMappingName = methodMappingValue[0];
//                        }
//                        String fullMappingName = clazzMappingName + methodMappingName;
//                        urlPattern.add(fullMappingName);
//                        logger.info("add filter url --> [" + fullMappingName + "]\n" +
//                                "===> controller:[" + name + "],method:[" + m.getName() +
//                                "]");
//                    }
//                }
//            }
//            if (StringUtils.isEmpty(methodMappingName)) {
//                String fullMappingName  = "";
//                if (clazzMappingName.endsWith("/")) {
//                    fullMappingName = clazzMappingName + "**";
//                }else {
//                    fullMappingName = clazzMappingName + "/**";
//                }
//                urlPattern.add(fullMappingName);
//                logger.info( "add filter url --> [" + fullMappingName + "]\n" +
//                        "===> controller:[" + clazz.getSimpleName() + "]");
//            }
//        }
//        String[] urlPatterns = new String[urlPattern.size()];
//        for (int i = 0; i < urlPattern.size(); i++) {
//            urlPatterns[i] = urlPattern.get(i);
//        }
//        return urlPatterns;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        pattern = executeInit();
//    }
//}
