package com.cn.util;


import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.SystemException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @ClassName ValidatorUtils
 * @Description TODO 字段校验工具类
 * @Author
 * @Date 2021/3/4 14:41
 */
@Slf4j
public class ValidatorUtils {

    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 对象校验
     *
     * @param object 待校验的对象
     * @param groups 待校验的组
     * @throws SystemException 校验不通过，则报SystemException异常
     */
    public static void setValidator(Object object, Class<?>... groups) throws SystemException {
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(object, groups);
        if (!constraintViolationSet.isEmpty()) {
            ConstraintViolation<Object> constraintViolation = (ConstraintViolation<Object>) constraintViolationSet.iterator().next();
            try {
                throw new Exception(constraintViolation.getMessage());
            } catch (Exception e) {
                log.error("字段验证报错{}", e);
            }
        }
    }

}
