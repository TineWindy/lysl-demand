package com.whu.lysl.base.enums;

import com.whu.lysl.base.utils.StringUtils;

/**
 * 匹配方式的枚举
 *
 * @author Jzh
 * @since 2020-02-09 20:08
 **/
public enum MatchingMethodEnum {

    /**
     * 系统自动匹配
     */
    AUTOMATIC_MATCHING("AUTOMATIC_MATCHING", "系统自动对捐赠单和需求单进行匹配"),

    /**
     * 捐赠者定向捐赠
     */
    TARGETED_DONATION("TARGETED_DONATION", "捐赠者定向捐赠资源"),

    /**
     * 志愿者人工匹配
     */
    ARTIFICAL_MATCHING("ARTIFICAL_MATCHING", "志愿者手工从爱心池中进行匹配"),
    ;

    /**
     * 枚举编码
     */
    private String code;

    /**
     * 枚举描述
     */
    private String description;

    MatchingMethodEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据code 获取对应枚举
     * @param code 枚举code
     * @return 有则返回对应枚举，无则返回null
     */
    public static MatchingMethodEnum getEnumByCode(String code) {
        for(MatchingMethodEnum matchingMethodEnum : MatchingMethodEnum.values()) {
            if(StringUtils.equal(code, matchingMethodEnum.getCode())) {
                return matchingMethodEnum;
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
