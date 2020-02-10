package com.whu.lysl.web.controllers;

import com.alibaba.fastjson.JSON;
import com.whu.lysl.entity.dto.Institution;
import com.whu.lysl.entity.dto.MatchOrder;
import com.whu.lysl.service.OrderMatchService;
import com.whu.lysl.web.LYSLBaseController;
import com.whu.lysl.web.LYSLResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public String artificialDispatch(HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            MatchOrder matchOrder = new MatchOrder();
            matchOrder.setDonorId(1);
            matchOrder.setDoneeId(1);
            matchOrder.setDemandOrderId(1);
            matchOrder.setDonationOrderId(1);
            List<Integer> testList = new ArrayList<>();
            testList.add(1);
            testList.add(2);
            matchOrder.setMaterialIdList(testList);
            matchOrder.setMaterialQuantityList(testList);
             orderMatchService.saveMatchOrder(matchOrder);

            // 这里应该 do 转 vo，先这样写个示例
//            List<Institution> institutionList = institutionService.getInstsByCondition(new InstCondition.Builder().status(LYSLDataStatusEnum.UNCHECKED.getCode()).build());
//            result.setResultObj(institutionList);
            return result;
        }, AuthEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }
}
