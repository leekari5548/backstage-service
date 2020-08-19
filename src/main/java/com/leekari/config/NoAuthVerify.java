package com.leekari.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author litao
 * @date 2020/7/31 14:38
 * @description
 */
@Target({ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface NoAuthVerify {
}
