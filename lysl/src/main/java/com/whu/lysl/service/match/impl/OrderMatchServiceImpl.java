package com.whu.lysl.service.match.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.whu.lysl.base.converters.MatchOrderConverter;
import com.whu.lysl.base.enums.DonationTypeEnum;
import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.enums.MatchingMethodEnum;
import com.whu.lysl.base.enums.MatchingStatusEnum;
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
import com.whu.lysl.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单匹配的具体实现类
 * @author Jzh
 * @since 2020-02-09 19:53
 **/
@Service
public class OrderMatchServiceImpl implements OrderMatchService {

    @Resource
    MatchOrderDAO matchOrderDAO;
    @Resource
    InstitutionService institutionService;
    @Resource
    DonationOrderService donationOrderService;
    @Resource
    CacheService cacheService;
    @Resource
    DemandService demandService;
    @Resource
    UserService userService;

    /**
     * 定向捐赠后的匹配接口（在人工审核后调用）
     * @param matchOrder 匹配单
     * @throws LYSLException 主要是参数异常
     */
    @Override
    public void saveMatchOrder(MatchOrder matchOrder) throws LYSLException {
        // 将DTO转换成DO，同时进行参数检查
        List<MatchOrderDo> matchOrderDoList = MatchOrderConverter.Model2DO(matchOrder);

        DonationOrderCondition donationOrderCondition = new DonationOrderCondition.Builder().donationOrderId(matchOrder.getDonationOrderId()).build();

        List<DonationOrder> donationOrderList = donationOrderService.getDonationOrderByCondition(donationOrderCondition);
        if (donationOrderList.size() == 0){
            throw new LYSLException("捐赠单不存在",LYSLResultCodeEnum.DATA_INVALID);
        }
        if (!donationOrderList.get(0).getDonationType().equals(matchOrder.getDonationType())){
            throw new LYSLException("捐赠单类型不匹配",LYSLResultCodeEnum.DATA_INVALID);
        }
        // TODO 去需求模块查询需求是否存在
        for (int i = 0;i< matchOrderDoList.size();i++){
            MatchOrderDo matchOrderDo = matchOrderDoList.get(i);
            // 调用DAO将数据存入数据库
            matchOrderDAO.saveMatchOrder(matchOrderDo);

        }
        matchOrder.setId(matchOrderDoList.get(0).getId());
        // 保存相关信息至redis中，后期展示用
        String hashStr = createHashByMatchOrder(matchOrder);
        System.out.println(hashStr);
        // TODO 发送短信

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
     * 更新物流信息
     * @param matchOrderId
     * @param logisticCode
     * @param remark
     * @throws LYSLException
     */
    @Override

    public void updateTrackingNumber(int matchOrderId,String logisticCode,String remark,String picList) throws LYSLException {
        String result = "";
        try {
            if (!StringUtils.isNotEmpty(logisticCode)){
                matchOrderDAO.updateLogisticInfo(matchOrderId,null,null,remark,null);
                return;
            }
            KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
            result = api.identifyOrder(logisticCode);
            IdentifyOrderResponse identifyOrderResponse = JSON.parseObject(result,IdentifyOrderResponse.class);
            if(identifyOrderResponse != null && identifyOrderResponse.getShippers() != null && identifyOrderResponse.getShippers().size() != 0){
                IdentifyOrderResponse.Shipper shipper = identifyOrderResponse.getShippers().get(0);
                matchOrderDAO.updateLogisticInfo(matchOrderId,shipper.getShipperCode(),logisticCode,remark,picList);
            }
            else{
                throw new LYSLException("物流单号查询失败",LYSLResultCodeEnum.DATA_INVALID);
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


    /**
     * 根据物资相关信息生成hash值
     * @param matchOrder
     * @return
     */
    @Override
    public String createHashByMatchOrder(MatchOrder matchOrder) {
        InstAndMaterialInfo instAndMaterialInfo = new InstAndMaterialInfo(matchOrder.getId(),matchOrder.getMaterialNameList(),matchOrder.getMaterialQuantityList());

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
        instAndMaterialInfo.setRecipient(user.getPhone());

        String hashStr = String.valueOf(instAndMaterialInfo.hashCode());
        System.out.println(hashStr);
        cacheService.addByKey("SUPPLYLOGISTICINFO",hashStr,instAndMaterialInfo,0);
        return hashStr;
    }

    @Override
    public InstAndMaterialInfo getInstAndMaterialInfoByHash(String hashStr) {
        InstAndMaterialInfo instAndMaterialInfo = (InstAndMaterialInfo) cacheService.selectByKey("SUPPLYLOGISTICINFO",hashStr,InstAndMaterialInfo.class);
        if (instAndMaterialInfo == null){
            throw new LYSLException("未查询到相关信息，请确认hash是否有误",LYSLResultCodeEnum.DATA_INVALID);
        }
        return instAndMaterialInfo;
    }


}
