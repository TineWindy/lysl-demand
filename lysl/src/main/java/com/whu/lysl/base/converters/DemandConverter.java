package com.whu.lysl.base.converters;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.whu.lysl.base.enums.OrderStatusEnum;
import com.whu.lysl.entity.condition.InstCondition;
import com.whu.lysl.entity.dbobj.DemandDO;
import com.whu.lysl.entity.dbobj.InstitutionDO;
import com.whu.lysl.entity.dbobj.UserDO;
import com.whu.lysl.entity.vo.DemandVO;
import com.whu.lysl.service.institution.InstitutionService;
import com.whu.lysl.service.user.UserService;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

public class DemandConverter {

    public static List<DemandDO> json2DO(int institutionId, int userId, String description, JSONArray materials){

        List<DemandDO> demandDOList = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String demandId = generateRandom();
        for(Object object : materials){
            JSONObject material = (JSONObject) object;
            DemandDO demandDO = new DemandDO();
            demandDO.setGmtCreated(df.format(new Date()));
            demandDO.setGmtModified(df.format(new Date()));
            demandDO.setDemandId(demandId);
            demandDO.setInstitutionId(institutionId);
            demandDO.setDoneeId(userId);
            demandDO.setMaterialName(material.getString("materialName"));
            demandDO.setMaterialNum((int) material.get("materialNum"));
            demandDO.setMaterialId((int) material.get("materialId"));
            demandDO.setStatus(OrderStatusEnum.UNCHECKED.getCode());
            demandDO.setDescription(description);
            demandDOList.add(demandDO);
        }
        return demandDOList;
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

    private static String generateRandom(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[8];
        random.nextBytes(salt);
        StringBuilder sb = new StringBuilder();
        for (byte b : salt) {
            sb.append(Math.abs(Byte.valueOf(b).intValue()) % 10);
        }
        return sb.toString();
    }
}
