package com.whu.lysl.service;

import com.whu.lysl.base.exceptions.LYSLException;
import com.whu.lysl.entity.dto.MatchOrder;

public interface OrderMatchService {

    /**
     * 对匹配结果进行保存，包含定向捐赠和志愿者人工捐赠
     * @param matchOrder
     * @throws LYSLException
     */
    void saveMatchOrder(MatchOrder matchOrder) throws LYSLException;

}
