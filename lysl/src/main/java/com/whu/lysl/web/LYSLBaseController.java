package com.whu.lysl.web;


import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.base.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

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
    protected enum BaseControllerEnum {
        IGNORE_VERIFY("IGNORE_VERIFY", "无需鉴权"),
        MANAGEMENT_OPERATION("MANAGEMENT_OPERATION", "管理人员权限"),
        BACK_MANAGE("BACK_MANAGE", "后台管理入口"),
        ;

        /** 枚举编码 */
        private String code;

        /** 枚举描述 */
        private String description;

        BaseControllerEnum(String code, String description) {
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
            log.info("Start request " + request.getRequestURI() + " from " + request.getRemoteAddr());

            // 1. 前置操作
            preRequestHandle(request);

            // 2. 核心处理逻辑
            result = logicCallBack.execute();
        } catch (LYSLException ge) {
            log.error(ge.getMessage() + "\n");
            result.setSuccess(false);
            result.setResultCode(ge.getErrorCode().getCode());
            result.setResultDesc(ge.getMessage());

            // temp
            result.setCode(-1);

            return result;
        } catch (Exception e) {
            log.error(e.getMessage() + "\n");
            result.setSuccess(false);
            result.setResultCode(LYSLResultCodeEnum.SYSTEM_ERROR.getCode());
            result.setResultDesc(LYSLResultCodeEnum.SYSTEM_ERROR.getDescription());

            // temp
            result.setCode(-1);

            return result;
        }
        result.setSuccess(true);
        result.setResultCode(LYSLResultCodeEnum.SUCCESS.getCode());
        result.setResultDesc(LYSLResultCodeEnum.SUCCESS.getDescription());

        // temp
        if (params.length == 2 && StringUtils.equal(BaseControllerEnum.BACK_MANAGE.code, params[1])) {
            result.setData(result.getResultObj());
            result.setMsg(result.getResultDesc());
            result.setResultObj(null);
            result.setResultDesc(null);
            result.setResultCode(null);

        }

        log.info("Finish request\n");
        return result;
    }

    /**
     * 前置操作
     * @param request 用户登录检测
     */
    private void preRequestHandle(HttpServletRequest request) {

    }

    private void verifyAuth (BaseControllerEnum baseControllerEnum) {
        // todo 鉴权逻辑
    }

}
