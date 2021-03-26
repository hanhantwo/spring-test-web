package com.cn.result.exception;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({GlobalExceptionHandler.class})
public @interface EnableGlobalExceptionHandler {
}
