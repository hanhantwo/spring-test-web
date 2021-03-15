package com.cn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.entity.AssessTrackVo;
import com.cn.entity.TestEntity;

public interface TestService extends IService<TestEntity> {
    /**
     * 树对象
     * @param targetId
     * @return
     */
    AssessTrackVo selAssessTrackVo(Long targetId);
}
