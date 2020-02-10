package com.whu.lysl.service.user;

import com.whu.lysl.entity.dto.User;

import java.util.List;

/**
 * 主体用户服务
 * @author Visionary
 * @since 2020/2/10 5:12 AM
 */
public interface UserService {

    /**
     * 新增一条用户信息
     * @param user user
     */
    void addAnUser(User user);

    /**
     * 根据 id 获取 user
     * @param id id
     * @return user
     */
    User getUserById(Integer id);

    /**
     * 根据 phone 获取 user
     * @param phone ohone
     * @return user
     */
    User getUserByPhone(String phone);

    /**
     * 获取所有的用户
     * @return user list
     */
    List<User> getAllUser();

}
