package com.whu.lysl.service.match.impl;

import com.alibaba.fastjson.JSON;
import com.whu.lysl.base.converters.MatchOrderConverter;
import com.whu.lysl.base.enums.DonationTypeEnum;
import com.whu.lysl.base.enums.LYSLResultCodeEnum;
import com.whu.lysl.base.enums.MatchingMethodEnum;
import com.whu.lysl.base.enums.MatchingStatusEnum;
import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.base.utils.KdniaoTrackQueryAPI;
import com.whu.lysl.dao.MatchOrderDAO;
import com.whu.lysl.entity.condition.DonationOrderCondition;
import com.whu.lysl.entity.condition.MatchOrderCondition;
import com.whu.lysl.entity.dbobj.MatchOrderDo;
import com.whu.lysl.entity.dto.DonationOrder;
import com.whu.lysl.entity.dto.ExpressInfo;
import com.whu.lysl.entity.dto.MatchOrder;
import com.whu.lysl.service.donation.DonationOrderService;
import com.whu.lysl.service.institution.InstitutionService;
import com.whu.lysl.service.match.OrderMatchService;
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
     * 更新物流单号
     * @param matchOrderId
     * @param shipperCode
     * @param logisticCode
     * @throws LYSLException
     */
    @Override
    public void updateTrackingNumber(int matchOrderId,String shipperCode,String logisticCode) throws LYSLException {
        // TODO : 更改完所有记录
        matchOrderDAO.updateLogisticInfo(matchOrderId,shipperCode,logisticCode);
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
            matchOrderList.add(matchOrder);
        }


        return matchOrderList;
    }

    @Override
    public ExpressInfo getTracesFromTrackingNumber(String ShipperCode,String trackingNumber) {
        KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
        String result = "";
        try {
            result = api.getOrderTracesByJson(ShipperCode, trackingNumber);
            ExpressInfo expressInfo = JSON.parseObject(result,ExpressInfo.class);
            return expressInfo;
        } catch (Exception e) {
            throw new LYSLException("查询物流单号接口调用失败",LYSLResultCodeEnum.SYSTEM_ERROR);
        }

    }


}
