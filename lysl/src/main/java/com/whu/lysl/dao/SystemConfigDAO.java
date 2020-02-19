package com.whu.lysl.dao;


import com.whu.lysl.entity.condition.SystemConfigCondition;
import com.whu.lysl.entity.dbobj.SystemConfigDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Visionary
 * @since 2020/1/29 12:26 PM
 */
@Mapper
public interface SystemConfigDAO {

    /**
     * 根据 condition 查询配置
     * @param systemConfigCondition 查询条件类
     * @return 配置 list
     */
    List<SystemConfigDO> selectByCondition(SystemConfigCondition systemConfigCondition);

    /**
     * 根据 id 更新系统配置
     * @return 更新成功与否
     */
    int update(SystemConfigDO systemConfigDO);

    /**
     * 插入一条配置
     * @param systemConfigDO do
     */
    void insert(SystemConfigDO systemConfigDO);

}
