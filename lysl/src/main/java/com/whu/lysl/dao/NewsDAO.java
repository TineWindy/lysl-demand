package com.whu.lysl.dao;

import com.whu.lysl.entity.condition.NewsCondition;
import com.whu.lysl.entity.dbobj.NewsDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 新闻 dao
 * @author Visionary
 * @since 2020/2/8 10:03 PM
 */
@Mapper
public interface NewsDAO {

    /**
     * 根据条件查询新闻
     * @param newsCondition 查询条件
     * @return list
     */
    List<NewsDO> selectByCondition(NewsCondition newsCondition);

    /**
     * 模糊查询 title
     * @param title title
     * @return list do
     */
    List<NewsDO> queryByPartitionOfTitle(@Param("title") String title);

    /**
     * 插入一条数据
     * @param newsDO do
     */
    int insert(NewsDO newsDO);

    /**
     * 更新一条数据
     * @param newsDO do
     * @return success
     */
    int update(NewsDO newsDO);

}
