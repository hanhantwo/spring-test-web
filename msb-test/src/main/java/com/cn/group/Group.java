package com.cn.group;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序，如果过AddGroup组失败，则UpdateGroup组不会再校验
 */
@GroupSequence({AddGroup.class,UpdateGroup.class})
public interface Group {
}
