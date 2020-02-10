package com.whu.lysl.service.demand.impl;

import com.whu.lysl.base.converters.DemandConverter;
import com.whu.lysl.dao.DemandDAO;
import com.whu.lysl.entity.dto.Demand;
import com.whu.lysl.service.demand.DemandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 机构服务实现
 * @author Visionary
 * @since 2020/2/8 9:54 PM
 */
@Service
public class DemandServiceImpl implements DemandService {

    @Resource
    private DemandDAO demandDAO;

    @Override
    public List<Demand> getUnreviewedDemands() {
        return DemandConverter.batchDo2Model(demandDAO.showUnreviewedDemands());
    }

    @Override
    public void insertDemand(Demand demand){
        demandDAO.insertDemand(DemandConverter.model2Do(demand));
    }

    @Override
    public void modifyStatus(String demandId, String status){
        demandDAO.modifyStatus(demandId, status);
    }
}
