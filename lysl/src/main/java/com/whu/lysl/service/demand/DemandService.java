package com.whu.lysl.service.demand;

import com.whu.lysl.entity.vo.DemandVO;
import com.whu.lysl.entity.condition.DemandCondition;
import com.whu.lysl.entity.dbobj.DemandDO;
import com.whu.lysl.entity.dto.Demand;
import com.whu.lysl.entity.vo.DemandVO;

import java.util.List;

public interface DemandService {

    /**
     * 查询尚未审核的所有需求
     * @return 需求 list
     */
    List<DemandVO> getUnreviewedDemands();

    /**
     * 查询尚未审核的指定机构所有需求
     * @param jsonString 查询条件
     * @return 需求 list
     */
    List<DemandVO> getUnreviewedDemandsById(String jsonString);

    /**
     * 向插入一条需求数据库
     * @param jsonString 查询条件
     */
    void insertDemand(String jsonString);

    /**
     * 修改一条需求的审核状态
     * @param jsonString 查询条件
     */
    void modifyStatus(String jsonString);

    /**
     * 查询获取 demand list
     * @param demandCondition 查询条件
     * @return demand list
     */
    List<DemandDO> getDemandsByCondition(DemandCondition demandCondition);
}