package com.whu.lysl.base.enums;

import com.whu.lysl.base.utils.StringUtils;

/**
 * 匹配状态的枚举类
 *
 * @author Jzh
 * @since 2020-02-10 10:55
 **/
public enum DirectedStatusEnum {

    /**
     * 定向分配未完成
     */
    UNFINISHED("UNFINISHED", "定向分配未完成"),

    /**
     * 定向分配已完成
     */
    FINISHED("FINISHED", "定向分配已完成"),

    /**
     * 非定向捐赠
     */
    UNDIRECTED("UNDIRECTED", "非定向捐赠");


    /**
     * 枚举编码
     */
    private String code;

    /**
     * 枚举描述
     */
    private String description;

    DirectedStatusEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据code 获取对应枚举
     * @param code 枚举code
     * @return 有则返回对应枚举，无则返回null
     */
    public DirectedStatusEnum getEnumByCode(String code) {
        for(DirectedStatusEnum matchingStatusEnum : DirectedStatusEnum.values()) {
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
