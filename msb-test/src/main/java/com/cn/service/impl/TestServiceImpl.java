package com.cn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.dao.TestMapper;
import com.cn.entity.TestEntity;
import com.cn.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @ClassName TestServiceImpl
 * @Description TODO
 * @Author
 * @Date 2020/11/6 14:43
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper,TestEntity>
        implements TestService {
}
