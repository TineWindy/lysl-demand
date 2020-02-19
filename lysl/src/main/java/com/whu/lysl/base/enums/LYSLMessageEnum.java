package com.whu.lysl.base.enums;

import com.whu.lysl.base.utils.StringUtils;

/**
 * @author Visionary
 * @since 2020/2/18 10:05 PM
 */
public enum LYSLMessageEnum {

    /** param1 是 demand id，param2 是 后台链接 */
    DEMAND_CHECK("【珞樱善联】有一笔需求 _param_ 信息录入，请登录 _param_ 及时审核!", "需求审核"),

    /** 姓名，受捐机构，捐赠单物资，更新物流信息链接，运营电话 */
    DONOR_SHIP("【珞樱善联】尊敬的 _param_ ，您向 _param_ 捐赠的物资 _param_ 已审核通过！请及时发货，" +
            "登录 _param_ 更新物流信息。如有疑问，可电话咨询 _param_ 。", "通知捐赠方发货"),

    /** 捐赠方名称，捐赠单号，捐赠单审核页 */
    DONATION_CHECK("【珞樱善联】有一笔来自 _param_ 的捐赠信息 _param_ 录入，请登录 _param_ 及时审核！",
            "捐赠单人工审核"),

    /** 姓名，捐赠单物资，审核未通过原因，后台地址，运营电话 */
    DONOR_DISAPPROVED("【珞樱善联】尊敬的 _param_ ，很抱歉的通知您，捐赠物资 _param_ 审核未通过，原因 _param_ 。" +
            "登录 _param_ 查看详情。如有疑问，可电话咨询 _param_ 。", "捐赠单人工拒绝结果通知"),

    /** 捐赠物资，捐赠单号，捐赠单审核页 */
    IN_LOVE_POOL("【珞樱善联】有新的捐赠物资 _param_ 加入爱心池，请关注留意，登录 _param_ 查看详情。",
            "爱心池新入库通知")

    ;

    /**
     * 枚举编码
     */
    private String code;

    /**
     * 枚举描述
     */
    private String description;

    LYSLMessageEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据code 获取对应枚举
     *
     * @param code 枚举code
     * @return 有则返回对应枚举，无则返回null
     */
    public LYSLMessageEnum getEnumByCode(String code) {
        for (LYSLMessageEnum lyslMessageEnum : LYSLMessageEnum.values()) {
            if (StringUtils.equal(code, lyslMessageEnum.getCode())) {
                return lyslMessageEnum;
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
