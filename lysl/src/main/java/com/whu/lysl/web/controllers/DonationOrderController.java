package com.whu.lysl.web.controllers;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whu.lysl.base.converters.DonationOrderConverter;
import com.whu.lysl.base.enums.DonationOrderStatusEnum;
import com.whu.lysl.base.enums.LYSLResultCodeEnum;
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

    @RequestMapping("queryDonationOrderByPage")
    @ResponseBody
    public String queryDonationOrderByPage(@RequestBody Map<String,Integer> map, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            // TODO 参数类型检查
            PageHelper.startPage(map.get("pageNo"), map.get("pageSize"));
            List<DonationOrder> donationOrderList = donationOrderService.getDonationOrderByCondition(null);
            PageInfo pageInfo = new PageInfo(DonationOrderConverter.batchModel2Vo(donationOrderList));
            result.setResultObj(pageInfo);
            return result;
        }, AuthEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    @RequestMapping(value="addDonationOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addDonationOrder(@RequestBody @Valid DonationOrderVO donationOrderVO, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            // 根据捐赠类型定向与非定向，判断捐赠对象字段
            AssertUtils.AssertNotNull(donationOrderVO.getDoneeId());
            if (donationOrderVO.getDonationType()==1 ) {
                if(donationOrderVO.getDoneeId()!=-1)
                throw new LYSLException("非定向捐赠，doneeId 应当为-1", LYSLResultCodeEnum.DATA_INVALID);
            } else {
                AssertUtils.StringNotEmpty(donationOrderVO.getDoneeName());
                // TODO 检查受捐赠主体
                if(donationOrderVO.getDoneeId()<=0)
                    throw new LYSLException("定向捐赠，doneeId 应当为捐赠对象id", LYSLResultCodeEnum.DATA_INVALID);
            }
            // TODO 检查捐赠主主体，物资

            int donation_insert = donationOrderService.insertDonationOrder(DonationOrderConverter.vo2Model(donationOrderVO));
            result.setResultObj(donation_insert==1?"插入成功":"插入失败");
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
            AssertUtils.AssertNotNull(donationOrderId);
            AssertUtils.AssertNotNull(status);
            if (!EnumUtils.isValidEnum(DonationOrderStatusEnum.class, status)) {
                throw new LYSLException("Status 不属于支持的枚举值", LYSLResultCodeEnum.DATA_INVALID);
            }

            List<DonationOrder> listDonationOrder = donationOrderService.getDonationOrderByCondition(
                    new DonationOrderCondition.Builder().donationOrderId(donationOrderId).build());
            if(listDonationOrder.size()==0) {
                throw new LYSLException("donationOrderId is invalid",  LYSLResultCodeEnum.DATA_INVALID);
            }
            int update_ans=0;
            if (StringUtils.equal(status, DonationOrderStatusEnum.APPROVED.getCode())) {
                update_ans = donationOrderService.checkPass(listDonationOrder.get(0));
            } else if (StringUtils.equal(status, DonationOrderStatusEnum.DISAPPROVED.getCode())){
                update_ans = donationOrderService.checkFail(listDonationOrder.get(0));
            } else {
                throw new LYSLException("status must be DISAPPROVED or APPROVED", LYSLResultCodeEnum.DATA_INVALID);
            }
            // TODO 通知捐赠主体 捐赠审核状态
            result.setResultObj(update_ans==1? "更新成功":"更新失败");
            return result;
        }, AuthEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }
}
