package com.whu.lysl.base.exceptions;


import com.whu.lysl.base.enums.LYSLResultCodeEnum;

/**
 * LYSL异常
 * @author Visionary
 * @since 2019/8/19 4:53 PM
 */
public class LYSLException extends RuntimeException {

    /** 错误码 */
    private LYSLResultCodeEnum errorCode;

    /**
     * 错误码构造器
     * @param errorCode 错误码
     */
    public LYSLException(LYSLResultCodeEnum errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 常用构造器
     * @param message 异常信息
     * @param errorCode 错误码
     */
    public LYSLException(String message, LYSLResultCodeEnum errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * 构造器
     * @param message 异常信息
     * @param errorCode 错误码
     * @param cause 异常
     */
    public LYSLException(String message, LYSLResultCodeEnum errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public LYSLException(String message, Throwable cause, LYSLResultCodeEnum errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public LYSLException(Throwable cause, LYSLResultCodeEnum errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public LYSLException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, LYSLResultCodeEnum errorCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorCode = errorCode;
    }

    /**
     * Get the value of errorCode
     *
     * @return the value of errorCode
     */
    public LYSLResultCodeEnum getErrorCode() {
        return errorCode;
    }

    /**
     * Set the errorCode
     *
     * @param errorCode errorCode
     */
    public void setErrorCode(LYSLResultCodeEnum errorCode) {
        this.errorCode = errorCode;
    }
}
