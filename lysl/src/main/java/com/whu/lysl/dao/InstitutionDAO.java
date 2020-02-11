package com.whu.lysl.dao;

import com.whu.lysl.entity.condition.InstCondition;
import com.whu.lysl.entity.dbobj.InstitutionDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 机构 dao
 * @author Visionary
 * @since 2020/2/8 10:03 PM
 */
@Mapper
public interface InstitutionDAO {

    /**
     * 根据条件查询机构
     * @param instCondition 查询条件
     * @return list
     */
    List<InstitutionDO> selectByCondition(InstCondition instCondition);

    /**
     * 模糊查询 name
     * @param name name
     * @return list do
     */
    List<InstitutionDO> queryByPartitionOfName(@Param("name") String name);

}
