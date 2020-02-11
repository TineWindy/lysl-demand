package com.whu.lysl.service.match;

import com.whu.lysl.base.exceptions.LYSLException;
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
     * @param donorName 捐赠者名字
     * @throws LYSLException
     */
    List<MatchOrder> getMatchOrderByDonorName(String donorName) throws LYSLException;

    /**
     * 根据受赠者Id对匹配单进行查询
     * @param doneeName 受赠者名字
     * @return
     * @throws LYSLException
     */
    List<MatchOrder> getMatchOrderByDoneeName(String doneeName) throws LYSLException;

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
     * @param trackingNumber
     * @throws LYSLException
     */
    void updateTrackingNumber(int matchOrderId,String trackingNumber) throws LYSLException;
}
