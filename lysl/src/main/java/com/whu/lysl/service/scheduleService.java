package com.whu.lysl.service;


import com.whu.lysl.base.enums.MatchingStatusEnum;
import com.whu.lysl.dao.MatchOrderDAO;
import com.whu.lysl.entity.dto.ExpressInfo;
import com.whu.lysl.entity.dto.LogisticInfo;
import com.whu.lysl.entity.dto.MatchOrder;
import com.whu.lysl.service.match.OrderMatchService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时任务
 *
 * @author Jzh
 * @since 2020-02-12 22:45
 **/
@Component
public class scheduleService {

    @Resource
    MatchOrderDAO matchOrderDAO;
    @Resource
    OrderMatchService orderMatchService;

    /**
     * 定时查询物流信息，并存入缓存中
     */
    @Scheduled(cron = "0 0 8,18 * * ?")
    public void queryExpressInfo(){
        // 查询需要获取物流信息的相关记录
        String[] statuses = new String[]{MatchingStatusEnum.IN_TRANSIT.getCode()};
        List<LogisticInfo> logisticInfoList = matchOrderDAO.selectLogisticInfoByStatus(statuses);

        for(int i=0;i<logisticInfoList.size();i++){
            LogisticInfo logisticInfo = new LogisticInfo();
            ExpressInfo expressInfo = orderMatchService.getTracingFromExpressInfo(logisticInfo.getShipperCode(),logisticInfo.getLogisticCode());
            // 物件已签收
            if(expressInfo.getState() == 3){
                orderMatchService.updateMatchOrderStatus(logisticInfo.getId(),MatchingStatusEnum.DELIVERED.getCode());
            }
            // 存入缓存中，等待查询
        }
    }

}
