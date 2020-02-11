package com.whu.lysl.web.controllers;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whu.lysl.base.converters.DonationOrderConverter;
import com.whu.lysl.base.enums.DonationOrderStatusEnum;
import com.whu.lysl.base.enums.DonationTypeEnum;
import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.enums.LovePoolStatusEnum;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.base.utils.AssertUtils;
import com.whu.lysl.base.utils.StringUtils;
import com.whu.lysl.entity.condition.DonationOrderCondition;
import com.whu.lysl.entity.dto.DonationOrder;
import com.whu.lysl.entity.vo.DonationOrderVO;
import com.whu.lysl.service.donation.DonationOrderService;
import com.whu.lysl.web.LYSLBaseController;
import com.whu.lysl.web.LYSLResult;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @Author Caspar
 * @CreateTime 2020/2/9 15:45
 * @Description:
 */

@RestController
@RequestMapping("donationOrder")
public class DonationOrderController extends LYSLBaseController {

    @Resource
    DonationOrderService donationOrderService;

    @RequestMapping(value = "queryDonationOrderByPage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String queryDonationOrderByPage(@RequestBody Map<String,Integer> map, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            AssertUtils.AssertNotNull(map.get("pageNo"));
            AssertUtils.AssertNotNull(map.get("pageSize"));
            PageHelper.startPage(map.get("pageNo"), map.get("pageSize"));
            List<DonationOrder> donationOrderList = donationOrderService.getDonationOrderByCondition(null);
            PageInfo pageInfo = new PageInfo(DonationOrderConverter.batchModel2Vo(donationOrderList));
            result.setResultObj(pageInfo);
            return result;
        }, AuthEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    @RequestMapping(value = "queryDonationOrderByStatusByPage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String queryDonationOrderByStatusByPage(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            Integer pageNo = (Integer)map.get("pageNo");
            Integer pageSize = (Integer)map.get("pageSize");
            String status = (String)map.get("status");
            AssertUtils.AssertNotNull(pageNo);
            AssertUtils.AssertNotNull(pageSize);
            PageHelper.startPage(pageNo, pageSize);
            List<DonationOrder> donationOrderList = donationOrderService.getDonationOrderByStatus(status);
            PageInfo pageInfo = new PageInfo(DonationOrderConverter.batchModel2Vo(donationOrderList));
            result.setResultObj(pageInfo);
            return result;
        }, AuthEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

//    爱心池展示
    @RequestMapping(value = "queryLovePoolByPage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String queryLovePoolByPage(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            Integer pageNo = (Integer)map.get("pageNo");
            Integer pageSize = (Integer)map.get("pageSize");
            String lovePoolStatus = (String)map.get("lovePoolStatus");
            AssertUtils.AssertNotNull(pageNo);
            AssertUtils.AssertNotNull(pageSize);
            PageHelper.startPage(pageNo, pageSize);
            List<DonationOrder> donationOrderList = donationOrderService.getDonationOrderInLovePool(lovePoolStatus);
            PageInfo pageInfo = new PageInfo(DonationOrderConverter.batchModel2Vo(donationOrderList));
            result.setResultObj(pageInfo);
            return result;
        }, AuthEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    // 修改爱心池状态
    @RequestMapping(value = "updateLovePoolStatus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String updateLovePoolStatus(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            Integer donationOrderId = (Integer)map.get("donationOrderId");
            String lovePoolStatus = (String)map.get("lovePoolStatus");

            List<DonationOrder> listDonationOrder = donationOrderService.getDonationOrderByCondition(
                    new DonationOrderCondition.Builder().donationOrderId(donationOrderId).build());
            AssertUtils.AssertNotNull(listDonationOrder);
            if(listDonationOrder.size()==0) {
                throw new LYSLException("donationOrderId is invalid",  LYSLResultCodeEnum.DATA_INVALID);
            }
            int ans_update = donationOrderService.updateDonationOrderLovePoolStatus(
                    listDonationOrder.get(0), lovePoolStatus);

            result.setResultObj(ans_update==1?"更新成功":"更新失败");
            return result;
        }, AuthEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    @RequestMapping(value="addDonationOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addDonationOrder(@RequestBody @Valid DonationOrderVO donationOrderVO, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            int donation_insert = donationOrderService.insertDonationOrder(DonationOrderConverter.vo2Model(donationOrderVO));
            result.setResultObj(donation_insert==1?"新增成功":"新增失败");
            return result;
        }, AuthEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    @RequestMapping(value="checkDonationOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String checkDonationOrder(@RequestBody Map<String,Object> map, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();

            int donationOrderId = Integer.parseInt(map.get("donationOrderId").toString());
            String status = map.get("status").toString();

            List<DonationOrder> listDonationOrder = donationOrderService.getDonationOrderByCondition(
                    new DonationOrderCondition.Builder().donationOrderId(donationOrderId).build());
            AssertUtils.AssertNotNull(listDonationOrder);
            if(listDonationOrder.size()==0) {
                throw new LYSLException("donationOrderId is invalid",  LYSLResultCodeEnum.DATA_INVALID);
            }
            int update_ans = donationOrderService.check(listDonationOrder.get(0), status);
            // TODO 通知捐赠主体 捐赠审核状态
            // TODO 加入爱心池
            result.setResultObj(update_ans==1? "更新成功":"更新失败");
            return result;
        }, AuthEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }
}
