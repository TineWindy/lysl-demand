package com.whu.lysl.entity.dto;

import lombok.Data;

import java.util.Date;

/**
 * 系统配置 Model
 * @author Visionary
 * @since 2020/1/29 12:18 PM
 */
@Data
public class SystemConfig {

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
