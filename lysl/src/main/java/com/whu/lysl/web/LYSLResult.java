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

    /** 本次需求，temporary */
    private int code;
    private String msg;
    private int count;
    private T data;

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

    /**
     * Get the value of code
     *
     * @return the value of code
     */
    public int getCode() {
        return code;
    }

    /**
     * Set the code
     *
     * @param code code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Get the value of msg
     *
     * @return the value of msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Set the msg
     *
     * @param msg msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Get the value of count
     *
     * @return the value of count
     */
    public int getCount() {
        return count;
    }

    /**
     * Set the count
     *
     * @param count count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Get the value of data
     *
     * @return the value of data
     */
    public T getData() {
        return data;
    }

    /**
     * Set the data
     *
     * @param data data
     */
    public void setData(T data) {
        this.data = data;
    }
}
