package com.whu.lysl.web.controllers.wx;

import com.alibaba.fastjson.JSON;
import com.sun.xml.internal.xsom.impl.parser.BaseContentRef;
import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.entity.vo.DemandVO;
import com.whu.lysl.web.LYSLBaseController;
import com.whu.lysl.web.LYSLResult;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author Caspar
 * @CreateTime 2020/2/28 16:38
 * @Description:
 */

@RestController
@RequestMapping("wx")
@Slf4j
public class wxMpController extends LYSLBaseController {

    @Resource
    WxMpService wxMpService;

    @RequestMapping("getJsapiTicket")
    public String getJsapiTicket(HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            String jsApiTicket;
            try {
                jsApiTicket = wxMpService.getJsapiTicket();
            } catch (WxErrorException e) {
                log.error(e.getMessage());
                throw new LYSLException("微信服务调用出错", LYSLResultCodeEnum.ERROR);
            }
            result.setResultObj(jsApiTicket);
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    @RequestMapping("createJsapiSignature")
    public String createJsapiSignature(String url, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            WxJsapiSignature wxJsApiSignature;
            try {
                wxJsApiSignature = wxMpService.createJsapiSignature(url);
            } catch (WxErrorException e) {
                log.error(e.getMessage());
                throw new LYSLException("微信服务调用出错", LYSLResultCodeEnum.ERROR);
            }
            result.setResultObj(wxJsApiSignature);
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

}
