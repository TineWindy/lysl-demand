package com.whu.lysl.service.demand;

import com.whu.lysl.entity.dbobj.DemandDO;
import com.whu.lysl.entity.dto.Demand;

import java.util.List;

public interface DemandService {

    /**
     * 查询尚未审核的所有需求
     * @return 需求 list
     */
    List<Demand> getUnreviewedDemands();

    /**
     * 向插入一条需求数据库
     * @param demand 查询条件
     */
    void insertDemand(Demand demand);

    /**
     * 修改一条需求的审核状态
     * @param demandId 此条需求的id
     * @param status 要修改的状态
     */
    void modifyStatus(String demandId, String status);
}