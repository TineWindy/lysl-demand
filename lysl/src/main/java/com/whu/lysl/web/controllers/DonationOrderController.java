package com.whu.lysl.web.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.whu.lysl.base.converters.DonationOrderConverter;
import com.whu.lysl.base.enums.*;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.base.utils.AssertUtils;
import com.whu.lysl.entity.condition.DonationOrderCondition;
import com.whu.lysl.entity.condition.MatchOrderCondition;
import com.whu.lysl.entity.dto.DonationOrder;
import com.whu.lysl.entity.dto.MatchOrder;
import com.whu.lysl.entity.dto.MaterialOrder;
import com.whu.lysl.entity.dto.User;
import com.whu.lysl.entity.vo.DonationOrderVO;
import com.whu.lysl.service.donation.DonationOrderService;
import com.whu.lysl.service.match.OrderMatchService;
import com.whu.lysl.service.notice.NoticeService;
import com.whu.lysl.service.system.SystemService;
import com.whu.lysl.service.user.UserService;
import com.whu.lysl.web.LYSLBaseController;
import com.whu.lysl.web.LYSLResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author Caspar
 * @CreateTime 2020/2/9 15:45
 * @Description:
 */

@RestController
@RequestMapping("donationOrder")
public class DonationOrderController extends LYSLBaseController {

    @Resource
    private DonationOrderService donationOrderService;
    @Resource
    private UserService userService;
    @Resource
    private OrderMatchService orderMatchService;
    @Resource
    private NoticeService noticeService;
    @Resource
    private SystemService systemService;

    @RequestMapping(value = "queryDonationOrderByPage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDonationOrderByPage(@RequestBody Map<String,Integer> map, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            AssertUtils.AssertNotNull(map.get("pageNo"), "pageNo is null");
            AssertUtils.AssertNotNull(map.get("pageSize"), "pageSize is null");
            PageHelper.startPage(map.get("pageNo"), map.get("pageSize"));
            List<DonationOrder> donationOrderList = donationOrderService.getDonationOrderByCondition(null);
            PageInfo pageInfo = new PageInfo(DonationOrderConverter.batchModel2Vo(donationOrderList));
            result.setResultObj(pageInfo);
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    @GetMapping(value = "queryDonationOrderByStatusByPage")
    public String queryDonationOrderByStatusByPage(HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
//            Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
//            Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
            String status = request.getParameter("status");
//            AssertUtils.AssertNotNull(pageNo);
//            AssertUtils.AssertNotNull(pageSize);
//            PageHelper.startPage(pageNo, pageSize);
            List<DonationOrder> donationOrderList = donationOrderService.getDonationOrderByStatus(status);
            List<DonationOrderVO> donationOrderVOS =  DonationOrderConverter.batchModel2Vo(donationOrderList);

            result.setCount(donationOrderVOS.size());
            result.setResultObj(donationOrderVOS);
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode(), BaseControllerEnum.BACK_MANAGE.getCode());

        return JSON.toJSONString(res);
    }

    @RequestMapping(value = "queryDonationOrderByDonorIdByPage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String queryDonationOrderByDonorIdByPage(@RequestBody Map<String, Integer> map, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            AssertUtils.AssertNotNull(map.get("pageNo"));
            AssertUtils.AssertNotNull(map.get("pageSize"));
            AssertUtils.AssertNotNull(map.get("donorId"));
            PageHelper.startPage(map.get("pageNo"), map.get("pageSize"));
            List<DonationOrder> donationOrderList = donationOrderService.getDonationOrderByDonorId(map.get("donorId"));
            PageInfo pageInfo = new PageInfo(DonationOrderConverter.batchModel2Vo(donationOrderList));
            result.setResultObj(pageInfo);
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

//    爱心池展示
    @GetMapping(value = "queryLovePoolByPage")
    public String queryLovePoolByPage(HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            int pageNo = Integer.parseInt(request.getParameter("pageNo"));
            int pageSize = Integer.parseInt(request.getParameter("pageSize"));
            String lovePoolStatus = request.getParameter("lovePoolStatus");
            if (pageNo > 0 && pageSize > 0) {
                PageHelper.startPage(pageNo, pageSize);
            }
            List<DonationOrder> donationOrderList = donationOrderService.getDonationOrderInLovePool(lovePoolStatus);
            List<DonationOrderVO> donationOrderVOS = DonationOrderConverter.batchModel2Vo(donationOrderList);

            result.setCount(donationOrderVOS.size());
            result.setResultObj(donationOrderVOS);
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode(), BaseControllerEnum.BACK_MANAGE.getCode());

        return JSON.toJSONString(res);
    }

    // 修改爱心池状态
    @RequestMapping(value = "updateLovePoolStatus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
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
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    // 修改定向捐赠订单状态
    @RequestMapping(value = "updateDirectedStatus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String updateDirectedStatus(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            Integer donationOrderId = (Integer)map.get("donationOrderId");
            String directedStatus = (String)map.get("directedStatus");

            List<DonationOrder> listDonationOrder = donationOrderService.getDonationOrderByCondition(
                    new DonationOrderCondition.Builder().donationOrderId(donationOrderId).build());
            AssertUtils.AssertNotNull(listDonationOrder);
            if(listDonationOrder.size()==0) {
                throw new LYSLException("donationOrderId is invalid",  LYSLResultCodeEnum.DATA_INVALID);
            }
            int ans_update = donationOrderService.updateDonationOrderDirectedStatus(
                    listDonationOrder.get(0), directedStatus);

            result.setResultObj(ans_update==1?"更新成功":"更新失败");
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

//
//    @Transactional
//    @RequestMapping(value="addDonationOrderList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    @ResponseBody
//    public String addDonationOrderList(@RequestBody @Valid DonationOrderListVO donationOrderListVO, HttpServletRequest request) {
//        LYSLResult<Object> res = protectController(request, () -> {
//            LYSLResult<Object> result = new LYSLResult<>();
//            List<DonationOrderVO> donationOrderVOList = DonationOrderConverter.donationOrderList2ListDonationOrder(donationOrderListVO);
//            List<Integer> insert_list_ans = new ArrayList();
//            for (DonationOrderVO donationOrderVO : donationOrderVOList) {
//                int donation_insert = donationOrderService.insertDonationOrder(DonationOrderConverter.vo2Model(donationOrderVO));
//                insert_list_ans.add(donation_insert);
//            }
//            boolean isSuccess = true;
//            for (Integer i : insert_list_ans) {
//                if (i!=1) {
//                    isSuccess=false;
//                }
//            }
//                result.setResultObj(isSuccess?"提交捐赠单成功":"提交捐赠单失败");
//            return result;
//        }, AuthEnum.IGNORE_VERIFY.getCode());
//
//        return JSON.toJSONString(res);
//    }

    @GetMapping(value="checkDonationOrder")
    public String checkDonationOrder(HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();

            int donationOrderId = Integer.parseInt(request.getParameter("donationOrderId"));
            String status = request.getParameter("status");

            List<DonationOrder> listDonationOrder = donationOrderService.getDonationOrderByCondition(
                    new DonationOrderCondition.Builder().donationOrderId(donationOrderId).build());
            if (listDonationOrder == null || listDonationOrder.size() == 0) {
                throw new LYSLException("Can't find the order with this donationOrderId", LYSLResultCodeEnum.DATA_INVALID);
            }
            int update_ans = donationOrderService.check(listDonationOrder.get(0), status);

            if (update_ans==1) {
                DonationOrder donationOrder = listDonationOrder.get(0);
                Map<String, String> map_customer = systemService.getCustomerServiceStaff();
                User user = userService.getUserById(donationOrder.getDonorId());
                if (status.equals(OrderStatusEnum.APPROVED.getCode())) {
                    // 审核通过, 告知捐赠人发货
//                    noticeService.sendSingleMessage(LYSLMessageEnum.DONOR_SHIP
//                            , user.getPhone()
////                            , "15927434600" //test
//                            , donationOrder.getDonorName()
//                            , donationOrder.getDoneeName()
//                            , donationOrder.getMaterialListToString()
//                            , "更新物流信息链接"
//                            , map_customer.values().toArray()[0].toString());

                    if (donationOrder.getDonationType().equals(DonationTypeEnum.UNDIRECTED.getCode())) {
                        // 捐赠订单加入爱心池
                        noticeService.sendSingleMessage(LYSLMessageEnum.IN_LOVE_POOL
                                , map_customer.values().toArray()[0].toString()
//                                , "15927434600" //test
                                , donationOrder.getMaterialListToString()
                                , "后台管理系统");
                    }
                } else if (status.equals(OrderStatusEnum.DISAPPROVED.getCode())) {
                    // 审核不通过, 告知捐赠人审核不通过原因
                    noticeService.sendSingleMessage(LYSLMessageEnum.DONOR_DISAPPROVED
                            , user.getPhone()
//                            , "15927434600" //test
                            , donationOrder.getDonorName()
                            , donationOrder.getMaterialListToString()
                            , "审核未通过原因"
                            , "后台管理系统"
                            , map_customer.values().toArray()[0].toString());
                }

            } else {

            }

            if (update_ans==1) {
                DonationOrder donationOrder = listDonationOrder.get(0);
                Map<String, String> map_customer = systemService.getCustomerServiceStaff();
                User user = userService.getUserById(donationOrder.getDonorId());
                if (status.equals(OrderStatusEnum.APPROVED.getCode())) {
                    // 审核通过, 告知捐赠人发货
                    noticeService.sendSingleMessage(LYSLMessageEnum.DONOR_SHIP
                            , user.getPhone()
//                            , "15927434600" //test
                            , donationOrder.getDonorName()
                            , donationOrder.getDoneeName()
                            , donationOrder.getMaterialListToString()
                            , "更新物流信息链接"
                            , map_customer.values().toArray()[0].toString());

                    if (donationOrder.getDonationType().equals(DonationTypeEnum.UNDIRECTED.getCode())) {
                        // 捐赠订单加入爱心池
                        noticeService.sendSingleMessage(LYSLMessageEnum.IN_LOVE_POOL
                                , map_customer.values().toArray()[0].toString()
//                                , "15927434600" //test
                                , donationOrder.getMaterialListToString()
                                , "后台管理系统");
                    }
                } else if (status.equals(OrderStatusEnum.DISAPPROVED.getCode())) {
                    // 审核不通过, 告知捐赠人审核不通过原因
                    noticeService.sendSingleMessage(LYSLMessageEnum.DONOR_DISAPPROVED
                            , user.getPhone()
//                            , "15927434600" //test
                            , donationOrder.getDonorName()
                            , donationOrder.getMaterialListToString()
                            , "审核未通过原因"
                            , "后台管理系统"
                            , map_customer.values().toArray()[0].toString());
                }

            } else {

            }

            result.setResultObj(update_ans == 1 ? "更新成功" : "更新失败");
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }


    @RequestMapping(value="addDonationOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String addDonationOrder(@RequestBody String requestStr, HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();
            JSONObject requestBody = JSON.parseObject(requestStr);
            DonationOrderVO donationOrderVO = requestBody.getObject("donationOrder", DonationOrderVO.class);
            AssertUtils.AssertNotNull(donationOrderVO, "donationOrder is null");
            User user = requestBody.getObject("user", User.class);
            AssertUtils.AssertNotNull(user, "user is null");

            int userId = userService.addAnUser(user);
            AssertUtils.AssertNotNull(userId, "user 插入失败！");
            donationOrderVO.setUserId(userId);
            donationOrderVO.setDonorId(userId);
            donationOrderVO.setDonorName(user.getName());

            DonationOrder donationOrder = DonationOrderConverter.vo2Model(donationOrderVO);
            int donationOrderId = donationOrderService.insertDonationOrderDetail(donationOrder);

            // 通知人工审核
            Map<String, String> map_customer = systemService.getCustomerServiceStaff();
            noticeService.sendSingleMessage(LYSLMessageEnum.DONATION_CHECK
                            , map_customer.values().toArray()[0].toString()
//                            , "15927434600" //test
                            , donationOrder.getDonorName()
                            , "捐赠单号：" + String.valueOf(donationOrderId)
                            , "后台管理系统");

            result.setResultObj("捐赠清单ID: "+donationOrderId);
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    /**
     * 获取 donation list
     * @param request request
     * @return json str
     */
    @GetMapping("getDonationList")
    public String getDonationList(HttpServletRequest request) {
        LYSLResult<Object> res = protectController(request, () -> {
            LYSLResult<Object> result = new LYSLResult<>();

            int pageNo = Integer.parseInt(request.getParameter("pageNo"));
            int pageSize = Integer.parseInt(request.getParameter("pageSize"));

            List<DonationOrder> donationOrderList = donationOrderService.getDonationOrderInLovePool(
                    LovePoolStatusEnum.IN_POOL.getCode());
            List<MatchOrder> matchOrderList = orderMatchService.getMatchOrderList(new MatchOrderCondition());
            matchOrderList.addAll(generateMathordersByDonations(donationOrderList));

            matchOrderList.sort((MatchOrder o1, MatchOrder o2) -> {
                if (o1.getGmtCreated().getTime() > o2.getGmtCreated().getTime()) {
                    return 1;
                }
                return -1;
            });

            int fromIndex = (pageNo * pageSize) > matchOrderList.size() ?
                    matchOrderList.size() : (pageNo * pageSize);
            int toIndex = (pageSize * (pageNo + 1)) > matchOrderList.size() ?
                    matchOrderList.size() : (pageSize * (pageNo + 1));

            result.setResultObj(matchOrderList.subList(fromIndex, toIndex));
            return result;
        }, BaseControllerEnum.IGNORE_VERIFY.getCode());

        return JSON.toJSONString(res);
    }

    /**
     * 将捐赠单转换为匹配单
     * @param donationOrders 捐赠单
     * @return 匹配单
     */
    private List<MatchOrder> generateMathordersByDonations(List<DonationOrder> donationOrders) {
        List<MatchOrder> matchOrders = new ArrayList<>();

        if (donationOrders != null) {
            for (DonationOrder donationOrder: donationOrders) {
                MatchOrder matchOrder = new MatchOrder();

                matchOrder.setGmtCreated(donationOrder.getGmtCreated());
                matchOrder.setDonorName(donationOrder.getDonorName());
                matchOrder.setStatus("LOVE_POOL");
                matchOrder.setDonationOrderId(donationOrder.getDonationOrderId());

                if (donationOrder.getMaterialOrderList() != null) {
                    List<Integer> materialQuantityList = new ArrayList<>();
                    List<String > materialNameList = new ArrayList<>();
                    for (MaterialOrder materialOrder: donationOrder.getMaterialOrderList()) {
                        materialQuantityList.add(materialOrder.getMaterialAmount());
                        materialNameList.add(materialOrder.getMaterialName());
                    }
                    matchOrder.setMaterialQuantityList(materialQuantityList);
                    matchOrder.setMaterialNameList(materialNameList);
                }

                User user = userService.getUserById(donationOrder.getDonorId());
                matchOrder.setDonorPhone(user.getPhone());

                matchOrders.add(matchOrder);
            }
        }

        return matchOrders;
    }

}
