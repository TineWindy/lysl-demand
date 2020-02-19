package com.whu.lysl.base.converters;

import com.whu.lysl.entity.condition.InstCondition;
import com.whu.lysl.entity.dbobj.DemandDO;
import com.whu.lysl.entity.dbobj.InstitutionDO;
import com.whu.lysl.entity.dbobj.UserDO;
import com.whu.lysl.entity.dto.Demand;
import com.whu.lysl.entity.vo.DemandVO;
import com.whu.lysl.service.institution.InstitutionService;
import com.whu.lysl.service.user.UserService;

import java.util.*;

public class DemandConverter {

    /**
     * model 2 do
     * @param demand model
     * @return do
     */
    public static DemandDO model2Do(Demand demand) {
        if (demand == null) {
            return null;
        }

        DemandDO demandDO = new DemandDO();
        demandDO.setId(demand.getId());
        demandDO.setGmtCreated(demand.getGmtCreated());
        demandDO.setGmtModified(demand.getGmtModified());
        demandDO.setInstitutionId(demand.getInstitutionId());
        demandDO.setDoneeId(demand.getDoneeId());
        demandDO.setDemandId(demand.getDemandId());
        demandDO.setMaterialId(demand.getMaterialId());
        demandDO.setMaterialName(demand.getMaterialName());
        demandDO.setMaterialNum(demand.getMaterialNum());
        demandDO.setDescription(demand.getDescription());
        demandDO.setStatus(demand.getStatus());

        return demandDO;
    }

    public static List<DemandVO> installVO(List<DemandDO> demandDOList,
                                           InstitutionService institutionService, UserService userService){
        Map<String, DemandVO> demandVOMap = new HashMap<>();
        for(DemandDO demandDO : demandDOList) {
            String demandID = demandDO.getDemandId();
            if(demandVOMap.keySet().contains(demandID))
                demandVOMap.get(demandID).addMaterial(demandDO);
            else{
                InstitutionDO institutionDO = InstConverter.model2Do(institutionService.getInstsByCondition(
                        new InstCondition.Builder().id(demandDO.getInstitutionId()).build()).get(0));
                UserDO userDO = UserConverter.model2DO(userService.getUserById(demandDO.getDoneeId()));
                DemandVO demandVO = new DemandVO(institutionDO, userDO, demandDO);
                demandVOMap.put(demandID, demandVO);
            }
        }
        List<DemandVO> demandVOList = new ArrayList<>(demandVOMap.values());
        for(DemandVO demandVO : demandVOList)
            demandVO.encapsulation();
        return demandVOList;
    }

}
