package com.whu.lysl.web.controllers;

import com.alibaba.fastjson.JSON;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whu.lysl.base.enums.DonationTypeEnum;
import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.enums.MatchingMethodEnum;
import com.whu.lysl.base.enums.MatchingStatusEnum;

import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.base.utils.StringUtils;
import com.whu.lysl.entity.condition.MatchOrderCondition;
import com.whu.lysl.entity.dto.ConfirmReceiptRequest;
import com.whu.lysl.entity.dto.MatchOrder;
import com.whu.lysl.entity.dto.UpdateLogisticInfoRequest;
import com.whu.lysl.service.match.OrderMatchService;
import com.whu.lysl.web.LYSLBaseController;
import com.whu.lysl.web.LYSLResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
            matchOrder.setDonationType(DonationTypeEnum.UNDIRECTED.getCode());
            orderMatchService.saveMatchOrder(matchOrder);
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

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
            int donorId = Integer.parseInt(request.getParameter("donorId"));
            List<MatchOrder> matchOrderList = orderMatchService.getMatchOrderByDonorId(donorId);
            result.setResultObj(matchOrderList);
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());
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
            int doneeId = Integer.parseInt(request.getParameter("doneeId"));
            List<MatchOrder> matchOrderList = orderMatchService.getMatchOrderByDoneeId(doneeId);
            result.setResultObj(matchOrderList);
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());
        return JSON.toJSONString(res);
    }

    /**
     * 通过复合查询获取匹配单
     * @param request
     * @return
     */
    @RequestMapping(value = "/getMatchOrder",method = RequestMethod.POST)
    public String getMatchOrder(HttpServletRequest request, @RequestBody MatchOrderCondition matchOrderCondition){
        LYSLResult<Object> res = protectController(request,() ->{
            LYSLResult<Object> result = new LYSLResult<>();
            PageHelper.startPage(matchOrderCondition.getPageNo(), matchOrderCondition.getPageSize());
            List<MatchOrder> matchOrderList = orderMatchService.getMatchOrderList(matchOrderCondition);
            PageInfo pageInfo = new PageInfo(matchOrderList);
            result.setResultObj(matchOrderList);
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());
        return JSON.toJSONString(res);
    }


    /**
     * 更新物流信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateLogisticInfo",method = RequestMethod.POST)
    public String updateCheckingNumber(HttpServletRequest request,@RequestBody UpdateLogisticInfoRequest infoRequest){
        LYSLResult<Object> res = protectController(request,() ->{
            LYSLResult<Object> result = new LYSLResult<>();
            orderMatchService.updateTrackingNumber(infoRequest.getMatchOrder(),infoRequest.getLogisticCode(),infoRequest.getRemark(),infoRequest.getPicListStr());
            return result;
        },BaseControllerEnum.IGNORE_VERIFY.getCode());
        return JSON.toJSONString(res);
    }

    /**
     * 根据快递信息获取物流情况
     * @param request
     * @return
     */
    @RequestMapping(value = "/getTracingByExpressInfo",method = RequestMethod.GET)
    public String getTracingByExpressInfo(HttpServletRequest request){
        LYSLResult<Object> res = protectController(request,() ->{
            LYSLResult<Object> result = new LYSLResult<>();
            String shipperCode = request.getParameter("shipperCode");
            String logisticCode = request.getParameter("logisticCode") ;
            result.setResultObj(orderMatchService.getTracingByExpressInfoFromRedis(shipperCode,logisticCode));
            return result;
        },BaseControllerEnum.IGNORE_VERIFY.getCode());
        return JSON.toJSONString(res);
    }

    /**
     * 根据hash值获取机构和物资相关信息
     * @param request
     * @return
     */
    @RequestMapping(value = "getInstAndMaterialInfoByHash",method = RequestMethod.GET)
    public String getInstAndMaterialInfo(HttpServletRequest request){
        LYSLResult<Object> res = protectController(request,() ->{
            LYSLResult<Object> result = new LYSLResult<>();
            String hashCode = request.getParameter("hashCode");
            result.setResultObj(orderMatchService.getInstAndMaterialInfoByHash(hashCode));
            return result;
        },BaseControllerEnum.IGNORE_VERIFY.getCode());
        return JSON.toJSONString(res);
    }

    /**
     * 确认收货
     * @param request
     * @return
     */

    @RequestMapping(value = "confirmReceipt",method = RequestMethod.POST)
    public String confirmReceipt(HttpServletRequest request,@RequestBody ConfirmReceiptRequest confirmReceiptRequest){


        LYSLResult<Object> res = protectController(request,() ->{
            LYSLResult<Object> result = new LYSLResult<>();
            int matchOrderId = confirmReceiptRequest.getMatchOrderId();
            if(matchOrderId <= 0){
                throw new LYSLException("matchOrderId参数必须存在", LYSLResultCodeEnum.DATA_INVALID);
            }
            orderMatchService.confirmReceipt(matchOrderId);
            result.setResultObj(null);
            return result;
        },BaseControllerEnum.IGNORE_VERIFY.getCode());
        return JSON.toJSONString(res);
    }
}
