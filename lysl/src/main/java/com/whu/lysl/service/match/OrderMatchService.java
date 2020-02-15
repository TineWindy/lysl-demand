package com.whu.lysl.service.match;

import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.entity.condition.MatchOrderCondition;
import com.whu.lysl.entity.dto.ExpressInfo;
import com.whu.lysl.entity.dto.MatchOrder;

import java.util.List;

public interface OrderMatchService {

    /**
     * 对匹配结果进行保存，包含定向捐赠和志愿者人工捐赠
     * @param matchOrder
     * @throws LYSLException
     */
    void saveMatchOrder(MatchOrder matchOrder) throws LYSLException;

    /**
     * 根据捐赠者Id对匹配单进行查询
     * @param donorId 捐赠者Id
     * @throws LYSLException
     */
    List<MatchOrder> getMatchOrderByDonorId(int donorId) throws LYSLException;

    /**
     * 根据受赠者Id对匹配单进行查询
     * @param doneeId 受赠者Id
     * @return
     * @throws LYSLException
     */
    List<MatchOrder> getMatchOrderByDoneeId(int doneeId) throws LYSLException;

    /**
     * 更新匹配单的状态
     * @param matchOrderId
     * @param status
     * @throws LYSLException
     */
    void updateMatchOrderStatus(int matchOrderId,String status) throws LYSLException;


    /**
     * 更新物流单号
     * @param matchOrderId
     * @param logisticCode
     * @param shipperCode
     * @throws LYSLException
     */
    void updateTrackingNumber(int matchOrderId,String shipperCode,String logisticCode) throws LYSLException;

    /**
     * 根据状态，捐赠人Id，受赠人Id等查询匹配单
     * @param matchOrderCondition
     * @return
     */
    List<MatchOrder> getMatchOrderList(MatchOrderCondition matchOrderCondition);

    /**
     * 根据物流单号，获取物流单状态
     */
    ExpressInfo getTracingFromExpressInfo(String ShipperCode, String trackingNumber);

}
