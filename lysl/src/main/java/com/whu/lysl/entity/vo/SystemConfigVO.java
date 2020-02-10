package com.whu.lysl.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author Visionary
 * @since 2020/1/29 9:55 PM
 */
@Data
public class SystemConfigVO {

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

    /** param */
    private String param;

}
