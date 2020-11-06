package com.cn.handler;

import com.cn.entity.BaseEntity;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName BaseTestHandler
 * @Description TODO
 * @Author
 * @Date 2020/11/6 15:55
 */
abstract class BaseTestHandler<T extends BaseEntity>{

    Set<T> getSet(Class<T> clazz) throws Exception{
        Set<T> set =new HashSet<>();
        set.add(clazz.newInstance());
        return set;
    }
}
