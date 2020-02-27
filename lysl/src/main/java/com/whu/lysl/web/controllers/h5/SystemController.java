package com.whu.lysl.web.controllers.h5;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.whu.lysl.base.constants.LYSLConstants;
import com.whu.lysl.base.converters.SystemConfigConverter;
import com.whu.lysl.base.enums.LYSLDataStatusEnum;
import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.base.utils.StringUtils;
import com.whu.lysl.entity.dto.SystemConfig;
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
import java.util.List;

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
                log.info("获取系统配置参数" + configKey + " " + tag);
                return res;

        }, BaseControllerEnum.IGNORE_VERIFY.getCode());
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
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());
        return JSON.toJSONString(result);
    }

    /**
     * 登录接口
     * @param request 请求
     * @return json str
     */
    @GetMapping("loginIn")
    public String loginIn(HttpServletRequest request) {
        return JSON.toJSONString(protectController(request, () -> {
            LYSLResult<Object> res = new LYSLResult<>();

            String userName = request.getParameter("userName");
            String password = request.getParameter("password");

            List<SystemConfig> userList = systemService.getConfigsByKeyStatusTag(LYSLConstants.COSTUMER_SERVICE_STAFF_PRE + userName,
                     LYSLDataStatusEnum.NORMAL.getCode(), null);
            if (userList.size() == 0) {
                throw new LYSLException("username or password is wrong", LYSLResultCodeEnum.DATA_INVALID);
            }
            JSONObject userJson = JSON.parseObject(userList.get(0).getConfigValue());
            if (!StringUtils.equal(password, userJson.getString("password"))) {
                throw new LYSLException("username or password is wrong", LYSLResultCodeEnum.DATA_INVALID);
            }
            userJson.getInnerMap().put("password", null);

            res.setResultObj(userJson);
            return res;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode(), BaseControllerEnum.BACK_MANAGE.getCode()));
    }

}
