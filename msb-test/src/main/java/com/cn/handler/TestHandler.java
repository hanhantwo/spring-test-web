package com.cn.handler;

import com.cn.entity.TestEntity;
import com.cn.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @ClassName TestHandler
 * @Description TODO
 * @Author
 * @Date 2020/11/6 16:01
 */
@Slf4j
@Component
public class TestHandler extends BaseTestHandler<TestEntity> {
    private TestService testService;
    @Autowired
    public TestHandler(TestService testService){
        this.testService=testService;
    }


    public void handlerTest(){

    }
}
