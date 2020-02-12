package com.whu.lysl.base.enums;

import com.whu.lysl.base.utils.StringUtils;

/**
 * 匹配状态的枚举类
 *
 * @author Jzh
 * @since 2020-02-10 10:55
 **/
public enum MatchingStatusEnum {

    /**
     * 未审核状态
     */
    UNCHECKED("UNCHECKED", "未审核状态"),

    /**
     * 审核通过
     */
    CHECKED("CHECKED", "人工审核通过"),

    /**
     * 审核不通过
     */
    CHECKEDFAILED("CHECKEDFAILED","审核不通过"),

    /**
     * 运送中
     */
    IN_TRANSIT("IN_TRANSIT", "快递运送途中"),

    /**
     * 待收货
     */
    WAIT_FOR_RECEIVING("WAIT_FOR_RECEIVING", "快递已送达，等待收货"),

    /**
     * 已收货
     */
    DELIVERED("DELIVERED", "受赠者已收货，配送完成"),
    ;

    /**
     * 枚举编码
     */
    private String code;

    /**
     * 枚举描述
     */
    private String description;

    MatchingStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据code 获取对应枚举
     * @param code 枚举code
     * @return 有则返回对应枚举，无则返回null
     */
    public static MatchingStatusEnum getEnumByCode(String code) {
        for(MatchingStatusEnum matchingStatusEnum : MatchingStatusEnum.values()) {
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
