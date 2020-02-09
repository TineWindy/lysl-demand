package com.whu.lysl.dao;

import com.whu.lysl.entity.dbobj.UserDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * user dao
 * @author Visionary
 * @since 2020/2/10 3:41 AM
 */
@Mapper
public interface UserDAO {

    /**
     * 插入一条数据
     * @param userDO do
     */
    void insert(UserDO userDO);

    /**
     * 更新一条数据
     * @param userDO do
     * @return wether success
     */
    int update(UserDO userDO);

    /**
     * 根据 id 查询
     * @param id id
     * @return do
     */
    UserDO selectById(Integer id);

    /**
     * 根据 phone 查询
     * @param phone phone
     * @return do
     */
    UserDO selectByPhone(String phone);

}
