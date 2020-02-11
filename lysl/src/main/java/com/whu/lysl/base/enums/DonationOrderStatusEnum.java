package com.whu.lysl.base.enums;

import com.whu.lysl.base.utils.StringUtils;

/**
 * 匹配状态的枚举类
 *
 * @author Jzh
 * @since 2020-02-10 10:55
 **/
public enum DonationOrderStatusEnum {

    /**
     * 未审核
     */
    UNCHECKED("UNCHECKED", "未审核状态"),

    /**
     * 审核状态
     */
    APPROVED("APPROVED", "人工审核通过"),

    /**
     * 审核不通过
     */
    DISAPPROVED("DISAPPROVED", "人工审核不通过");

    /**
     * 枚举编码
     */
    private String code;

    /**
     * 枚举描述
     */
    private String description;

    DonationOrderStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据code 获取对应枚举
     * @param code 枚举code
     * @return 有则返回对应枚举，无则返回null
     */
    public DonationOrderStatusEnum getEnumByCode(String code) {
        for(DonationOrderStatusEnum matchingStatusEnum : DonationOrderStatusEnum.values()) {
            if(StringUtils.equal(code, matchingStatusEnum.getCode())) {
                return matchingStatusEnum;
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
