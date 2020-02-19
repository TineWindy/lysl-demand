package com.whu.lysl.service.system;


import com.whu.lysl.entity.dto.SystemConfig;

import java.util.List;
import java.util.Map;

/**
 * 系统基础服务
 * @author Visionary
 * @since 2020/1/29 12:32 PM
 */
public interface SystemService {

    /**
     * 通过 key status tag 查询系统配置
     * @param configKey config key
     * @param status status
     * @param tag tag
     * @return system config list
     */
    List<SystemConfig> getConfigsByKeyStatusTag(String configKey, String status, String tag);

    /**
     * 更新系统配置
     * 根据 id 说明往往需要先进行查询再进行更新操作
     * @param systemConfig systemConfig to be updated
     */
    void updateConfig(SystemConfig systemConfig);

    /**
     * 获取 七牛存储 token
     * @return str token
     */
    String getQiniuToken();

    /**
     * 随机获取运营人员信息
     * key 为姓名，value 为电话号码
     * @return kv
     */
    Map<String, String> getCustomerServiceStaff();

}
