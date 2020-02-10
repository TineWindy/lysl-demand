package com.whu.lysl.web.controllers;

import com.alibaba.fastjson.JSON;
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
    @RequestMapping(value = "/getMatchOrderByHonorName",method = RequestMethod.GET)
    public String getMatchOrderByHonorName(HttpServletRequest request){
        LYSLResult<Object> res = protectController(request,() ->{
            LYSLResult<Object> result = new LYSLResult<>();
            String honorName = request.getParameter("honorName");
            List<MatchOrder> matchOrderList = orderMatchService.getMatchOrderByDonorName(honorName);
            result.setResultObj(matchOrderList);
            return result;
        }, AuthEnum.IGNORE_VERIFY.getCode());
        return JSON.toJSONString(res);
    }
}
