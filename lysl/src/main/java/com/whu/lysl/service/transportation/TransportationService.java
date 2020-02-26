package com.whu.lysl.service.transportation;

import com.whu.lysl.entity.condition.TransportationCondition;
import com.whu.lysl.entity.dto.DonationOrder;
import com.whu.lysl.entity.dto.Transportation;

import java.util.List;

/**
 * @Author Caspar
 * @CreateTime 2020/2/26 14:29
 * @Description:
 */
public interface TransportationService{

    /**
     * 查询符合条件的运输信息
     * @param transportationCondition 查询条件
     * @return 运输信息 list
     */
    List<Transportation> getTransportationByCondition(TransportationCondition transportationCondition);

    /**
     * h5展示，查询审核通过的运输信息
     * @return 运输信息 list
     */
    List<Transportation> getTransportationApproved();

    /**
     * 名称模糊查询
     * @param name name
     * @return 运输信息 list
     */
    List<Transportation> getTransportationByPartitionOfName(String name);

    /**
     * 新增一条机构数据
     * @param transportation 运输信息
     */
    int insertTransportation(Transportation transportation);

    int updateTransportation(Transportation transportation);

    int deleteTransportation(Integer id);

    int checkTranspirtatinById(Integer id, String status);




}
