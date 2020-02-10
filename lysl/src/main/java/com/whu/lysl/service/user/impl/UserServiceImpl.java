package com.whu.lysl.service.user.impl;

import com.whu.lysl.base.converters.UserConverter;
import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.base.utils.StringUtils;
import com.whu.lysl.dao.UserDAO;
import com.whu.lysl.entity.dto.User;
import com.whu.lysl.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * user service impl
 * @author Visionary
 * @since 2020/2/10 5:27 AM
 */
@Service
public class UserServiceImpl implements UserService {

    /** user dao */
    @Resource
    private UserDAO userDAO;

    @Override
    public void addAnUser(User user) {

        checkUser(user);

        User oldUser = UserConverter.do2Model(userDAO.selectByPhone(user.getPhone()));
        if (oldUser == null) {
            userDAO.insert(UserConverter.model2DO(user));
        } else {
            userDAO.update(UserConverter.model2DO(user));
        }

    }

    /**
     * 检验用户信息完整性
     * @param user user
     */
    private void checkUser(User user) {
        if (user == null || !StringUtils.isNotEmpty(user.getPhone()) ||
                !StringUtils.isNotEmpty(user.getName()) || user.getInstitutionId() == null) {
            throw new LYSLException("用户信息不完整", LYSLResultCodeEnum.DATA_INVALID);
        }
    }

    @Override
    public User getUserById(Integer id) {
        return UserConverter.do2Model(userDAO.selectById(id));
    }

    @Override
    public User getUserByPhone(String phone) {
        return UserConverter.do2Model(userDAO.selectByPhone(phone));
    }
}
