package com.whu.lysl.base.enums;

import com.whu.lysl.base.utils.StringUtils;

/**
 * 机构类型
 * @author Visionary
 * @since 2020/2/12 3:12 PM
 */
public enum InstitutionTypeEnum {

    HOSPITAL("HOSPITAL", "医院"),
    SCHOOL("SCHOOL", "学校"),
    COMMUNITY("COMMUNITY", "社区"),
    OTHER("OTHER", "其它"),
    ;

    private String code;
    private String description;

    InstitutionTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据code 获取对应枚举
     * @param code 枚举code
     * @return 有则返回对应枚举，无则返回null
     */
    public InstitutionTypeEnum getEnumByCode(String code) {
        for(InstitutionTypeEnum institutionTypeEnum : InstitutionTypeEnum.values()) {
            if(StringUtils.equal(code, institutionTypeEnum.getCode())) {
                return institutionTypeEnum;
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
