package com.whu.lysl.web.controllers;

import com.alibaba.fastjson.JSON;
import com.whu.lysl.base.converters.DemandConverter;
import com.whu.lysl.entity.dto.Demand;
import com.whu.lysl.service.demand.impl.DemandServiceImpl;
import com.whu.lysl.web.LYSLBaseController;
import com.whu.lysl.web.LYSLResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 机构 controller
 * @author Powen
 */
@RestController
@RequestMapping("demand")
public class DemandController extends LYSLBaseController {

    /** 机构服务 */
    @Resource
    private DemandServiceImpl demandServiceImpl;

    @RequestMapping("getReviewedDemands")
    public String getDemandByStatus(HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            List<Demand> demandList = demandServiceImpl.getUnreviewedDemands();
            result.setResultObj(demandList);
            return result;
        }, LYSLBaseController.AuthEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    @RequestMapping("insertDemand")
    public String insertDemand(HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            List<Demand> demandList = DemandConverter.request2Models(request);
            for(Demand demand : demandList)
                demandServiceImpl.insertDemand(demand);
            result.setResultObj("信息已入库");
            return result;
        }, LYSLBaseController.AuthEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    @RequestMapping("modifyStatus")
    public String modifyStatus(HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            demandServiceImpl.modifyStatus(request.getParameter("demandId"), request.getParameter("status"));
            result.setResultObj("审核结果已修改");
            return result;
        }, LYSLBaseController.AuthEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }
}
