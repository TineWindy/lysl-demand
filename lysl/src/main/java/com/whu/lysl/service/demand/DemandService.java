package com.whu.lysl.service.demand;

import com.whu.lysl.base.enums.OrderStatusEnum;
import com.whu.lysl.entity.dto.Institution;
import com.whu.lysl.entity.dto.User;
import com.whu.lysl.entity.vo.DemandVO;
import com.whu.lysl.entity.condition.DemandCondition;
import com.whu.lysl.entity.dbobj.DemandDO;
import com.whu.lysl.entity.dto.Demand;
import com.whu.lysl.entity.vo.DemandVO;

import java.util.List;
import java.util.Map;

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
     * 插入一条需求单
     * @param institution 机构
     * @param user 用户
     * @param materials 物资
     * @param description 描述
     */
    int insertDemand(Institution institution, User user, List<Map<String, String>> materials, String description);

    /**
     * 修改一个需求单的 status
     * @param demandId demand id
     */
    void modifyDemandOrderStatus(String demandId, OrderStatusEnum orderStatusEnum);

    /**
     * 查询获取 demand list
     * @param demandCondition 查询条件
     * @return demand list
     */
    List<DemandDO> getDemandsByCondition(DemandCondition demandCondition);
}