package com.whu.lysl.web.controllers;


import com.alibaba.fastjson.JSON;
import com.whu.lysl.base.converters.SystemConfigConverter;
import com.whu.lysl.base.enums.LYSLDataStatusEnum;
import com.whu.lysl.service.system.SystemService;
import com.whu.lysl.web.LYSLBaseController;
import com.whu.lysl.web.LYSLResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 系统服务
 * @author Visionary
 * @since 2020/1/29 9:45 PM
 */
@RestController
@RequestMapping("system")
public class SystemController extends LYSLBaseController {

    /** log */
    private final Logger log = LoggerFactory.getLogger(SystemController.class);

    /** system service */
    @Resource
    private SystemService systemService;

    @GetMapping("getConfigList")
    public String getConfigList(HttpServletRequest request) {
        LYSLResult<Object> result = protectController(request, () -> {
                LYSLResult<Object> res = new LYSLResult<>();

                String configKey = "", tag = "";
                if(request.getParameterMap().containsKey("configKey") && request.getParameterMap().get("configKey").length > 0) {
                    configKey = request.getParameterMap().get("configKey")[0];
                }
                if(request.getParameterMap().containsKey("tag") && request.getParameterMap().get("tag").length > 0) {
                    configKey = request.getParameterMap().get("tag")[0];
                }

                res.setResultObj(SystemConfigConverter.
                        batchModel2VO(systemService.getConfigsByKeyStatusTag(configKey, LYSLDataStatusEnum.NORMAL.getCode(), tag)));
                log.info("获取系统配置参数", configKey, tag);
                return res;

        }, AuthEnum.IGNORE_VERIFY.getCode());
        return JSON.toJSONString(result);
    }

    /**
     * 获取七牛云上传 token
     * @param request request
     * @return token str
     */
    @GetMapping("getUploadToken")
    public String getUploadToken(HttpServletRequest request) {
        LYSLResult<Object> result = protectController(request, () -> {
            LYSLResult<Object> res = new LYSLResult<>();

            String token = systemService.getQiniuToken();

            res.setResultObj(token);
            log.info("获取七牛云上传 token");
            return res;
        }, AuthEnum.IGNORE_VERIFY.getCode());
        return JSON.toJSONString(result);
    }

}
