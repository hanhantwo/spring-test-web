package com.cn.entity.enums;

import java.lang.annotation.*;

import org.springframework.stereotype.Service;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface OrderHandlerType {
	String source();
}
