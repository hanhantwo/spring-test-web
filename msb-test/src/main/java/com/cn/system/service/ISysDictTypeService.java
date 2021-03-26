package com.cn.system.service;


import com.cn.entity.common.core.domain.entity.BaseDictData;
import com.cn.entity.common.core.domain.entity.BaseDictType;

import java.util.List;

/**
 * 字典 业务层
 * 
 * @author ruoyi
 */
public interface ISysDictTypeService
{
    /**
     * 根据条件分页查询字典类型
     * 
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    public List<BaseDictType> selectDictTypeList(BaseDictType dictType);

    /**
     * 根据所有字典类型
     * 
     * @return 字典类型集合信息
     */
    public List<BaseDictType> selectDictTypeAll();

    /**
     * 根据字典类型查询字典数据
     * 
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    public List<BaseDictData> selectDictDataByType(String dictType);

    /**
     * 根据字典类型ID查询信息
     * 
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    public BaseDictType selectDictTypeById(Long dictId);

    /**
     * 根据字典类型查询信息
     * 
     * @param dictType 字典类型
     * @return 字典类型
     */
    public BaseDictType selectDictTypeByType(String dictType);

    /**
     * 批量删除字典信息
     * 
     * @param dictIds 需要删除的字典ID
     * @return 结果
     */
    public int deleteDictTypeByIds(Long[] dictIds);

    /**
     * 清空缓存数据
     */
    public void clearCache();

    /**
     * 新增保存字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    public int insertDictType(BaseDictType dictType);

    /**
     * 修改保存字典类型信息
     * 
     * @param dictType 字典类型信息
     * @return 结果
     */
    public int updateDictType(BaseDictType dictType);

    /**
     * 校验字典类型称是否唯一
     * 
     * @param dictType 字典类型
     * @return 结果
     */
    public String checkDictTypeUnique(BaseDictType dictType);
}
