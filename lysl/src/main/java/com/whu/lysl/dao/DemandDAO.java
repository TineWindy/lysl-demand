package com.whu.lysl.dao;

import com.whu.lysl.entity.condition.DemandCondition;
import com.whu.lysl.entity.dbobj.DemandDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 需求 dao
 * @author Powen
 */
@Mapper
public interface DemandDAO {

    List<DemandDO> showUnreviewedDemands();

    List<DemandDO> showUnreviewedDemandsById(int institutionId);

    void insertDemand(DemandDO demandDO);

    void modifyStatus(@Param("demandId") String demandId, @Param("status") String status);

    List<DemandDO> selectByCondition(DemandCondition demandCondition);

}