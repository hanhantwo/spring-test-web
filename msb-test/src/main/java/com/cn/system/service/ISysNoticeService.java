package com.cn.system.service;

import com.cn.system.domain.BaseNotice;

import java.util.List;

/**
 * 公告 服务层
 * 
 * @author ruoyi
 */
public interface ISysNoticeService
{
    /**
     * 查询公告信息
     * 
     * @param noticeId 公告ID
     * @return 公告信息
     */
    public BaseNotice selectNoticeById(Long noticeId);

    /**
     * 查询公告列表
     * 
     * @param notice 公告信息
     * @return 公告集合
     */
    public List<BaseNotice> selectNoticeList(BaseNotice notice);

    /**
     * 新增公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int insertNotice(BaseNotice notice);

    /**
     * 修改公告
     * 
     * @param notice 公告信息
     * @return 结果
     */
    public int updateNotice(BaseNotice notice);

    /**
     * 删除公告信息
     * 
     * @param noticeId 公告ID
     * @return 结果
     */
    public int deleteNoticeById(Long noticeId);
    
    /**
     * 批量删除公告信息
     * 
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    public int deleteNoticeByIds(Long[] noticeIds);
}
