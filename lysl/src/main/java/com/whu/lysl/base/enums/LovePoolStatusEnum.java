package com.whu.lysl.base.enums;

import com.whu.lysl.base.utils.StringUtils;

/**
 * @Author Caspar
 * @CreateTime 2020/2/11 17:10
 * @Description:
 */
public enum LovePoolStatusEnum {
    /**
     * 不在爱心池中，定向捐赠
     */
    NOT_IN_POOL("NOT_IN_POOL", "未加入爱心池"),

    /**
     * 在爱心池中，待分配
     */
    IN_POOL("IN_POOL", "在爱心池中，待分配"),

    /**
     * 已人工派单
     */
    ARTI_DISPATCHED("ARTI_DISPATCHED", "已人工派单"),

    /**
     * 已自动派单
     */
    AUTO_DISPATCHED("AUTO_DISPATCHED", "已自动派单");


    private String code;

    /**
     * 枚举描述
     */
    private String description;

    LovePoolStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据code 获取对应枚举
     * @param code 枚举code
     * @return 有则返回对应枚举，无则返回null
     */
    public OrderStatusEnum getEnumByCode(String code) {
        for(OrderStatusEnum matchingStatusEnum : OrderStatusEnum.values()) {
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
