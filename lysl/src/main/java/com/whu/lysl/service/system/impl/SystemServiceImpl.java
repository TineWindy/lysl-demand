package com.whu.lysl.service.system.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiniu.util.Auth;
import com.whu.lysl.base.constants.LYSLConstants;
import com.whu.lysl.base.converters.SystemConfigConverter;
import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.base.utils.AssertUtils;
import com.whu.lysl.base.utils.StringUtils;
import com.whu.lysl.dao.SystemConfigDAO;
import com.whu.lysl.entity.condition.SystemConfigCondition;
import com.whu.lysl.entity.dbobj.SystemConfigDO;
import com.whu.lysl.entity.dto.SystemConfig;
import com.whu.lysl.service.system.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author Visionary
 * @since 2020/1/29 1:18 PM
 */
@Service
@Slf4j
public class SystemServiceImpl implements SystemService {

    /** system config dao */
    @Resource
    private SystemConfigDAO systemConfigDAO;

    @Override
    public List<SystemConfig> getConfigsByKeyStatusTag(String configKey, String status, String tag) {
        SystemConfigCondition systemConfigCondition = new SystemConfigCondition();
        systemConfigCondition.setConfigKey(configKey);systemConfigCondition.setStatus(status);systemConfigCondition.setTag(tag);
        return SystemConfigConverter.batchDO2Model(systemConfigDAO.selectByCondition(systemConfigCondition));
    }

    @Override
    public void updateConfig(SystemConfig systemConfig) {
        log.info("开始更新系统配置参数", systemConfig);

        AssertUtils.AssertNotNull(systemConfig);
        if (systemConfig.getId() == null || !StringUtils.isNotEmpty(systemConfig.getConfigKey()) ||
                !StringUtils.isNotEmpty(systemConfig.getStatus())) {
            log.error("更新系统配置参数失败，systemconfig 数据残缺");
            throw new LYSLException("fail to update systemconfig due to lack of model data", LYSLResultCodeEnum.DATA_INVALID);
        }

        systemConfigDAO.update(SystemConfigConverter.model2DO(systemConfig));
        log.info("更新系统配置参数完成", systemConfig);
    }

    @Override
    public String getQiniuToken() {
        Auth auth = Auth.create(LYSLConstants.QINIU_ACCESS_KEY, LYSLConstants.QINIU_SECRET_KEY);
        return auth.uploadToken(LYSLConstants.QINIU_BUCKET);
    }

    @Override
    public Map<String, String> getCustomerServiceStaff() {
        List<SystemConfig> systemConfigs = getConfigsByKeyStatusTag("CUSTUMER_SERVICE", "NORMAL", null);

        Random random = new Random();
        int index = random.nextInt(systemConfigs.size());
        JSONObject jsonObject = JSON.parseObject(systemConfigs.get(index).getConfigValue());

        Map<String, String> map = new HashMap<>();
        map.put(jsonObject.getString("staffName"), jsonObject.getString("staffPhone"));
        return map;
    }

}
