package com.whu.lysl.base.converters;

import com.whu.lysl.base.utils.DateUtils;
import com.whu.lysl.entity.dbobj.DemandDO;
import com.whu.lysl.entity.dto.Demand;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DemandConverter {
    /**
     * do 2 model
     * @param demandDO do
     * @return model
     */
    public static Demand do2Model(DemandDO demandDO) {
        if (demandDO == null) {
            return null;
        }

        Demand demand = new Demand();
        demand.setId(demandDO.getId());
        demand.setMaterial(demandDO.getMaterial());
        demand.setMaterialNum(demandDO.getMaterialNum());
        demand.setGmtCreated(demandDO.getGmtCreated());
        demand.setDemandId(demandDO.getDemandId());
        demand.setInstitutionId(demandDO.getInstitutionId());
        demand.setDoneeId(demandDO.getDoneeId());
        demand.setAddress(demandDO.getAddress());
        demand.setStatus(demandDO.getStatus());
        demand.setDescription(demandDO.getDescription());
        return demand;
    }

    public static DemandDO model2Do(Demand demand) {
        if (demand == null) {
            return null;
        }

        DemandDO demandDO = new DemandDO();
        demandDO.setId(demand.getId());
        demandDO.setMaterial(demand.getMaterial());
        demandDO.setMaterialNum(demand.getMaterialNum());
        demandDO.setGmtCreated(demand.getGmtCreated());
        demandDO.setDemandId(demand.getDemandId());
        demandDO.setInstitutionId(demand.getInstitutionId());
        demandDO.setDoneeId(demand.getDoneeId());
        demandDO.setAddress(demand.getAddress());
        demandDO.setStatus(demand.getStatus());
        demandDO.setDescription(demand.getDescription());
        return demandDO;
    }

    public static List<Demand> request2Models(HttpServletRequest request){
        if (request == null) {
            return null;
        }
        List<Demand> demandList = new ArrayList<>();
        String[] materials = request.getParameter("material").split("and");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(String material : materials){
            Demand demand = new Demand();
            String[] materialMessage = material.split("in");
            demand.setMaterial(materialMessage[0]);
            demand.setMaterialNum(Integer.valueOf(materialMessage[1]));
            demand.setGmtCreated(df.format(new Date()));
            demand.setDemandId(request.getParameter("demandId"));
            demand.setInstitutionId(Integer.valueOf(request.getParameter("institutionId")));
            demand.setDoneeId(Integer.valueOf(request.getParameter("doneeId")));
            demand.setAddress(request.getParameter("address"));
            demand.setStatus(request.getParameter("status"));
            demand.setDescription(request.getParameter("description"));
            demandList.add(demand);
        }
        return demandList;
    }

    /**
     * batch do 2 model
     * @param demandDOS do list
     * @return model list
     */
    public static List<Demand> batchDo2Model(List<DemandDO> demandDOS) {
        List<Demand> demands = new ArrayList<>();

        if(demandDOS == null) {
            return demands;
        }

        for (DemandDO demandDO: demandDOS) {
            demands.add(do2Model(demandDO));
        }

        return demands;
    }
}
