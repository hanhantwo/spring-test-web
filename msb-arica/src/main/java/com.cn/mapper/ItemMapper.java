package com.cn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cn.entity.Item;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface ItemMapper extends BaseMapper<Item>{
    int saveItem(Item item);
}
