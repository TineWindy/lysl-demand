package com.whu.lysl.service;

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
     * @param honorName 捐赠者名字
     * @throws LYSLException
     */
    List<MatchOrder> getMatchOrderByDonorName(String honorName) throws LYSLException;

}