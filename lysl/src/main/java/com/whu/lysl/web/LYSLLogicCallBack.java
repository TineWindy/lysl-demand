package com.whu.lysl.web;


/**
 * controller 处理逻辑抽象
 * @author Visionary
 * @since 2019/8/19 3:27 PM
 */
public interface LYSLLogicCallBack {

    /**
     * 实现execute方法以处理controller请求
     * @return 处理结果
     * @throws Exception 需要异常处理
     */
    LYSLResult<Object> execute() throws Exception;

}
