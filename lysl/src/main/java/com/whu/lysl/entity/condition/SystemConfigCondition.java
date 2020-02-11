package com.whu.lysl.entity.condition;

import lombok.Data;

/**
 * query condition
 * @author Visionary
 * @since 2020/1/29 12:28 PM
 */
@Data
public class SystemConfigCondition {

    /** config key */
    private String configKey;

    /** status */
    private String status;

    /** tag */
    private String tag;

}
