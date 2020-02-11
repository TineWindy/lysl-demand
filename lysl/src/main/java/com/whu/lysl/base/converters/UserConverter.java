package com.whu.lysl.base.converters;

import com.whu.lysl.entity.dbobj.UserDO;
import com.whu.lysl.entity.dto.User;

import java.util.ArrayList;
import java.util.List;

/**
 * user converter
 * @author Visionary
 * @since 2020/2/10 5:20 AM
 */
public class UserConverter {

    /**
     * do 2 model
     * @param userDO do
     * @return model
     */
    public static User do2Model(UserDO userDO) {
        if (userDO == null) {
            return null;
        }

        User user = new User();
        user.setId(userDO.getId());
        user.setGmtCreated(userDO.getGmtCreated());
        user.setGmtModified(userDO.getGmtModified());
        user.setName(userDO.getName());
        user.setPhone(userDO.getPhone());
        user.setInstitutionId(userDO.getInstitutionId());
        user.setWxNumber(userDO.getWxNumber());

        return user;
    }

    /**
     * 批量 do 2 model
     * @param userDOS do list
     * @return model list
     */
    public static List<User> batchDo2Model(List<UserDO> userDOS) {
        List<User> users = new ArrayList<>();

        if (userDOS == null) {
            return users;
        }

        for (UserDO userDO : userDOS) {
            users.add(UserConverter.do2Model(userDO));
        }
        return users;
    }

    /**
     * model 2 do
     * @param user model
     * @return do
     */
    public static UserDO model2DO(User user) {
        if (user == null) {
            return null;
        }

        UserDO userDO = new UserDO();
        userDO.setId(user.getId());
        userDO.setGmtCreated(user.getGmtCreated());
        userDO.setGmtModified(user.getGmtModified());
        userDO.setName(user.getName());
        userDO.setPhone(user.getPhone());
        userDO.setInstitutionId(user.getInstitutionId());
        userDO.setWxNumber(user.getWxNumber());

        return userDO;
    }

}
