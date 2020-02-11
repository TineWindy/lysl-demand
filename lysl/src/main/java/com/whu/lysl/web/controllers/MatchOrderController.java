package com.whu.lysl.web.controllers;

import com.alibaba.fastjson.JSON;
import com.whu.lysl.base.enums.MatchingMethodEnum;
import com.whu.lysl.base.enums.MatchingStatusEnum;
import com.whu.lysl.entity.dto.Institution;
import com.whu.lysl.entity.dto.MatchOrder;
import com.whu.lysl.service.OrderMatchService;
import com.whu.lysl.web.LYSLBaseController;
import com.whu.lysl.web.LYSLResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 匹配单 Controller
 *
 * @author Jzh
 * @since 2020-02-09 23:14
 **/

@RestController
@RequestMapping("/matchOrder")
public class MatchOrderController extends LYSLBaseController {
    /** 机构服务 */
    @Resource
    private OrderMatchService orderMatchService;


    /**
     * 志愿者人工派单对外接口
     * @param request
     * @return
     */
    @RequestMapping(value = "/dispatch",method = RequestMethod.POST)
    public String artificialDispatch(HttpServletRequest request, @RequestBody MatchOrder matchOrder) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            matchOrder.setStatus(MatchingStatusEnum.CHECKED.getCode()); //志愿者默认已审核
            matchOrder.setMatchingMethod(MatchingMethodEnum.ARTIFICAL_MATCHING.getCode());//人工审核
             orderMatchService.saveMatchOrder(matchOrder);
            return result;
        }, AuthEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    /**
     * 根据捐赠者名字获取匹配单
     * @param request
     * @return
     */
    @RequestMapping(value = "/getMatchOrderByDonorName",method = RequestMethod.GET)
    public String getMatchOrderByDonorName(HttpServletRequest request){
        LYSLResult<Object> res = protectController(request,() ->{
            LYSLResult<Object> result = new LYSLResult<>();
            String honorName = request.getParameter("donorName");
            List<MatchOrder> matchOrderList = orderMatchService.getMatchOrderByDonorName(honorName);
            result.setResultObj(matchOrderList);
            return result;
        }, AuthEnum.IGNORE_VERIFY.getCode());
        return JSON.toJSONString(res);
    }


    /**
     * 根据受赠者名字获取匹配单
     * @param request
     * @return
     */
    @RequestMapping(value = "/getMatchOrderByDoneeName",method = RequestMethod.GET)
    public String getMatchOrderByDoneeName(HttpServletRequest request){
        LYSLResult<Object> res = protectController(request,() ->{
            LYSLResult<Object> result = new LYSLResult<>();
            String honorName = request.getParameter("doneeName");
            List<MatchOrder> matchOrderList = orderMatchService.getMatchOrderByDoneeName(honorName);
            result.setResultObj(matchOrderList);
            return result;
        }, AuthEnum.IGNORE_VERIFY.getCode());
        return JSON.toJSONString(res);
    }

    @RequestMapping(value = "/updateCheckingNumber",method = RequestMethod.GET)
    public String updateCheckingNumber(HttpServletRequest request){
        LYSLResult<Object> res = protectController(request,() ->{
            LYSLResult<Object> result = new LYSLResult<>();
            String trackingNumber = request.getParameter("trackingNumber");
            int matchOrderId = Integer.parseInt(request.getParameter("matchOrderId") + "");
            orderMatchService.updateTrackingNumber(matchOrderId,trackingNumber);
            return result;
        },AuthEnum.IGNORE_VERIFY.getCode());
        return JSON.toJSONString(res);
    }
}
