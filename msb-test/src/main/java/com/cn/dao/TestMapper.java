package com.cn.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.entity.AssessTrackVo;
import com.cn.entity.TestEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface TestMapper extends BaseMapper<TestEntity> {
    AssessTrackVo selAssessTrackVo(Long targetId);
    List<AssessTrackVo> selATVFirstListByTargetId(Long targetId,Long parentId);
}
