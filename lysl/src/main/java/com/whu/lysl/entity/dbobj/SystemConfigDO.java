package com.whu.lysl.entity.dbobj;

import lombok.Data;

import java.util.Date;

/**
 * 系统配置 DO
 * @author Visionary
 * @since 2020/1/29 12:18 PM
 */
@Data
public class SystemConfigDO {

    /** id */
    private Integer id;

    /** gmt_created */
    private Date gmtCreated;

    /** gmt_modified */
    private Date gmtModified;

    /** config_key */
    private String configKey;

    /** config_value */
    private String configValue;

    /** tag */
    private String tag;

    /** status */
    private String status;

    /** param */
    private String param;

}
