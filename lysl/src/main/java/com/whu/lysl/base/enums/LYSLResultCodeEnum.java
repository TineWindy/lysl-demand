package com.whu.lysl.base.enums;


import com.whu.lysl.base.utils.StringUtils;

/**
 * 结果状态码
 * @author Xiaohuang
 * @since 2019/10/11 22:13
 */
public enum LYSLResultCodeEnum {

    /** ---------- web 相关操作码 ---------- */
    /** 操作成功 */
    SUCCESS("SUCCESS", "操作成功"),

    /** 操作成功 */
    ERROR("ERROR", "操作失败"),

    /** 系统错误 */
    SYSTEM_ERROR("SYETEM_ERROR", "系统错误"),

    /** 调用外部接口数据错误 */
    CLIENT_INVOKE_ERROR("CLIENT_INVOKE_ERROR", "调用外部接口数据错误"),

    /** 用户未登录 */
    USER_NOT_LOGIN_IN("USER_NOT_LOGIN_IN", "用户未登录"),

    /** 服务降级 */
    SERVICE_DOWNGRADE("SERVICE_DOWNGRADE", "服务暂不可用"),

    /** 因时间原因，功能未开启 */
    INVALID_DATE("INVALID_DATE", "时间节点暂不可用"),


    /** ---------- 基础异常 相关操作码 ---------- */
    /** 数据违规异常 */
    DATA_INVALID("DATA_INVALID", "数据不合规"),

    /** 参数为空异常 */
    PARAM_EMPTY("PARAM_EMPTY", "参数为空"),

    /** 对象为空异常 */
    OBJECT_NULL("OBJECT_NULL", "对象为空"),

    /** 时间格式异常 */
    DATE_FORMAT_ERROR("DATE_FORMAT_ERROR","日期格式不规范"),

    /** 操作无权限 */
    PERMISSION_ERROR("PERMISSION_ERROR", "操作权限错误"),
    ;
    
    /** 结果code */
    private String code;
    
    /** 结果描述 */
    private String description;

    /**
     * 构造方法
     * @param code code
     * @param description 描述
     */
    LYSLResultCodeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 根据code 获取对应枚举
     * @param code 枚举code
     * @return 有则返回对应枚举，无则返回null
     */
    public LYSLResultCodeEnum getEnumByCode(String code) {
        for(LYSLResultCodeEnum lyslResultCodeEnum : LYSLResultCodeEnum.values()) {
            if(StringUtils.equal(code, lyslResultCodeEnum.getCode())) {
                return lyslResultCodeEnum;
            }
        }
        return null;
    }

    /**
     * Gets the value of code
     *
     * @return the value of code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code
     *
     * @param code code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description
     *
     * @param description description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
