package com.whu.lysl.base.enums;


import com.whu.lysl.base.utils.StringUtils;

/**
 * @author Visionary
 * @since 2019/8/29 12:44 AM
 */
public enum LYSLDataStatusEnum {

    /** 合法状态 */
    VALID("VALID", "合法状态"),

    /** 违法状态 */
    INVALID("INVALID", "违法状态"),

    /** 完整 */
    COMPLETED("COMPLETED", "数据完整"),

    /** 缓存已刷新 */
    REFRESHED("REFRESHED", "缓存已刷新"),

    /** 缓存等待刷新 */
    WAIT_FOR_REFRESH("WAIT_FOR_REFRESH", "缓存待刷新"),

    /** 正常状态 */
    NORMAL("NORMAL", "正常状态"),

    /** 过期状态 */
    EXPIRED("EXPIRED", "已过期"),

    /** 未审核 */
    UNCHECKED("UNCHECKED", "未审核"),
    ;

    /** 枚举编码 */
    private String code;

    /** 枚举描述 */
    private String description;

    LYSLDataStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据code 获取对应枚举
     * @param code 枚举code
     * @return 有则返回对应枚举，无则返回null
     */
    public LYSLDataStatusEnum getEnumByCode(String code) {
        for(LYSLDataStatusEnum lyslDataStatusEnum : LYSLDataStatusEnum.values()) {
            if(StringUtils.equal(code, lyslDataStatusEnum.getCode())) {
                return lyslDataStatusEnum;
            }
        }
        return null;
    }

    /**
     * Get the value of code
     *
     * @return the value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Set the code
     *
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description
     *
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }


}
