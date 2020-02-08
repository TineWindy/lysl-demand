package com.whu.lysl.web.controllers;

import com.alibaba.fastjson.JSON;
import com.whu.lysl.base.enums.LYSLDataStatusEnum;
import com.whu.lysl.entity.condition.InstCondition;
import com.whu.lysl.entity.dto.Institution;
import com.whu.lysl.service.institution.InstitutionService;
import com.whu.lysl.web.LYSLBaseController;
import com.whu.lysl.web.LYSLResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 机构 controller
 * @author Visionary
 * @since 2020/2/8 9:48 PM
 */
@RestController
@RequestMapping("inst")
public class InstController extends LYSLBaseController {

    /** 机构服务 */
    @Resource
    private InstitutionService institutionService;

    @RequestMapping("getInstByStatus")
    public String getInstByStatus(HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
           LYSLResult<Object> result = new LYSLResult<>();

           // 这里应该 do 转 vo，先这样写个示例
            List<Institution> institutionList = institutionService.getInstsByCondition(new InstCondition.Builder().status(LYSLDataStatusEnum.UNCHECKED.getCode()).build());
            result.setResultObj(institutionList);
            return result;
        }, AuthEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

}
