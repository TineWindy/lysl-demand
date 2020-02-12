package com.whu.lysl.web.controllers;

import com.alibaba.fastjson.JSON;
import com.whu.lysl.entity.vo.DemandVO;
import com.whu.lysl.service.demand.DemandService;
import com.whu.lysl.web.LYSLBaseController;
import com.whu.lysl.web.LYSLResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 机构 controller
 * @author Powen
 */
@RestController
@RequestMapping("demand")
public class DemandController extends LYSLBaseController {

    /** 机构服务 */
    @Resource
    private DemandService demandService;

    @RequestMapping("getReviewedDemands")
    public String getReviewedDemands(HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            List<DemandVO> demandList = demandService.getUnreviewedDemands();
            result.setResultObj(demandList);
            return result;
        }, LYSLBaseController.AuthEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    @RequestMapping("getReviewedDemandsById")
    public String getReviewedDemandsById(@RequestBody String jsonString, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            List<DemandVO> demandList = demandService.
                    getUnreviewedDemandsById(jsonString);
            result.setResultObj(demandList);
            return result;
        }, LYSLBaseController.AuthEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    @RequestMapping("insertDemand")
    public String insertDemand(@RequestBody String jsonString, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            demandService.insertDemand(jsonString);
            result.setResultObj("插入成功");
            return result;
        }, LYSLBaseController.AuthEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    @RequestMapping("modifyStatus")
    public String modifyStatus(@RequestBody String jsonString, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            demandService.modifyStatus(jsonString);
            result.setResultObj("审核结果已修改");
            return result;
        }, LYSLBaseController.AuthEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }
}
