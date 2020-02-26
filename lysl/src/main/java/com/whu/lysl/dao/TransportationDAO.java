package com.whu.lysl.dao;

import com.whu.lysl.entity.condition.NewsCondition;
import com.whu.lysl.entity.condition.TransportationCondition;
import com.whu.lysl.entity.dbobj.NewsDO;
import com.whu.lysl.entity.dbobj.TransportationDO;
import com.whu.lysl.entity.dto.Transportation;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Caspar
 * @CreateTime 2020/2/26 14:02
 * @Description:
 */
public interface TransportationDAO {

    /**
     * 根据条件查询新闻
     * @param transactionalCondition 查询条件
     * @return list
     */
    List<TransportationDO> selectByCondition(TransportationCondition transactionalCondition);

    /**
     * 模糊查询 name
     * @param name name
     * @return list do
     */
    List<TransportationDO> queryByPartitionOfName(@Param("name") String name);

    /**
     * 插入一条数据
     * @param transportationDO do
     */
    int insert(TransportationDO transportationDO);

    /**
     * 更新一条数据
     * @param transportationDO do
     * @return success
     */
    int update(TransportationDO transportationDO);

    /**
     * 删除一条数据
     * @param id Integer
     * @return success
     */
    int delete(Integer id);
}
