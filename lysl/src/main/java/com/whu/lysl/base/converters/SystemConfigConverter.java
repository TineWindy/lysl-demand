package com.whu.lysl.base.converters;


import com.whu.lysl.entity.dbobj.SystemConfigDO;
import com.whu.lysl.entity.dto.SystemConfig;
import com.whu.lysl.entity.vo.SystemConfigVO;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统配置转换
 * @author Visionary
 * @since 2020/1/29 1:58 PM
 */
public class SystemConfigConverter {

    /**
     * do 转换为 model
     * @param systemConfigDO do
     * @return model
     */
    public static SystemConfig do2Model(SystemConfigDO systemConfigDO) {
        if (systemConfigDO == null) {
            return null;
        }

        SystemConfig systemConfig = new SystemConfig();
        systemConfig.setId(systemConfigDO.getId());
        systemConfig.setConfigKey(systemConfigDO.getConfigKey());
        systemConfig.setConfigValue(systemConfigDO.getConfigValue());
        systemConfig.setTag(systemConfigDO.getTag());
        systemConfig.setStatus(systemConfigDO.getStatus());
        systemConfig.setParam(systemConfigDO.getParam());
        systemConfig.setGmtCreated(systemConfigDO.getGmtCreated());
        systemConfig.setGmtModified(systemConfigDO.getGmtModified());

        return systemConfig;
    }

    /**
     * 批量 do 2 model
     * @param systemConfigDOS do list
     * @return model list
     */
    public static List<SystemConfig> batchDO2Model(List<SystemConfigDO> systemConfigDOS) {
        List<SystemConfig> systemConfigs = new ArrayList<>();
        if (systemConfigDOS == null || systemConfigDOS.size() == 0) {
            return systemConfigs;
        }

        for (SystemConfigDO systemConfigDO: systemConfigDOS) {
            systemConfigs.add(do2Model(systemConfigDO));
        }

        return systemConfigs;
    }

    /**
     * model 2 do
     * @param systemConfig modle
     * @return do
     */
    public static SystemConfigDO model2DO(SystemConfig systemConfig) {
        if (systemConfig == null) {
            return null;
        }

        SystemConfigDO systemConfigDO = new SystemConfigDO();
        systemConfigDO.setId(systemConfig.getId());
        systemConfigDO.setConfigKey(systemConfig.getConfigKey());
        systemConfigDO.setConfigValue(systemConfig.getConfigValue());
        systemConfigDO.setTag(systemConfig.getTag());
        systemConfigDO.setStatus(systemConfig.getStatus());
        systemConfigDO.setParam(systemConfig.getParam());
        systemConfigDO.setGmtCreated(systemConfig.getGmtCreated());
        systemConfigDO.setGmtModified(systemConfig.getGmtModified());

        return systemConfigDO;
    }

    /**
     * model 2 vo
     * @param systemConfig model
     * @return vo
     */
    public static SystemConfigVO model2VO(SystemConfig systemConfig) {
        if (systemConfig == null) {
            return null;
        }

        SystemConfigVO systemConfigVO = new SystemConfigVO();
        systemConfigVO.setId(systemConfig.getId());
        systemConfigVO.setConfigKey(systemConfig.getConfigKey());
        systemConfigVO.setConfigValue(systemConfig.getConfigValue());
        systemConfigVO.setTag(systemConfig.getTag());
        systemConfigVO.setParam(systemConfig.getParam());
        systemConfigVO.setGmtCreated(systemConfig.getGmtCreated());
        systemConfigVO.setGmtModified(systemConfig.getGmtModified());

        return systemConfigVO;
    }

    /**
     * batch model 2 vo
     * @param systemConfigs models
     * @return vos
     */
    public static List<SystemConfigVO> batchModel2VO(List<SystemConfig> systemConfigs) {
        List<SystemConfigVO> systemConfigVOS = new ArrayList<>();
        if (systemConfigs == null || systemConfigs.size() == 0) {
            return systemConfigVOS;
        }

        for (SystemConfig systemConfig: systemConfigs) {
            systemConfigVOS.add(model2VO(systemConfig));
        }

        return systemConfigVOS;

    }

}
