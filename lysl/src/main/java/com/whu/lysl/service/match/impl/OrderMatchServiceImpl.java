package com.whu.lysl.service.match.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.whu.lysl.base.constants.CacheConstants;
import com.whu.lysl.base.converters.MatchOrderConverter;
import com.whu.lysl.base.enums.*;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.base.utils.KdniaoTrackQueryAPI;
import com.whu.lysl.base.utils.StringUtils;
import com.whu.lysl.dao.MatchOrderDAO;
import com.whu.lysl.entity.condition.DemandCondition;
import com.whu.lysl.entity.condition.DonationOrderCondition;
import com.whu.lysl.entity.condition.InstCondition;
import com.whu.lysl.entity.condition.MatchOrderCondition;
import com.whu.lysl.entity.dbobj.DemandDO;
import com.whu.lysl.entity.dbobj.MatchOrderDo;

import com.whu.lysl.entity.dbobj.UserDO;
import com.whu.lysl.entity.dto.*;
import com.whu.lysl.entity.vo.DemandVO;
import com.whu.lysl.service.cache.CacheService;
import com.whu.lysl.service.demand.DemandService;

import com.whu.lysl.entity.dto.DonationOrder;
import com.whu.lysl.entity.dto.ExpressInfo;
import com.whu.lysl.entity.dto.LogisticInfo;
import com.whu.lysl.entity.dto.MatchOrder;
import com.whu.lysl.service.cache.CacheService;

import com.whu.lysl.service.donation.DonationOrderService;
import com.whu.lysl.service.institution.InstitutionService;
import com.whu.lysl.service.match.OrderMatchService;
import com.whu.lysl.service.notice.NoticeService;
import com.whu.lysl.service.system.SystemService;
import com.whu.lysl.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jws.soap.SOAPBinding;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 订单匹配的具体实现类
 * @author Jzh
 * @since 2020-02-09 19:53
 **/
@Service
@Slf4j
public class OrderMatchServiceImpl implements OrderMatchService {

    @Resource
    private MatchOrderDAO matchOrderDAO;
    @Resource
    private InstitutionService institutionService;
    @Resource
    private DonationOrderService donationOrderService;
    @Resource
    private CacheService cacheService;
    @Resource
    private DemandService demandService;
    @Resource
    private UserService userService;
    @Resource
    private NoticeService noticeService;
    @Resource
    private SystemService systemService;

    /**
     * 定向捐赠后的匹配接口（在人工审核后调用）
     * @param matchOrder 匹配单
     * @throws LYSLException 主要是参数异常
     */
    @Override
    @Transactional
    public void saveMatchOrder(MatchOrder matchOrder) throws LYSLException {


        DonationOrderCondition donationOrderCondition = new DonationOrderCondition.Builder().donationOrderId(matchOrder.getDonationOrderId()).build();

        List<DonationOrder> donationOrderList = donationOrderService.getDonationOrderByCondition(donationOrderCondition);

        if (donationOrderList.size() == 0){
            throw new LYSLException("捐赠单不存在",LYSLResultCodeEnum.DATA_INVALID);
        }
        DonationOrder donationOrder = donationOrderList.get(0);
        if (!donationOrder.getDonationType().equals(matchOrder.getDonationType())){
            throw new LYSLException("捐赠单类型不匹配",LYSLResultCodeEnum.DATA_INVALID);
        }

        // 如果是非定向捐赠，需要保证其不再匹配池中
        if(donationOrder.getDonationType().equals(DonationTypeEnum.UNDIRECTED.getCode())){
            if(!donationOrder.getLovePoolStatus().equals(LovePoolStatusEnum.IN_POOL.getCode())){
                throw new LYSLException("该捐赠单不在爱心池中，请刷新后重试",LYSLResultCodeEnum.DATA_INVALID);
            }
        }
        else{// 如果是定向捐赠，需要其处于未匹配状态
            if(!donationOrder.getDirectedStatus().equals(DirectedStatusEnum.UNFINISHED.getCode())){
                throw new LYSLException("这个捐赠单无法进行匹配，请刷新后重试",LYSLResultCodeEnum.DATA_INVALID);
            }
        }

        // 设置捐赠相关参数
        User donor = userService.getUserById(donationOrderList.get(0).getDonorId());
        String donorPhone = donor.getPhone();
        matchOrder.setDonorPhone(donorPhone);
        matchOrder.setDonorName(donor.getName());
        matchOrder.setDonorId(donor.getId());
        matchOrder.setMaterial(donationOrder.getMaterialOrderList());

        // 查询需求模块的相关信息
        List<DemandDO> demandDOList = demandService.getDemandsByCondition(new DemandCondition.Builder().demandId(String.valueOf(matchOrder.getDemandOrderId())).build());
       if(demandDOList == null || demandDOList.size() ==0){
           throw new LYSLException("需求单不存在",LYSLResultCodeEnum.DATA_INVALID);
       }
       DemandDO demandDO = demandDOList.get(0);
       Institution institution = institutionService.getInstsByCondition(new InstCondition.Builder().id(demandDO.getInstitutionId()).build()).get(0);
       matchOrder.setDoneeId(demandDO.getDoneeId());
       matchOrder.setDoneeName(institution.getName());

        // 将DTO转换成DO，同时进行参数检查
        List<MatchOrderDo> matchOrderDoList = MatchOrderConverter.Model2DO(matchOrder);


        for (int i = 0;i< matchOrderDoList.size();i++){
            MatchOrderDo matchOrderDo = matchOrderDoList.get(i);
            // 调用DAO将数据存入数据库
            matchOrderDAO.saveMatchOrder(matchOrderDo);

        }
        matchOrder.setId(matchOrderDoList.get(0).getId());

        // 保存相关信息至缓存中，后期展示用
        String hashStr = createHashByMatchOrder(matchOrder);
        // 生成链接地址
        String notificationHttp = "http://47.113.115.120:8080/#/pages/wuliu_status/wuliu_status?hashCode=" + hashStr;
        // 获取运营人员电话号码
        Map<String,String> customer = systemService.getCustomerServiceStaff();
        Collection values = customer.values();    //获取Map集合的value集合
        String phone = "";
        for (Object object : values) {
            phone = object.toString();
        }

        // 根据不同的捐赠方式，发不同的短信
        if(donationOrder.getDonationType().equals(DonationTypeEnum.UNDIRECTED.getCode())){
            // 修改捐赠单状态
            donationOrderService.updateDonationOrderLovePoolStatus(donationOrder,LovePoolStatusEnum.ARTI_DISPATCHED.getCode());
            noticeService.sendSingleMessage(LYSLMessageEnum.UNDIRECT_DONATION,donorPhone,donor.getName(),
                    String.valueOf(donationOrder.getDonationOrderId()),institution.getName(),institution.getAddress(),notificationHttp,phone); /** 姓名，受捐机构，捐赠单物资，更新物流信息链接，运营电话 */
        }
        else{
            // 如果是定向捐赠，需要其处于未匹配状态
            donationOrderService.updateDonationOrderDirectedStatus(donationOrder,DirectedStatusEnum.FINISHED.getCode());
            noticeService.sendSingleMessage(LYSLMessageEnum.DONOR_SHIP,donorPhone,donor.getName(),matchOrder.getDoneeName(),matchOrder.getMaterialStrList(),notificationHttp,""); /** 姓名，受捐机构，捐赠单物资，更新物流信息链接，运营电话 */
        }


    }

    /**
     * 根据捐赠者名字查询匹配单
     * @param donorId
     * @return
     * @throws LYSLException
     */
    @Override
    public List<MatchOrder> getMatchOrderByDonorId(int donorId) throws LYSLException {
        List<Integer> donationOrderIdList = matchOrderDAO.selectDonationOrderIdByDonorId(donorId);
        List<MatchOrder> matchOrderList = new ArrayList<>();
        for (int i =0;i<donationOrderIdList.size();i++){

            List<MatchOrderDo> matchOrderDoList = matchOrderDAO.selectByDonorIdAndDonationOrderId(donorId,donationOrderIdList.get(i));
            MatchOrder matchOrder = MatchOrderConverter.DO2Model(matchOrderDoList);
            matchOrderList.add(matchOrder);
        }


        return matchOrderList;

    }

    /**
     * 根据受赠者名字查询匹配单
     * @param doneeId 受赠者名字
     * @return
     * @throws LYSLException
     */
    @Override
    public List<MatchOrder> getMatchOrderByDoneeId(int doneeId) throws LYSLException {
        List<Integer> demandOrderIdList = matchOrderDAO.selectDemandOrderIdByDoneeId(doneeId);
        List<MatchOrder> matchOrderList = new ArrayList<>();
        for (int i =0;i<demandOrderIdList.size();i++){

            List<MatchOrderDo> matchOrderDoList = matchOrderDAO.selectByDoneeIdAndDonationOrderId(doneeId,demandOrderIdList.get(i));
            MatchOrder matchOrder = MatchOrderConverter.DO2Model(matchOrderDoList);
            matchOrderList.add(matchOrder);
        }


        return matchOrderList;
    }

    /**
     * 更新匹配单状态
     * @param matchOrderId
     * @param status
     * @throws LYSLException
     */
    @Override
    public void updateMatchOrderStatus(int matchOrderId, String status) throws LYSLException {

        if (MatchingStatusEnum.getEnumByCode(status) == null){
            throw new LYSLException("状态值不存在",LYSLResultCodeEnum.DATA_INVALID);
        }

        matchOrderDAO.updateStatus(matchOrderId,status);

    }

    /**
     * 确认收货
     * @param matchOrderId
     * @throws LYSLException
     */
    @Override
    public void confirmReceipt(int matchOrderId) throws LYSLException {
        if (matchOrderId <= 0){
            throw new LYSLException("matchOrderId不能为空",LYSLResultCodeEnum.DATA_INVALID);
        }
        String status = matchOrderDAO.selectMatchingStatus(matchOrderId);
        if(status == null){
            throw new LYSLException("未找到这个匹配单",LYSLResultCodeEnum.DATA_INVALID);
        }
        MatchingStatusEnum matchingStatusEnum = MatchingStatusEnum.getEnumByCode(status);
        if (matchingStatusEnum.equals(MatchingStatusEnum.CHECKEDFAILED) || matchingStatusEnum.equals(MatchingStatusEnum.UNCHECKED) ||
        matchingStatusEnum.equals(MatchingStatusEnum.DELIVERED)){
            String error = "这个匹配单的状态为"+ matchingStatusEnum.getDescription() + ",不支持修改确认收货状态";
            throw new LYSLException(error,LYSLResultCodeEnum.DATA_INVALID);
        }
        matchOrderDAO.updateStatus(matchOrderId,MatchingStatusEnum.DELIVERED.getCode());

    }

    /**
     * 更新物流信息
     * @param matchOrderId
     * @param logisticCode
     * @param remark
     * @throws LYSLException
     */
    @Override
    public void updateTrackingNumber(int matchOrderId,String logisticCode,String remark,String picList) throws LYSLException {
//        String result = "";
        if (matchOrderId <= 0){
            throw new LYSLException("matchOrder不能为空",LYSLResultCodeEnum.DATA_INVALID);
        }
        try {
            if (!StringUtils.isNotEmpty(logisticCode)){
                matchOrderDAO.updateLogisticInfo(matchOrderId,null,null,remark,null);
                return;
            }
            KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
            String result = api.identifyOrder(logisticCode);
            IdentifyOrderResponse identifyOrderResponse = JSON.parseObject(result,IdentifyOrderResponse.class);
            if (identifyOrderResponse != null && identifyOrderResponse.getShippers() != null
                    && identifyOrderResponse.getShippers().size() != 0) {
                IdentifyOrderResponse.Shipper shipper = identifyOrderResponse.getShippers().get(0);
                matchOrderDAO.updateLogisticInfo(matchOrderId, shipper.getShipperCode(), logisticCode, remark, picList);

                MatchOrderCondition matchOrderCondition = new MatchOrderCondition();
                matchOrderCondition.setId(matchOrderId);
                List<MatchOrder> matchOrders = getMatchOrderList(matchOrderCondition);
                if (matchOrders.size() != 0) {
                    MatchOrder matchOrder = matchOrders.get(0);
                    String hashStr = createHashByMatchOrder(matchOrder);
                    // 生成链接地址
                    String notificationHttp = "http://47.113.115.120:8080/#/pages/add_logistics/add_logistics?hashCode=" + hashStr;
                    User user = userService.getUserById(matchOrder.getDoneeId());
                    // 获取运营人员电话号码
                    Map<String,String> customer = systemService.getCustomerServiceStaff();
                    Collection values = customer.values();    //获取Map集合的value集合
                    String phone = "";
                    for (Object object : values) {
                        phone = object.toString();
                    }
                    // 发放收货通知
                    noticeService.sendSingleMessage(LYSLMessageEnum.DONEE_RECEIVE,user.getPhone(),matchOrder.getDonorName(),notificationHttp,phone);
                }



                log.info(matchOrderId + "匹配单，更新物流信息成功; " + logisticCode);
            } else {
                throw new LYSLException("物流单号查询失败", LYSLResultCodeEnum.DATA_INVALID);
            }
        }
        catch (LYSLException e){
            throw e;
        }
        catch (Exception e) {
            throw new LYSLException("验证物流单号接口调用失败",LYSLResultCodeEnum.SYSTEM_ERROR);
        }


    }

    /**
     * 根据状态，捐赠人Id，受赠人Id等查询匹配单
     * @param matchOrderCondition
     * @return
     */
    @Override
    public List<MatchOrder> getMatchOrderList(MatchOrderCondition matchOrderCondition) {

        List<MatchOrder> matchOrderList = new ArrayList<>();
        List<MatchOrderCondition> matchOrderConditionList = new ArrayList<>();
        if (matchOrderCondition.isAllNull()){
            matchOrderConditionList = matchOrderDAO.selectAllMatchOrder();
        }
        else{
            matchOrderConditionList = matchOrderDAO.getMatchOrderGroupList(matchOrderCondition);
        }


        for (int i =0;i<matchOrderConditionList.size();i++){

            List<MatchOrderDo> matchOrderDoList = matchOrderDAO.selectByDoneeIdAndDonationOrderIdAndDonorIdAndDoneeId(matchOrderConditionList.get(i));
            MatchOrder matchOrder = MatchOrderConverter.DO2Model(matchOrderDoList);
            User user = userService.getUserById(matchOrder.getDonorId());
            matchOrder.setDonorPhone(user.getPhone());
            matchOrderList.add(matchOrder);
        }


        return matchOrderList;
    }

    /**
     * 根据物流单号调取api获取物流状态
     * @param shipperCode
     * @param trackingNumber
     * @return
     */
    @Override
    public ExpressInfo getTracingByExpressInfo(String shipperCode,String trackingNumber) {
        KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
        String result = "";
        try {
            result = api.getOrderTracesByJson(shipperCode, trackingNumber);
            ExpressInfo expressInfo = JSON.parseObject(result,ExpressInfo.class);
            return expressInfo;
        } catch (Exception e) {
            throw new LYSLException("查询物流单号接口调用失败",LYSLResultCodeEnum.SYSTEM_ERROR);
        }

    }

    /**
     * 从缓冲中获取物流单状态
     * @param shipperCode
     * @param logisticCode
     * @return
     */
    @Override
    public ExpressInfo getTracingByExpressInfoFromRedis(String shipperCode, String logisticCode){
        LogisticInfo logisticInfo = new LogisticInfo(shipperCode,logisticCode);
        ExpressInfo expressInfo = (ExpressInfo) cacheService.selectByKey("EXPRESSINFO",logisticInfo.getKey(),ExpressInfo.class);
        if(expressInfo == null){
            expressInfo = getTracingByExpressInfo(shipperCode,logisticCode);
        }
        return  expressInfo;
    }

    @Override
    public String createHashByMatchOrder(MatchOrder matchOrder) {
        InstAndMaterialInfo instAndMaterialInfo = new InstAndMaterialInfo(matchOrder.getId(),
                matchOrder.getMaterialNameList(), matchOrder.getMaterialQuantityList());

        instAndMaterialInfo.setDonorName(matchOrder.getDonorName());
        instAndMaterialInfo.setDonorPhone(matchOrder.getDonorPhone());
        instAndMaterialInfo.setLogisticCode(matchOrder.getLogisticCode());
        instAndMaterialInfo.setShipperCode(matchOrder.getShipperCode());
        instAndMaterialInfo.setStatus(matchOrder.getStatus());

        List<DemandDO> demandDOS = demandService.getDemandsByCondition(new DemandCondition.Builder()
                .demandId(String.valueOf(matchOrder.getDemandOrderId())).build());
        if (demandDOS.size() == 0) {
            throw new LYSLException("该需求单不存在", LYSLResultCodeEnum.DATA_INVALID);
        }
        Institution institution = institutionService.getInstsByCondition(new InstCondition.Builder().
                id(demandDOS.get(0).getInstitutionId()).build()).get(0);

        User user = userService.getUserById(demandDOS.get(0).getDoneeId());

        instAndMaterialInfo.setAddress(institution.getAddress());
        instAndMaterialInfo.setInstName(institution.getName());
        instAndMaterialInfo.setRecipient(user.getName());
        instAndMaterialInfo.setTel(user.getPhone());

        String hashStr = String.valueOf(instAndMaterialInfo.hashCode());
        cacheService.addByKey(CacheConstants.SUPPLYLOGISTICINFO, hashStr, instAndMaterialInfo, 0);

        log.info("生成匹配信息缓存，hash 值为：" + hashStr);
        return hashStr;
    }

    @Override
    public InstAndMaterialInfo getInstAndMaterialInfoByHash(String hashStr) {
        InstAndMaterialInfo instAndMaterialInfo = (InstAndMaterialInfo) cacheService.selectByKey(CacheConstants.SUPPLYLOGISTICINFO, hashStr, InstAndMaterialInfo.class);
        if (instAndMaterialInfo == null) {
            throw new LYSLException("未查询到相关信息，请确认hash是否有误", LYSLResultCodeEnum.DATA_INVALID);
        }
        return instAndMaterialInfo;
    }


}
