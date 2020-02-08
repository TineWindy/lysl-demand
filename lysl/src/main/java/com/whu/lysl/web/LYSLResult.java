package com.whu.lysl.web;

import java.io.Serializable;

/**
 * web请求结果泛型类
 * @author Visionary
 * @since 2019/8/19 3:13 PM
 */
public class LYSLResult<T> implements Serializable {

    /**
     * 序列号id
     */
    private static final long serialVersionUID = 761675137867578348L;

    /**
     * 请求结果
     */
    private boolean success = true;

    /**
     * 响应code
     */
    private String resultCode;

    /**
     * 响应描述
     */
    private String resultDesc;

    /**
     * 结果实体
     */
    private T resultObj;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Get the value of resultCode
     *
     * @return the value of resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * Set the resultCode
     *
     * @param resultCode resultCode
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * Get the value of resultDesc
     *
     * @return the value of resultDesc
     */
    public String getResultDesc() {
        return resultDesc;
    }

    /**
     * Set the resultDesc
     *
     * @param resultDesc resultDesc
     */
    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    /**
     * Get the value of resultObj
     *
     * @return the value of resultObj
     */
    public T getResultObj() {
        return resultObj;
    }

    /**
     * Set the resultObj
     *
     * @param resultObj resultObj
     */
    public void setResultObj(T resultObj) {
        this.resultObj = resultObj;
    }
}
