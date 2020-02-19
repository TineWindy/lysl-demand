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
    DONOR_SHIP("【珞樱善联】尊敬的_param_，您向_param_捐赠的物资_param_已审核通过！请及时发货，" +
            "登录 _param_ 更新物流信息。如有疑问，可电话咨询_param_。", "通知捐赠方发货"),
    /**
     * 非定向发货短信，捐赠者，捐赠单号，受赠者，受赠者地址，链接，电话号码
     */
    UNDIRECT_DONATION("【珞樱善联】尊敬的_param_，我们为您捐赠的物资_param_匹配到了_param_，" +
            "请将物资发往_param_，登录_param_查看详情。如有疑问，可电话咨询_param_。","非定向捐赠，通知捐赠方发货"),
    /**
     * 通知受赠方，快递已发货
     */
    DONEE_RECEIVE("【珞樱善联】我们为你筹得一笔来自 _param_ 的物资，请后续关注留意，登录 _param_ 查看详情。如有疑问，可电话咨询 _param_ 。","通知受赠方，快递已发货")
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
