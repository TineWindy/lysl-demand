package com.whu.lysl.web;


import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.base.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;

/**
 * controller 父类
 * @author created by Visionary on 2019/8/16
 */
@Slf4j
public class LYSLBaseController {


    /**
     * 鉴权类型枚举
     */
    protected enum AuthEnum {
        IGNORE_VERIFY("IGNORE_VERIFY", "无需鉴权"),
        MANAGEMENT_OPERATION("MANAGEMENT_OPERATION", "管理人员权限"),
        ;

        /** 枚举编码 */
        private String code;

        /** 枚举描述 */
        private String description;

        AuthEnum(String code, String description) {
            this.code = code;
            this.description = description;
        }

        public String getCode() {
            return code;
        }
    }

    /**
     * 基础controller
     * @param request 请求
     * @param logicCallBack 处理逻辑
     * @param params 扩展参数
     */
    final protected LYSLResult<Object> protectController(HttpServletRequest request,
                                                        LYSLLogicCallBack logicCallBack, String... params) {
        LYSLResult<Object> result = new LYSLResult<>();
        try {
            // 1. 前置操作
            preRequestHandle(request);
            if (params.length == 0 || StringUtils.equal(AuthEnum.IGNORE_VERIFY.code, params[0])) {
                log.info("request from " + request.getRemoteAddr() + " without auth-verify");
            } else if (StringUtils.equal(AuthEnum.MANAGEMENT_OPERATION.code, params[0])) {
                verifyAuth(AuthEnum.MANAGEMENT_OPERATION);
            }
            // 2. 核心处理逻辑
            result = logicCallBack.execute();
        } catch (LYSLException ge) {
            log.error(ge.getMessage());
            result.setSuccess(false);
            result.setResultCode(ge.getErrorCode().getCode());
            result.setResultDesc(ge.getMessage());

            return result;
        } catch (Exception e) {
            log.error(e.getMessage());
            result.setSuccess(false);
            result.setResultCode(LYSLResultCodeEnum.SYSTEM_ERROR.getCode());
            result.setResultDesc(LYSLResultCodeEnum.SYSTEM_ERROR.getDescription());

            return result;
        }
        result.setSuccess(true);
        result.setResultCode(LYSLResultCodeEnum.SUCCESS.getCode());
        result.setResultDesc(LYSLResultCodeEnum.SUCCESS.getDescription());
        return result;
    }

    /**
     * 前置操作
     * @param request 用户登录检测
     */
    private void preRequestHandle(HttpServletRequest request) {

    }

    private void verifyAuth (AuthEnum authEnum) {
        // todo 鉴权逻辑
    }

}
